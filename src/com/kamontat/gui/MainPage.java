package com.kamontat.gui;

import com.kamontat.code.constant.PageType;

import javax.swing.*;
import java.awt.*;

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
		decodeBtn.addActionListener(e -> {
			opPage page = new opPage(PageType.Decode);
			page.run(getLocation(), getSize());
			dispose();
		});
		encodeBtn.addActionListener(e -> {
			opPage page = new opPage(PageType.Encode);
			page.run(getLocation(), getSize());
			dispose();
		});
	}
	
	public void run(Point point) {
		pack();
		setMinimumSize(new Dimension(getWidth(), getHeight()));
		setLocation(point);
		setVisible(true);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}
}
