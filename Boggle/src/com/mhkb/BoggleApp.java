package com.mhkb;

/**
 * The Boggle application.
 * 
 */
public class BoggleApp {

	public static void main(String[] args) {
		BoggleController c = new BoggleController();
		Boggle m = new Boggle();
		BoggleView v = new BoggleView(c);
		c.setModel(m);
		c.setView(v);
		v.setVisible(true);
	}
}
