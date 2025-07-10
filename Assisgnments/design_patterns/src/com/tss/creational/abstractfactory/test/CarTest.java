package com.tss.creational.abstractfactory.test;

import java.util.Scanner;

import com.tss.creational.abstractfactory.model.ICarFactory;
import com.tss.creational.abstractfactory.model.ICars;
import com.tss.creational.abstractfactory.model.MahindraFactory;
import com.tss.creational.abstractfactory.model.MarutiFactory;
import com.tss.creational.abstractfactory.model.TataFactory;

public class CarTest {

	public static void main(String[] args) {
		

		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Enter the choice: ");
		System.out.println("1.Maruti");
		System.out.println("2.Tata");
		System.out.println("3.Mahindra");
		int choice = scanner.nextInt();
		
		
        
        if (choice == 1) {
        	ICarFactory marutiFactory = new MarutiFactory();
            ICars maruti = marutiFactory.makeCar();
            maruti.start();
            maruti.stop();
            return;
        }
        if(choice == 2) {
	        ICarFactory tataFactory = new TataFactory();
	        ICars tata = tataFactory.makeCar();
	        tata.start();
	        tata.stop();
	        return;
        }
        
        if(choice == 3) {
	        ICarFactory mahindraFactory = new MahindraFactory();
	        ICars mahindra = mahindraFactory.makeCar();
	        mahindra.start();
	        mahindra.stop();
	        return;
        }
        
        System.out.println("Invalid Choice");
        return;
	}

}
