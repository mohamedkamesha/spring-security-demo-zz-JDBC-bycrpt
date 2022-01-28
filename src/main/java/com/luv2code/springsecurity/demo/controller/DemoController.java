package com.luv2code.springsecurity.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DemoController {

	
	@GetMapping("/")
	public String showHome () {
		
		return "home";
	}
	
	@GetMapping("/testPage")
	public String showTest () {
		
		return "test-page";
	}
	
	@GetMapping("/leaders")
	public String showLeaders () {
		return "leaders";
	}
	
	@GetMapping("/systems")
	public String showSystems() {
		
		return "systems";
	}
}
