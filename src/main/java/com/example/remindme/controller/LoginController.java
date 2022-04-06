package com.example.remindme.controller;


import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @GetMapping(value = "")
    public String index() {
        return "redirect:/login";
    }

    @GetMapping(value = "/login")
    public String login(HttpSession session, @RequestParam(required = false) String lang) {
        
        SecurityContextHolder.clearContext();
        session.removeAttribute("userId");

        if(lang != null && !lang.equals("")) {
			session.setAttribute("lang", lang);
		} else {
            lang = ((String)session.getAttribute("lang"));
            if(lang != null && !lang.equals("")) {
                return "redirect:/login?lang="+lang;
            }
        }
        return "login";
    }

}
