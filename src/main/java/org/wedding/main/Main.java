package org.wedding.main;

import org.wedding.game.WeddingGameFactory;
import org.wedding.gui.GameFrame;

public class Main {

	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				GameFrame gameFrame = new GameFrame(new WeddingGameFactory());
				gameFrame.setLocation(300, 50);
				gameFrame.setVisible(true);
			}
		});

	}

}
