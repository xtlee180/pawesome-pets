package com.pawsomepets.service;

import com.pawsomepets.dto.AvailEnum;
import com.pawsomepets.dto.GenderEnum;
import com.pawsomepets.dto.Pet;
import com.pawsomepets.dto.SizeEnum;
import com.pawsomepets.model.dao.PetsDao;
import com.pawsomepets.model.service.PetServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PetsServiceTest {
        @Mock
        private PetsDao petsDao; //mocked dependency

        @InjectMocks
        private PetServiceImpl petService; //class being tested w/ mocked dependency

    //test to see if pets are successfully saved
        @Test
        public void addNewPetTest() {
            Pet testPet = new Pet();
            testPet.setPetName("Greg");
            testPet.setType("Dog");
            testPet.setAge(2);
            testPet.setBreed("Golden Lab");
            testPet.setSize(SizeEnum.MEDIUM);
            testPet.setGender(GenderEnum.MALE);
            testPet.setAvailability(AvailEnum.AVAILABLE);
            testPet.setDescription("A funky little dude.");
            testPet.setImage("dsghdsgdsgdsgsd.jpeg");

            //mocks behaviour of petDao save method
            //when save called w/ testPet, return same object w/ id set
            when(petsDao.save(testPet)).thenReturn(testPet);

            //test via calling method in service layer
            Pet savedPet = petService.addPet(testPet);

            assertEquals(testPet, savedPet);
        }

        // finds an individual pet by id rather than list
    @Test
    void findPetById() {
        Pet expectedPet = new Pet();
        expectedPet.setPetName("Greg");
        expectedPet.setType("Dog");
        expectedPet.setAge(2);
        expectedPet.setBreed("Golden Lab");
        expectedPet.setSize(SizeEnum.MEDIUM);
        expectedPet.setGender(GenderEnum.MALE);
        expectedPet.setAvailability(AvailEnum.AVAILABLE);
        expectedPet.setDescription("A funky little dude.");
        expectedPet.setImage("dsghdsgdsgdsgsd.jpeg");

        int petIdToFind = expectedPet.getPetID();

        petService.addPet(expectedPet);

        //sets up mock behaviour for pet dao when getPetById is called
        when(petsDao.getPetByPetID(petIdToFind)).thenReturn(expectedPet);

        Pet foundPet = petService.findPetById(expectedPet.getPetID());

        verify(petsDao).getPetByPetID(expectedPet.getPetID());

        assertEquals(expectedPet, foundPet);
    }

    //same as getPetsByGender test but with availability. if this works, the gender one should also work
    @Test
    void getListOfAvailablePetsTest() {
        Pet pet1 = new Pet();
        pet1.setPetName("Max");
        pet1.setAvailability(AvailEnum.AVAILABLE);
        pet1.setDescription("Friendly and playful");
        pet1.setGender(GenderEnum.MALE);
        pet1.setSize(SizeEnum.MEDIUM);
        pet1.setAge(2);
        pet1.setType("Dog");
        pet1.setBreed("Golden Retriever");
        pet1.setImage("https://www.example.com/pet1.jpg");

        Pet pet2 = new Pet();
        pet2.setPetName("Luna");
        pet2.setAvailability(AvailEnum.AVAILABLE);
        pet2.setDescription("Sweet and affectionate");
        pet2.setGender(GenderEnum.FEMALE);
        pet2.setSize(SizeEnum.SMALL);
        pet2.setAge(1);
        pet2.setType("Cat");
        pet2.setBreed("Siamese");
        pet2.setImage("https://www.example.com/pet2.jpg");

        Pet pet3 = new Pet();
        pet3.setPetName("Rocky");
        pet3.setAvailability(AvailEnum.ADOPTED);
        pet3.setDescription("Energetic and loyal");
        pet3.setGender(GenderEnum.MALE);
        pet3.setSize(SizeEnum.LARGE);
        pet3.setAge(3);
        pet3.setType("Dog");
        pet3.setBreed("German Shepherd");
        pet3.setImage("https://www.example.com/pet3.jpg");

        Pet pet4 = new Pet();
        pet4.setPetName("Whiskers");
        pet4.setAvailability(AvailEnum.AVAILABLE);
        pet4.setDescription("Curious and playful");
        pet4.setGender(GenderEnum.FEMALE);
        pet4.setSize(SizeEnum.SMALL);
        pet4.setAge(1);
        pet4.setType("Cat");
        pet4.setBreed("Tabby");
        pet4.setImage("https://www.example.com/pet4.jpg");

        List<Pet> expectedList = new ArrayList<>();
        expectedList.add(pet1);
        expectedList.add(pet2);
        expectedList.add(pet4);

        when(petsDao.getPetsByStatus(AvailEnum.AVAILABLE)).thenReturn(expectedList);

        List<Pet> retrievedList = petService.getAvailablePets();

        verify(petsDao).getPetsByStatus(AvailEnum.AVAILABLE);

        assertEquals(expectedList, retrievedList);

    }

    //get list of pet via type
    @Test
    void getListOfSpecificType() {
        Pet pet1 = new Pet();
        pet1.setPetName("Max");
        pet1.setAvailability(AvailEnum.AVAILABLE);
        pet1.setDescription("Friendly and playful");
        pet1.setGender(GenderEnum.MALE);
        pet1.setSize(SizeEnum.MEDIUM);
        pet1.setAge(2);
        pet1.setType("Dog");
        pet1.setBreed("Golden Retriever");
        pet1.setImage("https://www.example.com/pet1.jpg");

        Pet pet2 = new Pet();
        pet2.setPetName("Luna");
        pet2.setAvailability(AvailEnum.AVAILABLE);
        pet2.setDescription("Sweet and affectionate");
        pet2.setGender(GenderEnum.FEMALE);
        pet2.setSize(SizeEnum.SMALL);
        pet2.setAge(1);
        pet2.setType("Cat");
        pet2.setBreed("Siamese");
        pet2.setImage("https://www.example.com/pet2.jpg");

        Pet pet3 = new Pet();
        pet3.setPetName("Rocky");
        pet3.setAvailability(AvailEnum.ADOPTED);
        pet3.setDescription("Energetic and loyal");
        pet3.setGender(GenderEnum.MALE);
        pet3.setSize(SizeEnum.LARGE);
        pet3.setAge(3);
        pet3.setType("Dog");
        pet3.setBreed("German Shepherd");
        pet3.setImage("https://www.example.com/pet3.jpg");

        Pet pet4 = new Pet();
        pet4.setPetName("Whiskers");
        pet4.setAvailability(AvailEnum.AVAILABLE);
        pet4.setDescription("Curious and playful");
        pet4.setGender(GenderEnum.FEMALE);
        pet4.setSize(SizeEnum.SMALL);
        pet4.setAge(1);
        pet4.setType("Cat");
        pet4.setBreed("Tabby");
        pet4.setImage("https://www.example.com/pet4.jpg");

        List<Pet> expectedList = new ArrayList<>();
        expectedList.add(pet2);
        expectedList.add(pet4);

        when(petsDao.getPetsByType("Dog")).thenReturn(expectedList);

        List<Pet> retrievedList = petService.findPetsByType("Dog");

        //verifies that the method has actually been performed
        verify(petsDao).getPetsByType("Dog");

        assertEquals(expectedList, retrievedList);

    }

    //deletes a pet
    @Test
        public void deletePetTest() {
            Pet petToDelete = new Pet();
            petToDelete.setPetName("Greg");
            petToDelete.setType("Dog");
            petToDelete.setAge(2);
            petToDelete.setBreed("Golden Lab");
            petToDelete.setSize(SizeEnum.MEDIUM);
            petToDelete.setGender(GenderEnum.MALE);
            petToDelete.setAvailability(AvailEnum.AVAILABLE);
            petToDelete.setDescription("A funky little dude.");
            petToDelete.setImage("dsghdsgdsgdsgsd.jpeg");

            //adds pet first to dao
            petService.addPet(petToDelete);
            //then deletes pet
            petService.deletePet(petToDelete);
            
            //mockito verifies the pet has been deleted by getting pet id
            verify(petsDao).deletePetById(petToDelete.getPetID());
            assertNull(petService.findPetById(petToDelete.getPetID()));
        }

        //updates an existing pet details
        @Test
        public void updatePetDetailsTest() {
            Pet existingPet = new Pet();
            int petId = 1;
            existingPet.setPetName("Greg");
            existingPet.setPetID(petId);
            existingPet.setType("Dog");
            existingPet.setAge(2);
            existingPet.setBreed("Golden Lab");
            existingPet.setSize(SizeEnum.MEDIUM);
            existingPet.setGender(GenderEnum.MALE);
            existingPet.setAvailability(AvailEnum.AVAILABLE);
            existingPet.setDescription("A funky little dude.");
            existingPet.setImage("dsghdsgdsgdsgsd.jpeg");

            when(petsDao.save(existingPet)).thenReturn(existingPet);
            petService.addPet(existingPet);

            Pet updatedPet = new Pet();
            updatedPet.setPetName("Jasper");
            updatedPet.setType("Dog");
            updatedPet.setAge(3);
            updatedPet.setPetID(petId);
            updatedPet.setBreed("Black Lab");
            updatedPet.setSize(SizeEnum.MEDIUM);
            updatedPet.setGender(GenderEnum.MALE);
            updatedPet.setAvailability(AvailEnum.AVAILABLE);
            updatedPet.setDescription("A funky little dude");
            updatedPet.setImage("dsghdsgdsgdsgsd.jpeg");

            Pet updatedObject = petService.updatePetDetails(updatedPet);

            verify(petsDao).updateAllPetDetails("Jasper", AvailEnum.AVAILABLE, "A funky little dude", GenderEnum.MALE, SizeEnum.MEDIUM, 3, "Dog", "Black Lab", "dsghdsgdsgdsgsd.jpeg", 1);

            assertNotEquals(updatedObject, existingPet);

        }


}
