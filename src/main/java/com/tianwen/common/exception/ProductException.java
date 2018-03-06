package com.tianwen.common.exception;

public class ProductException extends RuntimeException {
	
	private String message;
	
	public ProductException(String message) {
		super(message);
		System.out.println(message);
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
