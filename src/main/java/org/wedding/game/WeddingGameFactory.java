package org.wedding.game;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.wedding.game.board.GameBoard;
import org.wedding.game.board.ShuffleBoardGenerator;
import org.wedding.game.turn.TurnFactoryImpl;

public class WeddingGameFactory implements GameFactory {

	private final String names[] = {
			"alberto",
			"ariza",
			"cris",
			"dani",
			"david",
			"daya",
			"edu",
			"gonza",
			"isma",
			"javi_cris",
			"javi_marta",
			"javi",
			"leti",
			"lidia",
			"maria",
			"marta",
			"nala",
			"pablo",
			"patri_david",
			"patri",
			"rober",
			"rocio",
			"rosario",
			"vero",
			"vili"
	};

	public Game build() {
		Set<String> cardNames = new HashSet<String>(Arrays.asList(names));
		GameBoard gameBoard = new GameBoard(new ShuffleBoardGenerator(cardNames, 10));
		return new Game(gameBoard, new TurnFactoryImpl());
	}

}
