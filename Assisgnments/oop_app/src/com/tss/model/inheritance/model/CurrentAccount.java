package com.tss.model.inheritance.model;

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
		System.out.println("Debit Failed");
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
		System.out.println("Credit Failed");
	}
	
	@Override
	public void displayInfo()
	{
		System.out.println("Account Number: "+accNo);
		System.out.println("Name: "+name);
		System.out.println("Balance: "+balance);
		System.out.println("Overdraft Limit: "+overdraftLimit);
	}

	
	
}