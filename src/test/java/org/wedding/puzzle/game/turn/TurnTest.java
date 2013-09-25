package org.wedding.puzzle.game.turn;

import static org.junit.Assert.assertFalse;
import static org.mockito.Matchers.notNull;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.wedding.game.board.Board;
import org.wedding.game.card.Card;
import org.wedding.game.card.Position;
import org.wedding.game.turn.CardsHandler;
import org.wedding.game.turn.Turn;
import org.wedding.game.turn.TwoMovesTurn;

@RunWith(MockitoJUnitRunner.class)
public class TurnTest {

	Turn turn, spyTurn;
	@Mock
	Board board;
	@Mock
	Card card;
	@Mock
	CardsHandler cardsHandler;

	@Before
	public void setUp() throws Exception {
		when(board.getCardBy(notNull(Position.class))).thenReturn(card);
		turn = new TwoMovesTurn(board, cardsHandler);
		spyTurn = spy(turn);
	}

	@Test
	public void discoverShowCardWhenIsNotVisibleAndIsNotDiscoveredYet() {
		when(card.isVisible()).thenReturn(false);
		when(card.isDiscovered()).thenReturn(false);
		
		turn.discover(new Position(0, 0));
		
		verify(card).show();
	}

	@Test
	public void testIsFinished() {
		boolean finished = turn.isFinished();
		
		assertFalse(finished);
	}
	
	@Test
	public void endFinalizeWithoutCoupleDiscover() {
		when(spyTurn.isFinished()).thenReturn(true);
		when(card.isVisible()).thenReturn(false);
		when(card.isDiscovered()).thenReturn(false);
		when(cardsHandler.areAllSame()).thenReturn(false);
		
		spyTurn.discover(new Position(0, 0));
		spyTurn.discover(new Position(0, 1));
		
		spyTurn.end();
		
		verify(cardsHandler).hideAll();
	}

	@Test
	public void endFinalizeWithCoupleDiscover() {
		when(spyTurn.isFinished()).thenReturn(true);
		when(card.isVisible()).thenReturn(false);
		when(card.isDiscovered()).thenReturn(false);
		when(cardsHandler.areAllSame()).thenReturn(true);
		
		spyTurn.discover(new Position(0, 0));
		spyTurn.discover(new Position(0, 1));
		
		spyTurn.end();
		
		verify(cardsHandler).blockAll();
	}
	
	@Test(expected = IllegalStateException.class)
	public void endThrowExceptionWhenIsCalledAndTurnNotIsFinishedYet() {
		turn.end();
	}
}
