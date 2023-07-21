package com.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.user.dao.UserDao;
import com.user.dto.User;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserDao userDao;

	@Override
	public User getByNameAndPass(String username, String password) {
		return userDao.getByUsernameAndPassword(username,password);

	}

	@Override
	public List<String> getAllUserNames() {
		return userDao.findAll().stream().map(name->name.getUserName()).toList();
	}

	@Override
	public List<User> getAllUsers() {
		return userDao.findAll();
	}

	@Override
	public User getUserByID(int userID) {
		return userDao.findById(userID).orElse(null);
	}

	@Override
	public User addUser(User user) {
		if(userDao.insertUser(user.getUserName(), user.getUserPassword(),user.getUserType().toString(),user.getFirstName(), 
				user.getLastName(), user.getEmail(), user.getPhoneNumber())>0) {
			return userDao.getbyUserName(user.getUserName());
		}
		return null;
	}

	@Override
	public User editUser(User user) {
		if(userDao.updateUserProfileInfo(user.getFirstName(), user.getLastName(), user.getEmail(), user.getPhoneNumber(), user.getUserID())>0) {
			return getUserByID(user.getUserID());
		}
		return null;
	}

	@Override
	public String deleteUser(int userID) {
		User userDelete = getUserByID(userID);
		
		if(userDelete!=null) {
			userDao.deleteById(userID);
			return "Deleted";
		}
		return "Not deleted";
	}

	@Override
	public User editNameAndPass(int userID, String userName, String password) {
		if(userDao.updateUsernameAndPassword(userName, password, userID)>0) {
			return getUserByID(userID);
		}
		return null;
	}

	@Override
	public User editUserType(int userID, String userType) {
		if(userDao.updateUserType(userType, userID)>0) {
			return getUserByID(userID);
		}
		return null;
	}
	
	@Override
	public User getUserByUserName(String userName) {
		return userDao.getbyUserName(userName);
	}
	

}
