package com.tss.test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.tss.model.Account;

public class AccountTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		List<Account> accounts = Arrays.asList(new Account(1, "Jay", 5000), 
				new Account(2, "Nimesh", 10000),
				new Account(3, "Mahesh", 15000), 
				new Account(4, "Ramesh", 2000), 
				new Account(5, "Siddharth", 8000));

		accounts.stream().min(Comparator.comparingDouble(Account::getBalance))
				.ifPresent(a -> System.out.println("Minimum Balance Account: " + a));

		accounts.stream().max(Comparator.comparingDouble(Account::getBalance))
				.ifPresent(a -> System.out.println("Maximum Balance Account: " + a));

		List<String> longNames = accounts.stream().map(Account::getName).filter(name -> name.length() > 6)
				.collect(Collectors.toList());
		System.out.println("Names longer than 6 characters: " + longNames);

		double totalBalance = accounts.stream().mapToDouble(Account::getBalance).sum();
		System.out.println("Total Balance of All Accounts: " + totalBalance);
	}

}
