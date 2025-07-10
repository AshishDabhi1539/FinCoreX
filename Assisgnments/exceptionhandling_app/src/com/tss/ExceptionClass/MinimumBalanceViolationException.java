package com.tss.ExceptionClass;

public class MinimumBalanceViolationException extends RuntimeException{
	
	private double minBalance;
    public MinimumBalanceViolationException(double minBalance) {
		super();
		this.minBalance = minBalance;
	}
    
    public String getMessage()
    {
    	return "Minimum Balance is: "+minBalance;
    }
	
    
}
