package com.user.testing;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.user.demo.TestApplicationConfiguration;
import com.user.dto.User;
import com.user.dto.UserType;
import com.user.service.UserService;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = TestApplicationConfiguration.class)
class UserServiceTests {
	
	@Autowired
	UserService userService;

	@BeforeEach
	void setUp() throws Exception {
		List<User> users = userService.getAllUsers();
		for(User user:users) {
			userService.deleteUser(user.getUserID());
		}
	}

	//add user and will produce the user if the UN and PW match
	@Test
	void testGetByNameAndPass() {
		User userAdd = new User("admin", "adminPass", UserType.ADMIN, "Steve", "Irwin", "SI@email.com", "082637824");
		userService.addUser(userAdd);
		
		User userRetrieved = userService.getUserByUserName("admin");
		
		assertEquals(userRetrieved, userService.getByNameAndPass("admin", "adminPass"));
		assertEquals(null,userService.getByNameAndPass("admin", "notpassword"));
		assertEquals(null, userService.getByNameAndPass("notUser", "adminPass"));

	}
	
	//add a couple of users and get list of their names
	@Test
	void testGetAllUserNames() {
		User userAdd = new User("admin", "adminPass", UserType.ADMIN, "Steve", "Irwin", "SI@email.com", "082637824");
		userService.addUser(userAdd);
		User userAdd2 = new User("user", "userPass", UserType.USER, "Margot", "Robbie", "MR@email.com", "654574824");
		userService.addUser(userAdd2);
		List<String> names = new ArrayList<>();
		names.add("admin");
		names.add("user");
		
		assertEquals(names, userService.getAllUserNames());
		
	}

	//add a user
	@Test
	void testGetAllUsers() {
		User userAdd = new User("admin", "adminPass", UserType.ADMIN, "Steve", "Irwin", "SI@email.com", "082637824");
		userService.addUser(userAdd);
		userAdd.setUserID(userService.getUserByUserName("admin").getUserID());
		User userAdd2 = new User("user", "userPass", UserType.USER, "Margot", "Robbie", "MR@email.com", "654574824");
		userService.addUser(userAdd2);
		userAdd2.setUserID(userService.getUserByUserName("user").getUserID());
		List<User> users = new ArrayList<>();
		users.add(userAdd);
		users.add(userAdd2);
		
		assertEquals(users, userService.getAllUsers());
	}

	@Test
	void testGetUserByID() {
		User userAdd = new User("admin", "adminPass", UserType.ADMIN, "Steve", "Irwin", "SI@email.com", "082637824");
		userService.addUser(userAdd);
		int id = userService.getUserByUserName("admin").getUserID();
		userAdd.setUserID(id);
		
		assertEquals(userAdd, userService.getUserByID(id));
	}

	//add user to the database
	@Test
	void testAddUser() {
		User userAdd = new User("admin", "adminPass", UserType.ADMIN, "Steve", "Irwin", "SI@email.com", "082637824");
		userService.addUser(userAdd);
		assertEquals(1, userService.getAllUsers().size());
	}
	
	//allows you to edit name,email and phone
	@Test
	void testEditUser() {
		User userAdd = new User("admin", "adminPass", UserType.ADMIN, "Steve", "Irwin", "SI@email.com", "082637824");
		userService.addUser(userAdd);
		int id = userService.getUserByUserName("admin").getUserID();
		userAdd.setUserID(id);
		
		assertEquals(userAdd, userService.getUserByID(id));
		
		userAdd.setFirstName("Jessie");
		userAdd.setLastName("Buckley");
		userAdd.setEmail("JB@email.com");
		
		assertEquals(userAdd, userService.editUser(userAdd));
	}

	@Test
	void testDeleteUser() {
		
		User userAdd = new User("admin", "adminPass", UserType.ADMIN, "Steve", "Irwin", "SI@email.com", "082637824");
		userService.addUser(userAdd);
		int id = userService.getUserByUserName("admin").getUserID();
		userAdd.setUserID(id);
		
		assertEquals(userAdd, userService.getUserByID(id));
		
		
		assertEquals("Deleted", userService.deleteUser(id));
	}

	@Test
	void testEditNameAndPass() {
		User userAdd = new User("admin", "adminPass", UserType.ADMIN, "Steve", "Irwin", "SI@email.com", "082637824");
		userService.addUser(userAdd);
		int id = userService.getUserByUserName("admin").getUserID();
		userAdd.setUserID(id);
		
		userAdd.setUserName("adminTest");
		userAdd.setUserPassword("password");
		
		assertEquals(userAdd, userService.editNameAndPass(userAdd.getUserID(), userAdd.getUserName(), userAdd.getUserPassword()));
	}

	@Test
	void testEditUserType() {
		
		User userAdd = new User("admin", "adminPass", UserType.ADMIN, "Steve", "Irwin", "SI@email.com", "082637824");
		userService.addUser(userAdd);
		int id = userService.getUserByUserName("admin").getUserID();
		userAdd.setUserID(id);
		
		userAdd.setUserType(UserType.USER);
		
		assertEquals(userAdd, userService.editUserType(id, "USER"));
	}

}
