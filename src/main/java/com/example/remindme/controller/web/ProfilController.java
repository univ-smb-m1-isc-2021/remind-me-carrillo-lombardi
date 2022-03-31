package com.example.remindme.controller.web;
import java.io.ByteArrayInputStream;

import com.example.remindme.controller.web.service.EventService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import com.example.remindme.controller.web.service.UserEntityService;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;

@Controller
public class ProfilController {


    private final EventService eventService;
	
    public ProfilController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping(value = "/profil")
    private final UserEntityService userEntityService;
	
    public ProfilController(UserEntityService userEntityService) {
        this.userEntityService = userEntityService;
    }

    @GetMapping(value = "/admin/profil")
    public String profil() {
        return "profil";
    }

    @PostMapping(value = "/profil/export")
	public ResponseEntity<InputStreamResource> jsonExport(Model model) throws JsonProcessingException {
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
    //https://docs.spring.io/spring-session/reference/guides/boot-redis.html
    //https://docs.spring.io/spring-session/reference/
    //https://stackoverflow.com/questions/13389444/creating-a-java-session

    //checker si application.properties nécessaire
    // @GetMapping(value = "/admin/profil/delete")
    // @ResponseStatus(HttpStatus.NO_CONTENT)
    // public String deleteProfil(HttpSession session) {
    //     session.getAttribute("user")
    //     this.userEntityService.delete();
    // }
}
