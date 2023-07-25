package com.pets.controller;


import com.pets.dto.AvailEnum;
import com.pets.dto.GenderEnum;
import com.pets.dto.Pet;
import com.pets.model.service.PetServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
//@RequestMapping("/pet")
public class PetController {

    @Autowired
    private PetServiceImpl petService;

    Set<ConstraintViolation<Pet>> violations = new HashSet<>();


    //gets ALL pets added to database
    @GetMapping("/allPets")
    public String showAllPets(Model model){
        List<Pet> listOfAllPets = petService.getAllPets();
        model.addAttribute("errors", violations);
        model.addAttribute("pets", listOfAllPets);
        return "redirect:/petsMainPage";
    }

    @GetMapping("/petsByAvailability")
    public String showPetsByAvailability(Model model, AvailEnum availability){
        List<Pet> listOfPets = petService.getPetsByAvailability(availability);
        model.addAttribute("errors", violations);
        model.addAttribute("availability", listOfPets);
        return "redirect:/petsMainPage"; //filter page to show by pets depending on availability
    } //probably more used for admin role

    //gets all pets available for adoption
    @GetMapping("/pets")
    public String showAllAvailable(Model model,
                                   @RequestParam(defaultValue = "0") int page,
                                   @RequestParam(defaultValue = "8") int size,
                                   @RequestParam(defaultValue = "AVAILABLE")AvailEnum filter){
        Page<Pet> listOfPets = petService.getAvailablePetsPaginated(filter, page, size);
        model.addAttribute("errors", violations);
        model.addAttribute("petsMainPage", listOfPets);
        model.addAttribute("currentFilter", filter);
        return "petsMainPage";
    }

    //need to sort out filters for dropdowns
    @GetMapping("/petsByGender")
    public String filterByGender(Model model, @RequestParam("gender") GenderEnum gender){ //drop-down to get pets filter by gender
        List<Pet> petsByGender = petService.findPetsByGender(gender);
        model.addAttribute("errors", violations);
        model.addAttribute("gender", petsByGender);
        return "redirect:/petsMainPage";
    }

    @GetMapping("/petById")
    public String findPetByID(Model model, @RequestParam("id") int petID){
        Pet pet = petService.findPetById(petID);
        model.addAttribute("errors", violations);
        model.addAttribute("id", pet);
        return "petInfoPage";
    }

    //adding new pet
    @GetMapping("/addPet") //for form
    public String showAddPet(Model model){
       model.addAttribute("pet", new Pet());
       return "addPet"; //should be the page to add a pet
    }

    @PostMapping("/addPet")
    public String executeAddPet(@Valid Pet pet, BindingResult result){
        if(result.hasErrors()){
            return "addPet";
        }
        petService.addPet(pet);
        return "redirect:/petsMainPage";
    }

    //updating details of a pet
    @GetMapping("/updatePet")
    public String showUpdateDetails(Integer petID, Model model){
        Pet petToUpdate = petService.findPetById(petID);
        model.addAttribute("pet", petToUpdate);
        return "updatePet";
    }

    @PostMapping("/updatePet")
    public String executeUpdateDetails(@Valid Pet petToUpdate, BindingResult result){
        if(result.hasErrors()){
            return "/updatePet";
        }
        petService.updatePetDetails(petToUpdate);
        return "redirect:/petsMainPage";
    }

    @GetMapping("/deletePet")
    public String showDeletePet(Model model, Integer petID){
        Pet petToDelete = petService.findPetById(petID);
        model.addAttribute("pet", petToDelete);
        return "deletePet";
    }

    @PostMapping("/deletePet")
    public String executeDeletePet(Integer petID){
        Pet petToDelete = petService.findPetById(petID);
        petService.deletePet(petToDelete);
        return "redirect:/petsMainPage";
    }

}
