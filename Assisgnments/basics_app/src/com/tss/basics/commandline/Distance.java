package com.tss.basics.commandline;

public class Distance {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int x1 = Integer.parseInt(args[0]);
		int x2 = Integer.parseInt(args[1]);
		int y1 = Integer.parseInt(args[2]);
		int y2 = Integer.parseInt(args[3]);
		
		distanceBetweenTwoLines(x1, x2, y1, y2);
		
	}

	private static void distanceBetweenTwoLines(int x1, int x2, int y1, int y2) {
		double Distance = Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 -y1, 2));
		
		System.out.println("Distance between two lines: " + Distance);
	}
}
