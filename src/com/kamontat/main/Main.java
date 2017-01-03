package com.kamontat.main;

import com.kamontat.code.morse_code.Morse;
import com.kamontat.gui.MainPage;

import java.awt.*;

public class Main {
	public static final Morse morse = Morse.getInstance();
	
	public static void main(String[] args) {
		MainPage page = new MainPage();
		page.run(new Point(0, 0));
	}
}
