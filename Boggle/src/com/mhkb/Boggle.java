package com.mhkb;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.SortedMap;

/**
 * A class that models how the game of Boggle is played. A Boggle object is a
 * composition of a Dictionary and a composition of a List of 16 Die objects.
 *
 */
public class Boggle {

	/**
	 * The letters on the 16 boggle dice (strings for one die on each line).
	 */
	private static final String[][] LETTERS = { { "A", "A", "E", "E", "G", "N" }, { "E", "L", "R", "T", "T", "Y" },
			{ "W", "A", "O", "O", "T", "T" }, { "A", "B", "B", "J", "O", "O" }, { "E", "H", "R", "T", "V", "W" },
			{ "C", "I", "M", "O", "T", "U" }, { "D", "I", "S", "T", "T", "Y" }, { "E", "I", "O", "S", "S", "T" },
			{ "Y", "D", "E", "L", "R", "V" }, { "A", "C", "H", "O", "P", "S" }, { "U", "H", "I", "M", "N", "QU" },
			{ "E", "E", "I", "N", "S", "U" }, { "E", "E", "G", "H", "N", "W" }, { "A", "F", "F", "K", "P", "S" },
			{ "H", "L", "N", "N", "R", "Z" }, { "X", "D", "E", "I", "L", "R" } };
	public static int NUMBER_OF_DICE = 16;

	/**
	 * The 16 boggle dice.
	 */
	private List<Die> dice;

	/**
	 * The dictionary.
	 */
	private Dictionary dictionary;

	/**
	 * Initializes a Boggle game by creating the 16 standard boggle dice and a
	 * dictionary.
	 * 
	 */
	public Boggle() {
		for (int i = 0; i < Boggle.NUMBER_OF_DICE; i++) {
			String[] faces = Boggle.LETTERS[i];
			dice.get(i);
		}
	}

	/**
	 * Returns a new list of the 16 dice in their current state. The order of dice
	 * is guaranteed to be stable between calls to <code>shuffleAndRoll</code>; in
	 * other words, all lists returned by this method are equal between calls to
	 * <code>shuffleAndRoll</code> (assuming that the returned lists and the dice in
	 * the lists are not modified).
	 * 
	 * <p>
	 * Clients are unable to modify the game dice using the returned list; i.e.,
	 * modifying the returned list has no effect on the dice held by the Boggle
	 * object, and modifying the dice in the returned list has no effect on the dice
	 * held by the Boggle object.
	 * 
	 * @return a list of the 16 dice in their current state; modifying the list or
	 *         the dice in the list does not modify the state of the Boggle dice
	 */
	public List<Die> getDice() {
		return this.dice;

	}

	/**
	 * Randomly shuffles the order of the dice and rolls all of dice. This simulates
	 * the shaking of the dice in the physical version of the game.
	 * 
	 * <p>
	 * Note to students: You can randomly shuffle the list of dice by using the
	 * method <code>Collections.shuffle</code>. You should then roll each die in the
	 * list.
	 */
	public void shuffleAndRoll() {
		SortedMap<Integer, String> valueMap = null;
		Collections.shuffle(dice);
		List keys = new ArrayList(valueMap.keySet());
		Collections.shuffle(keys);
		for (Object o : keys) {
			valueMap.get(o);
		}

	}

	/**
	 * Returns true if the specified string is a legal Boggle word, and false
	 * otherwise. A legal Boggle word is at least 3 letters long and can be found in
	 * the dictionary.
	 * 
	 * @param s a string
	 * @return true if the specified string is a legal Boggle word, and false
	 *         otherwise
	 */
	public boolean isABoggleWord(String s) {
		if (s.length() < 3) {
			return false;
		}
		if (dictionary.lookUp(s) == true) {
			return true;
		}
		return false;

	}
}