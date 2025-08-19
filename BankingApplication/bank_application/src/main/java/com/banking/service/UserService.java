package com.banking.service;

import java.util.ArrayList;
import java.util.List;

import com.banking.dao.UserDao;
import com.banking.model.User;
import com.banking.util.ValidationUtil;

public class UserService {
	private UserDao userDao = new UserDao();

	public List<String> validateAndRegister(User user, String confirmPassword) {
		List<String> errors = new ArrayList<>();

		if (!ValidationUtil.isValidFullName(user.getFullName()))
			errors.add("Invalid Full Name.");
		if (!ValidationUtil.isValidUsername(user.getUsername()))
			errors.add("Invalid Username.");
		if (userDao.isUsernameExists(user.getUsername()))
			errors.add("Username already exists.");
		if (!ValidationUtil.isValidEmail(user.getEmail()))
			errors.add("Invalid Email.");
		if (userDao.isEmailExists(user.getEmail()))
			errors.add("Email already registered.");
		if (!ValidationUtil.isValidPhone(user.getPhone()))
			errors.add("Invalid Phone.");
		if (!ValidationUtil.isValidDOB(user.getDob()))
			errors.add("You must be 18+ years old.");
		if (!ValidationUtil.isValidGender(user.getGender()))
			errors.add("Invalid Gender.");
		if (!ValidationUtil.isValidAddress(user.getAddress()))
			errors.add("Address must be at least 10 characters.");
		if (!ValidationUtil.isValidAadhaar(user.getAadhaar()))
			errors.add("Invalid Aadhaar.");
		if (!ValidationUtil.isValidPAN(user.getPan()))
			errors.add("Invalid PAN.");
		if (!ValidationUtil.isValidAccountType(user.getAccountType()))
			errors.add("Invalid Account Type.");
		if (!ValidationUtil.isValidDeposit(String.valueOf(user.getDeposit())))
			errors.add("Deposit must be at least 1000.");
		if (!ValidationUtil.isValidPassword(user.getPasswordHash()))
			errors.add("Password must be 8–20 chars with uppercase, lowercase, number & special char.");
		if (!ValidationUtil.doPasswordsMatch(user.getPasswordHash(), confirmPassword))
			errors.add("Passwords do not match.");

		

		if (errors.isEmpty()) {
			boolean saved = userDao.saveUser(user);
			if (!saved)
				errors.add("Error saving user. Try again later.");
		}

		return errors;
	}

	public List<User> getPendingUsers() {
	    return userDao.getUsersByStatus("PENDING");
	}

	public void updateUserStatus(long userId, String status) {
	    userDao.updateUserStatus(userId, status);
	}

	public User login(String username, String password) {
		return userDao.login(username, password);
	}
}
