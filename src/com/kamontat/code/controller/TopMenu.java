package com.kamontat.code.controller;

import com.kamontat.code.constant.PageType;
import com.kamontat.gui.AboutPage;
import com.kamontat.gui.MainPage;
import com.kamontat.gui.opPage;

import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
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
		item.setMnemonic(KeyEvent.VK_D);
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
		item.setMnemonic(KeyEvent.VK_E);
		item.getAccessibleContext().setAccessibleDescription("Go to encode page");
		item.addActionListener(e -> {
			new opPage(PageType.Encode).run(curF.getLocation(), curF.getSize());
			curF.dispose();
		});
		
		return item;
	}
	
	public static JMenu about(JFrame curF) {
		JMenu item;
		
		item = new JMenu("About");
		item.setFocusable(false);
		item.addMenuListener(new MenuListener() {
			@Override
			public void menuSelected(MenuEvent e) {
				AboutPage.run(curF);
				item.doClick();
			}
			
			@Override
			public void menuDeselected(MenuEvent e) {
			}
			
			@Override
			public void menuCanceled(MenuEvent e) {
			}
		}); // add event
		item.setMnemonic(KeyEvent.VK_A); // add hot-key
		return item;
	}
}
