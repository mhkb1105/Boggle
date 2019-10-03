package com.mhkb;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.TreeSet;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.junit.Rule;
import org.junit.rules.Timeout;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DieTest {

	@Rule
	public Timeout globalTimeout = Timeout.seconds(1);

	/**
	 * Makes faces "A", "B", ... for an n-sided die.
	 * 
	 * @param n the number of faces
	 * @return faces "A", "B", ... for an n-sided die @pre. n is greater than 0
	 */
	private String[] makeFaces(int n) {
		String[] faces = new String[n];
		for (int f = 1; f <= n; f++) {
			faces[f - 1] = "" + (char) ('A' + f - 1);
		}
		return faces;
	}

	@Test(expected = IllegalArgumentException.class)
	public void test01_ctorThrows() {
		new Die(new String[0]);
	}

	@Test
	public void test02_getNumberOfFaces() {
		final String[][] FACES = { { "hi" }, { "hi", "bye" }, { "bat", "cat", "hat" },
				{ "bungie", "bethesda", "id", "pavonis", "eidos", "fromsoft" } };
		for (int i = 0; i < FACES.length; i++) {
			Die d = new Die(FACES[i]);
			assertEquals("wrong number of faces; this might be a constructor failure", FACES[i].length,
					d.getNumberOfFaces());
		}
	}

	@Test
	public void test03_roll() {
		for (int n = 2; n <= 10; n++) {
			// make an n-sided die
			String[] faces = makeFaces(n);
			Die d = new Die(faces);

			// roll the die 10000 times and record the results
			Map<String, Integer> rolls = new TreeMap<>();
			for (int i = 0; i < 10000; i++) {
				String s = d.roll();
				if (rolls.containsKey(s)) {
					int count = rolls.get(s);
					rolls.put(s, count + 1);
				} else {
					rolls.put(s, 1);
				}
			}

			// check that the map keys are equal to the faces
			Set<String> expFaces = new TreeSet<>(Arrays.asList(faces));
			assertEquals("unexpected faces", expFaces, rolls.keySet());

			// check that each key appears approximately 10000 / n times
			for (String s : expFaces) {
				int count = rolls.get(s);
				int expCount = 10000 / n;
				assertTrue(Math.abs(expCount - count) < 500); // very
																// conservative
			}
		}
	}

	@Test
	public void test04_getValue() {
		for (int n = 2; n <= 10; n++) {
			// make an n-sided die
			String[] faces = makeFaces(n);
			List<String> faceList = Arrays.asList(faces);
			Collections.shuffle(faceList);
			Die d = new Die(faces);

			// check that the value is a face
			assertTrue("getValue returned a string not on the die", faceList.contains(d.getValue()));
		}
	}

	@Test
	public void test04_rollAndgetValue() {
		for (int n = 2; n <= 10; n++) {
			// make an n-sided die
			String[] faces = makeFaces(n);
			List<String> faceList = Arrays.asList(faces);
			Collections.shuffle(faceList);
			Die d = new Die(faces);

			// check that getValue and roll return the same value
			String roll = d.roll();
			String value = d.getValue();
			assertEquals("roll followed by getValue returned different faces", roll, value);
		}
	}

	@Test
	public void test05_getValueMap() {
		for (int n = 2; n <= 10; n++) {
			// make an n-sided die
			String[] faces = makeFaces(n);
			List<String> faceList = Arrays.asList(faces);
			Collections.shuffle(faceList);
			Die d = new Die(faces);

			SortedMap<Integer, String> exp = new TreeMap<>();
			for (int i = 1; i <= n; i++) {
				exp.put(i, faces[i - 1]);
			}

			// check for the expected map);
			assertEquals("getValueMap returned the wrong map", exp, d.getValueMap());

			// check that the returned map is independent from the die's map
			d.getValueMap().remove(1);
			assertEquals("getValueMap has a privacy leak", exp, d.getValueMap());
		}
	}

	@Test
	public void test06_hashCode() {
		for (int n = 2; n <= 10; n++) {
			// make an n-sided die
			String[] faces = makeFaces(n);
			List<String> faceList = Arrays.asList(faces);
			Collections.shuffle(faceList);
			Die d = new Die(faces);

			int exp = 0;
			for (String s : faces) {
				exp += s.hashCode();
			}
			assertEquals("wrong hashCode", exp, d.hashCode());
		}
	}

	@Test
	public void test07_equals() {
		for (int n = 2; n <= 10; n++) {
			// make an n-sided die
			String[] faces = makeFaces(n);
			Die d = new Die(faces);

			assertTrue("die should be equal to itself", d.equals(d));
			assertFalse("die should not be equal to null", d.equals(null));
			assertFalse("die should not be equal to a String", d.equals("hello"));
		}
	}

	@Test
	public void test08_equals() {
		for (int n = 2; n <= 10; n++) {
			// make 2 n-sided dice
			String[] faces = makeFaces(n);
			List<String> faceList = Arrays.asList(faces);
			Collections.shuffle(faceList);
			Die d1 = new Die(faces);

			faces = makeFaces(n);
			Die d2 = new Die(faces);

			while (!d1.getValue().equals(d2.getValue())) {
				d1.roll();
				if (d1.getValue().equals(d2.getValue())) {
					assertEquals("d1 and d2 have same values but equals returned false", d1, d2);
				} else {
					assertNotEquals("d1 and d2 have different values but equals returned true", d1, d2);
				}
			}
		}
	}

	@Test
	public void test09_equals() {
		for (int n = 2; n <= 10; n++) {
			// make 2 n-sided dice with different faces
			String[] faces = makeFaces(n);
			List<String> faceList = Arrays.asList(faces);
			Collections.shuffle(faceList);
			Die d1 = new Die(faces);

			faces = makeFaces(n);
			faces[0] = faces[0] + "*";
			Die d2 = new Die(faces);

			for (int i = 0; i < 100; i++) {
				d1.roll();
				assertNotEquals("d1 and d2 have different faces but equals returned true", d1, d2);
			}
		}
	}

	@Test
	public void test10_equals() {
		final String[][] FACES = { { "hi" }, { "hi", "bye" }, { "bat", "cat", "hat" },
				{ "bungie", "bethesda", "id", "pavonis", "eidos", "fromsoft" } };
		final String[] EXP = { "hi", "hi, bye", "bat, cat, hat", "bungie, bethesda, id, pavonis, eidos, fromsoft" };
		for (int i = 0; i < FACES.length; i++) {
			Die d = new Die(FACES[i]);
			assertEquals("toString returned the wrong string", EXP[i], d.toString());
		}
	}

	@Test
	public void test12_copyCtor() {
		for (int n = 2; n <= 10; n++) {
			// make 2 n-sided dice with different faces
			String[] faces = makeFaces(n);
			List<String> faceList = Arrays.asList(faces);
			Collections.shuffle(faceList);
			Die d1 = new Die(faces);

			Die d2 = new Die(d1);
			assertEquals("copy constructor did not create an equal die", d1, d2);
		}
	}
}
