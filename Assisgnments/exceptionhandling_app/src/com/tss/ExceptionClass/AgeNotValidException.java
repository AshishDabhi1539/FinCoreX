package com.tss.ExceptionClass;

public class AgeNotValidException extends Exception{
	
	private int age;

	public AgeNotValidException(int age) {
		this.age = age;
	}
	
	public String getMessage()
	{
		return "Age Not Valid: You have Entered: "+age;
	}
	
}