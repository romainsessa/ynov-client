package com.ynov.client.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.ynov.client.TokenContext;
import com.ynov.client.model.InternalUser;
import com.ynov.client.service.LoginService;

@Controller
public class PublicController {

	@Autowired
	private LoginService loginService;
	
	@Autowired
	private TokenContext tokenContext;
	
	@GetMapping("/login")
	public String login(HttpSession session) {
		// Creation d'un user en dur qui existe dans la base de données côté API
		// A remplacer par un formulaire
		InternalUser user = new InternalUser();
		user.setUsername("user");
		user.setPassword("user");
		
		String token = loginService.login(user);
		session.setAttribute("loggeduser", user.getUsername());
		session.setAttribute("token", token); // method 1 : token persisted in session
		tokenContext.setToken(token); // method 2 (best) : token persisted in spring context
		return "logged";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		tokenContext.setToken(null);
		session.invalidate();
		return "logout";
	}
	
	
}
