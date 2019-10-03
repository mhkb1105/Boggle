package com.mhkb;

import java.io.InputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * An implementation of an immutable dictionary.
 *
 */
public class Dictionary {

	private SortedSet<String> words;

	/**
	 * Reads the dictionary file and stores the words from the file in the Set
	 * this.dictionary. The words in this file are in all lower case.
	 * 
	 * <p>
	 * The dictionary file is named dictionary.txt and needs to be located in the
	 * eecs2030.lab4 package directory.
	 * 
	 * @throws RuntimeException if dictionary.txt cannot be found
	 * 
	 */
	private final void readDictionary() {
		InputStream in = this.getClass().getResourceAsStream("dictionary.txt");
		if (in == null) {
			throw new RuntimeException("dictionary.txt is missing");
		}
		Scanner dictionaryInput = new Scanner(in);
		while (dictionaryInput.hasNext()) {
			String word = dictionaryInput.next();
			this.words.add(word.trim());
		}
		dictionaryInput.close();
	}

	/**
	 * Initializes a dictionary by reading the default dictionary from a file.
	 */
	public Dictionary() {

	}

	/**
	 * Returns the number of words in the dictionary.
	 * 
	 * @return the number of words in the dictionary
	 */
	public int size() {
		return words.size();
	}

	/**
	 * Returns true if the specified word is in the dictionary, and false otherwise.
	 * The case of the specified word is not important; <code>lookUp("hello")</code>
	 * returns the same result as <code>lookUp("HeLLo")</code>.
	 * 
	 * @param word a word to look up in the dictionary
	 * @return true if the specified word is in the dictionary, and false otherwise
	 */
	public boolean lookUp(String word) {
		if (words.contains(word.toLowerCase())) {
			return true;
		}
		return false;
	}

	/**
	 * Returns a new sorted set of all of the words that are in the dictionary
	 * beginning with the specified prefix. The case of the prefix is not important;
	 * <code>wordsStartingWith("a")</code> returns the same result as
	 * <code>wordsStartingWith("A")</code>.
	 * 
	 * @param prefix a string that each word in the returned set must start with
	 * @return a new sorted set of words that are in the dictionary and begin with
	 *         the specified string
	 */
	/*
	 * public SortedSet<String> wordsStartingWith(String prefix) { SortedSet<String>
	 * m; List p = new ArrayList(); for (int i = 0; i < prefix.length(); i++) {
	 * p.add(prefix.charAt(i)); } List k = new ArrayList(); for(int i = 0; i <
	 * words.size(); i++) { k.add(k.get(i)); } for(int i = 0; i < k.size(); i++) {
	 * String f = k.get(i).toString(); List p = new ArrayList(); if(f.charAt(0) ==
	 * p.get(0)) { ((List) m).get(i); } return m; }
	 */

}
