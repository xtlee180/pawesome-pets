package com.users.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.users.dao.UserDao;
import com.users.dto.LoginRequest;
import com.users.dto.User;
import com.users.security.JwtUtils;

@RestController
public class AuthController {

	@Autowired
	private UserDao userDao;
	private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	
	@PostMapping(path="/login",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        String username = loginRequest.getUserName();
        String password = loginRequest.getUserPassword();

        User user = userDao.findByUsername(username);
        if (user != null && passwordEncoder.matches(password, user.getUserPassword())) {
            String token = JwtUtils.generateToken(username);
            return ResponseEntity.ok("logged in");//token
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }
}
