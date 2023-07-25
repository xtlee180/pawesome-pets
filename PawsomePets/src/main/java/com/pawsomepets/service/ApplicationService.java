package com.pawsomepets.service;

import java.time.LocalDate;
import java.util.List;

import com.pawsomepets.dto.Application;

public interface ApplicationService {
	
	boolean addApplication(Application application);
	List<Application>getAllApplications();
	Application getApplicationByID(int applicationID);
	boolean editApplicationByID(int applicationID, int petID, int userID, LocalDate applicationDate, LocalDate pickupDate);
	Application deleteApplicationByID(int applicationID);

}
