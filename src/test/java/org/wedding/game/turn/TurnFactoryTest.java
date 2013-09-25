package org.wedding.game.turn;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.wedding.game.board.Board;
import org.wedding.game.turn.Turn;
import org.wedding.game.turn.TurnFactory;
import org.wedding.game.turn.TurnFactoryImpl;

@RunWith(MockitoJUnitRunner.class)
public class TurnFactoryTest {

	TurnFactory turnFactory; 
	
	@Mock
	Board board;
	
	@Before
	public void setUp() throws Exception {
		turnFactory = new TurnFactoryImpl();
	}

	@Test
	public final void testBuildFor() {
		Turn turn = turnFactory.buildFor(board);
		
		assertNotNull(turn);
	}

}
