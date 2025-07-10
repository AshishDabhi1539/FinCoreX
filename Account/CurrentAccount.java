package com.tss.model;

import com.tss.ExceptionClass.NegativeAmountException;
import com.tss.ExceptionClass.OverdraftReachedLimitException;

public class CurrentAccount extends Account {
	
	private double overdraftLimit;

	public CurrentAccount(int accNo, String name, double balance, double overdraftLimit) {
		super(accNo, name, balance);
		this.overdraftLimit = overdraftLimit;
	}
	
	@Override
	public void debit(double amount)
	{
		if(balance-amount >= -overdraftLimit)
		{
			balance = (balance-amount);
			System.out.println("Amount Debited. New Ammount: "+balance);
			return;
		}
		throw new OverdraftReachedLimitException(overdraftLimit);
	}
	
	@Override
	public void credit(double amount)
	{
		if(amount>0)
		{
			balance = balance+amount;
			System.out.println("credited Successfully.  New Balance: "+balance);
			return;
		}
		throw new NegativeAmountException(amount);
	}
	
	@Override
	public void displayDetails()
	{
		System.out.println("Account Number: "+accNo);
		System.out.println("Name: "+name);
		System.out.println("Balance: "+balance);
		System.out.println("Overdraft Limit: "+overdraftLimit);
	}
}	