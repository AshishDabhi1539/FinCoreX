package com.tss.ExceptionClass;

public class InsufficientWaterException extends RuntimeException{

	private String insufficientLevel;
	
	

	public InsufficientWaterException(String insufficientLevel) {
		super();
		this.insufficientLevel = insufficientLevel;
	}
	
	public String getMessage()
	{
		return "Water level Not Valid: You have Entered: "+ insufficientLevel;
	}
}
