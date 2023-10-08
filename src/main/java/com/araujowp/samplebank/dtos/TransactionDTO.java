package com.araujowp.samplebank.dtos;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransactionDTO {

	private BigDecimal value;
	private long senderId;
	private long receiverId;
	
}
