package com.tss.ExceptionClass;

public class OverdraftReachedLimitException extends RuntimeException{
	private double overdraftLimit;

	public OverdraftReachedLimitException(double overdraftLimit) {
		super();
		this.overdraftLimit = overdraftLimit;
	}
	
	public String getMessage()
    {
    	return "You have reched to your overdraftlimit: "+overdraftLimit;
    }
}