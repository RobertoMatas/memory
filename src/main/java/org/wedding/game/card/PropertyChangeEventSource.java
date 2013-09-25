package org.wedding.game.card;

import java.beans.PropertyChangeListener;

public interface PropertyChangeEventSource {

	public abstract void addPropertyChangeListener(PropertyChangeListener listener);

}