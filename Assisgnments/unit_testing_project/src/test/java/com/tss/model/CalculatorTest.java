package com.tss.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CalculatorTest {

	
	Calculator calculator;
	@BeforeEach
	void init() {
		calculator = new Calculator();
	}
	@AfterAll
	static void display() {
		System.out.println("Testing done");
	}
	@Test
	void testAddition() {
		
		assertEquals(5, calculator.addition(3, 2));
		assertEquals(9, calculator.addition(5, 4));
	}

	@Test
	void testSubstraction() {
		
		
		
		assertEquals(1, calculator.subraction(3, 2));
		assertEquals(2, calculator.subraction(5, 3));
	}

	@Test
	void testMultiplication() {
		

		assertEquals(6, calculator.multiplication(3, 2));
		assertEquals(9, calculator.multiplication(3, 3));
	}

	@Test
	void testDivision() {
		
		assertEquals(0, calculator.division(3, 0));
		assertThrows(ArithmeticException.class, () -> calculator.division(3, 0));
		assertEquals(1, calculator.division(3, 2));
		assertEquals(2, calculator.division(4, 2));
	}
}
