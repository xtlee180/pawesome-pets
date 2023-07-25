package com.pawsomepets.dto;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotBlank;

import org.springframework.beans.factory.annotation.Autowired;

import com.pawsomepets.service.UserService;

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
	
	@NotBlank(message = "Username is required and must be unique")
	@Column(unique = true)
	private String userName;
	
	@NotBlank(message="Password must not be blank!")
	private String userPassword;
	
	@Enumerated(EnumType.STRING)
	private UserType userType;
	
	@NotBlank(message = "please just fill in the form")
	private String firstName;
	
	@NotBlank(message = "missed something!")
	private String lastName;
	
	@NotBlank(message = "email is required and must be unique")
	@Column(unique = true)
	private String email;
	
	@NotBlank(message = "ring ring...")
	private String phoneNumber;
	
//	@NotBlank(message = "might be helpful")
	@Transient
	private String confirmPassword;
	
	
	
	@AssertTrue(message = "Passwords must match")
	private boolean isPasswordValid() {
		return userPassword !=null && userPassword.equals(confirmPassword);
	}
	

	
	
	@Transient
	@Autowired
	private UserService service;
	
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
