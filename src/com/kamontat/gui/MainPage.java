package com.kamontat.gui;

import com.kamontat.code.constant.HotKey;
import com.kamontat.code.constant.PageType;
import com.kamontat.code.controller.Display;
import com.kamontat.code.controller.TopMenu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

import static com.kamontat.gui.KeyTablePage.addKeyTo;

/**
 * @author kamontat
 * @version 1.0
 * @since 1/2/2017 AD - 5:18 PM
 */
public class MainPage extends JFrame {
	private JButton decodeBtn;
	private JButton encodeBtn;
	private JPanel contentPane;
	
	public MainPage() {
		super("MainPage Page");
		setContentPane(contentPane);
		addMenu();
		
		Action decodeAction = getAction(PageType.Decode);
		decodeBtn.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(HotKey.DECODE.getKeyStroke(), "decodeAction"); // add key
		decodeBtn.getActionMap().put("decodeAction", decodeAction); // add action (call with key press)
		decodeBtn.addActionListener(decodeAction); // add action (call when press button)
		
		Action encodeAction = getAction(PageType.Encode);
		encodeBtn.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(HotKey.ENCODE.getKeyStroke(), "encodeAction");
		encodeBtn.getActionMap().put("encodeAction", encodeAction);
		encodeBtn.addActionListener(encodeAction);
		
		addKeyTo(this, contentPane);
		
		pack();
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
	
	public void run() {
		pack();
		setMinimumSize(new Dimension(getWidth(), getHeight()));
		setLocation(Display.getCenterLocation(getSize()));
		setVisible(true);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}
}
