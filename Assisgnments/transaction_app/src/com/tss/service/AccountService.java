package com.tss.service;

import java.util.Scanner;

import com.tss.controller.AccountController;

public class AccountService {

	private final AccountController controller = new AccountController();

    public void showTransaction() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Sender Account ID: ");
        int senderId = scanner.nextInt();

        System.out.print("Enter Receiver Account ID: ");
        int receiverId = scanner.nextInt();

        System.out.print("Enter Amount to Transfer: ");
        double amount = scanner.nextDouble();

        String result = controller.transferFunds(senderId, receiverId, amount);
        System.out.println(result);
        
        scanner.close();
    }
}
