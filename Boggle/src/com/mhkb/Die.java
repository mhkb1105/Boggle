package com.mhkb;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.SortedMap;

/**
 * A class that represents an n-sided die where the sides are decorated with a
 * string. Every Die has at least one face.
 * 
 * <p>
 * Implementation Details: Every n-sided Die object has-a sorted map that stores
 * the mapping between the face number 1, 2, ..., n to the corresponding face
 * string. For example, a 4-sided die whose face strings are "ONE", "TWO",
 * "THREE" and "FOUR" would have the following map:
 * 
 * <table summary="Map of face numbers to face strings">
 * <tr>
 * <th>Key&nbsp;&nbsp;&nbsp;</th>
 * <th>Value</th>
 * </tr>
 * <tr>
 * <td>1</td>
 * <td>"ONE"</td>
 * </tr>
 * <tr>
 * <td>2</td>
 * <td>"TWO"</td>
 * </tr>
 * <tr>
 * <td>3</td>
 * <td>"THREE"</td>
 * </tr>
 * <tr>
 * <td>4</td>
 * <td>"FOUR"</td>
 * </tr>
 * </table>
 * 
 * <p>
 * The die also stores it current value as an integer between 1 and n. To return
 * the current value of the die, the die uses its current value as a key and
 * returns the corresponding value in the map (the current face string).
 * 
 * <p>
 * To roll a die, the die sets its current value to a random value between 1 and
 * n, and returns the string of the current face.
 * 
 */
public class Die {

	private SortedMap<Integer, String> valueMap;

	/**
	 * Initializes an n-sided die where the sides are decorated with the strings in
	 * the specified array. Each string in the array is assigned to exactly one face
	 * of the die. The die will have as many faces as there are strings in the
	 * array. For example:
	 * 
	 * <pre>
	 * String[] faces = { "A", "A", "E", "E", "G", "N" };
	 * Die d = new Die(faces);
	 * </pre>
	 * 
	 * <p>
	 * would construct a 6-sided die where the sides are lettered <code>"A"</code>,
	 * <code>"A"</code>, <code>"E"</code>, <code>"E"</code>, <code>"G"</code>, and
	 * <code>"N"</code>.
	 * 
	 * <p>
	 * The mapping of the letters to the die faces is unspecified; all that is
	 * guaranteed is that each letter in the given string is mapped to one and only
	 * one face of the die.
	 * 
	 * <p>
	 * The current value of the die is unspecified; calling <code>getValue()</code>
	 * immediately after constructing a die could return any face that belongs to
	 * the die.
	 * 
	 * @param faces an array of strings, one string for each face of the die
	 * 
	 * @throws IllegalArgumentException if faces.length == 0
	 * 
	 */
	public Die(String[] faces) {
		if (faces.length == 0) {
			throw new IllegalArgumentException();
		}
		for (int i = 0; i < faces.length; i++) {
			valueMap.put(i, Arrays.asList(faces).get(i));
		}
	}

	/**
	 * Construct an independent copy of an existing die. The new die will have the
	 * same strings on the same faces as the existing die.
	 * 
	 * <p>
	 * The current value of this die will be the same as the other die.
	 * 
	 * @param other the die to copy
	 */
	public Die(Die other) {
		this.valueMap = other.valueMap;

	}

	/**
	 * Returns the number of faces that this die has.
	 * 
	 * @return the number of faces that this die has
	 */
	public int getNumberOfFaces() {
		return valueMap.size();

	}

	/**
	 * Rolls the die to a new random face, and returns the string on the face.
	 * 
	 * @return the string on face after rolling the die
	 */
	public String roll() {
		List keys = new ArrayList(valueMap.keySet());
		Collections.shuffle(keys);
		for (Object o : keys) {
			valueMap.get(o);
		}
		return valueMap.get(4);

	}

	/**
	 * Returns the string corresponding to the current face value of the die.
	 * 
	 * @return the string corresponding to the current face value of the die
	 */
	public String getValue() {
		return this.roll();
	}

	/**
	 * Returns the mapping of face numbers to strings for this die. The faces are
	 * numbered using the <code>Integer</code> values <code>1</code> through
	 * <code>n</code> where <code>n</code> is the number of sides of the die, and
	 * the returned map is sorted on its keys (the face numbers). For example, the
	 * die with faces:
	 * 
	 * <p>
	 * <code>1, 2, 3, 4, 5, 6</code>
	 * 
	 * <p>
	 * having face strings:
	 * 
	 * <p>
	 * <code>"C", "M", "I", "O", "U", "T"</code>
	 * 
	 * <p>
	 * would return the map whose <code>toString</code> method would produce the
	 * following string:
	 * 
	 * <p>
	 * <code>{1=C, 2=M, 3=I, 4=O, 5=U, 6=T}</code>
	 * 
	 * <p>
	 * Clients are unable to modify the mapping of faces to letters using the
	 * returned map; i.e., modifying the returned map has no effect on the die.
	 * 
	 * @return a sorted map of the faces to letters
	 */
	public SortedMap<Integer, String> getValueMap() {
		List map = new ArrayList();
		for (int i = 0; i < this.getNumberOfFaces(); i++) {
			valueMap.put(i, this.getValue());
		}
		return valueMap;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((valueMap == null) ? 0 : valueMap.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Die other = (Die) obj;
		if (valueMap == null) {
			if (other.valueMap != null)
				return false;
		} else if (!valueMap.equals(other.valueMap))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Die [valueMap=" + valueMap + "]";
	}

}
