package org.wedding.puzzle.game.board;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.wedding.game.board.BoardGenerator;
import org.wedding.game.board.ShuffleBoardGenerator;
import org.wedding.game.card.Card;

public class ShuffleBoardGeneratorTest {

	private static final int COLUMNS = 4;
	BoardGenerator boardGenerator;
	private Set<String> cardNames;
	private final String names[] = { "1", "2", "3", "4" };

	@Before
	public void setUp() throws Exception {
		cardNames = new HashSet<String>(Arrays.asList(names));
		boardGenerator = new ShuffleBoardGenerator(cardNames, COLUMNS);
	}

	@Test
	public final void generateDuplicateInputNames() {
		Card[][] board = boardGenerator.generate();

		assertThatBoardContainsTwiceEachCardName(board);
	}

	@Test
	public final void generateBuildAMatrixWithNumberOfColumnsPassed() {
		Card[][] board = boardGenerator.generate();

		assertThat(board[0].length, is(COLUMNS));
		assertThat(board.length, is(rows()));
	}

	@Test(expected = IllegalArgumentException.class)
	public final void generateOnlyAcceptEvenNumberOfNames() {
		cardNames.add("5");

		boardGenerator.generate();

	}

	private int rows() {
		return 2;
	}

	private void assertThatBoardContainsTwiceEachCardName(Card[][] board) {
		for (int i = 0; i < names.length; i++) {
			int count = count(board, names[i]);
			assertThat(names[i] + " no aparece dos veces", count, is(2));
		}
	}

	private int count(Card[][] board, String name) {
		int count = 0;
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				if (board[i][j].getName().equals(name)) {
					count++;
				}
			}
		}
		return count;
	}

}
