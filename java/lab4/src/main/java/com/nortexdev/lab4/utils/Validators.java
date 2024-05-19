package com.nortexdev.lab4.utils;

public class Validators {
	public static boolean validateInt(String value) {
		try {
			Integer.parseInt(value);
			return true;
		} catch (NumberFormatException e) {
			return false;

		}
	}

	public static boolean validateDouble(String value) {
		try {
			Double.parseDouble(value);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	public static boolean validateShort(String value) {
		try {
			Short.parseShort(value);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	public static boolean validateLong(String value) {
		try {
			Long.parseLong(value);
			return true;
		} catch (NumberFormatException e) {
			return false;

		}
	}

	public static boolean validateFloat(String value) {
		try {
			Float.parseFloat(value);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	public static boolean validateBytes(String value) {
		try {
			Byte.parseByte(value);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
}
