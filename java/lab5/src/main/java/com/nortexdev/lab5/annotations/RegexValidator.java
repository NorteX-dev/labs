package com.nortexdev.lab5.annotations;

public class RegexValidator implements Validator<String> {
	private final Regex regexAnnotation;
	private boolean isValid = false;
	private String message = "";

	public RegexValidator(Regex regexAnnotation) {
		this.regexAnnotation = regexAnnotation;
	}

	@Override
	public void validate(String value) {
		if (!value.matches(regexAnnotation.regex())) {
			isValid = false;
			message = regexAnnotation.message();
		} else {
			isValid = true;
			message = null;
		}
	}

	@Override
	public boolean isValid() {
		return isValid;
	}

	@Override
	public String getMessage() {
		return message;
	}
}
