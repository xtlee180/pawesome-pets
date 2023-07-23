package com.application.test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.application.demo.TestApplicationConfiguration;
import com.application.dto.Application;
import com.application.service.ApplicationService;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = TestApplicationConfiguration.class)
class ApplicationServiceTest {

	@Autowired
	ApplicationService applicationService;
	
	@BeforeEach
	void setUp() throws Exception {
		List<Application> applications = applicationService.getAllApplications();
		for(Application app: applications) {
			applicationService.deleteApplicationByID(app.getApplicationID());
		}
	}

	//Add an application
	@Test
	void testAddApplication() {
		Application application = new Application();
		application.setApplicationID(101);
		application.setPetID(208);
		application.setUserID(305);
		application.setApplicationDate(LocalDate.now());
		application.setPickupDate(LocalDate.now().plusWeeks(1));
		
		boolean result = applicationService.addApplication(application);
        assertTrue(result);
	}

	//Get all of the applications
	@Test
	void testGetAllApplications() {
		Application application = new Application();
		application.setApplicationID(101);
		application.setPetID(208);
		application.setUserID(305);
		application.setApplicationDate(LocalDate.now());
		application.setPickupDate(LocalDate.now().plusWeeks(1));
		
		applicationService.addApplication(application);
		List<Application> applications = applicationService.getAllApplications();
		
		 assertNotNull(applications);
		 assertEquals(1, applications.size());	
	}

	//Get an application by its ID
	@Test
	void testGetApplicationByID() {
		Application application = new Application();
		application.setApplicationID(101);
		application.setPetID(208);
		application.setUserID(305);
		application.setApplicationDate(LocalDate.now());
		application.setPickupDate(LocalDate.now().plusWeeks(1));
		
		applicationService.addApplication(application);
		
		Application retrievedApp = applicationService.getApplicationByID(application.getApplicationID());
		
		assertNotNull(retrievedApp);
		assertEquals(application.getApplicationID(), retrievedApp.getApplicationID());	
	}
	
    //Edit an application and save the changes
	@Test
	void testEditApplicationByID() {
		Application application = new Application();
		application.setApplicationID(101);
		application.setPetID(208);
		application.setUserID(305);
		application.setApplicationDate(LocalDate.now());
		application.setPickupDate(LocalDate.now().plusWeeks(1));

		applicationService.addApplication(application);

		Application retrievedApp = applicationService.getApplicationByID(application.getApplicationID());
		assertNotNull(retrievedApp);

		int originalPetID = retrievedApp.getPetID();
		LocalDate originalPickupDate = retrievedApp.getPickupDate();

		retrievedApp.setPetID(210);
		retrievedApp.setApplicationDate(LocalDate.now());
		retrievedApp.setPickupDate(LocalDate.now().plusWeeks(2));

		boolean editResult = applicationService.editApplicationByID(retrievedApp.getApplicationID(),
				retrievedApp.getPetID(), retrievedApp.getUserID(), retrievedApp.getApplicationDate(),
				retrievedApp.getPickupDate());

		assertTrue(editResult);

		Application editedApp = applicationService.getApplicationByID(retrievedApp.getApplicationID());
		assertNotNull(editedApp);

		assertEquals(retrievedApp.getPetID(), editedApp.getPetID());
		assertEquals(retrievedApp.getApplicationDate(), editedApp.getApplicationDate());
		assertEquals(retrievedApp.getPickupDate(), editedApp.getPickupDate());

		assertNotEquals(originalPetID, editedApp.getPetID());
		assertNotEquals(originalPickupDate, editedApp.getPickupDate());
	}
	
    //Delete an application by its ID 
	@Test
	void testDeleteApplicationByID() {
		Application application = new Application();
		application.setApplicationID(101);
		application.setPetID(208);
		application.setUserID(305);
		application.setApplicationDate(LocalDate.now());
		application.setPickupDate(LocalDate.now().plusWeeks(1));
		
		applicationService.addApplication(application);
		
		Application deletedApp = applicationService.deleteApplicationByID(application.getApplicationID());
		assertNotNull(deletedApp);
		
		Application retrievedApp = applicationService.getApplicationByID(application.getApplicationID());
		assertNull(retrievedApp);
	}
}
