package com.api.bookstore.exceptions;

public class TokenNotValidException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	public TokenNotValidException(String msg) {
		super(msg);
	}

}
