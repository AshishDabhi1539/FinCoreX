package com.tss.test;

import com.tss.model.AccountType;
import com.tss.model.Bank;
import java.util.Scanner;
public class AccountTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Please enter the number of accounts: ");
		int n = scanner.nextInt();
		Bank bank[] = new Bank[n];
		
		for(int i = 1; i<=bank.length; i++)
			accountDetails(bank, scanner, i);
		for(int i = 1; i<=bank.length; i++)
			System.out.println(accountDetails(bank, scanner, i));
		
	}

	private static Bank[] accountDetails(Bank[] bank, Scanner scanner, int i) {
		// TODO Auto-generated method stub
		System.out.println("Enter the Account ID:");
		int accountId = scanner.nextInt();
		
		scanner.nextLine();
		System.out.println("Enter the Account holder name:");
		String name = scanner.nextLine();
		
		
		System.out.println("Enter the Account Number: ");
		int accountNumber = scanner.nextInt();
		
		System.out.println("Enter the Initial Balance:");
		int initialBalance = scanner.nextInt();
		
		scanner.nextLine();
	    
	    boolean isAccountType = true;
	    while (isAccountType) {
			System.out.print("Enter Account Type (savings/current/FD): ");
			String accountType = scanner.nextLine();
//			if (accountType.equalsIgnoreCase("savings")) {
//				bank[i] = new Bank(accountId, accountNumber, name, initialBalance, AccountType.SAVINGS);
//				isAccountType = false;
//			} else if (accountType.equalsIgnoreCase("current")) {
//				bank[i] = new Bank(accountId, accountNumber, name, initialBalance, AccountType.CURRENT);
//				isAccountType = false;
//			} else if (accountType.equalsIgnoreCase("FD")) {
//				bank[i] = new Bank(accountId, accountNumber, name, initialBalance, AccountType.FD);
//				isAccountType = false;
//			} else {
//				System.out.println("Enter Valid things!!");
//				isAccountType = true;
//			}

		}
		System.out.println("Account created successfully.");
		i++;
		
		return bank;
	}
	

}
