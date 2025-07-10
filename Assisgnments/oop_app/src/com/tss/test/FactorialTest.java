package com.tss.test;

public class FactorialTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int number = Integer.parseInt(args[0]);
		
		int result = calculateFactorial(number);
		System.out.print(result);
	}

	private static int calculateFactorial(int number) {
		// TODO Auto-generated method stub
		if (number == 0)
			return 1;
		if (number<0)
			return -1;
		int fact = 1;
		for(int i=1;i<=number;i++) {
			fact=fact*i;
		}
		return fact;
	}

}
