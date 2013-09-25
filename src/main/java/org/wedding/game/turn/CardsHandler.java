package org.wedding.game.turn;

import org.wedding.game.card.Card;

public interface CardsHandler {

	void hideAll();
	
	void blockAll();
	
	void add(Card card);
	
	boolean areAllSame();
}
