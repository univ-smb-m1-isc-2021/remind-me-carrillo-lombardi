package com.example.remindme.controller;


import javax.servlet.http.HttpSession;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    //Redirection vers la page login
    @GetMapping(value = "")
    public String index() {
        return "redirect:/login";
    }

    //Redirection vers la page login
    @GetMapping(value = "/logout")
    public String logout(HttpSession session, @RequestParam(required = false) String lang) {
        //Déconnecte l'utilisateur et nettoye la session (pas le language)
        SecurityContextHolder.clearContext();
        session.removeAttribute("userId");

        //verification de la langue selectionné par l'utilisateur
        if(lang != null && !lang.equals("")) {
			session.setAttribute("lang", lang);
		} else {
            lang = ((String)session.getAttribute("lang"));
            if(lang != null && !lang.equals("")) {
                return "redirect:/login?lang="+lang;
            }
        }
        return "redirect:/login";
    }

    @GetMapping(value = "/login")
    public String login(HttpSession session, @RequestParam(required = false) String lang) {
        
        //Déconnecte l'utilisateur et nettoye la session (pas le language)
        SecurityContextHolder.clearContext();
        session.removeAttribute("userId");

        //verification de la langue selectionné par l'utilisateur
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
