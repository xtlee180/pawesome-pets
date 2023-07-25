package com.pawsomepets.controller;

import com.pawsomepets.service.PetServiceImpl;
import com.pets.dto.AvailEnum;
import com.pets.dto.Pet;
import com.pets.exception.PetNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PetControllerTesting {

    @Autowired
    private PetServiceImpl petService;



    @GetMapping("/pets")
    public ResponseEntity<Page<Pet>> getAvailablePets (@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "8") int size){
        Pageable pageable = PageRequest.of(page-1, size);
    }

    @GetMapping("/petByID")
    public ResponseEntity<Pet> findPetByID(@RequestParam("id") int petID){
        try{
            Pet pet = petService.getPetByID(petID);
            return ResponseEntity.ok(pet);
        } catch (PetNotFoundException e){
            return ResponseEntity.notFound().build();
        } catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
