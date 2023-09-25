package com.araujowp.samplebank.controollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.araujowp.samplebank.domain.user.User;
import com.araujowp.samplebank.dtos.UserDTO;
import com.araujowp.samplebank.services.UserService;

@RestController("/users")
public class UserController {

	@Autowired
	UserService userService;
	
	@PostMapping
	public ResponseEntity createUser(UserDTO user) {
		User newUser  = userService.createUser(user);
		return new ResponseEntity<>(newUser,HttpStatus.CREATED);
	}
}
