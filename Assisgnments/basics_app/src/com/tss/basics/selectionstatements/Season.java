package com.tss.basics.selectionstatements;

import java.util.Scanner;
public class Season {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Enter the month you want to check: ");
		String month = scanner.nextLine().toLowerCase();
		
		switch(month) {
		case "january" : case "febuaray" : case "march" :
			System.out.println("The season is winter in " + month + ".");
			break;
		case "april" : case "may" : case "june" :
			System.out.println("The season is summer in " + month + ".");
			break;
		case "july" : case "august" : case "september" :
			System.out.println("The season is rainy in " + month + ".");
			break;
		case "october" : case "november" : case "december" :
			System.out.println("The season is winter in " + month + ".");
			break;
		default: System.out.println("Enter a valid month.");
		}
	}

}
