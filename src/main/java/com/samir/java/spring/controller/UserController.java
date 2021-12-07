package com.samir.java.spring.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.samir.java.spring.model.User;
import com.samir.java.spring.service.UserService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class UserController {
	
	@Autowired
	UserService userService;
	
	@PostMapping("/v1/user")
	public ResponseEntity<Object> registerUser( @RequestBody User user) {
		
		log.info("registor user request received");
		
		ResponseEntity<Object> response = null;
		
		boolean result = userService.registerUser(user);
		
		if(result) {
			 response = new ResponseEntity<>(user, HttpStatus.CREATED);
		}
		else {
			response =  new ResponseEntity<Object>("User registration failed", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return response;
	}
	
	
	@GetMapping("/v1/user")
	public ResponseEntity<Object> getAllregisterdUsers(){
	
		ResponseEntity<Object> response = null;
		List<User> registeredUsers = userService.getAllRegisteredUsers();
		
		if(registeredUsers != null && registeredUsers.size() >0) {
			response = new ResponseEntity<Object>(registeredUsers, HttpStatus.ACCEPTED);
		}
		else
		{
			response = new ResponseEntity<Object>("No registered user found", HttpStatus.NO_CONTENT);
			
		}
		
		return response;
		
	}

	
	@GetMapping(value={"/v1/user/{userId}"})
	public ResponseEntity<Object> getRegisterdUserById(@PathVariable("userId") Integer userdId){
	
		
		log.info("Retrieve user for user_id={}", userdId);
		ResponseEntity<Object> response = null;
		Optional<User> registeredUser = userService.getRegistredUserById(userdId);
		
		if(registeredUser == null || registeredUser.isEmpty()) {
			response = new ResponseEntity<Object>("No registered user found with id", HttpStatus.NO_CONTENT);
			
		}
		else
		{
			response = new ResponseEntity<Object>(registeredUser, HttpStatus.ACCEPTED);
			
		}
		
		return response;
		
	}
}
