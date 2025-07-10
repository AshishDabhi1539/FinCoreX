package com.tss.basics.games;

import java.util.Scanner;
import java.util.Random;

public class PIG {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner sc = new Scanner(System.in);
		Random rn = new Random();

		int points = 20;

		int turns = 1;
		int result = 0;
		while(turns<=5)
		{
			boolean is_role = true;
			System.out.println("TURN:" + turns);
			if (result >= 20) {
				System.out.println("YOu finished " + turns + "!");
				System.out.println("Game Over!");
			}
		while (is_role) {
			
			System.out.println("Roll or hold(r/h):");
			String action = sc.nextLine();

			int random_number = rn.nextInt(6) + 1;
			System.out.println("Random Number:-" + random_number);
			if (action.equalsIgnoreCase("r")) {
				if (random_number != 1) {
					
					result += random_number;
					System.out.println("Die: " + result);
				}

				else {
					result = 0;
					is_role = false;
					turns++;
					System.out.println("Turn Over. No Score.");
				}
			} else if (action.equalsIgnoreCase("h")) {
				System.out.println("Current Score is: " + result);
				turns++;
				is_role = false;
			}

			else {
				System.out.println("ENter whelther you want to rool or hold");
			}
			
		}

		
		
		}

		if (result >= 20) {
			System.out.println("YOu finished " + turns + "!");
			System.out.println("Game Over!");
		}

	}
}
