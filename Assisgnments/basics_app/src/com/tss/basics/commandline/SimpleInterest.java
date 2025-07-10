package com.tss.basics.commandline;

import java.util.Scanner;

public class SimpleInterest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		double principal = Double.parseDouble(args[0]);
		double rateofinterest = Double.parseDouble(args[1]);
		double timeperiod = Double.parseDouble(args[2]);
		
		simpleinterest(principal, rateofinterest, timeperiod);

	}

	private static void simpleinterest(double principal, double rateofinterest, double timeperiod) {
		double simple_interest = (principal * rateofinterest * timeperiod);
		double divide = simple_interest/100;
		
		System.out.println("Simple Interest = " + divide);
	}
}
