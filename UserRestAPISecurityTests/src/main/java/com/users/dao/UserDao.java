package com.users.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.users.dto.User;

@Repository
public interface UserDao extends JpaRepository<User, Integer>{
	
	@Query("from User where userName=:id")
	User findByUsername(@Param("id") String userName);
}
