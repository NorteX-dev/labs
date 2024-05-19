package com.nortexdev.lab4;

public class Validators {
	public static boolean validateInt(String value) {
		return value.matches("\\d+");
	}

	public static boolean validateDouble(String value) {
		return value.matches("\\d+(\\.\\d+)?");
	}

	public static boolean validateShort(String value) {
		return value.matches("\\d+");
	}

	public static boolean validateLong(String value) {
		return value.matches("\\d+");
	}

	public static boolean validateFloat(String value) {
		return value.matches("\\d+(\\.\\d+)?");
	}

	public static boolean validateBytes(String value) {
		return value.matches("[01]");
	}

}
