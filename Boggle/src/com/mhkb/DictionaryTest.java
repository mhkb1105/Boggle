package com.mhkb;

import static org.junit.Assert.*;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.junit.Rule;
import org.junit.rules.Timeout;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.SortedSet;
import java.util.TreeSet;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DictionaryTest {

	@Rule
	public Timeout globalTimeout = Timeout.seconds(3);

	private static final int NUMBER_OF_WORDS = 370101;

	private static final List<String> WORDS = Collections.unmodifiableList(DictionaryTest.readDictionary());

	private static List<String> readDictionary() {
		List<String> result = new ArrayList<>();
		InputStream in = DictionaryTest.class.getResourceAsStream("dictionary.txt");
		if (in == null) {
			throw new RuntimeException("dictionary.txt is missing");
		}
		try (BufferedReader br = new BufferedReader(new InputStreamReader(in))) {
			for (String line; (line = br.readLine()) != null;) {
				result.add(line.trim());
			}
		} catch (Exception ex) {
			fail("can't read dictionary.txt");
		}
		return result;
	}

	@Test
	public void test00_ctor() {
		assertEquals("can't read dictionary.txt", NUMBER_OF_WORDS, WORDS.size());

		Dictionary d = new Dictionary();
		assertEquals("dictionary has the wrong number of words", NUMBER_OF_WORDS, d.size());
	}

	@Test
	public void test01_lookUp() {
		assertEquals("can't read dictionary.txt", NUMBER_OF_WORDS, WORDS.size());

		// look up some random words that should be in the dictionary
		Dictionary d = new Dictionary();
		final int N = 1000;
		Random rng = new Random();
		for (int i = 0; i < N; i++) {
			String word = WORDS.get(rng.nextInt(WORDS.size()));
			assertTrue(word + " is in the dictionary but lookUp returned false", d.lookUp(word));

			// check case insensitivity
			word = word.toUpperCase();
			assertTrue(word + " is in the dictionary but lookUp returned false", d.lookUp(word));
		}
	}

	@Test
	public void test02_lookUp() {
		assertEquals("can't read dictionary.txt", NUMBER_OF_WORDS, WORDS.size());

		// look up some random words that are not in the dictionary
		final String[] NOT_WORDS = { "ablz", "bh", "cse", "eecs", "lol" };
		Dictionary d = new Dictionary();
		for (int i = 0; i < NOT_WORDS.length; i++) {
			String word = NOT_WORDS[i];
			assertFalse(word + " is not in the dictionary but lookUp returned true", d.lookUp(word));

			// check case insensitivity
			word = word.toUpperCase();
			assertFalse(word + " is not in the dictionary but lookUp returned true", d.lookUp(word));
		}
	}

	@Test
	public void test03_size() {
		assertEquals("can't read dictionary.txt", NUMBER_OF_WORDS, WORDS.size());

		Dictionary d = new Dictionary();
		assertEquals("dictionary has the wrong number of words", NUMBER_OF_WORDS, d.size());
	}

	@Test
	public void test04_wordsStartingWith() {
		final String[] PREFIX = { "cagel", "prici", "thesis", "uf", "zz" };
		final String[][] EXP = { { "cageless", "cagelike", "cageling", "cagelings" },
				{ "pricier", "priciest", "pricing" }, { "thesis" },
				{ "ufer", "ufo", "ufologies", "ufologist", "ufology", "ufos", "ufs" }, {} };
		/*
		 * Dictionary d = new Dictionary(); for (int i = 0; i < PREFIX.length; i++) {
		 * SortedSet<String> exp = new TreeSet<>(Arrays.asList(EXP[i]));
		 * assertEquals("wordsStartingWith(\"" + PREFIX[i] + "\") failed", exp,
		 * d.wordsStartingWith(PREFIX[i])); }
		 */
	}
}
