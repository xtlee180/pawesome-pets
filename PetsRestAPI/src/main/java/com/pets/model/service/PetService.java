package com.pets.model.service;

import com.pets.dto.AvailEnum;
import com.pets.dto.GenderEnum;
import com.pets.dto.Pet;
import com.pets.dto.SizeEnum;
import org.springframework.data.domain.Page;

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
    Page<Pet> getAvailablePetsPaginated(AvailEnum availability, int page, int size);
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
