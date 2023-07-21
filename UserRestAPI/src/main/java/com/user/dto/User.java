package com.user.dto;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userID;
	private String userName;
	private String userPassword;
	
	@Enumerated(EnumType.STRING)
	private UserType userType;
	private String firstName;
	private String lastName;
	private String email;
	private String phoneNumber;
	
	public User(String userName, String password, UserType userType, String firstName, String lastName, String email,
			String phoneNumber) {
		super();
		this.userName = userName;
		this.userPassword = password;
		this.userType = userType;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phoneNumber = phoneNumber;
	}
	
	

}
