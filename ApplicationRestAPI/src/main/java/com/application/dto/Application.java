package com.application.dto;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Application {
	
	@Id
	private int applicationID;
	private int petID;
	private int userID;
	private LocalDate applicationDate;
	private LocalDate pickupDate;
}
