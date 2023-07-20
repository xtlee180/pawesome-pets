package com.user.dto;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
	private int userID;
	private String userName;
	private String password;
	
	@Enumerated(EnumType.STRING)
	private UserType userType;
	private String firstName;
	private String lastName;
	private String email;
	private String phoneNumber;

}
