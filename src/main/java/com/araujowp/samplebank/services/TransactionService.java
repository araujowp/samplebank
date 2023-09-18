package com.araujowp.samplebank.services;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.araujowp.samplebank.domain.user.Transaction;
import com.araujowp.samplebank.domain.user.User;
import com.araujowp.samplebank.dtos.TransactionDTO;
import com.araujowp.samplebank.repositories.TransactionRepository;

@Service
public class TransactionService {

	@Autowired
	private TransactionRepository transactionRepository;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RestTemplate restTemplate;
	
	 
	public void createTransaction(TransactionDTO transactionDTO) throws Exception {
		User sender  = userService.fingById(transactionDTO.getSenderId());
		User receiver  = userService.fingById(transactionDTO.getReceiverId());
		
		userService.validationTransacion(sender, transactionDTO.getAmount());
		
		boolean isAuthorized = this.authorizeTransaction(sender, transactionDTO.getAmount());
		if(!isAuthorized) {
			throw new Exception("Transação não autorizada");
		}
		
		Transaction transaction = new Transaction();
		transaction.setAmount(transactionDTO.getAmount());
		transaction.setSender(sender);
		transaction.setReceiver(receiver);
		transaction.setTimestamp(LocalDateTime.now());
		
		sender.setBalance(sender.getBalance().subtract(transactionDTO.getAmount()));
		receiver.setBalance(receiver.getBalance().add(transaction.getAmount()));
		transactionRepository.save(transaction);
		this.userService.saveUser(sender);
		this.userService.saveUser(receiver);
		
	}
	
	public boolean authorizeTransaction(User sender, BigDecimal value) {
		
		ResponseEntity<Map> authorizationResponse = restTemplate.getForEntity("https://run.mocky.io/v3/8fafdd68-a090-496f-8c9a-3442cf30dae6", Map.class);
		
		if(authorizationResponse.getStatusCode() == HttpStatus.OK) {
			String message = (String) authorizationResponse.getBody().get("message");
			return "Autorizado".equalsIgnoreCase(message);
		}else {
			return false;
		}
			
			
	}
}
