package com.vacation.backend.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@AllArgsConstructor
public class BadRequestException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	private HttpStatus status;
	private String message;
	
}
