package com.tss.basics;

import java.util.Scanner;
public class Distance {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Enter the x1 number: ");
		int x1 = scanner.nextInt();
		
		System.out.println("Enter the x2 number: ");
		int x2 = scanner.nextInt();
		
		System.out.println("Enter the y1 number: ");
		int y1 = scanner.nextInt();
		
		System.out.println("Enter the y2 number: ");
		int y2 = scanner.nextInt();
		
		
		distanceBetweenTwoLines(x1, x2, y1, y2);
		
	}

	private static void distanceBetweenTwoLines(int x1, int x2, int y1, int y2) {
		double Distance = Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 -y1, 2));
		
		System.out.println("Distance between two lines: " + Distance);
	}
}
