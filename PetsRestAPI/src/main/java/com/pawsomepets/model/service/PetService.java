package com.pawsomepets.model.service;

import com.pawsomepets.dto.AvailEnum;
import com.pawsomepets.dto.GenderEnum;
import com.pawsomepets.dto.Pet;
import com.pawsomepets.dto.SizeEnum;

import java.util.List;

/*
+ getAvailablePets
+ getAllPets
+ findPetById
+ findPetByType
+ findPetByBreed
+ findPetByGender
+ findPetByAgeRange
+ findPetBySize
+ updatePetAvailbiity (i.e. avail.)
+ deletePetById
+ createPet
+ updatePetDetails (all fields)


 */
public interface PetService {
    List<Pet> getAvailablePets();
    List<Pet> getAllPets();
    Pet findPetById(int petID);
    List<Pet> findPetsByType(String type);
    List<Pet> findPetsByGender(GenderEnum gender);
    List<Pet> findPetsBySize(SizeEnum size);
    void updatePetAvailability(int id, AvailEnum availability);
    Pet updatePetDetails(Pet pet); //updates all fields of the pet
    void deletePet(Pet pet);
    Pet addPet(Pet newPet);

}
