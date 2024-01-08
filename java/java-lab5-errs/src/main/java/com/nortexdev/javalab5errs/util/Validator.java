package com.nortexdev.javalab5errs.util;

import com.nortexdev.javalab5errs.exceptions.EmailValidationException;
import com.nortexdev.javalab5errs.exceptions.PasswordValidationException;

public class Validator {
	public static void validateEmailOrThrow(String email) throws EmailValidationException {
		if (email == null || email.isEmpty()) {
			throw new EmailValidationException("Email is empty.");
		}
		if (!email.contains("@")) {
			throw new EmailValidationException("Email is invalid.");
		}
	}

	public static void validatePasswordOrThrow(String password) throws PasswordValidationException {
		if (password == null || password.isEmpty()) {
			throw new PasswordValidationException("Password is empty.");
		}
		if (password.length() < 8) {
			throw new PasswordValidationException("Password is too short. Minimum allowed length is " + PasswordValidationException.getMinAllowedLength());
		}
	}
}
