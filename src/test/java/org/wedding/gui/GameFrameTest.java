package org.wedding.gui;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.fest.swing.edt.FailOnThreadViolationRepaintManager;
import org.fest.swing.edt.GuiActionRunner;
import org.fest.swing.edt.GuiQuery;
import org.fest.swing.fixture.FrameFixture;
import org.fest.swing.fixture.JButtonFixture;
import org.fest.swing.testing.FestSwingTestCaseTemplate;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.wedding.game.GameFactory;
import org.wedding.stub.StubbedGameFactory;

public class GameFrameTest extends FestSwingTestCaseTemplate {

	private FrameFixture window;
	
	private GameFactory spyGameFactory;

	@BeforeClass
	public static void setUpOnce() {
		FailOnThreadViolationRepaintManager.install();
	}

	@Before
	public void setUp() throws Exception {
		setUpRobot();
		this.spyGameFactory = spy(new StubbedGameFactory());
		GameFrame frame = GuiActionRunner.execute(new GuiQuery<GameFrame>() {
			protected GameFrame executeInEDT() {
				return new GameFrame(spyGameFactory);
			}
		});
		window = new FrameFixture(robot(), frame);
		window.show();
	}

	@Test
	public final void oneClickMakeTheCardVisible() {
		JButtonFixture buttonFixture = window.button("ariza0-0");
		GameCard gameCard = (GameCard) buttonFixture.component();

		buttonFixture.click();

		assertTrue(gameCard.getCard().isVisible());
	}
	
	@Test
	public final void whenUserWinsADialogAppaerWithOptions() {
		winGame();
		
		window.optionPane().requireOptions(new Object[]{ "Si, claro", "Jamás" });
	}
	
	@Test
	public final void userWinsAndSelectNewgame() {
		winGame();
		
		window.optionPane().requireEnabled();
		window.optionPane().buttonWithText("Si, claro").click();
		
		verify(spyGameFactory, times(2)).build();
	}

	private void winGame() {
		window.button("ariza0-0").click();
		window.button("ariza1-0").click();
		window.button("cris0-1").click();
		window.button("cris1-1").click();
	}

	@After
	public void tearDown() {
		cleanUp();
	}

}
