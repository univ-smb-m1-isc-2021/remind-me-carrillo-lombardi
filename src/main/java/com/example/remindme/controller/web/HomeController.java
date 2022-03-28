package com.example.remindme.controller.web;

import com.example.remindme.classes.persistence.Event;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

	@GetMapping(value="/home")
	public String home(Model model) {
		//model.addAttribute("name", name);
        //log.info("Home page returned - testing logstash integration");
		model.addAttribute("event", new Event());
		return "home";
	}

}