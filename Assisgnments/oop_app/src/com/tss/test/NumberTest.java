package com.tss.test;
import com.tss.model.Number;


public class NumberTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Number number1 = new Number(10);
		Number number2 = new Number(20);
		
		System.out.println("Before Swapping");
		System.out.println("Number 1: "+number1.getNumber());
		System.out.println("Number 2: "+number2.getNumber());
		
		swap(number1,number2);
		
		System.out.println("After Swapping");
		System.out.println("Number 1: "+number1.getNumber());
		System.out.println("Number 2: "+number2.getNumber());
	}

	private static void swap(Number number1, Number number2) {
		// TODO Auto-generated method stub
		Number temp = new Number();
		temp.setNumber(number1.getNumber());
		number1.setNumber(number2.getNumber());
		number2.setNumber(temp.getNumber());
	}

}
