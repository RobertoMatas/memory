package org.wedding.gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;

import org.wedding.game.Game;
import org.wedding.game.GameFactory;
import org.wedding.game.board.Board;
import org.wedding.game.card.Card;

public class GameFrame extends JFrame implements Observer, ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2128829372548850729L;
	private Game game;
	private GameCard[][] cards;
	private final GameFactory gameFactory;

	public GameFrame(GameFactory gameFactory) {
		this.gameFactory = gameFactory;
		initNewGame();
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}

	private void initNewGame() {
		removeGameCardsIfExist();
		this.game = gameFactory.build();
		this.game.addObserver(this);
		render(this.game.getBoard());
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
		this.pack();
	}

	private void removeGameCardsIfExist() {
		if (cards != null) {
			for (int i = 0; i < cards.length; i++) {
				for (int j = 0; j < cards[i].length; j++) {
					this.remove(cards[i][j]);
				}
			}
		}
		
	}

	public void update(Observable o, Object arg) {
		Object[] options = { "Si, claro", "Jamás" };
		int n = JOptionPane.showOptionDialog(this, "Has ganado.\n¿Quieres volver a jugar?", "Champion!", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
		if (n == JOptionPane.YES_OPTION) {
			initNewGame();
			
		} else if (n == JOptionPane.NO_OPTION) {
			System.exit(NORMAL);
		}
	}

	public void actionPerformed(ActionEvent e) {
		GameCard source = (GameCard) e.getSource();
		game.discover(source.getPosition());
	}

}
