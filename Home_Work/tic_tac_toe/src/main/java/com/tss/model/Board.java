package com.tss.model;

public class Board {
    private final int size = 3;
    private final char[][] board = new char[size][size];

    public Board() {
        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++)
                board[i][j] = ' ';
    }

    public boolean placeMark(int row, int col, char symbol) {
        if (isValidMove(row, col)) {
            board[row][col] = symbol;
            return true;
        }
        return false;
    }

    public boolean isValidMove(int row, int col) {
        return row >= 0 && col >= 0 && row < size && col < size && board[row][col] == ' ';
    }

    public boolean checkWin(char symbol) {
        for (int i = 0; i < size; i++)
            if ((board[i][0] == symbol && board[i][1] == symbol && board[i][2] == symbol) ||
                (board[0][i] == symbol && board[1][i] == symbol && board[2][i] == symbol))
                return true;

        return (board[0][0] == symbol && board[1][1] == symbol && board[2][2] == symbol) ||
               (board[0][2] == symbol && board[1][1] == symbol && board[2][0] == symbol);
    }

    public boolean isFull() {
        for (char[] row : board)
            for (char cell : row)
                if (cell == ' ') return false;
        return true;
    }

    public void printBoard() {
        System.out.println("\n   0   1   2"); // Column headers
        for (int i = 0; i < size; i++) {
            System.out.print(i + " "); // Row label
            for (int j = 0; j < size; j++) {
                System.out.print(" " + board[i][j] + " ");
                if (j < size - 1) System.out.print("|");
            }
            System.out.println();
            if (i < size - 1) System.out.println("  ---+---+---");
        }
    }


    public char[][] getBoard() {
        return board;
    }
}
