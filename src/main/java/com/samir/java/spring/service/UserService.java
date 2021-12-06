package com.samir.java.spring.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.samir.java.spring.model.User;
import com.samir.java.spring.repository.UserRepository;


@Service
public class UserService {
	
	@Autowired
	UserRepository userRepository;
	
	public boolean registerUser(User user) {
		
		try {
			userRepository.save(user);	
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
		
	}
	
	public List<User> getAllRegisteredUsers(){
		
		try {
			return userRepository.findAll();
		}
		catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	public Optional<User> getRegistredUserById(int userId){
		
		try {
			return userRepository.findById(userId);
		}
		catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}


}
