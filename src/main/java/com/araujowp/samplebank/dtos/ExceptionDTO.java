package com.araujowp.samplebank.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ExceptionDTO {

	private String message;
	private String statusCode;
}
