package com.tss.model;

public class DBLogger2 implements ILogger{

	@Override
	public void log(String err) {
		// TODO Auto-generated method stub
		System.out.println("Logged to database: " + err);
	}

}
