package com.pets.model.service;

import com.pets.dto.AvailEnum;
import com.pets.dto.GenderEnum;
import com.pets.dto.Pet;
import com.pets.dto.SizeEnum;
import com.pets.model.dao.PetsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetServiceImpl implements PetService {
    @Autowired
    PetsDao petsDao;


    //specifically for available pets
    @Override
    public Page<Pet> getAvailablePetsPaginated(AvailEnum availability, int page, int size){
        Pageable pageable = PageRequest.of(page, size);
        return petsDao.findAvailablePets(availability, pageable);
    }

    //used if need to get pets by a certain availability e.g. adopted or inprogress
    public List<Pet> getPetsByAvailability(AvailEnum availability){
        return petsDao.getPetsByStatus(availability);
    }

    //returns a list of all pets in the database
    @Override
    public List<Pet> getAllPets() {
        return petsDao.findAll();
    }

    //possible method: filter by age range w/ a min param & max param
    @Override
    public Pet findPetById(int petID) { //returns individual pet
        return petsDao.getPetByPetID(petID);
    }

    @Override
    public List<Pet> findPetsByType(String type) {
        return petsDao.getPetsByType(type);
    }

    @Override
    public List<Pet> findPetsByGender(GenderEnum gender) {
        return petsDao.getPetsByGender(gender);
    }

    @Override
    public List<Pet> findPetsBySize(SizeEnum size) {
        return petsDao.getPetsBySize(size);
    }

    @Override //only updates availability
    public void updatePetAvailability(int id, AvailEnum availability) {
        petsDao.updatePetAvailability(id, availability);
    }

    @Override //updates ALL details of a pet
    public Pet updatePetDetails(Pet pet) {
        return petsDao.updateAllPetDetails(pet.getPetName(),pet.getAvailability(),pet.getDescription(),pet.getGender(), pet.getSize(),pet.getAge(),pet.getType(),pet.getBreed(),pet.getImage(),pet.getPetID());
    }

    @Override
    public void deletePet(Pet pet) {
        petsDao.deletePetById(pet.getPetID());
    }

    @Override
    public Pet addPet(Pet newPet) {
        petsDao.save(newPet);
        return newPet;
    }
}
