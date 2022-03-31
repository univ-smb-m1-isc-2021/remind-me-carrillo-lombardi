package com.example.remindme.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProfilController {

    @GetMapping(value = "/profil")
    public String profil() {
        return "profil";
    }

}
