package com.kamontat.code.controller;

import com.kamontat.code.constant.Constant;
import com.kamontat.code.constant.HotKey;
import com.kamontat.code.constant.PageType;
import com.kamontat.gui.AboutPage;
import com.kamontat.gui.MainPage;
import com.kamontat.gui.opPage;

import javax.swing.*;

/**
 * @author kamontat
 * @version 1.0
 * @since 1/2/2017 AD - 8:36 PM
 */
public class TopMenu {
	public static JMenuItem back(JFrame curF) {
		JMenuItem item;
		
		item = new JMenuItem("Back");
		item.setAccelerator(HotKey.back);
		item.addActionListener(e -> {
			new MainPage().run(curF.getLocation());
			curF.dispose();
		});
		
		return item;
	}
	
	public static JMenuItem decodePage(JFrame curF) {
		JMenuItem item;
		
		item = new JMenuItem("Decode");
		item.setAccelerator(HotKey.decode_a);
		item.addActionListener(e -> {
			new opPage(PageType.Decode).run(curF.getLocation(), curF.getSize());
			curF.dispose();
		});
		
		return item;
	}
	
	public static JMenuItem encodePage(JFrame curF) {
		JMenuItem item;
		
		item = new JMenuItem("Encode");
		item.setAccelerator(HotKey.encode_a);
		item.addActionListener(e -> {
			new opPage(PageType.Encode).run(curF.getLocation(), curF.getSize());
			curF.dispose();
		});
		
		return item;
	}
	
	public static JMenu setting(JFrame curF) {
		JMenu menu;
		
		menu = new JMenu("Setting");
		menu.add(about(curF));
		
		return menu;
	}
	
	private static JMenuItem about(JFrame curF) {
		JMenuItem item;
		
		item = new JMenuItem(String.format("About (%s)", Constant.version));
		item.setAccelerator(HotKey.about);
		item.addActionListener(e -> AboutPage.run(curF));
		
		return item;
	}
}
