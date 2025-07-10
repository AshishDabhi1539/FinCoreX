package com.tss.model;

import com.tss.ExceptionClass.NegativeAmountException;

public abstract class Account {
	
	protected int accNo;
	protected String name;
	protected double balance;
	
	public Account(int accNo, String name, double balance)
	{
		this.accNo = accNo;
		this.name = name;
		if(balance<0)
			throw new NegativeAmountException(balance);
		this.balance = balance;
	}
	
	public abstract void credit(double amount);
	
	public abstract void debit(double amount);
	
    public abstract void displayDetails();
        
}
