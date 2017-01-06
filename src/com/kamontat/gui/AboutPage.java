package com.kamontat.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class AboutPage extends JDialog {
	private JPanel contentPane;
	private JButton buttonOK;
	
	private JButton helpBtn;
	private JButton infoBtn;
	
	private JPanel helpPanel;
	private JPanel infoPanel;
	
	private JButton ntkBtn;
	private JButton conMtoNBtn;
	private JButton conNtoMBtn;
	
	private JScrollPane ntkPanel;
	private JScrollPane mnPanel;
	private JScrollPane nmPanel;
	
	private static AboutPage page;
	
	public static void run(JFrame f) {
		if (page == null) page = new AboutPage();
		page.run(f.getLocation());
	}
	
	public AboutPage() {
		setContentPane(contentPane);
		setModal(true);
		getRootPane().setDefaultButton(buttonOK);
		
		buttonOK.addActionListener(e -> onOK());
		infoBtn.addActionListener(e -> setVisible(infoPanel));
		helpBtn.addActionListener(e -> setVisible(helpPanel));
		ntkBtn.addActionListener(e -> setVisible(ntkPanel));
		conMtoNBtn.addActionListener(e -> setVisible(mnPanel));
		conNtoMBtn.addActionListener(e -> setVisible(nmPanel));
		
		// call onCancel() when cross is clicked
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				onOK();
			}
		});
		
		// call onCancel() on ESCAPE
		contentPane.registerKeyboardAction(e -> onOK(), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
	}
	
	@Override
	public void pack() {
		super.pack();
		setSize(new Dimension(366, getHeight()));
	}
	
	private void setVisible(JComponent c) {
		c.setVisible(!c.isVisible());
		pack();
	}
	
	private void run(Point point) {
		pack();
		setMinimumSize(new Dimension(getWidth(), getHeight()));
		setLocation(point);
		setVisible(true);
	}
	
	private void onOK() {
		dispose();
	}
}
