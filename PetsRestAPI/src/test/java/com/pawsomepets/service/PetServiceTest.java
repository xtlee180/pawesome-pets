package com.pawsomepets.service;


import com.pawsomepets.dto.AvailEnum;
import com.pawsomepets.dto.GenderEnum;
import com.pawsomepets.dto.Pet;
import com.pawsomepets.dto.SizeEnum;
import com.pawsomepets.model.dao.PetsDao;
import com.pawsomepets.model.service.PetServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;


import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class PetServiceTest {
    @Mock
    private PetsDao petsDao; //mocked dependency
    
    @InjectMocks
    private PetServiceImpl petService; //class being tested w/ mocked dependency

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
        Mockito.when(petsDao.save(testPet)).thenReturn(testPet);

        //test via calling method in service layer
        Pet savedPet = petService.addPet(testPet);

        Mockito.verify(petsDao).save(testPet);
        assertEquals(testPet, savedPet);
    }

    @Test
    public void findByIdTest() {
    }



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

        Pet updatedPet = new Pet();
        updatedPet.setPetName("Jasper");
        updatedPet.setType("Dog");
        updatedPet.setAge(2);
        updatedPet.setPetID(petId);
        updatedPet.setBreed("Black Lab");
        updatedPet.setSize(SizeEnum.MEDIUM);
        updatedPet.setGender(GenderEnum.MALE);
        updatedPet.setAvailability(AvailEnum.AVAILABLE);
        updatedPet.setDescription("A funky little dude.");
        updatedPet.setImage("dsghdsgdsgdsgsd.jpeg");

        Mockito.when(petsDao.getPetByPetID(petId)).thenReturn(existingPet);

        Pet updatedObject = petService.updatePetDetails(updatedPet);

        Mockito.verify(petsDao).save(updatedPet);
        assertEquals(updatedPet, updatedObject);


    }
}
