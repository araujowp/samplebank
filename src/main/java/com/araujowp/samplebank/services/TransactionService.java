package com.araujowp.samplebank.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.araujowp.samplebank.domain.user.User;
import com.araujowp.samplebank.dtos.TransactionDTO;
import com.araujowp.samplebank.repositories.TransactionRepository;

@Service
public class TransactionService {

	@Autowired
	private TransactionRepository transactionRepository;
	
	@Autowired
	private UserService userService;
	
	public void createTransaction(TransactionDTO transactionDTO) throws Exception {
		User sender  = userService.fingById(transactionDTO.getSenderId());
		User receiver  = userService.fingById(transactionDTO.getReceiverId());
		
		userService.validationTransacion(sender, transactionDTO.getAmount());
		
	}
}
