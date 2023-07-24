package com.users.service;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.users.dao.UserDao;
import com.users.dto.User;


@Service
public class UserService implements UserDetailsService {
	
	@Autowired
	private UserDao dao;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
		User user = dao.findByUsername(username);
		if(user==null) {
			throw new UsernameNotFoundException("User not found");
		}
		return new org.springframework.security.core.userdetails.User(
	            user.getUserName(),
	            user.getUserPassword(),
	            Collections.emptyList()
	        );
	}

}
