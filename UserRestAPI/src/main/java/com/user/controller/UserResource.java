package com.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.user.dto.Login;
import com.user.dto.User;
import com.user.service.UserService;

@RestController
public class UserResource {
	
	@Autowired
	UserService userService;
	
	@PostMapping(path = "/login", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public User getUserByUnAndPw(@RequestBody Login login) {
		return userService.getByNameAndPass(login.getUserName(), login.getUserPassword());
	}
	
	@GetMapping(path = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<User> getAllUsers(){
		return userService.getAllUsers();
	}
	
	@GetMapping(path = ("/users/{id}"), produces = MediaType.APPLICATION_JSON_VALUE)
	public User	getUserByID(@PathVariable("id")int userID) {
		return userService.getUserByID(userID);
	}
	
	@PostMapping(path= ("/users"), produces = MediaType.APPLICATION_JSON_VALUE , consumes = MediaType.APPLICATION_JSON_VALUE)
	public User insertUser(@RequestBody User user) {
		return userService.addUser(user);
	}
	
	@PutMapping(path = "/users", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public User editUser(@RequestBody User user) {
		return userService.editUser(user);
	}
	
	@DeleteMapping(path = "/users/{id}", produces = MediaType.TEXT_PLAIN_VALUE)
	public String deleteUser(@PathVariable("id") int userID) {
		return userService.deleteUser(userID);
	}
	
	@PutMapping(path="/usersEditPN", produces = MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
	public User editUnAndPw(@RequestBody User user) {
		return userService.editNameAndPass(user.getUserID(), user.getUserName(), user.getUserPassword());
	}
	
	@PutMapping(path="/usersEditUT", produces = MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
	public User editUserType(@RequestBody User user) {
		return userService.editUserType(user.getUserID(), user.getUserType().toString());
	}


}
