package com.example.remindme.controller.web;

import javax.servlet.http.HttpSession;

import com.example.remindme.classes.persistence.Event;
import com.example.remindme.controller.web.service.EventService;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

	private final EventService eventService;
	
    public AdminController(EventService eventService) {
        this.eventService = eventService;
    }

	@GetMapping(value="/admin")
	public String home(Model model, Authentication authentication, HttpSession session) {

		session.setAttribute("userName", authentication.getName());

		model.addAttribute("events", eventService.events());
		model.addAttribute("evento", new Event());
		return "admin";
	}

}