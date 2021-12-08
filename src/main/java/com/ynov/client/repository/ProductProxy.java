package com.ynov.client.repository;

import java.nio.charset.Charset;
import java.util.List;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.ynov.client.ApiProperties;
import com.ynov.client.model.Product;

@Component
public class ProductProxy {

	@Autowired
	private ApiProperties props;
	
	// Création d'un header pour la méthode Basic Auth 
	@SuppressWarnings("unused")
	private HttpHeaders createBasicAuthHeaders(String username, String password){
		return new HttpHeaders() {
			private static final long serialVersionUID = 1L;
			{
				String auth = username + ":" + password;
		        byte[] encodedAuth = Base64.encodeBase64( 
		            auth.getBytes(Charset.forName("US-ASCII")) );
		        String authHeader = "Basic " + new String( encodedAuth );
		        set( "Authorization", authHeader );
		    }
		};
	}
	
	// Création d'un header pour la méthode Bearer Token
	private HttpHeaders createTokenHeaders() {
		return new HttpHeaders() {
			private static final long serialVersionUID = 1L;
			{
				// utilisation du token qui est dans la classe ApiProperties
				String authHeader = "Bearer " + props.getToken();
				set("Authorization", authHeader);
			}
		};
	}
	
	public List<Product> getProducts() {
		
		RestTemplate restTemplate = new RestTemplate();
		
		ResponseEntity<List<Product>> response =
				restTemplate.exchange(
						props.getUrl() + "/product", 
						HttpMethod.GET, 
						new HttpEntity<>(createTokenHeaders()), 
						new ParameterizedTypeReference<List<Product>>() {}
					);
		return response.getBody();
	}

	public Product getProductById(Integer id) {
		RestTemplate restTemplate = new RestTemplate();
		
		ResponseEntity<Product> response =
				restTemplate.exchange(
						props.getUrl() + "/product/" + id, 
						HttpMethod.GET, 
						new HttpEntity<>(createTokenHeaders()), 
						Product.class);
		return response.getBody();
	}
	
	public void save(Product product) {
		RestTemplate restTemplate = new RestTemplate();
		
		HttpEntity<Product> request = new HttpEntity<Product>(product, createTokenHeaders());
		
		restTemplate.exchange(
				props.getUrl() + "/product",
				HttpMethod.POST,
				request,
				Product.class				
				);
	}	
	
}
