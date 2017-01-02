package com.kamontat.gui;

import javax.swing.*;
import java.awt.*;

/**
 * @author kamontat
 * @version 1.0
 * @since 1/2/2017 AD - 7:08 PM
 */
public class EncodePage extends JFrame {
	private JPanel ContentPane;
	
	public EncodePage() {
		super("Encode Page");
		setContentPane(ContentPane);
	}
	
	public void run(Point point, Dimension size) {
		setMinimumSize(new Dimension(0, 0));
		setSize(size);
		setLocation(point);
		setVisible(true);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}
}
