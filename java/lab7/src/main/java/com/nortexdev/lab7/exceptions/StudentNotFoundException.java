package com.nortexdev.lab7.exceptions;

import org.springframework.http.HttpStatusCode;
import org.springframework.web.server.ResponseStatusException;

public class StudentNotFoundException extends ResponseStatusException {
	public StudentNotFoundException(HttpStatusCode status, String reason) {
		super(status, reason);
	}
}
