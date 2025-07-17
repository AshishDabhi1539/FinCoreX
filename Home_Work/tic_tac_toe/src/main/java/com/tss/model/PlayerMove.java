package com.tss.model;

public class PlayerMove extends Player{

	    public PlayerMove(String name, char symbol, IMoveStrategy moveStrategy) {
		super(name, symbol, moveStrategy);
		// TODO Auto-generated constructor stub
	}

	    @Override
	    public void makeMove(Board board) {
	        boolean moveMade = false;
	        while (!moveMade) {
	            int[] move = moveStrategy.getNextMove(board.getBoard());
	            moveMade = board.placeMark(move[0], move[1], symbol);
	            if (!moveMade) {
	                System.out.println("Invalid move. Try again.");
	            }
	        }
	    
	}

}
