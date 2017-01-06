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
	
	private JButton keyTableBtn;
	
	private JPanel ntkPanel;
	private JScrollPane mnPanel;
	private JScrollPane nmPanel;
	
	private static AboutPage page;
	
	public static void run(JFrame f) {
		if (page == null) page = new AboutPage();
		page.run(f.getLocation());
	}
	
	public static void run(JFrame f, boolean info) {
		if (page == null) page = new AboutPage();
		page.setVisible(info, false, false, false, false);
		page.run(f.getLocation());
	}
	
	public static void run(JFrame f, boolean info, boolean help) {
		if (page == null) page = new AboutPage();
		page.setVisible(info, help, false, false, false);
		page.run(f.getLocation());
	}
	
	public static void run(JFrame f, boolean info, boolean ntk, boolean mToN, boolean nToM) {
		boolean h = true;
		if (page == null) page = new AboutPage();
		if (!ntk && !mToN && !nToM) h = false;
		page.setVisible(info, h, ntk, mToN, nToM);
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
	
	/**
	 * set panel visible for more easy to look
	 *
	 * @param a
	 * 		information Panel
	 * @param b
	 * 		help Panel
	 * @param c
	 * 		need to know Panel
	 * @param d
	 * 		morse to normal Panel
	 * @param e
	 * 		normal to morse Panel
	 */
	private void setVisible(boolean a, boolean b, boolean c, boolean d, boolean e) {
		infoPanel.setVisible(a);
		helpPanel.setVisible(b);
		ntkPanel.setVisible(c);
		mnPanel.setVisible(d);
		ntkPanel.setVisible(e);
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
