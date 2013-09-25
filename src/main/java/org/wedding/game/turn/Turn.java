package org.wedding.game.turn;

import org.wedding.game.card.Position;

public interface Turn {

	void discover(Position position);

	boolean isFinished();

	void end();

}