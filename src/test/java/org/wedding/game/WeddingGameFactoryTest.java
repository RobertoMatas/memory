package org.wedding.game;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class WeddingGameFactoryTest {

	GameFactory gameFactory;
	
	@Before
	public void setUp() throws Exception {
		this.gameFactory = new WeddingGameFactory();
	}

	@Test
	public final void testBuild() {
		Game game = gameFactory.build();
		
		assertNotNull(game);
	}

}
