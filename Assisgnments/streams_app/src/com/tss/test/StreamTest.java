package com.tss.test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class StreamTest {

	public static void main(String[] args) {
		List<Integer> numbers = Arrays.asList(5, 3, 4, 7, 6);

		System.out.println("All numbers:");
		numbers.stream().forEach((number) -> System.out.println(number));

		System.out.println("\nSquare of numbers:");
		numbers.stream().forEach((number) -> System.out.println(number * number));

		List<Integer> oddNumbers = numbers.stream().filter(number -> number % 2 != 0).collect(Collectors.toList());
		System.out.println("\nOdd Numbers: " + oddNumbers);

		Set<Integer> squareNumbers = numbers.stream().map(number -> number * number).collect(Collectors.toSet());
		System.out.println("Square Numbers Set: " + squareNumbers);

		List<Integer> evennumberSquare = numbers.stream().filter(number -> number % 2 == 0)
				.map(number -> number * number).collect(Collectors.toList());
		System.out.println("Even Number Squares: " + evennumberSquare);

		int sum = numbers.stream().reduce(0, (number1, number2) -> number1 + number2);
		System.out.println("Sum: " + sum);

		System.out.println("Count: " + numbers.stream().count());

		System.out.println("Limit 3:");
		numbers.stream().limit(3).forEach(number -> System.out.println(number + "\t"));

		System.out.println("\nSkip first 2:");
		numbers.stream().skip(2).forEach(number -> System.out.println(number));

		boolean anyMatch = numbers.stream().anyMatch(number -> number > 4);
		System.out.println("Any number > 4? " + anyMatch);

		boolean allMatch = numbers.stream().allMatch(number -> number > 0);
		System.out.println("All numbers > 0? " + allMatch);

		List<Integer> sortedList = numbers.stream().sorted().collect(Collectors.toList());
		System.out.println("Sorted List: " + sortedList);

		Optional<Integer> max = numbers.stream().max(Comparator.naturalOrder());
		System.out.println("Max: " + (max.isPresent() ? max.get() : "No Max"));

		Optional<Integer> min = numbers.stream().min(Comparator.naturalOrder());
		System.out.println("Min: " + (min.isPresent() ? min.get() : "No Min"));
	}
}
