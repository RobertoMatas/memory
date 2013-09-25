package org.wedding.game.board;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.wedding.game.card.Card;
import org.wedding.game.card.Position;
import org.wedding.game.card.SimpleCard;

public class ShuffleBoardGenerator implements BoardGenerator {

	private final Set<String> cardNames;
	private final int columns;

	public ShuffleBoardGenerator(final Set<String> cardNames, final int columns) {
		this.cardNames = cardNames;
		this.columns = columns;
	}

	public Card[][] generate() {
		validateInput();
		List<String> duplicate = duplicate(cardNames);
		Collections.shuffle(duplicate);
		return fillMatrixBoard(duplicate);
	}

	private Card[][] fillMatrixBoard(List<String> duplicate) {
		int rowNumber = 0, colNumber = 0;
		int rows = duplicate.size() / columns, jumpToNextRow = columns - 1;
		Card[][] board = new Card[rows][columns];
		for (String name : duplicate) {
			board[rowNumber][colNumber] = new SimpleCard(name, new Position(rowNumber, colNumber));
			colNumber++;
			if (colNumber > jumpToNextRow) {
				colNumber = 0;
				rowNumber++;
			}
		}
		return board;
	}

	private void validateInput() {
		if (notIsEven(cardNames.size()))
			throw new IllegalArgumentException("El número de cartas ha de ser par");
	}

	private List<String> duplicate(Set<String> cardNames) {
		String[] names = new String[cardNames.size()];
		cardNames.toArray(names);
		List<String> duplicate = new ArrayList<String>(cardNames);
		Collections.addAll(duplicate, names);
		return duplicate;
	}

	private boolean notIsEven(int number) {
		return number % 2 != 0;
	}
}
