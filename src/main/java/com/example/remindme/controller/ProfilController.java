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

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ProfilController {


    private final EventService eventService;
    private final UserEntityService userEntityService;
	
    public ProfilController(EventService eventService,UserEntityService userEntityService) {
        this.eventService = eventService;
        this.userEntityService = userEntityService;
        
    }

    @GetMapping(value = "/admin/profil")
    public String profil(HttpSession session, @RequestParam(required = false) String lang) {

		if(lang != null && !lang.equals("")) {
			session.setAttribute("lang", lang);
		} else {
            lang = ((String)session.getAttribute("lang"));
            if(lang != null && !lang.equals("")) {
                return "redirect:/admin/profil?lang="+lang;
            }
        }

        return "profil";
    }

    @PostMapping(value = "/admin/profil/export")
	public ResponseEntity<InputStreamResource> jsonExport() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        byte[] buf = mapper.writeValueAsBytes(eventService.events());

        return ResponseEntity
            .ok()
            .header("Content-Disposition", "attachment; filename=\"notification_event.json\"")
            .contentLength(buf.length)
            .contentType(
                    MediaType.parseMediaType("application/octet-stream"))
            .body(new InputStreamResource(new ByteArrayInputStream(buf)));
	}


    // @RequestMapping(value = "/profil/import", method = RequestMethod.POST)
    // @ResponseBody
    // public String uploadFile(@RequestParam("file") MultipartFile file) {

    
    //     return "admin";
    // }
    @PostMapping("/admin/profil/import")
    public String multiUploadFileModel(@ModelAttribute FormWrapper model) throws IOException {

        //transform bytes to string 
        String s = new String(model.getImage().getBytes());
        //Transforme string to events
        ObjectMapper mapper = new ObjectMapper();
        List<Event> events = Arrays.asList(mapper.readValue(s, Event[].class));

        eventService.createAll(events);

        return "redirect:/admin/profil";
    }


    //https://docs.spring.io/spring-session/reference/guides/boot-redis.html
    //https://docs.spring.io/spring-session/reference/
    //https://stackoverflow.com/questions/13389444/creating-a-java-session

    //checker si application.properties nécessaire
    @GetMapping(value = "/admin/profil/delete")
    public String deleteProfil(HttpSession session) {
        UserEntity userEntity = userEntityService.findByName(session.getAttribute("userName").toString());
        this.userEntityService.delete(userEntity.getId());
        return "redirect:/login";
    }
}
