package com.example.remindme.controller;

import javax.servlet.http.HttpSession;

import com.example.remindme.classes.persistence.Event;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

	@GetMapping(value="/home")
	public String home(Model model, HttpSession session, @RequestParam(required = false) String lang) {
		
		if(lang != null && !lang.equals("")) {
			session.setAttribute("lang", lang);
		} else {
            lang = ((String)session.getAttribute("lang"));
            if(lang != null && !lang.equals("")) {
                return "redirect:/home?lang="+lang;
            }
        }

		model.addAttribute("event", new Event());
		return "home";
	}

}