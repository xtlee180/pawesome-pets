package com.pawsomepets.service;

import java.util.List;

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
import com.pawsomepets.dto.UserList;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	RestTemplate restTemplate;
	private final ObjectMapper objectMapper = new ObjectMapper();
	
	@Override
	public User signIn(Login loginInfo) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		
		String jsonRequest = null;
		try {
			jsonRequest = objectMapper.writeValueAsString(loginInfo);
		} catch (JsonProcessingException e) {
			return null;
		}
		
		HttpEntity<String> requestEntity = new HttpEntity<>(jsonRequest,headers);
		
		String url = "http://localhost:8084/login";
		
//		  ResponseEntity<User> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, User.class);
		  
		  User user = restTemplate.postForObject(url, requestEntity, User.class);
		    
		return user;
	}

	//so if returns null it didnt work and then basically redirect to the login page with a message above
	@Override
	public User registerUser(User user) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		
		String jsonRequest = null;
		try {
			jsonRequest = objectMapper.writeValueAsString(user);
		} catch (JsonProcessingException e) {
			return null;
		}
		
		HttpEntity<String> requestEntity = new HttpEntity<>(jsonRequest,headers);
		
		String url = "http://localhost:8084/users";
		
//		ResponseEntity<User> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, User.class);
		  
		User userAdded = restTemplate.postForObject(url, requestEntity, User.class);
		    
		return userAdded;
	}

	@Override
	public User editUserInfo(User user) {
		// put /users - data in request body
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		
		String jsonRequest = null;
		try {
			jsonRequest = objectMapper.writeValueAsString(user);
		} catch (JsonProcessingException e) {
			return null;
		}
		
		HttpEntity<String> requestEntity = new HttpEntity<>(jsonRequest,headers);
		
		String url = "http://localhost:8084/users";
		ResponseEntity<User> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, User.class);
	
		return responseEntity.getBody();
	}

	@Override
	public User editCredentials(User user) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		
		String jsonRequest = null;
		try {
			jsonRequest = objectMapper.writeValueAsString(user);
		} catch (JsonProcessingException e) {
			return null;
		}
		
		HttpEntity<String> requestEntity = new HttpEntity<>(jsonRequest,headers);
		
		String url = "http://localhost:8084/usersEditPN";
		ResponseEntity<User> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, User.class);
	
		return responseEntity.getBody();

	}

	@Override
	public User editUserType(User user) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		
		String jsonRequest = null;
		try {
			jsonRequest = objectMapper.writeValueAsString(user);
		} catch (JsonProcessingException e) {
			return null;
		}
		
		HttpEntity<String> requestEntity = new HttpEntity<>(jsonRequest,headers);
		
		String url = "http://localhost:8084/usersEditUT";
		ResponseEntity<User> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, User.class);
	
		return responseEntity.getBody();
	}

	@Override
	public String deleteUser(int userID) {
		//delete /users/{id}
		
		String url = "http://localhost:8084/users/"+userID;
		ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.DELETE, null, String.class);
		
		
		return responseEntity.getBody();
	}

	@Override
	public List<User> getAllUsers() {
		// get /users
		
		UserList users = restTemplate.getForObject("http://localhost:8084/users", UserList.class);
		List<User> userList = users.getUsers();
		
		return userList;
	}

}
