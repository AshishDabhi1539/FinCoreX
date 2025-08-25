package com.banking.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.regex.Pattern;

public class SecurityUtil {
    
    private static final String HASH_ALGORITHM = "SHA-256";
    private static final String SALT_ALGORITHM = "SHA1PRNG";
    private static final int SALT_LENGTH = 16;
    
    // Password validation patterns
    private static final Pattern PASSWORD_PATTERN = Pattern.compile(
        "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,20}$"
    );
    
    private static final Pattern USERNAME_PATTERN = Pattern.compile("^[a-zA-Z0-9_]{3,20}$");
    private static final Pattern EMAIL_PATTERN = Pattern.compile(
        "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$"
    );
    
    /**
     * Hash a password with salt
     */
    public static String hashPassword(String password) {
        try {
            // Generate salt
            SecureRandom random = SecureRandom.getInstance(SALT_ALGORITHM);
            byte[] salt = new byte[SALT_LENGTH];
            random.nextBytes(salt);
            
            // Hash password with salt
            MessageDigest md = MessageDigest.getInstance(HASH_ALGORITHM);
            md.update(salt);
            byte[] hashedPassword = md.digest(password.getBytes());
            
            // Combine salt and hash
            byte[] combined = new byte[salt.length + hashedPassword.length];
            System.arraycopy(salt, 0, combined, 0, salt.length);
            System.arraycopy(hashedPassword, 0, combined, salt.length, hashedPassword.length);
            
            return Base64.getEncoder().encodeToString(combined);
            
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error hashing password", e);
        }
    }
    
    /**
     * Verify a password against a stored hash
     */
    public static boolean verifyPassword(String password, String storedHash) {
        try {
            // Decode stored hash
            byte[] combined = Base64.getDecoder().decode(storedHash);
            
            // Extract salt
            byte[] salt = new byte[SALT_LENGTH];
            System.arraycopy(combined, 0, salt, 0, SALT_LENGTH);
            
            // Hash input password with same salt
            MessageDigest md = MessageDigest.getInstance(HASH_ALGORITHM);
            md.update(salt);
            byte[] hashedPassword = md.digest(password.getBytes());
            
            // Compare hashes
            byte[] storedHashBytes = new byte[combined.length - SALT_LENGTH];
            System.arraycopy(combined, SALT_LENGTH, storedHashBytes, 0, storedHashBytes.length);
            
            return MessageDigest.isEqual(hashedPassword, storedHashBytes);
            
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error verifying password", e);
        }
    }
    
    /**
     * Validate password strength
     */
    public static boolean isValidPassword(String password) {
        if (password == null || password.length() < 8 || password.length() > 20) {
            return false;
        }
        return PASSWORD_PATTERN.matcher(password).matches();
    }
    
    /**
     * Validate username format
     */
    public static boolean isValidUsername(String username) {
        if (username == null) {
            return false;
        }
        return USERNAME_PATTERN.matcher(username).matches();
    }
    
    /**
     * Validate email format
     */
    public static boolean isValidEmail(String email) {
        if (email == null) {
            return false;
        }
        return EMAIL_PATTERN.matcher(email).matches();
    }
    
    /**
     * Generate a secure random token
     */
    public static String generateToken() {
        SecureRandom random = new SecureRandom();
        byte[] tokenBytes = new byte[32];
        random.nextBytes(tokenBytes);
        return Base64.getUrlEncoder().withoutPadding().encodeToString(tokenBytes);
    }
    
    /**
     * Sanitize input to prevent XSS
     */
    public static String sanitizeInput(String input) {
        if (input == null) {
            return null;
        }
        
        return input.replaceAll("<", "&lt;")
                   .replaceAll(">", "&gt;")
                   .replaceAll("\"", "&quot;")
                   .replaceAll("'", "&#x27;")
                   .replaceAll("&", "&amp;");
    }
    
    /**
     * Validate phone number format (basic validation)
     */
    public static boolean isValidPhone(String phone) {
        if (phone == null) {
            return false;
        }
        // Basic phone validation - 10 digits
        return phone.matches("^\\d{10}$");
    }
    
    /**
     * Validate Aadhaar number format
     */
    public static boolean isValidAadhaar(String aadhaar) {
        if (aadhaar == null) {
            return false;
        }
        // Aadhaar validation - 12 digits
        return aadhaar.matches("^\\d{12}$");
    }
    
    /**
     * Validate PAN number format
     */
    public static boolean isValidPAN(String pan) {
        if (pan == null) {
            return false;
        }
        // PAN validation - 10 alphanumeric characters
        return pan.matches("^[A-Z]{5}[0-9]{4}[A-Z]{1}$");
    }
    
    /**
     * Generate account number
     */
    public static String generateAccountNumber() {
        SecureRandom random = new SecureRandom();
        StringBuilder accountNumber = new StringBuilder();
        
        // Generate 12-digit account number
        for (int i = 0; i < 12; i++) {
            accountNumber.append(random.nextInt(10));
        }
        
        return accountNumber.toString();
    }
    
    /**
     * Mask sensitive data for logging
     */
    public static String maskSensitiveData(String data, String type) {
        if (data == null || data.length() < 4) {
            return "****";
        }
        
        switch (type.toLowerCase()) {
            case "password":
                return "****";
            case "email":
                String[] parts = data.split("@");
                if (parts.length == 2) {
                    String username = parts[0];
                    String domain = parts[1];
                    if (username.length() <= 2) {
                        return "**@" + domain;
                    }
                    return username.substring(0, 2) + "***@" + domain;
                }
                return "***@" + data.substring(data.indexOf("@") + 1);
            case "phone":
                return data.substring(0, 2) + "****" + data.substring(data.length() - 2);
            case "aadhaar":
                return data.substring(0, 4) + "****" + data.substring(data.length() - 4);
            case "pan":
                return data.substring(0, 2) + "****" + data.substring(data.length() - 2);
            case "account":
                return "****" + data.substring(data.length() - 4);
            default:
                return "****";
        }
    }
}

