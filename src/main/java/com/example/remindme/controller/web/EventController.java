package com.example.remindme.controller.web;

import com.example.remindme.classes.persistence.Event;
import com.example.remindme.controller.web.service.EventService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class EventController {

    private final EventService eventService;
	

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }


    @PostMapping(value="/event/create")
	public String createEvent(@ModelAttribute Event event, Model model) {
        //TODO faire en meme temps les notifs
		eventService.create(event.getTitle(), event.getDetails());
        return "redirect:/admin";
	}
}
