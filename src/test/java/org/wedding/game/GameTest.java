package org.wedding.game;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Observable;
import java.util.Observer;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.wedding.game.Game;
import org.wedding.game.board.Board;
import org.wedding.game.card.Position;
import org.wedding.game.turn.Turn;
import org.wedding.game.turn.TurnFactory;

@RunWith(MockitoJUnitRunner.class)
public class GameTest {

	Game game;
	@Mock
	Turn turn;
	@Mock
	TurnFactory turnFactory;
	@Mock
	Board board;

	@Before
	public void setUp() throws Exception {
		when(turnFactory.buildFor(board)).thenReturn(turn);
		game = new Game(board, turnFactory);
	}

	@Test
	public void discoverCollaborationWithTurn() {
		Position p = new Position(0, 0);

		game.discover(p);

		verify(turn).discover(p);
	}

	@Test
	public void gameCallsEndTurnIfTurnIsFinished() {
		Position p = new Position(0, 0);
		when(turn.isFinished()).thenReturn(true);

		game.discover(p);

		InOrder inOrder = Mockito.inOrder(turn);
		inOrder.verify(turn).discover(p);
		inOrder.verify(turn).isFinished();
		inOrder.verify(turn).end();
	}

	@Test
	public void whenTurnIsFinishedGameEndedIsChecked() {
		Position p = new Position(0, 0);
		when(turn.isFinished()).thenReturn(true);

		game.discover(p);

		verify(board).isAllCardsVisible();
	}

	@Test
	public void whenTurnIsFinishedAndGameIsNotFinishedNewTurnIsCreated() {
		Position p = new Position(0, 0);
		when(turn.isFinished()).thenReturn(true);
		when(board.isAllCardsVisible()).thenReturn(false);

		game.discover(p);

		InOrder inOrder = Mockito.inOrder(board, turnFactory);
		inOrder.verify(board).isAllCardsVisible();
		inOrder.verify(turnFactory).buildFor(board);
	}

	@Test
	public void whenGameIsFinishedTheObserversAreNotified() {
		Position p = new Position(0, 0);
		when(turn.isFinished()).thenReturn(true);
		when(board.isAllCardsVisible()).thenReturn(true);
		AnObserver observer = new AnObserver();
		game.addObserver(observer);

		game.discover(p);

		assertTrue(observer.isNotified());
	}

	@Test
	public void testGetBoard() {
		Board board2 = game.getBoard();

		assertSame(board2, board);
	}

	private class AnObserver implements Observer {

		boolean notified = false;

		public void update(Observable o, Object arg) {
			this.notified = true;

		}

		public boolean isNotified() {
			return notified;
		}

	}
}
