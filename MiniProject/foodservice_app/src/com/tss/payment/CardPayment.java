package com.tss.payment;

import java.util.Scanner;

public class CardPayment extends PaymentDecorator {
    public CardPayment(Payment payment) {
        super(payment);
    }

    @Override
    public void pay(double amount) {
        super.pay(amount);
        try (Scanner sc = new Scanner(System.in)) {
			System.out.print("Enter Card Number: ");
			String card = sc.nextLine();
			System.out.println("Card payment successful with card: " + card);
		}
    }
}
