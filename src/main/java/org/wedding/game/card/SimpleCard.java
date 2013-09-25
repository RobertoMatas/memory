package org.wedding.game.card;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class SimpleCard implements Card {
	private final PropertyChangeSupport propertyChangeSupport;
	private final String name;
	private final Position position;
	private boolean visible, discovered;

	public SimpleCard(final String name, final Position position) {
		this.name = name;
		this.position = position;
		this.visible = false;
		this.discovered = false;
		this.propertyChangeSupport = new PropertyChangeSupport(this);
	}

	public void show() {
		setVisibility(true);
	}

	public void hide() {
		setVisibility(false);
	}

	private void setVisibility(boolean visible) {
		boolean oldValue = this.visible;
		this.visible = visible;
		propertyChangeSupport.firePropertyChange("visible", oldValue, visible);
	}

	public String getName() {
		return this.name;
	}

	public Position getPosition() {
		return this.position;
	}

	public boolean isVisible() {
		return visible;
	}

	public void addPropertyChangeListener(PropertyChangeListener listener) {
		propertyChangeSupport.addPropertyChangeListener(listener);

	}

	public boolean isDiscovered() {
		return this.discovered;
	}

	public void discover() {
		this.discovered = true;
		propertyChangeSupport.firePropertyChange("discovered", false, true);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SimpleCard other = (SimpleCard) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
}
