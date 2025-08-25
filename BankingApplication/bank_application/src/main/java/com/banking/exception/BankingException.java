package com.banking.exception;

public class BankingException extends Exception {
    
    private static final long serialVersionUID = 1L;
    
    private String errorCode;
    private String errorType;
    
    public BankingException(String message) {
        super(message);
        this.errorType = "GENERAL";
    }
    
    public BankingException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
        this.errorType = "BUSINESS";
    }
    
    public BankingException(String message, String errorCode, String errorType) {
        super(message);
        this.errorCode = errorCode;
        this.errorType = errorType;
    }
    
    public BankingException(String message, Throwable cause) {
        super(message, cause);
        this.errorType = "SYSTEM";
    }
    
    public BankingException(String message, String errorCode, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
        this.errorType = "BUSINESS";
    }
    
    public String getErrorCode() {
        return errorCode;
    }
    
    public String getErrorType() {
        return errorType;
    }
    
    @Override
    public String toString() {
        return "BankingException{" +
                "errorCode='" + errorCode + '\'' +
                ", errorType='" + errorType + '\'' +
                ", message='" + getMessage() + '\'' +
                '}';
    }
}

