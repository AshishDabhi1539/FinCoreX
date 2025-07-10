package com.tss.test;

import java.util.Scanner;

import com.tss.model.Account;
import com.tss.model.CurrentAccount;
import com.tss.model.SavingAccount;

public class AccountTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		 Scanner scanner = new Scanner(System.in);
		 while(true) {
		 try {
		 Account account = null;
		 boolean loop = true;
	        while (loop) {
	        	System.out.println();
	            System.out.println("Please enter your service");
	            System.out.println("1. Create Current Account");
	            System.out.println("2. Create Savings Account");
	            System.out.println("3. Credit Amount");
	            System.out.println("4. Debit Amount");
	            System.out.println("5. Display Account Details");
	            System.out.println("6. Exit");
	            System.out.print("Enter choice: ");
	            int choice = scanner.nextInt();

	            switch (choice) {
	                case 1:
	                    System.out.print("Enter Acc No: ");
	                    int currentAccNo = scanner.nextInt();
	                    scanner.nextLine();
	                    System.out.print("Enter Name: ");
	                    String currentAccountName = scanner.nextLine();
	                    System.out.print("Enter Balance: ");
	                    double currentAccountBalance = scanner.nextDouble();
	                    System.out.print("Enter Overdraft Limit: ");
	                    double limit = scanner.nextDouble();
	                    account = new CurrentAccount(currentAccNo, currentAccountName, currentAccountBalance, limit);
	                    System.out.println("Current account created.");
	                    break;

	                case 2:
	                    System.out.print("Enter Acc No: ");
	                    int savingsAccountNo = scanner.nextInt();
	                    scanner.nextLine();
	                    System.out.print("Enter Name: ");
	                    String savingsAccountName = scanner.nextLine();
	                    System.out.print("Enter Balance: ");
	                    double savingsAccountBalance = scanner.nextDouble();
	                    System.out.print("Enter Minimum Balance: ");
	                    double minimumBal = scanner.nextDouble();
	                    account = new SavingAccount(savingsAccountNo, savingsAccountName, savingsAccountBalance, minimumBal);
	                    System.out.println("Savings account created.");
	                    break;

	                case 3:
	                    if (account != null) {
	                        System.out.print("Enter amount to credit: ");
	                        double amt = scanner.nextDouble();
	                        account.credit(amt);
	                        break;
	                    }
	                    System.out.println("Create an account first.");
	                    break;

	                case 4:
	                    if (account != null) {
	                        System.out.print("Enter amount to debit: ");
	                        double amt = scanner.nextDouble();
	                        if (account instanceof CurrentAccount) {
	                            ((CurrentAccount) account).debit(amt);
	                            break;
	                        } else if (account instanceof SavingAccount) {
	                            ((SavingAccount) account).debit(amt);
	                            break;
	                        }
	                    } 
	                    System.out.println("Create an account first.");
	                    break;

	                case 5:
	                    if (account != null) {
	                        account.displayDetails();
	                        break;
	                    } 
                        System.out.println("No account to display.");
	                    break;

	                case 6:
	                    System.out.println("Thank you for using the bank app.");
	                    loop = false;
	                    break;

	                default:
	                    System.out.println("Invalid choice!");
	            }
	        }
		 }
	        catch(Exception exception)
			 {
				 System.out.println(exception.getMessage());
			 }
		 }
		 

}
}
