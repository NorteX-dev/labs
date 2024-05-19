package com.nortexdev.lab5.annotations;

public interface Validator<T> {
	void validate(T value);

	boolean isValid();

	String getMessage();
}
