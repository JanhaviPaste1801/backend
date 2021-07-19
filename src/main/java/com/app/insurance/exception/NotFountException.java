package com.app.insurance.exception;

public class NotFountException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public NotFountException() {
		
	}
	
	public NotFountException(String message) {
		super(message);
	}
}
