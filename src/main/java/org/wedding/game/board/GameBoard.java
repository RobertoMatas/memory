package org.wedding.game.board;

import java.io.PrintStream;

import org.wedding.game.card.Card;
import org.wedding.game.card.Position;

public class GameBoard implements Board {
	private final Card[][] board;

	public GameBoard(BoardGenerator boardGenerator) {
		this.board = boardGenerator.generate();
	}

	public Card getCardBy(Position position) {
		return board[position.x][position.y];
	}

	public void print(PrintStream writer) {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				writer.print(board[i][j].getName() + "|");
			}
			writer.println();
		}
	}

	public Card[][] getBoard() {
		return this.board;
	}

	public boolean isAllCardsVisible() {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				if (!board[i][j].isVisible()) {
					return false;
				}
			}
		}
		return true;
	}
}