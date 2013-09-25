package org.wedding.game.turn;

import java.util.ArrayList;
import java.util.List;

import org.wedding.game.card.Card;

public class DefaultCardsHandler implements CardsHandler {

	private List<Card> cards;

	int index = 0;

	public DefaultCardsHandler() {
		this.cards = new ArrayList<Card>();
	}

	public void hideAll() {
		new Thread(new Runnable() {

			public void run() {
				try {
					Thread.sleep(900);
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
					for (Card card : cards) {
						card.hide();
					}
				}

			}
		}).start();

	}

	public void blockAll() {
		for (Card card : cards) {
			card.discover();
		}
	}

	public void add(Card card) {
		cards.add(card);
	}

	public boolean areAllSame() {
		return cards.get(0).equals(cards.get(1));
	}
}
