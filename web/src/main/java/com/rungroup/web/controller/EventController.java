package com.rungroup.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.rungroup.web.models.Event;
import com.rungroup.web.service.EventService;

@Controller
public class EventController {
	
	private EventService eventService;
	
	@Autowired
	public EventController(EventService eventService) {
		this.eventService = eventService;
	}
	
	@GetMapping("/events/{clubId}/new")
	public String createClubForm(@PathVariable("clubId") Long clubId, Model model) {
		Event event = new Event();
		model.addAttribute("clubId", clubId);
		model.addAttribute("event", event);
		
		return "events-create";
	}
	
	// strting from here tomorrow!
}
