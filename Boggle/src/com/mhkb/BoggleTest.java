package com.mhkb;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.SortedMap;

import org.junit.Test;

import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.junit.Rule;
import org.junit.rules.Timeout;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BoggleTest {

	private static final String[][] LETTERS = { { "A", "A", "E", "E", "G", "N" }, { "E", "L", "R", "T", "T", "Y" },
			{ "W", "A", "O", "O", "T", "T" }, { "A", "B", "B", "J", "O", "O" }, { "E", "H", "R", "T", "V", "W" },
			{ "C", "I", "M", "O", "T", "U" }, { "D", "I", "S", "T", "T", "Y" }, { "E", "I", "O", "S", "S", "T" },
			{ "Y", "D", "E", "L", "R", "V" }, { "A", "C", "H", "O", "P", "S" }, { "U", "H", "I", "M", "N", "QU" },
			{ "E", "E", "I", "N", "S", "U" }, { "E", "E", "G", "H", "N", "W" }, { "A", "F", "F", "K", "P", "S" },
			{ "H", "L", "N", "N", "R", "Z" }, { "X", "D", "E", "I", "L", "R" } };

	@Rule
	public Timeout globalTimeout = Timeout.seconds(1);

	@Test
	public void test00_field() {
		assertEquals("NUMBER_OF_DICE is incorrect", 16, Boggle.NUMBER_OF_DICE);
	}

	@Test
	public void test01_getDice() {
		Boggle b = new Boggle();
		List<Die> got = b.getDice();

		assertEquals("there should be 16 dice", 16, got.size());
	}

	@Test
	public void test02_getDice() {
		Boggle b = new Boggle();
		List<Die> dice = b.getDice();

		// check that the 16 dice contain the correct faces
		for (String[] faces : LETTERS) {
			List<String> facesList = new ArrayList<>(Arrays.asList(faces));
			Collections.sort(facesList);

			List<List<String>> allDiceFaces = new ArrayList<>();
			for (Die d : dice) {
				SortedMap<Integer, String> map = d.getValueMap();
				List<String> dieFaces = new ArrayList<>(map.values());
				Collections.sort(dieFaces);
				allDiceFaces.add(dieFaces);
			}
			assertTrue("dice with faces " + Arrays.asList(faces) + " is missing", allDiceFaces.contains(facesList));
		}
	}

	@Test
	public void test03_getDice() {
		Boggle b = new Boggle();
		List<Die> dice1 = b.getDice();
		List<Die> dice2 = b.getDice();
		assertNotSame("repeated calls to getDice return a reference to the same list", dice1, dice2);
	}

	@Test
	public void test04_getDice() {
		Boggle b = new Boggle();
		List<Die> dice1 = b.getDice();
		List<Die> dice2 = b.getDice();
		assertEquals("repeated calls to getDice returned unequal lists", dice1, dice2);
	}

	@Test
	public void test05_getDice() {
		Boggle b = new Boggle();
		List<Die> dice1 = b.getDice();
		List<Die> dice2 = b.getDice();
		for (int i = 0; i < dice1.size(); i++) {
			Die d1 = dice1.get(i);
			Die d2 = dice2.get(i);
			assertNotSame("lists contain references to the same Die objects", d1, d2);
		}
	}

	@Test
	public void test06_shuffleAndRoll() {
		Boggle b = new Boggle();
		b.shuffleAndRoll();
		List<Die> dice = b.getDice();
		assertEquals("there should be 16 dice", 16, dice.size());

		// check that the 16 dice contain the correct faces
		for (String[] faces : LETTERS) {
			List<String> facesList = new ArrayList<>(Arrays.asList(faces));
			Collections.sort(facesList);

			List<List<String>> allDiceFaces = new ArrayList<>();
			for (Die d : dice) {
				SortedMap<Integer, String> map = d.getValueMap();
				List<String> dieFaces = new ArrayList<>(map.values());
				Collections.sort(dieFaces);
				allDiceFaces.add(dieFaces);
			}
			assertTrue("dice with faces " + Arrays.asList(faces) + " is missing", allDiceFaces.contains(facesList));
		}
	}

	@Test
	public void test07_shuffleAndRoll() {
		Boggle b = new Boggle();
		List<Die> dice1 = b.getDice();
		b.shuffleAndRoll();
		List<Die> dice2 = b.getDice();
		if (dice1.equals(dice2)) {
			b.shuffleAndRoll();
			dice2 = b.getDice();
			if (dice1.equals(dice2)) {
				fail("shuffleAndRoll failed to shuffle and roll the dice");
			}
		}
	}

	@Test
	public void test08_isABoggleWord() {
		final String[] WORDS = { "CAT", "zygosity", "teamaker", "dRaStic" };
		Boggle b = new Boggle();
		for (String word : WORDS) {
			assertEquals(word + " is a Boggle word", true, b.isABoggleWord(word));
		}
	}

	@Test
	public void test09_isABoggleWord() {
		final String[] NOT_WORDS = { "czt", "ab", "eecs", "zzzzzzz" };
		Boggle b = new Boggle();
		for (String word : NOT_WORDS) {
			assertEquals(word + " is not a Boggle word", false, b.isABoggleWord(word));
		}
	}
}
