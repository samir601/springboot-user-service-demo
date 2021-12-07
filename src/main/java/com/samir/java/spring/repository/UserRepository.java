package com.samir.java.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.samir.java.spring.model.User;



@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	
	

}
