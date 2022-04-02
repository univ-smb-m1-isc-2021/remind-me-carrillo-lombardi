package com.example.remindme.controller.web;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

import com.example.remindme.classes.FormWrapper;
import com.example.remindme.classes.persistence.Event;

import com.example.remindme.classes.persistence.UserEntity;

import com.example.remindme.controller.web.service.EventService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import com.example.remindme.controller.web.service.UserEntityService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;

@Controller
public class ProfilController {


    private final EventService eventService;
    private final UserEntityService userEntityService;
	
    public ProfilController(EventService eventService,UserEntityService userEntityService) {
        this.eventService = eventService;
        this.userEntityService = userEntityService;
        
    }

    @GetMapping(value = "/admin/profil")
    public String profil() {
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
    public ResponseEntity<?> multiUploadFileModel(@ModelAttribute FormWrapper model) throws IOException {
        try {
            // Save as you want as per requiremens
            saveUploadedFile(model.getImage());
            //formRepo.save(mode.getTitle(), model.getDescription());
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        System.out.println("IM INNNNNNNNNNNNNNNNNNNNNNNNNNNN");
        System.out.println(new String(model.getImage().getBytes()));

        return new ResponseEntity("Successfully uploaded!", HttpStatus.OK);
    }

    private void saveUploadedFile(MultipartFile file) throws IOException {
        /*if (!file.isEmpty()) {
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
            Files.write(path, bytes);
        }*/
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
