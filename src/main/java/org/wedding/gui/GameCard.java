package org.wedding.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.net.URL;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.plaf.basic.BasicBorders.ButtonBorder;

import org.wedding.game.card.Card;
import org.wedding.game.card.Position;

public class GameCard extends JButton implements PropertyChangeListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6738350809142514732L;
	private final Card card;

	public GameCard(Card card) {
		this.card = card;
		setName(card.getName() + card.getPosition());
		Icon image = getImage("back");
		this.setIcon(image);
		this.setBackground(new Color(103, 174, 245));
		setSize(image);
		setBorder(Color.WHITE);
		this.card.addPropertyChangeListener(this);
	}

	private void setSize(Icon image) {
		this.setMinimumSize(new Dimension(image.getIconWidth(), image
				.getIconHeight()));
		this.setMaximumSize(new Dimension(image.getIconWidth(), image
				.getIconHeight()));
	}

	private Icon getImage(String name) {
		ClassLoader classLoader = this.getClass().getClassLoader();
		URL resource = classLoader.getResource(name + ".png");
		return new ImageIcon(resource);
	}

	public Position getPosition() {
		return this.card.getPosition();
	}

	public void propertyChange(PropertyChangeEvent evt) {
		Card card = (Card) evt.getSource();
		if ("discovered".equals(evt.getPropertyName())) {
			setBorder(Color.MAGENTA);
		} else {
			if (card.isVisible()) {
				this.setIcon(getImage(this.card.getName()));
				setBorder(Color.YELLOW);
			} else {
				this.setIcon(getImage("back"));
				setBorder(Color.WHITE);
			}
		}

	}

	private void setBorder(Color color) {
		this.setBorder(new ButtonBorder(color, color, color, color));
		this.setBorderPainted(true);
	}

	/**
	 * @return the card
	 */
	public Card getCard() {
		return card;
	}
}
