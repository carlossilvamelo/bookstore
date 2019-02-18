package com.api.bookstore.exceptions;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ResourceExceptionHandler {
	static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	
	@ExceptionHandler
	public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request){
		HttpStatus status = HttpStatus.NOT_FOUND;
		
		StandardError standardError = new StandardError(LocalDateTime.now().format(FORMATTER),
				status.value(),
				"Resource not found",
				e.getMessage(),
				request.getRequestURI());
		
		return ResponseEntity.status(status).body(standardError);
	}
	
	@ExceptionHandler
	public ResponseEntity<StandardError> tokenNotFound(TokenNotFoundException e, HttpServletRequest request){
		HttpStatus status = HttpStatus.UNAUTHORIZED;
		
		StandardError standardError = new StandardError(LocalDateTime.now().format(FORMATTER),
				status.value(),
				"Token not found",
				e.getMessage(),
				request.getRequestURI());
		
		return ResponseEntity.status(status).body(standardError);
	}
	
	@ExceptionHandler
	public ResponseEntity<StandardError> generalExceptions(Exception e, HttpServletRequest request){
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		StandardError standardError = new StandardError(LocalDateTime.now().format(FORMATTER),
				status.value(),
				"Something went wrong.",
				e.getMessage(),
				request.getRequestURI());
		return ResponseEntity.status(status).body(standardError);
	}
	
}
