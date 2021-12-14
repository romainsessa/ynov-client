package com.ynov.client.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import com.ynov.client.ApiProperties;
import com.ynov.client.model.ApiUser;

@Repository
public class LoginProxy {

	@Autowired
	private ApiProperties props;
	
	public String login(ApiUser user) {
		RestTemplate restTemplate = new RestTemplate();
		
		HttpEntity<ApiUser> request = 
				new HttpEntity<ApiUser>(user);
		
		ResponseEntity<String> response = restTemplate.exchange(
				props.getPublicurl() + "/login",
				HttpMethod.POST,
				request,
				String.class				
				);
		System.out.println("Body response : " + response.getBody());
		
		// Extraction du token du header de la réponse
		String token = response.getHeaders()
				.get(HttpHeaders.AUTHORIZATION).get(0);
		System.out.println("Received token is " + token);
			
		return token;
	}
	
}
