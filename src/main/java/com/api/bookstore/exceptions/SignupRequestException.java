package com.api.bookstore.exceptions;

public class SignupRequestException extends RuntimeException {

	private static final long serialVersionUID = 4827405629994631422L;

	public SignupRequestException(String msg) {
		super(msg);
	}
}
