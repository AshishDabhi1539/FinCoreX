package com.tss.basics;

import java.util.Scanner;

public class AreaOfCircle {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Enter the radius: ");
		float radius = scanner.nextFloat();
		
		areaofcircle(radius);
		
		

	}
		private static void areaofcircle(float radius) {
			double pi = 3.14;
			double area = pi * radius *radius;
			System.out.println("Area of the circle is:" + area);
		}
}
