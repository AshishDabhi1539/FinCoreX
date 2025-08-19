package com.banking.util;

import java.time.LocalDate;
import java.time.Period;
import java.util.regex.Pattern;

public class ValidationUtil {

    private static final Pattern NAME_PATTERN = Pattern.compile("^[A-Za-z ]{3,50}$");
    private static final Pattern USERNAME_PATTERN = Pattern.compile("^[a-zA-Z0-9_]{4,20}$");
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[\\w._%+-]+@[\\w.-]+\\.[A-Za-z]{2,6}$");
    private static final Pattern PHONE_PATTERN = Pattern.compile("^[6-9][0-9]{9}$");
    private static final Pattern AADHAAR_PATTERN = Pattern.compile("^[2-9]{1}[0-9]{11}$");
    private static final Pattern PAN_PATTERN = Pattern.compile("^[A-Z]{5}[0-9]{4}[A-Z]{1}$");
    private static final Pattern PASSWORD_PATTERN = 
        Pattern.compile("^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@#$%^&+=!]).{8,20}$");

    public static boolean isValidFullName(String name) {
        return name != null && NAME_PATTERN.matcher(name).matches();
    }

    public static boolean isValidUsername(String username) {
        return username != null && USERNAME_PATTERN.matcher(username).matches();
    }

    public static boolean isValidEmail(String email) {
        return email != null && EMAIL_PATTERN.matcher(email).matches();
    }

    public static boolean isValidPhone(String phone) {
        return phone != null && PHONE_PATTERN.matcher(phone).matches();
    }

    public static boolean isValidDOB(LocalDate dob) {
        if (dob == null) return false;
        return Period.between(dob, LocalDate.now()).getYears() >= 18;
    }

    public static boolean isValidGender(String gender) {
        return gender != null && (gender.equals("Male") || gender.equals("Female") || gender.equals("Other"));
    }

    public static boolean isValidAddress(String address) {
        return address != null && address.trim().length() >= 10;
    }

    public static boolean isValidAadhaar(String aadhaar) {
        return aadhaar != null && AADHAAR_PATTERN.matcher(aadhaar).matches();
    }

    public static boolean isValidPAN(String pan) {
        return pan != null && PAN_PATTERN.matcher(pan).matches();
    }

    public static boolean isValidDeposit(String deposit) {
        try {
            double amount = Double.parseDouble(deposit);
            return amount >= 1000;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean isValidAccountType(String type) {
        return type != null && (type.equals("Savings") || type.equals("Current") || type.equals("Fixed Deposit"));
    }

    public static boolean isValidPassword(String password) {
        return password != null && PASSWORD_PATTERN.matcher(password).matches();
    }

    public static boolean doPasswordsMatch(String pass, String confirm) {
        return pass != null && pass.equals(confirm);
    }
}
