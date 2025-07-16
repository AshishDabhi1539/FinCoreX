package com.tss.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AreaCalculatorTest {

	CalculatorOfArea calculator;

	@BeforeEach
	void init() {
		calculator = new CalculatorOfArea();
	}

	@Test
	void areaOfCircleTest() {

		assertEquals(314, calculator.areaOfCircle(10));

	}

	@Test
	void areaOfRectangleTest() {

		assertEquals(200, calculator.areaOfRectangle(10, 20));

	}
}
