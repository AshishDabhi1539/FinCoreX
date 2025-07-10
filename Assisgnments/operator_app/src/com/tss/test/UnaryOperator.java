package com.tss.test;

public class UnaryOperator {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int unary = 5;
		int negativeUnary = -unary;
		System.out.println("The unary numbner is: "+(+unary));
		
		System.out.println("The unary negative is: "+(-unary));
		
		System.out.println("The unary negative is: "+(negativeUnary));
		
		boolean condition = true;
		int firstNumber = 12;
		int secondNumber = 13;
		
		if(!(firstNumber>secondNumber))
			System.out.println(!(condition));
		
		
		unary++;
		System.out.println("The unary after increament is: "+(unary));
		
		++unary;
		System.out.println("The unary after pre-increament is: "+(unary));
		
		unary--;
		System.out.println("The unary after deincreament is: "+(unary));
		
		--unary;
		System.out.println("The unary after pre-deincreament is: "+(unary));
		
		//First it will find the compliment of the bitwise number of the unary value and then 
		//it will do the 2's complement of the compliment decimal number
		System.out.println(~unary);
	}

}
