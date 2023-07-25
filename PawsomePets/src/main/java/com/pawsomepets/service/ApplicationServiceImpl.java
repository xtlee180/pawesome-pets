package com.pawsomepets.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pawsomepets.dao.ApplicationDao;
import com.pawsomepets.dto.Application;

@Service
public class ApplicationServiceImpl implements ApplicationService {
	
	@Autowired
	ApplicationDao applicationDao;

	@Override
	public boolean addApplication(Application application) {
		//Check if an application already exists for the pet
	    List<Application> existingApplications = applicationDao.findPetByID(application.getPetID());
	    if (!existingApplications.isEmpty()) {
	        //An application already exists for the pet
	        return false;
	    }

	    //Application does not already exist so it can be added
	    application.setApplicationDate(LocalDate.now());
	    applicationDao.save(application);
	    return true;
	}

	@Override
	public List<Application> getAllApplications() {
		return applicationDao.findAll();
	}

	@Override
	public Application getApplicationByID(int applicationID) {
		return applicationDao.findById(applicationID).orElse(null);
	}

	@Override
	public boolean editApplicationByID(int applicationID, int petID, int userID, LocalDate applicationDate, LocalDate pickupDate) {
		Application app = getApplicationByID(applicationID);
	    if (app != null && app.getUserID() == userID) {
	        //Check the pickup date is a future date
	        LocalDate currentDate = LocalDate.now();
	        if (pickupDate.isAfter(currentDate)) {
	            app.setPetID(petID);
	            app.setPickupDate(pickupDate);
	            app.setApplicationDate(currentDate); //The application date will update to current date
	            applicationDao.save(app);
	            return true;
	        } else {
	            //The pickup date is not a valid future date
	            return false;
	        }
	    }
	    //The application with the current applicationID does not exist belong to the current user
	    return false;
	}

	@Override
	public Application deleteApplicationByID(int applicationID) {
		Application app = getApplicationByID(applicationID);
        if (app != null) 
            applicationDao.deleteById(applicationID);
          
        return app;
	}
}