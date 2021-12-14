package com.ynov.client.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ynov.client.TokenContext;
import com.ynov.client.model.ApiUser;
import com.ynov.client.repository.LoginProxy;

@Service
public class LoginService {

	@Autowired
	private TokenContext tokenContext;
	
	@Autowired
	private LoginProxy loginProxy;
	
	public void login(ApiUser user) {
		String token = loginProxy.login(user);
		tokenContext.setToken(token);
	}
	
}
