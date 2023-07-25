package com.pawsomepets.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.pawsomepets.dto.AvailEnum;
import com.pawsomepets.dto.GenderEnum;
import com.pawsomepets.dto.Pet;
import com.pawsomepets.dto.SizeEnum;
import com.pawsomepets.service.PetService;

@Controller
public class AdminController {
	
	@Autowired
	private PetService petService;
	
	private List<AvailEnum> getAvailability(){
		List<AvailEnum> avail = Arrays.asList(AvailEnum.values());
		return avail;
	}
	
	private List<GenderEnum> getGender(){
		List<GenderEnum> gender = Arrays.asList(GenderEnum.values());
		return gender;
	}
	
	private List<SizeEnum> getSize(){
		List<SizeEnum> size = Arrays.asList(SizeEnum.values());
		return size;
	}
	
	//get to admin home page
	
	@GetMapping("/admin")
	public String adminPage() {
		return "adminHome";
	}
	
	//add animal - addAnimalInput
	//get mapping - post mapping
	
	@GetMapping("/addAnimalInput")
	public String addAnimalInput(Model model) {
		model.addAttribute("addAnimal", new Pet());
		model.addAttribute("avaList", getAvailability());
		model.addAttribute("genderList", getGender());
		model.addAttribute("sizeList", getSize());
		return "addAnimal";
	}
	
	@PostMapping("/addAnimal")
	public String addAnimal(Pet addAnimal) {
		//will need pet service add an animal
		return null;
	}
	
	
	//edit animal - editAnimalInput
	@GetMapping("/editAnimalInput")
	public String editAnimalInput(Model model) {
		//will need to add list of pets to this model(petList)
		return "editAnimalInput";
	}
	
	@PostMapping("/editAnimal")
	public String editAnimal(Model model,@RequestParam int petID) {
		//use the pet id to get the pet info and put as model in edit animal(editAnimal)
		model.addAttribute("avaList", getAvailability());
		model.addAttribute("genderList", getGender());
		model.addAttribute("sizeList", getSize());
		return "editAnimal";
	}
	
	@PostMapping("/editAnimalNow")
	public String editAnimalHere(Model model, Pet editAnimal) {
		//call service for edit animal and maybe return to the admin home with a message
		return "adminHome";
	}
	
	//delete animal - deleteAnimalInput

}
