package com.nortexdev.javalab5errs.exceptions;

public class PasswordValidationException extends ValidationException {
	public PasswordValidationException(String message) {
		super(message);
	}

	public static int getMinAllowedLength() {
		return 8;
	}
}
