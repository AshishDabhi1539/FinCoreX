package com.tss.ExceptionClass;

public class OverfillException extends RuntimeException{
	
	private String overFill;
	
	

	public OverfillException(String overFill) {
		super();
		this.overFill = overFill;
	}
	
	public String getMessage()
	{
		return "Water level Not Valid: You have Entered: "+ overFill;
	}
}
