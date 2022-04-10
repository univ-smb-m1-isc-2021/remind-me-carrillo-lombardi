package com.example.remindme.controller;

import javax.servlet.http.HttpSession;

import com.example.remindme.classes.persistence.Event;
import com.example.remindme.classes.persistence.UserEntity;
import com.example.remindme.service.EventService;
import com.example.remindme.service.UserEntityService;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AdminController {

	private final EventService eventService;
	private final UserEntityService userEntityService;
	
    public AdminController(EventService eventService,UserEntityService userEntityService) {
        this.eventService = eventService;
		this.userEntityService = userEntityService;
    }

	//Page admin
	@GetMapping(value="/admin")
	public String home(Model model, Authentication authentication, HttpSession session, @RequestParam(required = false) String lang) {

		//ajout du userId dans la session
		UserEntity userEntity = userEntityService.findByName(authentication.getName());
		session.setAttribute("userId", userEntity.getId());

		//Ajout des models pour la page html
		model.addAttribute("events", eventService.eventsOfUser(userEntity.getId()));
		model.addAttribute("evento", new Event());
		model.addAttribute("event", new Event());

		//verification de la langue selectionné par l'utilisateur
		if(lang != null && !lang.equals("")) {
			session.setAttribute("lang", lang);
		} else {
            lang = ((String)session.getAttribute("lang"));
            if(lang != null && !lang.equals("")) {
                return "redirect:/admin?lang="+lang;
            }
        }
		
		return "admin";
	}

}