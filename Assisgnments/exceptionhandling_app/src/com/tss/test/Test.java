package com.tss.test;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int number1=Integer.parseInt(args[0]);
		int number2=Integer.parseInt(args[1]);

		
		try

		{
		float result =number1/number2;
		System.out.println("Division is: "+result);
		}
		catch(Exception exception)
		{
		System.out.println("Please enter the valid value "+exception.getMessage());
		}
		System.out.println("Exiting");
		}
}

