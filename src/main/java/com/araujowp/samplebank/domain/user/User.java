package com.araujowp.samplebank.domain.user;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.araujowp.samplebank.dtos.UserDTO;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "users")
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode(of="id")
@NoArgsConstructor
public class User {
	
	public User(UserDTO data) {
		this.firstName = data.getFirstName();
		this.lastName = data.getLastName();
		this.balance = data.getBalance();
		this.document = data.getDocument();
		this.userType = data.getUserType();
		this.email = data.getEmail();
		this.password = data.getPassword();
	}

	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	private long id;
	private String firstName;
	private String lastName;
	
	@Column(unique=true)
	private String document;

	@Column(unique=true)
	private String email;
	private String password;
	private BigDecimal balance;
	
	@Enumerated(EnumType.STRING)
	private UserType userType; 
	
}
