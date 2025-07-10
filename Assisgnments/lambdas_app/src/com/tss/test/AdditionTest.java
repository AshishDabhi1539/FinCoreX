package com.tss.test;

import java.util.function.BiConsumer;

public class AdditionTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		BiConsumer <Integer, Integer> add = (number1, number2) -> System.out.println("Sum is: "+(number1 + number2));
		
		add.accept(10, 20);
		
	}

}
