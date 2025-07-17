package com.tss.test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Hello {
    public static void main(String[] args) {
        List<String> sentences = Arrays.asList("Hello world", "hello Java", "Stream API");

        Set<String> uniqueWords = sentences.stream()
            .flatMap(s -> Arrays.stream(s.toLowerCase().split("\\W+")))
            .filter(word -> !word.isEmpty())
            .collect(Collectors.toCollection(HashSet::new));

        System.out.println("Unique Words (Sorted):");
        uniqueWords.forEach(System.out::println);
    }
}
