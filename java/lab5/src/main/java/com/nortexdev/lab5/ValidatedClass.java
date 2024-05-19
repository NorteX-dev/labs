package com.nortexdev.lab5;

import com.nortexdev.lab5.annotations.Regex;
import com.nortexdev.lab5.annotations.RegexValidator;

import java.lang.reflect.Field;

public class ValidatedClass {
	@Regex(regex = "[1-9]", message = "Must be a single digit")
	public String field = "";

	public void setField(String value) throws Exception {
		Field field = getClass().getField("field");
		Regex regex = field.getAnnotation(Regex.class);
		RegexValidator validator = new RegexValidator(regex);
		validator.validate(value);
		if (validator.isValid()) {
			this.field = value;
		} else {
			throw new Exception(validator.getMessage());
		}
	}
}
