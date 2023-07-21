package com.pawsomepets.model.dao;

import com.pawsomepets.dto.AvailEnum;
import com.pawsomepets.dto.GenderEnum;
import com.pawsomepets.dto.Pet;
import com.pawsomepets.dto.SizeEnum;
import org.hibernate.engine.jdbc.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/*
+ searchByBreed(String breed): List<Pet>
+ searchByType(String type): List<Pet>

+ searchPetById(Int id): Pet object

+ searchPetByGender(String gender): List<Pet>

+ searchPetBySize(Enum size): List<Pet>

+ searchPetByAgeRange(String range?)

+ searchPetByAvailability(String avail): List<Pet>

+ getAllPets(): List<Pet>

+ getAllAvailablePets() = List<Pet>

+ deletePetById(Int id): void idk

+ updatePetAvailability: Pet

+ updatePetDetails: Pet
 */

@Repository
public interface PetsDao extends JpaRepository<Pet, Integer> {

    @Query("FROM Pet p WHERE p.petID=:id")
    Pet getPetByPetID(@Param("id") int petID);

    @Query("FROM Pet p WHERE p.type=:type")
    List<Pet> getPetsByType(@Param("type")String type);

    @Query("From Pet p WHERE p.gender=:gender")
    List<Pet> getPetsByGender(@Param("gender")GenderEnum gender);

    @Query("FROM Pet p WHERE p.size=:size")
    List<Pet> getPetsBySize(@Param("size")SizeEnum size);

    @Query("FROM Pet p WHERE p.availability=:availability")
    List<Pet> getAllAvailable(@Param("availability")AvailEnum availability);

    //only updates AVAILABILTIY
    @Modifying //use when performing data modification
    @Query("UPDATE Pet SET availability=:newAvail WHERE petID=:id")
    void updatePetAvailability(int id, AvailEnum availability);

    //updates ALL other details except availability
    @Modifying
    @Query("UPDATE Pet SET petName=:name,availability=:available, description=:descript, gender=:gender, size=:size, age=:age, type=:type, breed=:breed, image=:img WHERE petID=:id")
    Pet updateAllPetDetails(@Param("name")String name, @Param("available")AvailEnum available, @Param("descript")String description, @Param("gender")GenderEnum gender, @Param("size")SizeEnum size, @Param("age")int age, @Param("type")String type, @Param("breed")String breed, @Param("img")String image, @Param("id")int id);

    @Modifying
    @Query("DELETE FROM Pet p WHERE p.petID=:id")
    void deletePetById(@Param("id")int id);

}
