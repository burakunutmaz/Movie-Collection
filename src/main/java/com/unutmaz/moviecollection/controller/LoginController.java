package com.unutmaz.moviecollection.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.unutmaz.moviecollection.model.User;
import com.unutmaz.moviecollection.service.AppService;

@Controller
public class LoginController {

	@Autowired
	private AppService appService;
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@GetMapping("/register")
	public String register(Model model) {
		model.addAttribute("user", new User());
		return "register";
	}
	
	@PostMapping("/register")
	public String registerConfirm(@ModelAttribute User user, Model model) {
		if (user.getUsername().length() <= 0 || user.getPassword().length() <= 0) {
			return "redirect:/register";
		}
		else {
			user.setEnabled(true);
			appService.registerUser(user);
			return "login";
		}
		
	}
}
