package com.araujowp.samplebank.dtos;

import java.math.BigDecimal;

import com.araujowp.samplebank.domain.user.UserType;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserDTO {

	private String firtName;
	private String lastName;
	private String document;
	private BigDecimal balance;
	private UserType UserType;
	private String email;
	private String passWord;
	
}
