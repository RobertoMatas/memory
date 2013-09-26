package org.wedding.stub;

import org.wedding.game.Game;
import org.wedding.game.GameFactory;
import org.wedding.game.board.GameBoard;
import org.wedding.game.turn.TurnFactoryImpl;

public class StubbedGameFactory implements GameFactory {

	public Game build() {
		return new Game(new GameBoard(new BoardGeneratorStub()), new TurnFactoryImpl());
	}

}
