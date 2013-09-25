package org.wedding.game.turn;

import org.wedding.game.board.Board;

public class TurnFactoryImpl implements TurnFactory {

	public TwoMovesTurn buildFor(Board board) {
		return new TwoMovesTurn(board, new DefaultCardsHandler());
	}

}
