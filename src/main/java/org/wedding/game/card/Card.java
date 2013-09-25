package org.wedding.game.card;

public interface Card extends Operations, PropertyChangeEventSource {

	String getName();

	Position getPosition();
}
