package com.tss.service;

import com.tss.dao.UserDAO;
import com.tss.exception.ApplicationException;
import com.tss.model.User;
import com.tss.util.PasswordEncoder;

public class UserService {
	private final UserDAO userDAO = new UserDAO();

	/**
	 * Authenticate user during login
	 */
	public User login(String username, String password) {
		if (username == null || password == null || username.trim().isEmpty()) {
			throw new ApplicationException("Username and password are required.");
		}

		User user = userDAO.findByUsername(username.trim());
		if (user == null) {
			throw new ApplicationException("Invalid username or password.");
		}

		if (!PasswordEncoder.matches(password, user.getPasswordHash())) {
			throw new ApplicationException("Invalid username or password.");
		}

		return user;
	}

	/**
	 * Register a new customer
	 */
	public void registerCustomer(User user) {
		// ✅ 1. Validate input
		validateUserInput(user);

		// ✅ 2. Business Rule: Username must be unique
		if (userDAO.usernameExists(user.getUsername())) {
			throw new ApplicationException("Username '" + user.getUsername() + "' is already taken.");
		}

		// ✅ 3. Hash password (security logic belongs in service)
		// The passwordHash field contains the raw password at this point
		String hashedPassword = PasswordEncoder.hash(user.getPasswordHash());

		// ✅ 4. Set role and hashed password
		user.setRole("Customer");
		user.setPasswordHash(hashedPassword);

		// ✅ 5. Only after all validation → go to DAO
		userDAO.insertUser(user);
	}

	private void validateUserInput(User user) {
		if (user == null) {
			throw new ApplicationException("User data is missing.");
		}
		if (user.getUsername() == null || user.getUsername().trim().length() < 4) {
			throw new ApplicationException("Username must be at least 4 characters.");
		}
		// Check raw password length (before hashing)
		if (user.getPasswordHash() == null || user.getPasswordHash().length() < 6) {
			throw new ApplicationException("Password must be at least 6 characters.");
		}
		if (user.getFullName() == null || user.getFullName().trim().isEmpty()) {
			throw new ApplicationException("Full name is required.");
		}
		if (user.getEmail() == null || !user.getEmail().contains("@")) {
			throw new ApplicationException("Valid email is required.");
		}
		if (user.getPhone() == null || !user.getPhone().matches("\\d{10,15}")) {
			throw new ApplicationException("Phone must be 10-15 digits.");
		}
	}
}