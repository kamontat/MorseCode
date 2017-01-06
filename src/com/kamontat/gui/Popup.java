package com.kamontat.gui;

import javax.swing.*;
import java.awt.*;

/**
 * @author kamontat
 * @version 1.0
 * @since 1/7/2017 AD - 4:31 AM
 */
public class Popup {
	private JDialog popup = new JDialog();
	private boolean show = false;
	private JLabel label;
	
	public Popup(String text) {
		popup.setUndecorated(true);
		popup.setAlwaysOnTop(true);
		
		label = new JLabel(text);
		label.setFont(new Font(null, Font.BOLD, 18));
		popup.add(label);
		popup.pack();
	}
	
	public void popup(Point f) {
		popup.setLocation(new Point(f.x - (popup.getSize().width / 2), f.y - (popup.getSize().height / 2)));
		popup.setVisible(true);
		show = true;
	}
	
	public void move(Point f) {
		if (show) popup.setLocation(new Point(f.x - (popup.getSize().width / 2), f.y - (popup.getSize().height / 2)));
	}
	
	public void cancel() {
		popup.setVisible(false);
		show = false;
	}
}
