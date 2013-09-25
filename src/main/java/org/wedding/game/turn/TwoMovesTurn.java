package org.wedding.game.turn;

import org.wedding.game.board.Board;
import org.wedding.game.card.Card;
import org.wedding.game.card.Position;

public class TwoMovesTurn implements Turn {

	private static final int MOVES_LIMIT = 2;

	private final Board board;

	private int movesInTurn;

	private final CardsHandler movesHandler;

	public TwoMovesTurn(final Board board, final CardsHandler movesHandler) {
		this.board = board;
		this.movesHandler = movesHandler;
		this.movesInTurn = 0;
	}

	public synchronized void discover(Position position) {
		Card card = board.getCardBy(position);
		if (isUndiscoveredAndInvisible(card)) {
			card.show();
			movesHandler.add(card);
			movesInTurn++;
		}
	}

	private boolean isUndiscoveredAndInvisible(Card card) {
		return !card.isDiscovered() && !card.isVisible();
	}

	public boolean isFinished() {
		return movesInTurn == MOVES_LIMIT;
	}

	public synchronized void end() {
		if (isFinished()) {
			if (!coupleDiscovered()) {
				hideCards();
			} else {
				blockCards();
			}
		} else {
			throw new IllegalStateException("El turno no ha terminado todavía. " + "Llama a isFinished() para saber cuando ha terminado");
		}

	}

	private void blockCards() {
		movesHandler.blockAll();
	}

	private void hideCards() {
		movesHandler.hideAll();

	}

	private synchronized boolean coupleDiscovered() {
		return movesHandler.areAllSame();
	}
}
