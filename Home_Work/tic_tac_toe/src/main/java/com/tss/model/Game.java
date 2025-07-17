package com.tss.model;

public class Game {
    private final Board board;
    private final Player player1;
    private final Player player2;

    public Game(Player player1, Player player2) {
        this.board = new Board();
        this.player1 = player1;
        this.player2 = player2;
    }

    public void start() {
        Player current = player1;
        board.printBoard();

        while (true) {
            System.out.println(current.getName() + "'s turn (" + current.getSymbol() + ")");
            current.makeMove(board);
            board.printBoard();

            if (board.checkWin(current.getSymbol())) {
                System.out.println(current.getName() + " wins!");
                break;
            } else if (board.isFull()) {
                System.out.println("It's a draw!");
                break;
            }

            current = (current == player1) ? player2 : player1;
        }
    }
}
