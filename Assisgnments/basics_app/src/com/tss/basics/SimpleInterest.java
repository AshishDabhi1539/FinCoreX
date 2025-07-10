package com.tss.basics;

import java.util.Scanner;

public class SimpleInterest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Enter the Principal Value:");
		double principal = scanner.nextDouble();
		
		System.out.println("Enter the Rate of Interest:");
		double rateofinterest = scanner.nextDouble();
		
		System.out.println("Enter the Time Period:");
		double timeperiod = scanner.nextDouble();
		
		simpleinterest(principal, rateofinterest, timeperiod);

	}

	private static void simpleinterest(double principal, double rateofinterest, double timeperiod) {
		double simple_interest = (principal * rateofinterest * timeperiod);
		double divide = simple_interest/100;
		
		System.out.println("Simple Interest = " + divide);
	}
}
