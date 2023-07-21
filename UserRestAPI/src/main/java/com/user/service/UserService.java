package com.user.service;

import java.util.List;

import com.user.dto.User;

public interface UserService {
	
	public User getByNameAndPass(String username,String password);
	public List<String> getAllUserNames();
	public List<User> getAllUsers();
	public User getUserByID(int userID);
	public User addUser(User user);
	public User editUser(User user);
	public String deleteUser(int userID);
	public User editNameAndPass(int userID, String userName, String password);
	public User editUserType(int userID ,String userType);
	public User getUserByUserName(String userName);
	

}
