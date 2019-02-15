package com.api.bookstore.exceptions;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler
	public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request){
		HttpStatus status = HttpStatus.NOT_FOUND;
		
		StandardError standardError = new StandardError(System.currentTimeMillis(),
				status.value(),
				"Resource not found",
				e.getMessage(),
				request.getRequestURI());
		
		return ResponseEntity.status(status).body(standardError);
	}
	
	@ExceptionHandler
	public ResponseEntity<StandardError> tokenNotFound(TokenNotFoundException e, HttpServletRequest request){
		HttpStatus status = HttpStatus.UNAUTHORIZED;
		
		StandardError standardError = new StandardError(System.currentTimeMillis(),
				status.value(),
				"Token not found",
				e.getMessage(),
				request.getRequestURI());
		
		return ResponseEntity.status(status).body(standardError);
	}
	
}
