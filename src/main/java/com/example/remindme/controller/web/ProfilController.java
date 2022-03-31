package com.example.remindme.controller.web;

import java.io.ByteArrayInputStream;

import com.example.remindme.controller.web.service.EventService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ProfilController {

    private final EventService eventService;
	
    public ProfilController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping(value = "/profil")
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

}
