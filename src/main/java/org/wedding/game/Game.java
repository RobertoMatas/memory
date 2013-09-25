package org.wedding.game;

import java.util.Observable;

import org.wedding.game.board.Board;
import org.wedding.game.card.Position;
import org.wedding.game.turn.Turn;
import org.wedding.game.turn.TurnFactory;

public class Game extends Observable {

	private Turn actualTurn;
	private final Board board;
	private final TurnFactory turnFactory;

	public Game(Board board, TurnFactory turnFactory) {
		this.board = board;
		this.turnFactory = turnFactory;
		newTurn();
	}

	public void discover(Position p) {
		this.actualTurn.discover(p);
		if (actualTurn.isFinished()) {
			actualTurn.end();
			if (gameIsNotFinished()) {
				newTurn();
			}
		}
	}

	private void newTurn() {
		this.actualTurn = turnFactory.buildFor(board);
	}

	private boolean gameIsNotFinished() {
		return !board.isAllCardsVisible();
	}

	/**
	 * @return the board
	 */
	public Board getBoard() {
		return board;
	}
}
