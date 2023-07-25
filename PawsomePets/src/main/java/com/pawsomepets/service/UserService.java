package com.pawsomepets.service;

import java.util.List;

import com.pawsomepets.dto.Login;
import com.pawsomepets.dto.User;

public interface UserService {
	
	public User signIn(Login loginInfo);
	public User registerUser(User user);
	public User editUserInfo(User user);
	public User editCredentials(User user);
	public User editUserType(User user);
	public String deleteUser(int userID);
	public List<User> getAllUsers();
}
