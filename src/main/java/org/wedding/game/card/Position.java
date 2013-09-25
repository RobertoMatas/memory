package org.wedding.game.card;

public class Position {

	public final int x;

	public final int y;

	public Position(final int x, final int y) {
		this.x = x;
		this.y = y;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return String.format("%s-%s", x, y);
	}

}
