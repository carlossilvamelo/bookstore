package com.api.bookstore.exceptions;

public class BookOrderedException extends RuntimeException{

	private static final long serialVersionUID = -405742015369659793L;

	public BookOrderedException(String msg) {
		super(msg);
	}
}
