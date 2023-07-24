package com.pawsomepets.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pawsomepets.dto.Login;
import com.pawsomepets.dto.User;

@Service
public class UserService {
	
	@Autowired
	RestTemplate restTemplate;
	private final ObjectMapper objectMapper = new ObjectMapper();
	
	public User signIn(Login loginInfo) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		
		String jsonRequest = null;
		try {
			jsonRequest = objectMapper.writeValueAsString(loginInfo);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			return null;
		}
		
		HttpEntity<String> requestEntity = new HttpEntity<>(jsonRequest,headers);
		
		String url = "http://localhost:8084/login";
		
		  ResponseEntity<User> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, User.class);
		  
		  User user = restTemplate.postForObject(url, requestEntity, User.class);
		    
		return user;
	}

}
