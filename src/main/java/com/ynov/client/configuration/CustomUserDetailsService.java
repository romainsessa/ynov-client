package com.ynov.client.configuration;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ynov.client.model.ApiUser;
import com.ynov.client.service.LoginService;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private LoginService loginService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// IF TOKEN AUTH IS ENABLED ON API
		// 1) Authenticate client app with API
		/*ApiUser apiUser = new ApiUser();
		apiUser.setUsername("user");
		apiUser.setPassword("user");
		loginService.login(apiUser);*/
		
		// COULD BE CHANGE BY LOADING A USER FROM DB
		// 2) Authenticate web user with client creating an inMemory user
		User webUser = new User(
				username, 
				new BCryptPasswordEncoder().encode("romain"),
				getGrantedAuthorities());
		return webUser;
	}
	
	private List<GrantedAuthority> getGrantedAuthorities() {
		List<GrantedAuthority> authorities = 
				new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
		return authorities;
	}

}
