package com.araujowp.samplebank.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.araujowp.samplebank.domain.user.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long>{

}
