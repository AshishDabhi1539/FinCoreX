package com.tss.exception;

public class CuisineNotFoundException extends Exception {

    // Default constructor
    public CuisineNotFoundException() {
        super("❌ Cuisine not found.");
    }

    // Constructor with custom message
    public CuisineNotFoundException(String message) {
        super(message);
    }

    // Constructor with message and cause
    public CuisineNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    // Constructor with cause
    public CuisineNotFoundException(Throwable cause) {
        super(cause);
    }
}
