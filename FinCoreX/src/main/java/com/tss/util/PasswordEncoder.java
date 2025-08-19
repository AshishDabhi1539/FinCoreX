package com.tss.util;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordEncoder {
    public static String hash(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public static boolean matches(String rawPassword, String hashed) {
        return BCrypt.checkpw(rawPassword, hashed);
    }

    /*
    public static void main(String[] args) {
        String rawPassword = "admin123";

        // Hash the password
        String hashedPassword = PasswordEncoder.hash(rawPassword);
        System.out.println("Hashed Password: " + hashedPassword);

        // Verify password
        boolean isMatch = PasswordEncoder.matches(rawPassword, hashedPassword);
        System.out.println("Password matches: " + isMatch);

        // Wrong password test
        boolean isMatchWrong = PasswordEncoder.matches("wrongPassword", hashedPassword);
        System.out.println("Wrong password matches: " + isMatchWrong);
    }*/
}
