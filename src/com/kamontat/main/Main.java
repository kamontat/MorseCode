package com.kamontat.main;

import com.kamontat.code.constant.Constant;
import com.kamontat.code.morse_code.Morse;
import com.kamontat.factory.UpdaterFactory;
import com.kamontat.gui.MainPage;
import com.kamontat.implementation.GUpdater;

public class Main {
	public static final Morse morse = Morse.getInstance();
	
	public static void main(String[] args) {
		UpdaterFactory.setUpdater(new GUpdater(Constant.owner, Constant.version, 1));
		
		MainPage page = new MainPage();
		page.run();
	}
}