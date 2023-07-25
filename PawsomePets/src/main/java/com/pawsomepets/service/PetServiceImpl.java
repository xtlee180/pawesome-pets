package com.pawsomepets.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pets.dto.AvailEnum;
import com.pets.dto.GenderEnum;
import com.pets.dto.Pet;
import com.pets.dto.SizeEnum;
import com.pets.exception.PetNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@Service
public class PetServiceImpl implements PetService {

    private final RestTemplate restTemplate;
    private final String resourceURL = "http://localhost:8092"; //to refer to the pet API so = port number
    private final ObjectMapper objectMapper = new ObjectMapper();


    @Autowired
    public PetServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    @Override
    public Pet getPetByID(int petID) throws PetNotFoundException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        String apiURL = resourceURL + "/pets/" + petID;
        HttpEntity<?> requestEntity = new HttpEntity<>(headers);

        try{
            ResponseEntity<Pet> response = restTemplate.exchange(apiURL, HttpMethod.GET, requestEntity, Pet.class); //pet.class to deserialise
            if (response.getStatusCode() == HttpStatus.OK) {
                return response.getBody(); //returns Pet object
            } else if (response.getStatusCode() == HttpStatus.NOT_FOUND){
                throw new PetNotFoundException("Pet ID "+ petID + " cannot be found.");
            } else {
                throw new RuntimeException("Failed to fetch pet.");
            }
        } catch (HttpClientErrorException.NotFound ex) {
            throw new PetNotFoundException("Pet not found with ID: " + petID);
        } catch (RestClientException ex) {
            // Handle any other exceptions that may occur during the request
            throw new RuntimeException("Failed to fetch pet. Error: " + ex.getMessage());
        }
    }

    @Override //filter for availability foes on at the controller
    public Page<Pet> getAllAvailablePetsPaginated(AvailEnum availability, int page, int size) throws PetNotFoundException {
        String apiURL = resourceURL + "/pets" + "?page=" + page + "&size=" + size;

        // FILTERS FOR AVAILABILITY
        if (availability != null) {
            apiURL += "&availability=" + availability; // Corrected the parameter name to "availability"
        }

        try {
            ResponseEntity<Page<Pet>> response = restTemplate.exchange(
                    apiURL,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<Page<Pet>>() {}
            );

            // Check if the API call was successful (HTTP status 2xx)
            if (response.getStatusCode() == HttpStatus.OK) {
                return response.getBody();
            } else if (response.getStatusCode() == HttpStatus.NOT_FOUND) {
                // Handle the case where pets are not found (HTTP 404)
                throw new PetNotFoundException("Pets do not exist in the database");
            } else {
                // Handle other HTTP status codes if needed
                throw new RuntimeException("Failed to fetch available pets. HTTP status: " + response.getStatusCodeValue());
            }
        } catch (RestClientException e) {
            // Handle any exceptions that occurred during the API call
            System.out.println("Error has occurred: " + e.getMessage()); // Log the error for debugging purposes
            throw new RuntimeException("Failed to fetch available pets. Error: " + e.getMessage());
        }
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
