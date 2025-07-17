package com.tss.model;

import java.util.Scanner;

public class MoveStrategy implements IMoveStrategy {
    private final Scanner scanner = new Scanner(System.in);

    @Override
    public int[] getNextMove(char[][] board) {
        System.out.println("Enter row and then the column (0-2): ");
        return new int[] {scanner.nextInt(), scanner.nextInt()};
    }
}
