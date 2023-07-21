package com.pawsomepets.model.service;

import com.pawsomepets.dto.AvailEnum;
import com.pawsomepets.dto.GenderEnum;
import com.pawsomepets.dto.Pet;
import com.pawsomepets.dto.SizeEnum;
import com.pawsomepets.model.dao.PetsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetServiceImpl implements PetService {
    @Autowired
    PetsDao petsDao;

    //returns a list of all pets in the database
    @Override
    public List<Pet> getAvailablePets() {
        return petsDao.getAllAvailable(AvailEnum.AVAILABLE);
    }

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
    public List<Pet> findPetByType(String type) {
        return petsDao.getPetsByType(type);
    }

    @Override
    public List<Pet> findPetByGender(GenderEnum gender) {
        return petsDao.getPetsByGender(gender);
    }

    @Override
    public List<Pet> findPetBySize(SizeEnum size) {
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
    public void deletePetById(Pet pet) {
        petsDao.deletePetById(pet.getPetID());
    }

    @Override
    public Pet addPet(Pet newPet) {
        petsDao.save(newPet);
        return newPet;
    }
}
