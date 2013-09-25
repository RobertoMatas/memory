package org.wedding.game.card;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import org.junit.Before;
import org.junit.Test;
import org.wedding.game.card.Card;
import org.wedding.game.card.Position;
import org.wedding.game.card.SimpleCard;

public class CardTest {
	private final Position POSITION = new Position(1, 2);
	private final String NAME = "aName";

	Card card;

	@Before
	public void setUp() {
		this.card = new SimpleCard(NAME, POSITION);
	}

	@Test
	public void testShow() {

		card.show();

		assertThat(card.isVisible(), is(true));
	}

	@Test
	public void testHide() {
		card.hide();

		assertThat(card.isVisible(), is(false));
	}

	@Test
	public void testGetName() {
		String name = card.getName();

		assertThat(name, is(NAME));
	}

	@Test
	public void testGetPosition() {
		Position position = card.getPosition();

		assertThat(position, is(POSITION));
	}

	@Test
	public void listenerAreNotifiedWhenVisiblePropertyChange() {
		PropertyChangeListenerFake listener = new PropertyChangeListenerFake();
		card.addPropertyChangeListener(listener);
		card.show();

		assertTrue(listener.isNotified());
	}
	
	@Test
	public void testTwoCardAreEqualsIfHaveSameName() {
		Card card1 = new SimpleCard("card1", new Position(0, 0));
		Card card2 = new SimpleCard("card1", new Position(0, 1));
		
		assertThat(card1, equalTo(card2));
	}

	@Test
	public void discoverMarksACardLikeDiscover() {
		assertFalse(card.isDiscovered());

		card.discover();

		assertTrue(card.isDiscovered());
	}

	private class PropertyChangeListenerFake implements PropertyChangeListener {

		private boolean notified = false;

		public void propertyChange(PropertyChangeEvent evt) {
			notified = true;
		}

		public boolean isNotified() {
			return notified;
		}

	}
}
