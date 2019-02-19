package com.api.bookstore.exceptions;

public class ObjectNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 4524368000752090692L;

	public ObjectNotFoundException(String msg) {
		super(msg);
	}
}
