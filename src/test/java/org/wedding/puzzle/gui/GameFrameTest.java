package org.wedding.puzzle.gui;

import static org.junit.Assert.assertTrue;

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
import org.wedding.game.Game;
import org.wedding.game.board.GameBoard;
import org.wedding.game.turn.TurnFactoryImpl;
import org.wedding.gui.GameCard;
import org.wedding.gui.GameFrame;
import org.wedding.puzzle.game.stub.BoardGeneratorStub;

public class GameFrameTest extends FestSwingTestCaseTemplate {

	private FrameFixture window;

	@BeforeClass
	public static void setUpOnce() {
		FailOnThreadViolationRepaintManager.install();
	}
	
	@Before
	public void setUp() throws Exception {
		setUpRobot();
		GameFrame frame = GuiActionRunner.execute(new GuiQuery<GameFrame>() {
			protected GameFrame executeInEDT() {
				GameBoard gameBoard = new GameBoard(new BoardGeneratorStub());
				return new GameFrame(new Game(gameBoard, new TurnFactoryImpl()));
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

	@After
	public void tearDown() {
		cleanUp();
	}

}
