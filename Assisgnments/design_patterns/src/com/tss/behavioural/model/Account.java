package com.tss.behavioural.model;

import java.util.ArrayList;
import java.util.List;

import com.tss.behavioural.model.exception.InsufficientFundsException;

public class Account {
	private String accountNumber;
	private String name;
	private double balance;
	private List<INotifier> notifiers;

	public Account(String accountNumber, String name, double balance) {
		this.accountNumber = accountNumber;
		this.name = name;
		this.balance = balance;
		this.notifiers = new ArrayList<>();
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public String getName() {
		return name;
	}

	public double getBalance() {
		return balance;
	}

	public void deposit(double amount) {
		balance += amount;
		notifyAll("Deposited " + amount + ", New Balance: " + balance);
	}

	public void withdraw(double amount) throws InsufficientFundsException {
		if (amount > balance) {
			throw new InsufficientFundsException("Insufficient funds to withdraw" + amount);
		}
		balance -= amount;
		notifyAll("Withdrew " + amount + ", New Balance: " + balance);
	}

	public void registerNotifier(INotifier notifier) {
	    boolean alreadyRegistered = notifiers.stream()
	        .anyMatch(n -> n.getClass().equals(notifier.getClass()));

	    if (alreadyRegistered) {
	        System.out.println(notifier.getClass().getSimpleName() + " is already registered.");
	    } else {
	        notifiers.add(notifier);
	        System.out.println(notifier.getClass().getSimpleName() + " successfully registered.");
	    }
	}


	public void removeNotifier(INotifier notifier) {
		notifiers.remove(notifier);
	}

	private void notifyAll(String message) {
		for (INotifier notifier : notifiers) {
			notifier.sendNotification(this, message);
		}
	}
	
	public void removeNotifierByType(Class<? extends INotifier> notifierType) {
	    notifiers.removeIf(n -> n.getClass().equals(notifierType));
	}


	public void display() {
		System.out.println("Account Number : " + accountNumber);
		System.out.println("Name           : " + name);
		System.out.println("Balance        : " + balance);
		System.out.println("Notifiers      : " + notifiers.size());
	}
}
