package com.tss.model;

public class InputValidator {

	
	public static boolean validateUsername(String username) {
		validateValues(username);
		return username.length() >= 3 && username.length() <= 20;
	}

	public static boolean validatePassword(String password) {
		validateValues(password);
		return password.length() >= 8 && password.length() <= 30;
	}

	public static boolean validateEmail(String email) {
		validateValues(email);
		return email.length() >= 5 && email.length() <= 50 && email.contains("@") && email.contains(".");
	}

	public static boolean validateValues(String input) {
		 return input != null && !input.isEmpty();
	}
}
