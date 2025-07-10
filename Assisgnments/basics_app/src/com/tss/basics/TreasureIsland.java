package com.tss.basics;

import java.util.Scanner;

public class TreasureIsland {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Enter whelther you want to go left or right?");
		String string = scanner.nextLine();
		
		if (string.equalsIgnoreCase("left")) {
			System.out.println("Choose whelther to swim or wait");
			String choose = scanner.nextLine();
			
			if(choose.equalsIgnoreCase("wait")) {
				System.out.println("Choose the door form Red, Yellow, or Blue:");
				String door = scanner.nextLine();
				
				if(door.equalsIgnoreCase("Red")) {
					System.out.println("Burned by fire."
							+ "Game Over.");
				}
				
				else if(door.equalsIgnoreCase("yellow")) {
					System.out.println("You Win."
							+ "Game Over.");
				}
				
				else {
					System.out.println("Eaten by beast."
							+ "Game Over.");
				}
			}
			else {
				System.out.println("Attacked by trout."
						+ "Game Over.");
			}
		}
		
		else {
			System.out.println(" Fall into a hole "
					+ "Game Over.");
		}
	}

}
