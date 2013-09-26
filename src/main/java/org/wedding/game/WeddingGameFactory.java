package org.wedding.game;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.wedding.game.board.GameBoard;
import org.wedding.game.board.ShuffleBoardGenerator;
import org.wedding.game.turn.TurnFactoryImpl;

public class WeddingGameFactory implements GameFactory {

	private final String names[] = { "ariza", "cris", "dani", "edu", "isma", "javi", "lidia", "marta", "patri", "robert", "rocio", "pablo"};

	public Game build() {
		Set<String> cardNames = new HashSet<String>(Arrays.asList(names));
		GameBoard gameBoard = new GameBoard(new ShuffleBoardGenerator(cardNames, 6));
		return new Game(gameBoard, new TurnFactoryImpl());
	}

}
