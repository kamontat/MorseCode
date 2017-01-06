package com.kamontat.code.controller;

import com.kamontat.code.constant.Constant;
import com.kamontat.code.constant.HotKey;
import com.kamontat.code.constant.PageType;
import com.kamontat.gui.AboutPage;
import com.kamontat.gui.MainPage;
import com.kamontat.gui.opPage;

import javax.swing.*;
import java.awt.event.KeyEvent;

/**
 * @author kamontat
 * @version 1.0
 * @since 1/2/2017 AD - 8:36 PM
 */
public class TopMenu {
	public static JMenuItem back(JFrame curF) {
		JMenuItem item;
		
		item = new JMenuItem("(B)ack");
		item.setMnemonic(KeyEvent.VK_B);
		item.getAccessibleContext().setAccessibleDescription("Go back to main page");
		item.addActionListener(e -> {
			new MainPage().run(curF.getLocation());
			curF.dispose();
		});
		
		return item;
	}
	
	public static JMenuItem decodePage(JFrame curF) {
		JMenuItem item;
		
		item = new JMenuItem("(D)ecode");
		item.setAccelerator(HotKey.decode_a);
		item.getAccessibleContext().setAccessibleDescription("Go to decode page");
		item.addActionListener(e -> {
			new opPage(PageType.Decode).run(curF.getLocation(), curF.getSize());
			curF.dispose();
		});
		
		return item;
	}
	
	public static JMenuItem encodePage(JFrame curF) {
		JMenuItem item;
		
		item = new JMenuItem("(E)ncode");
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
		
		item = new JMenuItem(String.format("(A)bout (%s)", Constant.version));
		item.setAccelerator(HotKey.about);
		item.addActionListener(e -> AboutPage.run(curF));
		
		return item;
	}
}
