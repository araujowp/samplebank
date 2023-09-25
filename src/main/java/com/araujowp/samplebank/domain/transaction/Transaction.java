package com.araujowp.samplebank.domain.transaction;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.araujowp.samplebank.domain.user.User;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name="transactions")
@Table(name="transactions")
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode(of="id")
@NoArgsConstructor
public class Transaction {

	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	private long id;
	private BigDecimal amount;

	@ManyToOne
	@JoinColumn(name="sender_id")
	private User sender;
	
	@ManyToOne
	@JoinColumn(name="receiver_id")
	private User receiver;
	private LocalDateTime timestamp;
	
}
