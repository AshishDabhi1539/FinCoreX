package com.tss.basics;

import java.util.Scanner;

public class RideGame {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner scanner = new Scanner(System.in);

		System.out.println("Please enter your height: ");
		float height = scanner.nextFloat();

		if (height > 120) {
			System.out.println("Can Ride");

			System.out.println("Please enter the age: ");
			int age = scanner.nextInt();

			if (age < 12) {
				System.out.println("You need to pay +$5.");

				System.out.println("Enter whelther you want photo or not?");
				String photo = scanner.nextLine();

				if (photo.equalsIgnoreCase("yes")) {
					System.out.println("You need to pay + $3");
				}
			}

			else if (age > 12 && age <= 18) {
				System.out.println("You need to pay +$7.");

				System.out.println("Enter whelther you want photo or not?");
				String photo = scanner.nextLine();

				if (photo.equalsIgnoreCase("yes")) {
					System.out.println("You need to pay + $3");
				}
			}

			else if (age > 18) {
				System.out.println("You need to pay +$12.");

				System.out.println("Enter whelther you want photo or not?");
				String photo = scanner.nextLine();

				if (photo.equalsIgnoreCase("yes")) {
					System.out.println("You need to pay + $3");
				}
			}

			else if (age > 45 && age < 55) {
				System.out.println("You need to pay +$0.");

				System.out.println("Enter whelther you want photo or not?");
				String photo = scanner.nextLine();

				if (photo.equalsIgnoreCase("yes")) {
					System.out.println("You need to pay + $3");
				}
			}

			else {
				System.out.println("Enter valid age.");
			}

		}

		else {
			System.out.println("Can't Ride.");
		}

	}

}
