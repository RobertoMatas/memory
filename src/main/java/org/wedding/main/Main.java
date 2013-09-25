package org.wedding.main;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.wedding.game.Game;
import org.wedding.game.board.GameBoard;
import org.wedding.game.board.ShuffleBoardGenerator;
import org.wedding.game.turn.TurnFactoryImpl;
import org.wedding.gui.GameFrame;

public class Main {

	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				String arr[] = { "ariza", "cris", "dani",
						"edu", "isma", "javi", "lidia", "marta", "patri",
						"robert", "rocio", "pablo" };
				Set<String> names = new HashSet<String>(Arrays.asList(arr));
				GameBoard gameBoard = new GameBoard(new ShuffleBoardGenerator(names, 6));
				GameFrame gameFrame = new GameFrame(new Game(gameBoard, new TurnFactoryImpl()));
				gameFrame.setBounds(300, 50, 780, 600);
				gameFrame.setVisible(true);
			}
		});

	}

}
