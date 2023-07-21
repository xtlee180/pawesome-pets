package com.user.dao;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.user.dto.User;

@Transactional
@Repository
public interface UserDao extends JpaRepository<User, Integer> {
	
	//get by username and password
	@Query("from User where userName=:un and userPassword=:pw")
	User getByUsernameAndPassword(@Param("un") String UN,@Param("pw") String PW);
	
	//get by username
	@Query("from User where userName=:un")
	User getbyUserName(@Param("un")String username);
	
	//edit user profile information
	@Modifying
	@Query(nativeQuery = true, value = "UPDATE USER SET FIRSTNAME=?,LASTNAME=?,EMAIL=?,PHONENUMBER=? WHERE USERID=?")
	int  updateUserProfileInfo(String fName, String lName, String email, String phone, int userID);
	
	//edit username and password
	@Modifying
	@Query(nativeQuery = true, value = "UPDATE USER SET USERNAME=?,USERPASSWORD=? WHERE USERID=?")
	int updateUsernameAndPassword(String username, String password,int userID);
	
	//edit user type
	@Modifying
	@Query(nativeQuery = true, value = "UPDATE USER SET USERTYPE=? WHERE USERID=?")
	int updateUserType(String userType, int userID);
	
	//insert user
	@Modifying
	@Query(nativeQuery = true, value = "INSERT INTO USER (USERNAME,USERPASSWORD,USERTYPE,FIRSTNAME,LASTNAME,EMAIL,PHONENUMBER) VALUES(?,?,?,?,?,?,?)")
	int insertUser(String username,String password,String userType,String fName, String lName,String email,String phone);
}
