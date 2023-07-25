package com.pets.model.dao;

import com.pets.dto.AvailEnum;
import com.pets.dto.GenderEnum;
import com.pets.dto.Pet;
import com.pets.dto.SizeEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;
import java.util.List;

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

    //for any status
    @Query("FROM Pet p WHERE p.availability=:availability")
    List<Pet> getPetsByStatus(@Param("availability")AvailEnum availability);

    //specifically for pagination = all available pets
    @Query("FROM Pet p where p.availability=:availability")
    Page<Pet> findAvailablePets(@Param("availability") AvailEnum availability, Pageable pageable);

    //only updates AVAILABILTIY
    @Modifying //use when performing data modification
    @Query("UPDATE Pet SET availability=:newAvail WHERE petID=:id")
    void updatePetAvailability(@Param("id") int id, @Param("newAvail") AvailEnum availability);

    //updates ALL other details except availability
    @Modifying
    @Query("UPDATE Pet SET petName=:name,availability=:available, description=:descript, gender=:gender, size=:size, age=:age, type=:type, breed=:breed, image=:img WHERE petID=:id")
    Pet updateAllPetDetails(@Param("name")String name, @Param("available")AvailEnum available, @Param("descript")String description, @Param("gender")GenderEnum gender, @Param("size")SizeEnum size, @Param("age")int age, @Param("type")String type, @Param("breed")String breed, @Param("img")String image, @Param("id")int id);

    @Modifying
    @Query("DELETE FROM Pet p WHERE p.petID=:id")
    void deletePetById(@Param("id")int id);

}
