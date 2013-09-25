package org.wedding.game.board;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

import java.io.PrintStream;

import org.junit.Before;
import org.junit.Test;
import org.wedding.game.board.Board;
import org.wedding.game.board.BoardGenerator;
import org.wedding.game.board.GameBoard;
import org.wedding.game.card.Card;
import org.wedding.game.card.Position;
import org.wedding.stub.BoardGeneratorStub;

public class GameBoardTest {

	Board board;
	BoardGenerator boardGenerator;
	private Card[][] cards;

	@Before
	public void setUp() throws Exception {
		boardGenerator = new BoardGeneratorStub();
		board = new GameBoard(boardGenerator);
		cards = board.getBoard();
	}

	@Test
	public void testGetCardBy() {
		Card card = board.getCardBy(new Position(1, 0));

		assertThat(card.getName(), is("ariza"));
	}

	@Test
	public void testGetBoard() {
		Card[][] board2 = board.getBoard();

		assertNotNull(board2);
	}

	@Test
	public void allCardsVisibleReturnsFalse() {
		assertFalse(board.isAllCardsVisible());
	}

	@Test
	public void testPrintBoard() {
		PrintStream spyps = spy(System.out);
		doNothing().when(spyps).print(anyString());
		doNothing().when(spyps).println();

		((GameBoard) board).print(spyps);

		verify(spyps, atLeastOnce()).print(anyString());
		verify(spyps, atLeastOnce()).println();
	}

	@Test
	public void allCardsVisibleReturnsTrue() {
		showAllCard();
		
		assertTrue(board.isAllCardsVisible());
	}

	private void showAllCard() {
		for (int i = 0; i < cards.length; i++) {
			for (int j = 0; j < cards[i].length; j++) {
				cards[i][j].show();
			}
		}
	}
}