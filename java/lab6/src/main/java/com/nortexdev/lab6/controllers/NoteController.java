package com.nortexdev.lab6.controllers;

import org.springframework.web.bind.annotation.GetMapping;

public class MainController {
	@GetMapping("/")
	public String index() {
		return "index";
	}
}
