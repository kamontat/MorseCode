package com.kamontat.gui;

import com.kamontat.code.constant.HotKey;
import com.kamontat.code.constant.PageType;
import com.kamontat.code.controller.TopMenu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * @author kamontat
 * @version 1.0
 * @since 1/2/2017 AD - 5:18 PM
 */
public class MainPage extends JFrame {
	private JButton decodeBtn;
	private JButton encodeBtn;
	private JPanel ContentPane;
	
	public MainPage() {
		super("MainPage Page");
		setContentPane(ContentPane);
		addMenu();
		
		Action decodeAction = getAction(PageType.Decode);
		Action encodeAction = getAction(PageType.Encode);
		
		decodeBtn.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(HotKey.decode, "decodeAction");
		decodeBtn.getActionMap().put("decodeAction", decodeAction);
		decodeBtn.addActionListener(decodeAction);
		
		encodeBtn.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(HotKey.encode, "encodeAction");
		encodeBtn.getActionMap().put("encodeAction", encodeAction);
		encodeBtn.addActionListener(encodeAction);
	}
	
	private Action getAction(PageType t) {
		return new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				opPage page = new opPage(t);
				page.run(getLocation(), getSize());
				dispose();
			}
		};
	}
	
	private void addMenu() {
		JMenuBar menu = new JMenuBar();
		menu.add(Box.createHorizontalGlue());
		menu.add(TopMenu.setting(this));
		setJMenuBar(menu);
	}
	
	public void run(Point point) {
		pack();
		setMinimumSize(new Dimension(getWidth(), getHeight()));
		setLocation(point);
		setVisible(true);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}
}
