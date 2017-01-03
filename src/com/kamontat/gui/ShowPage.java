package com.kamontat.gui;

import javax.swing.*;
import java.awt.*;

/**
 * @author kamontat
 * @version 1.0
 * @since 1/3/2017 AD - 3:51 PM
 */
public class ShowPage extends JFrame {
	private JTextPane normalTP;
	private JTextPane morseTP;
	private JPanel selectedPanel;
	private JComboBox SW_ComboBox;
	private JComboBox S_ComboBox;
	private JComboBox SC_ComboBox;
	private JComboBox L_ComboBox;
	private JButton backBtn;
	private JPanel contentPane;
	
	public ShowPage() {
		setContentPane(contentPane);
		addBtnEvent();
	}
	
	private void addBtnEvent() {
		backBtn.addActionListener(e -> dispose());
	}
	
	
	public void run(Point point) {
		pack();
		setMinimumSize(new Dimension(getWidth(), getHeight()));
		setLocation(point);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
}
