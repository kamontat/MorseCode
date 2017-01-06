package com.kamontat.code.controller;

import com.kamontat.code.constant.Constant;
import com.kamontat.code.constant.HotKey;
import com.kamontat.code.constant.PageType;
import com.kamontat.gui.AboutPage;
import com.kamontat.gui.MainPage;
import com.kamontat.gui.opPage;

import javax.swing.*;
import javax.swing.text.JTextComponent;

/**
 * @author kamontat
 * @version 1.0
 * @since 1/2/2017 AD - 8:36 PM
 */
public class TopMenu {
	public static JMenuItem back(JFrame curF) {
		JMenuItem item;
		
		item = new JMenuItem("Back");
		item.setAccelerator(HotKey.BACK.getKeyStroke());
		item.addActionListener(e -> {
			new MainPage().run();
			curF.dispose();
		});
		
		return item;
	}
	
	public static JMenuItem decodePage(JFrame curF) {
		JMenuItem item;
		
		item = new JMenuItem("Decode");
		item.setAccelerator(HotKey.DECODE_A.getKeyStroke());
		item.addActionListener(e -> {
			new opPage(PageType.Decode).run(curF.getLocation(), curF.getSize());
			curF.dispose();
		});
		
		return item;
	}
	
	public static JMenuItem encodePage(JFrame curF) {
		JMenuItem item;
		
		item = new JMenuItem("Encode");
		item.setAccelerator(HotKey.ENCODE_A.getKeyStroke());
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
		item.setAccelerator(HotKey.ABOUT.getKeyStroke());
		item.addActionListener(e -> AboutPage.run(curF));
		
		return item;
	}
	
	public static JMenu edit(JTextComponent textComponent) {
		JMenu menu;
		
		menu = new JMenu("Edit");
		menu.add(cut(textComponent));
		menu.add(copy(textComponent));
		menu.add(paste(textComponent));
		menu.add(deleteAll(textComponent));
		
		return menu;
	}
	
	private static JMenuItem cut(JTextComponent t) {
		JMenuItem item;
		item = new JMenuItem("cut");
		item.setAccelerator(HotKey.CUT.getKeyStroke());
		item.addActionListener(e -> {
			t.requestFocus();
			t.cut();
		});
		return item;
	}
	
	private static JMenuItem copy(JTextComponent t) {
		JMenuItem item;
		item = new JMenuItem("copy");
		item.setAccelerator(HotKey.COPY.getKeyStroke());
		item.addActionListener(e -> {
			t.requestFocus();
			t.copy();
		});
		return item;
	}
	
	private static JMenuItem paste(JTextComponent t) {
		JMenuItem item;
		item = new JMenuItem("paste");
		item.setAccelerator(HotKey.PASTE.getKeyStroke());
		item.addActionListener(e -> {
			t.requestFocus();
			t.paste();
		});
		return item;
	}
	
	private static JMenuItem deleteAll(JTextComponent t) {
		JMenuItem item;
		item = new JMenuItem("delete all");
		item.setAccelerator(HotKey.DELETE_ALL.getKeyStroke());
		item.addActionListener(e -> {
			t.setText("");
		});
		return item;
	}
}
