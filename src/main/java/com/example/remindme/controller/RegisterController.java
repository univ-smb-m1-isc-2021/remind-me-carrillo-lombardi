package com.example.remindme.controller;


import javax.servlet.http.HttpSession;

import com.example.remindme.service.UserEntityService;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RegisterController {

    private final UserEntityService userEntityService;
	
    public RegisterController(UserEntityService userEntityService) {
        this.userEntityService = userEntityService;
    }

    @GetMapping(value = "/register")
    public String register(HttpSession session, @RequestParam(required = false) String lang) {
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

    @PostMapping(value="/register/create")
	public String createEvent(@RequestParam(name = "username") String username,
                        @RequestParam(name = "password") String password,
                        @RequestParam(name = "password2") String password2) {

        if(!password.equals(password2))
            return "redirect:/register";

        if(userEntityService.findByName(username) != null)
            return "redirect:/register";

        this.userEntityService.create(username, new BCryptPasswordEncoder().encode(password));

        return "redirect:/login";
	}
}
