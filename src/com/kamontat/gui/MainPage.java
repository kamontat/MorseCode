package com.kamontat.gui;

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
			DecodePage page = new DecodePage();
			page.run(getLocation(), getSize());
			dispose();
		});
		encodeBtn.addActionListener(e -> {
			EncodePage page = new EncodePage();
			page.run(getLocation(), getSize());
			dispose();gi
		});
	}
	
	public void run(Point point) {
		setMinimumSize(new Dimension(259, 110));
		pack();
		setLocation(point);
		setVisible(true);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}
}
