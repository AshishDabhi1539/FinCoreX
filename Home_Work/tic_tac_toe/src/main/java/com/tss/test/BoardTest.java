package com.tss.test;

import com.tss.model.Game;
import com.tss.model.MoveStrategy;
import com.tss.model.Player;
import com.tss.model.PlayerMove;

public class BoardTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Player player1 = new PlayerMove("Player 1", 'X', new MoveStrategy());
		Player player2 = new PlayerMove("Player 2", 'O', new MoveStrategy());

		Game game = new Game(player1, player2);
		game.start();
	}

}
