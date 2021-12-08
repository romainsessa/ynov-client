package com.ynov.client.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.ynov.client.model.InternalUser;
import com.ynov.client.service.LoginService;

@Controller
public class PublicController {

	@Autowired
	private LoginService loginService;
	
	@GetMapping("/login")
	public String login() {
		// Creation d'un user en dur qui existe dans la base de données côté API
		// A remplacer par un formulaire
		InternalUser user = new InternalUser();
		user.setUsername("user");
		user.setPassword("user");
		
		loginService.login(user);
		return "logged";
	}
	
	
}
