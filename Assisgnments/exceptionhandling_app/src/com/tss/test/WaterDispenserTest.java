package com.tss.test;

import java.util.Scanner;
import com.tss.model.WaterDispenser;
import com.tss.ExceptionClass.InsufficientWaterException;
import com.tss.ExceptionClass.OverfillException;

public class WaterDispenserTest {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        WaterDispenser waterDispenser = new WaterDispenser(); // Ensure state is retained

        while (true) {
            System.out.println("Water Dispenser Menu");
            System.out.println("1. Fill Water");
            System.out.println("2. Dispense Water");
            System.out.println("3. Dispay current water level");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            
            int choice = scanner.nextInt();

            try (scanner){
                switch (choice) {
                    case 1:
                        System.out.print("Enter amount to fill (in liters): ");
                        int fillAmount = scanner.nextInt();
                        waterDispenser.fillWater(fillAmount);
                        break;

                    case 2:
                        System.out.print("Enter amount to dispense (in liters): ");
                        int dispenseAmount = scanner.nextInt();
                        waterDispenser.dispenseWater(dispenseAmount);
                        break;

                    case 3:
                    	System.out.println("Current water level: " + waterDispenser.getCurrentWaterLevel() + "L");
                    case 4:
                    	System.out.println("Exiting the system.");
                        return;
                        

                    default:
                        System.out.println("Invalid choice. Please select again.");
                }
            } catch (OverfillException | InsufficientWaterException exception) {
                System.out.println("Error: " + exception.getMessage());
            } finally {
                System.out.println("Current water level: " + waterDispenser.getCurrentWaterLevel() + "L");
            }
        }
    }
}
