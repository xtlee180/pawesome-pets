package com.application.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.application.dto.Application;
import com.application.dto.ApplicationList;
import com.application.service.ApplicationService;

@RestController
public class ApplicationController {
	@Autowired
	private ApplicationService applicationService;

	// Add an application
	@PostMapping(path = "/applications", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Application addApplication(@RequestBody Application application) {
		if (applicationService.addApplication(application))
			return application;
		else
			return null;
	}

	// Get all applications
	@GetMapping(path = "/applications", produces = MediaType.APPLICATION_JSON_VALUE)
	public ApplicationList getAllApplications() {
		return new ApplicationList(applicationService.getAllApplications());
	}

	// Get application by ID
	@GetMapping(path = "/applications/{applicationID}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Application getApplicationByID(@PathVariable("applicationID") int id) {
		return applicationService.getApplicationByID(id);
	}

	// Edit an application
	@PutMapping(path = "/applications/{applicationID}", produces = MediaType.APPLICATION_JSON_VALUE)
	public String editApplication(@PathVariable("applicationID") int id, @RequestBody Application updatedApplication) {
		int petID = updatedApplication.getPetID();
		int userID = updatedApplication.getUserID();
		LocalDate applicationDate = updatedApplication.getApplicationDate();
		LocalDate pickupDate = updatedApplication.getPickupDate();

		boolean isUpdated = applicationService.editApplicationByID(id, petID,
				userID, applicationDate, pickupDate);
		
		if (isUpdated) {
			return "Application updated successfully.";
		} else {
			return "Failed to update application.";
		}
	}

	// Delete an application
	@DeleteMapping(path = "/applications/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Application deleteEmployeeByID(@PathVariable int id) {
		return applicationService.deleteApplicationByID(id);
	}
}