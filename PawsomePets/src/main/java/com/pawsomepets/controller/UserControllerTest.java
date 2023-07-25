package com.pawsomepets.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;
import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validation;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.pawsomepets.dto.Login;
import com.pawsomepets.dto.User;
import com.pawsomepets.dto.UserType;
import com.pawsomepets.service.UserService;

@Controller
public class UserControllerTest {

	@Autowired
	private UserService userService;
	
	Set<ConstraintViolation<User>> violationsUser = new HashSet<>();
	Set<ConstraintViolation<Login>> violationsLogin = new HashSet<>();
	
	//login mapping
	@GetMapping("/login")
	public String loginPage(Model model) {
		model.addAttribute("login", new Login());
		model.addAttribute("errors", violationsLogin);
		return "login";
	}
	
	//validation - it's not working but it should be????????????
	@PostMapping("/loginUser")
	public String loginUser(Model model,Login login,HttpSession session) {
		
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		violationsLogin = validator.validate(login);
	
		if(!violationsLogin.isEmpty()) {
			return "redirect:/login";
		}
		
		User user = userService.signIn(login);
		if(user!=null) {	
			model.addAttribute("hiddenPassword", "*********");
			model.addAttribute("actualPassword",user.getUserPassword());
			model.addAttribute("user", user);
			session.setAttribute("sessionUser", user);
			return "ProfilePage";
		}else {
			model.addAttribute("message", "Username or password is incorrect");
			return "login";
		}
		
	}
	
	//logout mapping
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/login";
	}
	
	//sign up mapping
	
	@GetMapping("/signUp")
	public String signUpPage(Model model) {
		model.addAttribute("newUser", new User());
		model.addAttribute("errors", violationsUser);
		
		return "SignUpPage";
	}
	
	//just need to add email verification...
	@PostMapping("/addUser")
	public String addUser(Model model,User newUser) {
		newUser.setUserType(UserType.USER);
		
		
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		violationsUser = validator.validate(newUser);
		
		//check is user name is valid
		
		List<String> names = userService.getAllUserNames();
					
		for(String name:names) {
			if(name.equals(newUser.getUserName())) {
				model.addAttribute("message", "username already exists");
				model.addAttribute("newUser", new User());
				model.addAttribute("errors", violationsUser);
				return "SignUpPage";
			}
		}
		
		if(!violationsUser.isEmpty()) {
			return "redirect:/signUp";
		}
		
		User addedUser = userService.registerUser(newUser);
		
		if(addedUser!=null) {
			model.addAttribute("message", "Registration successful!");
		}else {
			model.addAttribute("message", "Something went wrong...");
		}
		
		model.addAttribute("login", new Login());
		return "login";
	}
	
	//delete account mapping
	@GetMapping("/deleteConfirm")
	public String deleteConfirm() {
		return "deleteAccount";
	}
	
	@DeleteMapping("/delete")
	public String deleteUser(Model model, HttpSession session) {
		User userDelete = (User) session.getAttribute("sessionUser");
		String outcome = userService.deleteUser(userDelete.getUserID());
		
		if(outcome.equals("Deleted")) {
			model.addAttribute("message", "Delete successful!");
		}else {
			model.addAttribute("message", "Something went wrong...");
		}
		
		session.invalidate();
		model.addAttribute("login", new Login());
		return "login";
	}
	
	//cancel and return to profile
	@GetMapping("/profile")
	public String profilePage(Model model, HttpSession session) {
		User user = (User) session.getAttribute("sessionUser");
		model.addAttribute("hiddenPassword", "*********");
		model.addAttribute("actualPassword",user.getUserPassword());
		model.addAttribute("user", user);
		
		return "ProfilePage";
	}
	
	//edit profile mapping
	
	@GetMapping("/editInputProfile")
	public String editInputProfile(Model model, HttpSession session) {
		User userEdit = (User) session.getAttribute("sessionUser");
		model.addAttribute("userEdit", userEdit);
		model.addAttribute("errors", violationsUser);
		return "editProfile";
	}
	
	//validation here
	@PostMapping("/editProfile")
	public String editProfile(User editedUser,HttpSession session) {
		editedUser.setConfirmPassword(editedUser.getUserPassword());
		
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		violationsUser = validator.validate(editedUser);
		
		if(!violationsUser.isEmpty()) {
			return "redirect:/editInputProfile";
		}
		
		User userEdited =  userService.editUserInfo(editedUser);
		session.setAttribute("sessionUser", userEdited);
		return "redirect:/profile";
	}
	
	//edit username and password mapping
	@GetMapping("/editInputUP")
	public String editinputUP(Model model, HttpSession session) {
		User userEdit = (User) session.getAttribute("sessionUser");
		model.addAttribute("userEdit", userEdit);
		model.addAttribute("errors", violationsUser);
		return "editUNPW";
	}
	
	//will need validation here
	@PostMapping("/editUNPW")
	public String editUNPW(User editedUser, HttpSession session) {
		
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		violationsUser = validator.validate(editedUser);
		
		if(!violationsUser.isEmpty()) {
			return "redirect:/editInputUP";
		}
		
		User userEdited =  userService.editCredentials(editedUser);
		session.setAttribute("sessionUser", userEdited);
		return "redirect:/profile";
	}
}
