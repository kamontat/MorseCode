package com.kamontat.gui;

import javax.swing.*;
import java.awt.*;

/**
 * @author kamontat
 * @version 1.0
 * @since 1/3/2017 AD - 3:51 PM
 */
public class ShowPage extends JDialog {
	private JTextPane normalTP;
	private JTextPane morseTP;
	private JButton backBtn;
	private JPanel contentPane;
	
	public ShowPage(Frame f, String morse, String normal) {
		super(f);
		setModal(true);
		setContentPane(contentPane);
		addBtnEvent();
		addText(morse, normal);
	}
	
	private void addText(String m, String n) {
		morseTP.setText(m);
		morseTP.setPreferredSize(new Dimension(getWidth() / 2, getHeight() / 2));
		normalTP.setText(n);
	}
	
	private void addBtnEvent() {
		backBtn.addActionListener(e -> dispose());
	}
	
	public void run(Point point, Dimension d) {
		pack();
		setMinimumSize(new Dimension(getWidth(), d.height));
		setLocation(point);
		setVisible(true);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	}
}
