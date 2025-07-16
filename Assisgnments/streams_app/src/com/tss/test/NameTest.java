package com.tss.test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class NameTest {

	public static void main(String[] args) {
		List<String> names = Arrays.asList("Jay", "Nimesh", "Mark", "Mahesh", "Ramesh");

		System.out.println("All Names:");
		names.stream().forEach(name -> System.out.println(name));

		System.out.println("\nFirst 3 Names Sorted:");
		List<String> firstThreeSorted = names.stream().limit(3).sorted().collect(Collectors.toList());
		System.out.println(firstThreeSorted);

		System.out.println("\nFirst 3 Names containing 'a' or 'A' and Sorted:");
		List<String> filteredFirstThreeSorted = names.stream().limit(3)
				.filter(name -> name.contains("a") || name
						.contains("A"))
						.sorted()
						.collect(Collectors.toList());
		System.out.println(filteredFirstThreeSorted);

		System.out.println("\nAll Names Reverse Sorted:");
		List<String> reverseSorted = names.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
		System.out.println(reverseSorted);

		System.out.println("\nFirst 3 Characters of Each Name:");
		List<String> namePrefixes = names.stream().map(name -> name.length() >= 3 ? name.substring(0, 3) : name)
				.collect(Collectors.toList());
		System.out.println(namePrefixes);

		System.out.println("\nNames with Length <= 4:");
		List<String> shortNames = names.stream().filter(name -> name.length() <= 4).collect(Collectors.toList());
		System.out.println(shortNames);

	}
}
