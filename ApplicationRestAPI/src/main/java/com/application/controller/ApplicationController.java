package com.application.controller;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.application.dto.Application;
import com.application.dto.ApplicationList;
import com.application.service.ApplicationService;

@RestController
public class ApplicationController {
	@Autowired
	private ApplicationService applicationService;

	// Add an application
	@RequestMapping("/applicationInput")
	public ModelAndView InputEmployeeDetailsPageController() {
		ModelAndView modelAndView=new ModelAndView();
		
		modelAndView.addObject("app", new Application());
	    modelAndView.setViewName("Application/ApplicationPage");
		return modelAndView;
	}
	
	@RequestMapping("/addApplication")
	public ModelAndView saveApplicationController(@Valid @ModelAttribute ("app")Application application,BindingResult results) {
		ModelAndView modelAndView = new ModelAndView();
		
		if (results.hasErrors()) {
			List<Application> applicationList = applicationService.getAllApplications();
			modelAndView.addObject("applications", applicationList);
	        modelAndView.addObject("app", application);
	        modelAndView.setViewName("Application/ApplicationPage");
	        return modelAndView;
	    }
		
		String message = null;
		if (applicationService.addApplication(application))
			message = "Congrats, your application is successful!";
		else
			message = "An application couldn't be made at this time";

		modelAndView.addObject("message", message);
		modelAndView.setViewName("Application/ApplicationConfirmationPage");

		return modelAndView;
	}

	// Get all applications
	@GetMapping("/applications")
	public ModelAndView displayapplicationsController() {
		ModelAndView modelAndView = new ModelAndView();

		List<Application> applicationList = applicationService.getAllApplications();
		modelAndView.addObject("applications", applicationList);
		modelAndView.addObject("app", new Application());
		modelAndView.setViewName("Application/DisplayApplicationsPage");
		return modelAndView;
	}

	// Get application by ID
	@GetMapping("/applicationsByID")
    public String displayApplicationByID(int applicationID, Model model) {
		Application application = applicationService.getApplicationByID(applicationID);
        model.addAttribute("application", application);
        return "applications";
    }

	// Edit an application
    @GetMapping("/editApplication")
    public String editApplication(int applicationID, Model model) {
    	Application application = applicationService.getApplicationByID(applicationID);
        model.addAttribute("application", application);
        return "Application/ApplicationEditPage";
    }

	@PostMapping("/editApplication")
    public String EditApplicationImpl(Application application) {
        applicationService.editApplicationByID(application.getApplicationID(), 
        		application.getPetID(), application.getUserID(),
        		application.getApplicationDate(), application.getPickupDate());
        return "redirect:/applications";
    }
    
	// Delete an application
	@GetMapping("/deleteApplication")
	 public String deleteApplication(int applicationID, Model model) {
        Application application = applicationService.getApplicationByID(applicationID);
        model.addAttribute("application", application);
        return "Application/ApplicationDeletePage";
    }
	
	@PostMapping("/deleteApplication")
	public String deleteApplicationPage(int applicationID) {
        applicationService.deleteApplicationByID(applicationID);
        return "redirect:/applications";
    }
}