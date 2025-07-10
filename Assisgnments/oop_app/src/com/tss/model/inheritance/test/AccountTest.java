package com.tss.model.inheritance.test;

import java.util.Scanner;

import com.tss.model.inheritance.model.Account;
import com.tss.model.inheritance.model.CurrentAccount;
import com.tss.model.inheritance.model.SavingsAccount;

public class AccountTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Account account = null;
		Scanner scanner = new Scanner(System.in);
		
		while(true) {
			System.out.println("Menu: ");
			System.out.println("1. Make a Current Account");
			System.out.println("2. Make a Savings Account");
			System.out.println("3. Credit Amount");
			System.out.println("4. Debit Account");
			System.out.println("5. Display Account Details");
			System.out.println("6. Exit");
			
			System.out.print("Enter choice: ");
	        int choice = scanner.nextInt();
	           
	        switch(choice) {
	        case 1:
	        	 System.out.print("Enter Acc No: ");
                 int currentAccNo = scanner.nextInt();
                 scanner.nextLine();
                 System.out.print("Enter Name: ");
                 String currentName = scanner.nextLine();
                 System.out.print("Enter Balance: ");
                 double currentBalance = scanner.nextDouble();
                 System.out.print("Enter Overdraft Limit: ");
                 double limit = scanner.nextDouble();
                 account = new CurrentAccount(currentAccNo, currentName, currentBalance, limit);
                 System.out.println("Current account created.");
                 break;
                
	        case 2:
                System.out.print("Enter Acc No: ");
                int savingsAccNo = scanner.nextInt();
                scanner.nextLine();
                System.out.print("Enter Name: ");
                String savingsName = scanner.nextLine();
                System.out.print("Enter Balance: ");
                double savingsBalance = scanner.nextDouble();
                System.out.print("Enter Minimum Balance: ");
                double minBal = scanner.nextDouble();
                account = new SavingsAccount(savingsAccNo, savingsName, savingsBalance, minBal);
                
                if(savingsBalance<minBal) {
                	System.out.println("NOt enough balance");
                }
                else
                	System.out.println("Savings account created.");
                break;
                
	        case 3:
                if (account != null) {
                    System.out.print("Enter amount to credit: ");
                    double amt = scanner.nextDouble();
                    account.credit(amt);
                } else {
                    System.out.println("Create an account first.");
                }
                break;
                
	        case 4:
                if (account != null) {
                    System.out.print("Enter amount to debit: ");
                    double amt = scanner.nextDouble();
                    if (account instanceof CurrentAccount) {
                        ((CurrentAccount) account).debit(amt);
                    } else if (account instanceof SavingsAccount) {
                        ((SavingsAccount) account).debit(amt);
                    }
                } else {
                    System.out.println("Create an account first.");
                }
                break;
                
	        case 5:
                if (account != null) {
                    account.displayInfo();
                } else {
                    System.out.println("No account to display.");
                }
                break;

            case 6:
                System.out.println("Thank you for using the bank app.");
                scanner.close();
                System.exit(0);

            default:
                System.out.println("Invalid choice!");


	        	
	        }

		}

	}

}
