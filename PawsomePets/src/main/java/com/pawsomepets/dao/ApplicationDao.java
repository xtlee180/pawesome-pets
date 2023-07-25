package com.pawsomepets.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.pawsomepets.dto.Application;

@Repository
public interface ApplicationDao extends JpaRepository<Application, Integer> {
	
	@Query("from Application where petID=:pet")
	List<Application> findPetByID(@Param("pet") int petId);
	
}
