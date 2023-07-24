package com.users.controllers;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.users.dao.UserDao;
import com.users.dto.User;
import com.users.security.JwtUtils;

@RestController
public class SecuredController {
	
	private UserDao userDao;
	
	@GetMapping(path="/secured",produces = MediaType.APPLICATION_JSON_VALUE)
    public User secured(@RequestHeader("Authorization") String tokenHeader) {
        String token = tokenHeader.substring("Bearer ".length());
        String username = JwtUtils.extractUsername(token);

        User user = userDao.findByUsername(username);
        
        return user;
//        return ResponseEntity.ok("Welcome to the secured page, " + username + "!");
}
	
}
