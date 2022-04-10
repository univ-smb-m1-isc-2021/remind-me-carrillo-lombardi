package com.example.remindme.controller;


import javax.servlet.http.HttpSession;

import com.example.remindme.service.UserEntityService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RegisterController {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserEntityService userEntityService;

    //Page d'inscription
    @GetMapping(value = "/register")
    public String register(HttpSession session, @RequestParam(required = false) String lang) {
        //verification de la langue selectionné par l'utilisateur
        if(lang != null && !lang.equals("")) {
			session.setAttribute("lang", lang);
		} else {
            lang = ((String)session.getAttribute("lang"));
            if(lang != null && !lang.equals("")) {
                return "redirect:/register?lang="+lang;
            }
        }
        return "register";
    }

    //Créer un utilisateur
    @PostMapping(value="/register/create")
	public String createEvent(@RequestParam(name = "username") String username,
                        @RequestParam(name = "password") String password,
                        @RequestParam(name = "password2") String password2,
                        @RequestParam(name = "tweeter") String tweeter,
                        @RequestParam(name = "email") String email) {

        if(!password.equals(password2))
            return "redirect:/register";

        if(userEntityService.findByName(username) != null)
            return "redirect:/register";

        userEntityService.create(username, password, tweeter, email);

        return "redirect:/login";
	}
}
