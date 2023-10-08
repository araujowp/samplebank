package com.araujowp.samplebank.controollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.araujowp.samplebank.domain.transaction.Transaction;
import com.araujowp.samplebank.dtos.TransactionDTO;
import com.araujowp.samplebank.services.TransactionService;

@RestController()
@RequestMapping("/transactions")
public class TransactionController {

	@Autowired
	TransactionService transactionService;
	
	@PostMapping
	public ResponseEntity<Transaction>createTransaction (@RequestBody TransactionDTO transaction) throws Exception{
		Transaction newTransaction = this.transactionService.createTransaction(transaction);
		return new ResponseEntity<>(newTransaction,HttpStatus.CREATED);
	}	
}
