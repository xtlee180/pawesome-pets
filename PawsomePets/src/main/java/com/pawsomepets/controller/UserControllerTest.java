package com.pawsomepets.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.pawsomepets.dto.Login;
import com.pawsomepets.dto.User;
import com.pawsomepets.service.UserService;

@Controller
public class UserControllerTest {

	@Autowired
	private UserService userService;
	
	@GetMapping("/login")
	public String loginPage(Model model) {
		model.addAttribute("login", new Login());
		return "login";
	}
	
	@PostMapping("/loginUser")
	public String loginUser(Model model,Login login,HttpSession session) {
		User user = userService.signIn(login);
		model.addAttribute("hiddenPassword", "*********");
		model.addAttribute("actualPassword",user.getUserPassword());
		model.addAttribute("user", user);
		session.setAttribute("sessionUser", user);
		return "ProfilePage";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/login";
	}
	
}
