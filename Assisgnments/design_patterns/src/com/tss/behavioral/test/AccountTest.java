package com.tss.behavioral.test;

import java.util.Scanner;

import com.tss.behavioural.model.Account;
import com.tss.behavioural.model.EmailNotifier;
import com.tss.behavioural.model.SmsNotifier;
import com.tss.behavioural.model.WhatsappNotifier;
import com.tss.behavioural.model.exception.InsufficientFundsException;

public class AccountTest {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		// Create account
		System.out.print("Enter Account Number: ");
		String accountNumber = scanner.nextLine();

		System.out.print("Enter Account Holder Name: ");
		String name = scanner.nextLine();

		System.out.print("Enter Initial Balance: ");
		double balance = scanner.nextDouble();

		Account account = new Account(accountNumber, name, balance);

		
		System.out.println("Choose Notifiers to Register:");
		System.out.println("1. SMS");
		System.out.println("2. Email");
		System.out.println("3. WhatsApp");
		System.out.println("Enter all the notification choices you need separated by commas: ");
		scanner.nextLine();
		String[] choices = scanner.nextLine().split(",");

		
		for (String choice : choices) {
			switch (choice.trim()) {
			case "1":
				account.registerNotifier(new SmsNotifier());
				break;
			case "2":
				account.registerNotifier(new EmailNotifier());
				break;
			case "3":
				account.registerNotifier(new WhatsappNotifier());
				break;
			default:
                System.out.println("Invalid notifier option.");
			}
		}
		

		while (true) {
		    System.out.println("\n ----------- Account Menu ----------");
		    System.out.println("1. Deposit");
		    System.out.println("2. Withdraw");
		    System.out.println("3. Display Account");
		    System.out.println("4. Exit");
		    System.out.println("5. Manage Notifiers (Add/Remove)");
		    System.out.print("Enter choice: ");
		    int choice = scanner.nextInt();
		    scanner.nextLine(); // consume newline

		    switch (choice) {
		        case 1:
		            System.out.print("Enter deposit amount: ");
		            double depoist = scanner.nextDouble();
		            account.deposit(depoist);
		            break;

		        case 2:
		            System.out.print("Enter withdraw amount: ");
		            double withdraw = scanner.nextDouble();
		            try {
		                account.withdraw(withdraw);
		            } catch (InsufficientFundsException e) {
		                System.out.println(e.getMessage());
		            }
		            break;

		        case 3:
		            account.display();
		            break;

		        case 4:
		            System.out.println("Thank you");
		            return;

		        case 5:
		            System.out.println("\nManage Notifiers:");
		            System.out.println("1. Add SMS");
		            System.out.println("2. Remove SMS");
		            System.out.println("3. Add Email");
		            System.out.println("4. Remove Email");
		            System.out.println("5. Add WhatsApp");
		            System.out.println("6. Remove WhatsApp");
		            System.out.print("Enter option: ");
		            int manageOption = scanner.nextInt();
		            scanner.nextLine(); 

		            switch (manageOption) {
		                case 1:
		                	account.registerNotifier(new SmsNotifier());
		                    break;
		                case 2:
		                    account.removeNotifierByType(SmsNotifier.class);
		                    System.out.println("SMS Notifier Removed");
		                    break;
		                case 3:
		                    account.registerNotifier(new EmailNotifier());
		                    break;
		                case 4:
		                    account.removeNotifierByType(EmailNotifier.class);
		                    System.out.println("Email Notifier Removed");
		                    break;
		                case 5:
		                    account.registerNotifier(new WhatsappNotifier());
		                    break;
		                case 6:
		                    account.removeNotifierByType(WhatsappNotifier.class);
		                    System.out.println("WhatsApp Notifier Removed");
		                    break;
		                default:
		                    System.out.println("Invalid notifier option.");
		            }
		            break;

		        default:
		            System.out.println("Invalid choice.");
		    }
		}

	}
	

}
