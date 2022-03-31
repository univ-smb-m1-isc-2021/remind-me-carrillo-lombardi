package com.example.remindme.controller.web;

import com.example.remindme.controller.web.service.UserEntityService;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;

@Controller
public class ProfilController {

    private final UserEntityService userEntityService;
	
    public ProfilController(UserEntityService userEntityService) {
        this.userEntityService = userEntityService;
    }

    @GetMapping(value = "/admin/profil")
    public String profil() {
        return "profil";
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
