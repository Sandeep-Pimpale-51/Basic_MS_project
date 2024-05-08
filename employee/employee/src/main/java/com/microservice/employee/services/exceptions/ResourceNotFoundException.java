package com.microservice.employee.services.exceptions;

@SuppressWarnings("serial")
public class ResourceNotFoundException extends RuntimeException {

	public ResourceNotFoundException() {
		super("Resource not Found on server !!!");
	}

	public ResourceNotFoundException(String message) {

		super(message);
	}
}
