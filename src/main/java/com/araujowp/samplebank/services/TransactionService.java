package com.araujowp.samplebank.services;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;

import com.araujowp.samplebank.domain.transaction.Transaction;
import com.araujowp.samplebank.domain.user.User;
import com.araujowp.samplebank.dtos.TransactionDTO;
import com.araujowp.samplebank.repositories.TransactionRepository;

@Service
public class TransactionService {

	@Autowired
	private TransactionRepository transactionRepository;
	
	@Autowired
	private NotificationService notificationService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@PostMapping 
	public Transaction createTransaction(TransactionDTO transactionDTO) throws Exception {
		User sender  = userService.fingById(transactionDTO.getSenderId());
		User receiver  = userService.fingById(transactionDTO.getReceiverId());
		
		userService.validationTransacion(sender, transactionDTO.getValue());
		
		boolean isAuthorized = this.authorizeTransaction(sender, transactionDTO.getValue());
		if(!isAuthorized) {
			throw new Exception("Transação não autorizada");
		}
		
		Transaction newTransaction = new Transaction();
		newTransaction.setAmount(transactionDTO.getValue());
		newTransaction.setSender(sender);
		newTransaction.setReceiver(receiver);
		newTransaction.setTimestamp(LocalDateTime.now());
		
		sender.setBalance(sender.getBalance().subtract(transactionDTO.getValue()));
		receiver.setBalance(receiver.getBalance().add(newTransaction.getAmount()));
		transactionRepository.save(newTransaction);
		this.userService.saveUser(sender);
		this.userService.saveUser(receiver);
		
		this.notificationService.sendNotification(sender, "Transacao realizada com sucesso!");
		this.notificationService.sendNotification(receiver, "Transacao recebida com sucesso!");
		
		return newTransaction;
	}
	
	public boolean authorizeTransaction(User sender, BigDecimal value) {
		
		try {
			
			ResponseEntity<Map> authorizationResponse = restTemplate.getForEntity("https://run.mocky.io/v3/8fafdd68-a090-496f-8c9a-3442cf30dae6", Map.class);
			
			if(authorizationResponse.getStatusCode() == HttpStatus.OK) {
				String message = (String) authorizationResponse.getBody().get("message");
				return "Autorizado".equalsIgnoreCase(message);
			}else {
				return false;
			}
		} catch (Exception e) {
			System.out.println("serviço de autorização inoperante !");
			return true;
		}
	}
}
