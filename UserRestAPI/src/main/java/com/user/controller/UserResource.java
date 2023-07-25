package com.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
import com.user.dto.UserList;
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
	public UserList getAllUsers(){
		return new UserList(userService.getAllUsers());
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
	public ResponseEntity<User> editUser(@RequestBody User user) {
		User userEdit = userService.editUser(user);
		if (userEdit!=null) {
			return new ResponseEntity<User>(userEdit, HttpStatus.OK);
		}else {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@DeleteMapping(path = "/users/{id}", produces = MediaType.TEXT_PLAIN_VALUE)
	public String deleteUser(@PathVariable("id") int userID) {
		return userService.deleteUser(userID);
	}
	
	@PutMapping(path="/usersEditPN", produces = MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> editUnAndPw(@RequestBody User user) {
		User userCred = userService.editNameAndPass(user.getUserID(), user.getUserName(), user.getUserPassword());
		if(userCred!=null) {
			return new ResponseEntity<User>(userCred, HttpStatus.OK);
		}else {
			return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
		}
	
	}
	
	@PutMapping(path="/usersEditUT", produces = MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> editUserType(@RequestBody User user) {
		User userType = userService.editUserType(user.getUserID(), user.getUserType().toString());
		
		if(userType!=null) {
			return new ResponseEntity<User>(userType, HttpStatus.OK);
		}else {
			return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
		}
	
	}
	
	@GetMapping(path = "/usersNames")
	public List<String> getAllUserNames(){
		return userService.getAllUserNames();
	}


}
