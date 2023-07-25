package com.pawsomepets.service;

import com.pets.dto.AvailEnum;
import com.pets.dto.GenderEnum;
import com.pets.dto.Pet;
import com.pets.dto.SizeEnum;
import com.pets.exception.PetNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@Service
public class PetServiceImpl implements PetService {

    @Autowired
    private RestTemplate restTemplate;
    private final String baseURL = "http://localhost:8092"; //to refer to the pet API so = port number

    @Override
    public Pet getPetByID(int petID) throws PetNotFoundException {
        String apiURL = baseURL + "/" + petID;

        try {
            ResponseEntity<Pet> response = restTemplate.getForEntity(apiURL, Pet.class);

            //checks if successful or not
            if (response.getStatusCode() == HttpStatus.OK) {
                return response.getBody();
            } else {
                throw new RuntimeException("Failed to fetch pet from ID.");
            }
        } catch (HttpClientErrorException.NotFound ex){
            throw new PetNotFoundException("Pet not found in database");
        } catch (Exception e){
            throw new RuntimeException("Failed to fetch pet from ID", e);
        }
    }

    @Override
    public List<Pet> getPetsByAvailability(AvailEnum availability) {
        return null;
    }

    @Override
    public List<Pet> getPetsByType(String type) {
        return null;
    }

    @Override
    public List<Pet> getPetsByGender(GenderEnum gender) {
        return null;
    }

    @Override
    public List<Pet> getPetsBySize(SizeEnum size) {
        return null;
    }

    @Override
    public List<Pet> getAllPets() {
        return null;
    }

    @Override
    public Pet updatePetDetails(Pet pet, int petID) {
        return null;
    }

    @Override
    public Pet updatePetAvailability(int petID, AvailEnum availability) {
        return null;
    }
}
