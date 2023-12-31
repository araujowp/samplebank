package com.araujowp.samplebank.services;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.araujowp.samplebank.domain.user.User;
import com.araujowp.samplebank.domain.user.UserType;
import com.araujowp.samplebank.dtos.UserDTO;
import com.araujowp.samplebank.repositories.UserRepository;

import javassist.NotFoundException;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	public void validationTransacion(User sender, BigDecimal amount) throws Exception {
		if(sender.getUserType() == UserType.MERCHANT) {
			throw new Exception("Lojistas não podem fazer transações");
		}
		
		if(sender.getBalance().compareTo(amount) < 0) {
			throw new Exception("Saldo insuficiente");
		}
	}
	
	public User fingById(long id) throws Exception{
		return this.userRepository.findById(id).orElseThrow(()-> new Exception("usuario não encontrado"));
	}

	public void saveUser(User user) {
		this.userRepository.save(user);
	}

	public User createUser(UserDTO data) {
		User newUser = new User(data);
		this.saveUser(newUser);
		return newUser; 
	}
	
	public List<User> getAllUsers(){
		return this.userRepository.findAll();
	}
	
}
