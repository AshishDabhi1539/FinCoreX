package com.tss.basics.commandline;

import java.util.Scanner;

public class AreaOfCircle {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		float radius = Float.parseFloat(args[0]);
		
		areaofcircle(radius);
		
		

	}
		private static void areaofcircle(float radius) {
			double pi = 3.14;
			double area = pi * radius *radius;
			System.out.println("Area of the circle is:" + area);
		}
}
