package org.wedding.game.board;

import org.wedding.game.card.Card;
import org.wedding.game.card.Position;

public interface Board {
	
	Card getCardBy(Position position);
	
	Card[][] getBoard();
	
	boolean isAllCardsVisible();
}
