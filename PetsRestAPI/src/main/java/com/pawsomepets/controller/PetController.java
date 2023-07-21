package com.pawsomepets.controller;


import com.pawsomepets.dto.Pet;
import com.pawsomepets.model.service.PetService;
import com.pawsomepets.model.service.PetServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pets")
public class PetController {

    @Autowired
    private PetServiceImpl petService;

    @GetMapping("/newPet")
    public String showAddPetForm(Model model){
       model.addAttribute("pet", new Pet());
       return "newPetForm";
    }

    @PostMapping("/newPet")
    public String addNewPet(@ModelAttribute Pet pet){
        petService.addPet(pet);
        return "redirect:/pets";
    }


}
