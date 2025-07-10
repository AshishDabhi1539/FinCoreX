package com.tss.test;

import java.util.Scanner;
import java.util.Random;
import com.tss.model.Bank;
import com.tss.model.AccountType;

public class BankTest {

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);

		//To take the input form the user for the array size
		System.out.println("Enter The Number Of Array accounts You Want to create: ");
		int numberOfArrayAccounts = scanner.nextInt();
		
		//for the java bug so you so not skip the line
		scanner.nextLine();

		//Creating an object array named accounts for the account creation with the users input for the size of the array 
		Bank[] Accounts = new Bank[numberOfArrayAccounts];

		int i =0;
		
		
		int accountId;
		Bank account;
		boolean loop = true;
		while (loop) {
			if( i< numberOfArrayAccounts) {
			System.out.println("Please select the service");
			}
			System.out.println("1. Create Accounts");
			System.out.println("2. Display Balance");
			System.out.println("3. Deposit");
			System.out.println("4. Withdraw");
			System.out.println("5. Transfer");
			System.out.println("6. Exit");
			System.out.print("Enter your choice: ");
			int choice = scanner.nextInt();
			
			if(i>=numberOfArrayAccounts) {
				if(choice == 1) {
					System.out.println("Invalid Option!");
					continue;
				}
			}
//			if(i>numberOfArrayAccounts) {
//				
//			}
			switch (choice) {
			case 1:
				
					getInfo(scanner, Accounts, i);
					i++;
					
					

				

				break;
			case 2:
				System.out.println("Enter Account Id");
				accountId = scanner.nextInt();

				account = findAccountId(Accounts, accountId);
				if (account != null) {
					System.out.println("Current Balance: " + account.getBalance());
				} else {
					System.out.println("No account found. Please create one first.");
				}
				break;

			case 3:
				System.out.println("Enter Account Id");
				accountId = scanner.nextInt();
				account = findAccountId(Accounts, accountId);
				if (account != null) {
					System.out.print("Enter deposit amount: ");
					double depositAmount = scanner.nextInt();
					account.depositAmount(depositAmount);
				} else {
					System.out.println("No account found. Please create one first.");
				}
				break;

			case 4:
				System.out.println("Enter Account Id");
				accountId = scanner.nextInt();
				account = findAccountId(Accounts, accountId);
				if (account != null) {
					System.out.print("Enter withdrawal amount: ");
					double withdrawAmount = scanner.nextInt();
					account.withdrawnAmount(withdrawAmount);
				} else {
					System.out.println("No account found. Please create one first.");
				}
				break;

			case 5:
				System.out.println("Enter Sender's Account Id: ");
				int senderAccountId = scanner.nextInt();
				Bank senderAccount = findAccountId(Accounts, senderAccountId);
				

				System.out.println("Enter Receiver's Account Id: ");
				int receiverAccountId = scanner.nextInt();
				Bank receiverAccount = findAccountId(Accounts, receiverAccountId);

				System.out.println("Enter Amount You Want to Transfer: ");
				double amount = scanner.nextInt();
				
				if(senderAccount.getBalance() - amount < 500)
					System.out.println("Insufficient Balance");

				else if (senderAccount == null || receiverAccount == null) {
					System.out.println("Enter Valid Sender Or Receiver Id");
				} 
				else if(amount<=0)
					System.out.println("Enter the valid amount to transfer");
				
				else if(amount>senderAccount.getBalance()){
					System.out.println("Insufficient Balance!");
				}
				else {
					senderAccount.withdrawnAmount(amount);
					receiverAccount.depositAmount(amount);
					System.out.println("Withdrawed Successfully");
					System.out.println("Sender's New Balance: " + senderAccount.getBalance()
							+ " Receiver's New Balance: " + receiverAccount.getBalance());
				}
				break;

			case 6:
				System.out.println("Exited");
				loop = false;
				break;

			default:
				System.out.println("Invalid choice. Please try again.");
			}
		}

	}

	private static void getInfo(Scanner scanner, Bank account[], int i) {
		System.out.println("Account Details");

		String accountNumber = generateAccountNumber(account,i);
		System.out.println("Account number :"+accountNumber);
		
		scanner.nextLine();
		System.out.print("Enter Name: ");
		String name = scanner.nextLine();
		
		System.out.print("Enter Initial Balance: ");
		double balance = scanner.nextDouble();
		if(balance <=500) {
			System.out.println("Please make an account with minimum balance of 500 rupees");
		System.out.print("Enter Initial Balance: ");
		balance = scanner.nextDouble();
		}
		
		scanner.nextLine();
		
		boolean isAccountType = true;
		while (isAccountType) {
			System.out.print("Enter Account Type (savings/current/FD): ");
			String accountType = scanner.nextLine();
			if (accountType.equalsIgnoreCase("savings")) {
				account[i] = new Bank(i + 1, accountNumber, name, balance, AccountType.SAVINGS);
				isAccountType = false;
			} else if (accountType.equalsIgnoreCase("current")) {
				account[i] = new Bank(i + 1, accountNumber, name, balance, AccountType.CURRENT);
				isAccountType = false;
			} else if (accountType.equalsIgnoreCase("FD")) {
				account[i] = new Bank(i + 1, accountNumber, name, balance, AccountType.FD);
				isAccountType = false;
			} else {
				System.out.println("Enter Valid things!!");
				isAccountType = true;
			}

		}
		
		System.out.println("Account created successfully.");
	
	}

	private static String generateAccountNumber(Bank[] accounts, int i) {
		Random random = new Random();
		String accountNumber;
		String prefix = "AXIS1000";

		int randomNumber = random.nextInt(100000, 1000000);
		accountNumber = prefix + randomNumber;

		if (i == 0) {
			return accountNumber;
		} else {
			for (int j = 0; j < accounts.length; j++) {
				if (accounts[j] != null) {
					if (accounts[j].getAccountNumber().equals(accountNumber)) {
						return "The account number is:" +generateAccountNumber(accounts, i);
					}
				}
			}
			return accountNumber;
		}
	}

	private static Bank findAccountId(Bank[] accounts, int accountId) {
		for (int i = 0; i < accounts.length; i++) {
			if (accounts[i] != null && accounts[i].getAccountId() == accountId) {
				return accounts[i];
			}
		}
		return null;
	}

}
