package com.example.remindme.controller;

import javax.servlet.http.HttpSession;

import com.example.remindme.classes.persistence.Event;
import com.example.remindme.service.EventService;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AdminController {

	private final EventService eventService;
	
    public AdminController(EventService eventService) {
        this.eventService = eventService;
    }

	@GetMapping(value="/admin")
	public String home(Model model, Authentication authentication, HttpSession session, @RequestParam(required = false) String lang) {

		session.setAttribute("userName", authentication.getName());

		if(lang != null && !lang.equals("")) {
			session.setAttribute("lang", lang);
		} else {
            lang = ((String)session.getAttribute("lang"));
            if(lang != null && !lang.equals("")) {
                return "redirect:/admin?lang="+lang;
            }
        }

		model.addAttribute("events", eventService.events());
		model.addAttribute("evento", new Event());
		return "admin";
	}

}