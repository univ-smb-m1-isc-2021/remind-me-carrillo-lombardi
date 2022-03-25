package com.example.remindme.controller.web;

import com.example.remindme.controller.web.service.EventService;

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
	public String home(Model model) {
		model.addAttribute("events", eventService.events());
		return "admin";
	}

}