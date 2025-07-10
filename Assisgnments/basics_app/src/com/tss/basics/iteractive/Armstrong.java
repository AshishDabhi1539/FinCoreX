package com.tss.basics.iteractive;

import java.util.Scanner;
public class Armstrong {

	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		// TODO Auto-generated method stub
		
		System.out.print("Enter a number: ");
        int number = scanner.nextInt();
        int temp = number;
        
        int sum = 0;
        
        int power = String.valueOf(number).length();
        
        while(number>0) {
        	double digits = number % 10;
        	sum += Math.pow(digits, power);
        	number = number / 10;
        	System.out.println(sum);
        	
        }
        if (sum == temp) {
            System.out.println(temp + " is an Armstrong number.");
        } else {
            System.out.println(temp + " is not an Armstrong number.");
        }
	}

}
