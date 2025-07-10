package com.tss.model;

public class Bank {

	private int accountId;
	private String accountNumber;
	private String name;
	private double balance;
	private AccountType accountType;
	
	public Bank() {
		accountId = 45454;
		accountNumber = "AXIS1000654640";
		name = "TSS";
		balance = 548451;
		accountType = AccountType.SAVINGS;
	}
	
	public Bank(int accountId, String accountNumber, String name, double balance, AccountType accountType) {
			
		this.accountId = accountId;
		this.accountNumber = accountNumber;
		this.name = name;
		this.balance = balance;
		this.accountType = accountType;
		}
	
	public int getAccountId() {
		return accountId;
	}
	

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}
	public AccountType getAccountType() {
		return accountType;
	}
	public void setAccountType(AccountType accountType) {
		this.accountType = accountType;
	}
	
	public void depositAmount(double amount) {
		if(amount>0) {
			this.balance +=amount;
			System.out.println("The new balance after depositing "+amount + "is:" +balance );
		}
		else {
			System.out.println("Please enter proper amount to deposit.");
		}
		
	}
	
	public void withdrawnAmount(double amount) {
		if(amount>balance) {
			System.out.println("Insufficient Balance");
		}
		else {
			if((balance-amount)>=500) {
				balance -= amount;
				System.out.println("Money withdrawn successfully and the remaining balance is: "+balance);
			}
			else {
				System.out.println("Insufficient Balance.");
			}
		}
	}
	
//	public void transferAmount(Bank targetAccount, double transferAmount) {
//		 if(transferAmount <= 0) {
//	            System.out.println("Please enter a valid amount to transfer.");
//	            return;
//	        }
//
//	        if(transferAmount > this.balance) {
//	            System.out.println("Insufficient balance to make the transfer.");
//	            return;
//	        }
//
//	        if((this.balance - transferAmount) < 500) {
//	            System.out.println("Cannot transfer. Minimum balance of 500 must be maintained.");
//	            return;
//	        }
//
//	        this.balance -= transferAmount;
//	        targetAccount.balance += transferAmount;
//
//	        System.out.println("Transferred " + transferAmount + " successfully.");
//	        System.out.println("Sender new balance: " + this.balance);
//	        System.out.println("Receiver new balance: " + targetAccount.balance);
//
//	}
	
	public void display() {
		System.out.println("AccountId:" + accountId);
		System.out.println("AccountNumber: "+accountNumber);
		System.out.println("Name: "+name);
		System.out.println("Balance: "+balance);
		System.out.println("AccountType: "+accountType);
	}

	@Override
	public String toString() {
		return "Bank [accountId=" + accountId + ", accountNumber=" + accountNumber + ", name=" + name + ", balance="
				+ balance + ", accountType=" + accountType + "]";
	}

	
	
}


