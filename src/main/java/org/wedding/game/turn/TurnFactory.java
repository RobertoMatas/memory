package org.wedding.game.turn;

import org.wedding.game.board.Board;

public interface TurnFactory {

	Turn buildFor(Board board);
}
