package com.example.remindme.controller;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.example.remindme.classes.FormWrapper;
import com.example.remindme.classes.persistence.Event;
import com.example.remindme.classes.persistence.UserEntity;
import com.example.remindme.service.EventService;
import com.example.remindme.service.UserEntityService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.ui.Model;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ProfileController {

    private final EventService eventService;
    private final UserEntityService userEntityService;
	
    public ProfileController(EventService eventService,UserEntityService userEntityService) {
        this.eventService = eventService;
        this.userEntityService = userEntityService;
    }

    @GetMapping(value = "/admin/profile")
    public String profile(Model model, HttpSession session, @RequestParam(required = false) String lang) {

        //Récupère l'utilisateur a partir de son id (dans la session) pour l'ajouter en model
        UserEntity user = userEntityService.findById((Long)(session.getAttribute("userId")));
        model.addAttribute("user", user);

        //verification de la langue selectionné par l'utilisateur
		if(lang != null && !lang.equals("")) {
			session.setAttribute("lang", lang);
		} else {
            lang = ((String)session.getAttribute("lang"));
            if(lang != null && !lang.equals("")) {
                return "redirect:/admin/profile?lang="+lang;
            }
        }
        return "profile";
    }

    //Permet de modifier le profile de l'utilisateur
    @PostMapping(value="/admin/profile/update")
	public String createEvent( HttpSession session, @RequestParam(name = "tweeter") String tweeter, @RequestParam(name = "email") String email) {

        UserEntity user = userEntityService.findById((Long)(session.getAttribute("userId")));
        userEntityService.update(user.getId(), user.getName(), user.getPassword(), tweeter, email);

        return "redirect:/admin/profile";
	}

    //Permet d'exporter les évenement de l'utilisateur
    @PostMapping(value = "/admin/profile/export")
	public ResponseEntity<InputStreamResource> jsonExport(HttpSession session) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        byte[] buf = mapper.writeValueAsBytes(eventService.eventsOfUser((Long)(session.getAttribute("userId"))));

        return ResponseEntity
            .ok()
            .header("Content-Disposition", "attachment; filename=\"notification_event.json\"")
            .contentLength(buf.length)
            .contentType(
                    MediaType.parseMediaType("application/octet-stream"))
            .body(new InputStreamResource(new ByteArrayInputStream(buf)));
	}

    //Permet d'importer des évenements
    @PostMapping("/admin/profile/import")
    public String multiUploadFileModel(@ModelAttribute("model") FormWrapper model, HttpSession session) throws IOException {
        //transform bytes to string 
        String s = new String(model.getImage().getBytes());
        //Transforme string to events
        ObjectMapper mapper = new ObjectMapper();
        List<Event> events = Arrays.asList(mapper.readValue(s, Event[].class));

        eventService.createAll(events, (Long)(session.getAttribute("userId")));

        return "redirect:/admin/profile";
    }

    //Permet de supprimer un utilisateur
    @GetMapping(value = "/admin/profile/delete")
    public String deleteProfil(HttpSession session) {
        this.userEntityService.delete((Long)(session.getAttribute("userId")));
        return "redirect:/login";
    }
}
