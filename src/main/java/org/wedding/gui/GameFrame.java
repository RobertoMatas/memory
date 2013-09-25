package org.wedding.gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import org.wedding.game.Game;
import org.wedding.game.board.Board;
import org.wedding.game.card.Card;

public class GameFrame extends JFrame implements Observer, ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2128829372548850729L;
	private final Game game;
	private GameCard[][] cards;

	public GameFrame(Game game) {
		this.game = game;
		this.game.addObserver(this);
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		render(this.game.getBoard());
		this.pack();
	}

	private void render(Board board) {
		Card[][] cards = board.getBoard();
		setLayout(new GridLayout(cards.length, cards[0].length));
		this.cards = new GameCard[cards.length][cards[0].length];
		for (int i = 0; i < cards.length; i++) {
			for (int j = 0; j < cards[i].length; j++) {
				this.cards[i][j] = new GameCard(cards[i][j]);
				this.cards[i][j].addActionListener(this);
				add(this.cards[i][j]);
			}
		}

	}

	public void update(Observable o, Object arg) {
	}

	public void actionPerformed(ActionEvent e) {
		GameCard source = (GameCard) e.getSource();
		game.discover(source.getPosition());
	}

}
