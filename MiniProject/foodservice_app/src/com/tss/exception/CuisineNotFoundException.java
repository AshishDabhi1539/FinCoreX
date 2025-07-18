package com.tss.exception;

public class CuisineNotFoundException extends Exception {

    
    public CuisineNotFoundException() {
        super("Cuisine not found.");
    }

    
    public CuisineNotFoundException(String message) {
        super(message);
    }

   
    public CuisineNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }


    public CuisineNotFoundException(Throwable cause) {
        super(cause);
    }
}
