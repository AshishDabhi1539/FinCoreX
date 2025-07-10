package com.tss.basics.iteractive;

import java.util.Scanner;

public class PerfectNumber {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int sum = 0;
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Number:-");
		int number = sc.nextInt();
		int i = 1;
		while(i<=number/2)
		{
			if(number%i==0)
			{
				sum += i;
			}
			i++;
		}
		
		if(sum==number)
		{
			System.out.println("Number is Perfect");
		}
		else
		{
			System.out.println("number is not perfect");
		}
	}

}
