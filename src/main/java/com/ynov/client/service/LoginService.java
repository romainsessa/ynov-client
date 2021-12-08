package com.ynov.client.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ynov.client.model.InternalUser;
import com.ynov.client.repository.LoginProxy;

@Service
public class LoginService {

	@Autowired
	private LoginProxy loginProxy;
	
	public void login(InternalUser user) {
		loginProxy.login(user);
	}
	
}