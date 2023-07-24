package com.pawsomepets.dto;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Login {

	@NotBlank(message = "username must not be left blank.")
	private String userName;
	@NotBlank(message = "password must not be left blank.")
	private String userPassword;
}
