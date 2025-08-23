package com.banking.util;

import java.math.BigDecimal;
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

    // ✅ Name validation
    public static boolean isValidFullName(String name) {
        return name != null && NAME_PATTERN.matcher(name).matches();
    }

    // ✅ Username validation
    public static boolean isValidUsername(String username) {
        return username != null && USERNAME_PATTERN.matcher(username).matches();
    }

    // ✅ Email validation
    public static boolean isValidEmail(String email) {
        return email != null && EMAIL_PATTERN.matcher(email).matches();
    }

    // ✅ Phone validation
    public static boolean isValidPhone(String phone) {
        return phone != null && PHONE_PATTERN.matcher(phone).matches();
    }

    // ✅ Date of birth validation (must be 18+)
    public static boolean isValidDOB(LocalDate dob) {
        if (dob == null) return false;
        return Period.between(dob, LocalDate.now()).getYears() >= 18;
    }

    // ✅ Gender validation
    public static boolean isValidGender(String gender) {
        return gender != null &&
                (gender.equalsIgnoreCase("Male") ||
                 gender.equalsIgnoreCase("Female") ||
                 gender.equalsIgnoreCase("Other"));
    }

    // ✅ Address validation
    public static boolean isValidAddress(String address) {
        return address != null && address.trim().length() >= 10;
    }

    // ✅ Aadhaar validation
    public static boolean isValidAadhaar(String aadhaar) {
        return aadhaar != null && AADHAAR_PATTERN.matcher(aadhaar).matches();
    }

    // ✅ PAN validation
    public static boolean isValidPAN(String pan) {
        return pan != null && PAN_PATTERN.matcher(pan).matches();
    }

    // ✅ Deposit validation (>= 1000 for new account opening)
    public static boolean isValidInitialDeposit(String deposit) {
        try {
            double amount = Double.parseDouble(deposit);
            return amount >= 1000;
        } catch (Exception e) {
            return false;
        }
    }

    // ✅ Account type validation
    public static boolean isValidAccountType(String type) {
        return type != null &&
                (type.equalsIgnoreCase("Savings") ||
                 type.equalsIgnoreCase("Current") ||
                 type.equalsIgnoreCase("Fixed Deposit"));
    }

    // ✅ Password validation
    public static boolean isValidPassword(String password) {
        return password != null && PASSWORD_PATTERN.matcher(password).matches();
    }

    // ✅ Confirm password match
    public static boolean doPasswordsMatch(String pass, String confirm) {
        return pass != null && pass.equals(confirm);
    }

    // ✅ Amount validation for deposit/withdraw/transfer (> 0)
    public static boolean isValidAmount(Double amount) {
        return amount != null && amount > 0.0;
    }


    // ✅ Amount should be within daily limit (e.g., max 2L per transaction)
    public static boolean isWithinTransactionLimit(BigDecimal amount) {
        BigDecimal limit = new BigDecimal("200000");
        return amount != null && amount.compareTo(limit) <= 0;
    }

    // ✅ Account ID validation (positive number)
    public static boolean isValidAccountId(long accountId) {
        return accountId > 0;
    }

    public static boolean isValidDeposit(String value) {
        try {
            if (value == null || value.trim().isEmpty()) {
                return false;
            }
            double amount = Double.parseDouble(value.trim());

            // Deposit must be at least 1000 (minimum requirement)
            return amount >= 1000;
        } catch (NumberFormatException e) {
            return false; // invalid number format
        }
    }

}
