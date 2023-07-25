package com.pawsomepets.service;

import com.pets.dto.AvailEnum;
import com.pets.dto.GenderEnum;
import com.pets.dto.Pet;
import com.pets.dto.SizeEnum;
import com.pets.exception.PetNotFoundException;
import org.springframework.data.domain.Page;

import java.util.List;

public interface PetService {
    Pet getPetByID(int petID) throws PetNotFoundException;

    //filter for availability foes on at the controller
    Page<Pet> getAllAvailablePetsPaginated(AvailEnum availibility, int page, int size) throws PetNotFoundException;
    List<Pet> getPetsByAvailability(AvailEnum availability);
    List<Pet> getPetsByType(String type);
    List<Pet> getPetsByGender(GenderEnum gender);
    List<Pet> getPetsBySize(SizeEnum size);
    List<Pet> getAllPets();
    Pet updatePetDetails(Pet pet, int petID);
    Pet updatePetAvailability(int petID, AvailEnum availability);

}
