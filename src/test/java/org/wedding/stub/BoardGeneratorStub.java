package org.wedding.stub;

import org.wedding.game.board.BoardGenerator;
import org.wedding.game.card.Card;
import org.wedding.game.card.Position;
import org.wedding.game.card.SimpleCard;

public class BoardGeneratorStub implements BoardGenerator {

	public Card[][] generate() {
		Card[][] cards = new Card[2][2];
		cards[0][0] = new SimpleCard("ariza", new Position(0, 0));
		cards[0][1] = new SimpleCard("cris", new Position(0, 1));
		cards[1][0] = new SimpleCard("ariza", new Position(1, 0));
		cards[1][1] = new SimpleCard("cris", new Position(1, 1));
		return cards;
	}

}
