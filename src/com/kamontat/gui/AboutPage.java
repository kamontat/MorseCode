package com.kamontat.gui;

import com.kamontat.code.controller.Display;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.URL;

import static com.kamontat.gui.KeyTablePage.addKeyTo;

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
	private JButton historyBtn;
	
	private static AboutPage page;
	
	public static void run(JFrame f) {
		run(f, false, false);
	}
	
	public static void run(JFrame f, boolean info) {
		run(f, info, false);
	}
	
	public static void run(JFrame f, boolean info, boolean help) {
		if (page == null) page = new AboutPage();
		page.setVisible(info, help, false, false, false);
		page.run();
	}
	
	public static void run(JFrame f, boolean info, boolean ntk, boolean mToN, boolean nToM) {
		boolean h = true;
		if (page == null) page = new AboutPage();
		if (!ntk && !mToN && !nToM) h = false;
		page.setVisible(info, h, ntk, mToN, nToM);
		page.run();
	}
	
	public AboutPage() {
		super((Frame) null, "About Page");
		setContentPane(contentPane);
		setModal(true);
		getRootPane().setDefaultButton(buttonOK);
		
		addKeyTo(this, contentPane);
		
		buttonOK.addActionListener(e -> onOK());
		infoBtn.addActionListener(e -> setVisible(infoPanel));
		helpBtn.addActionListener(e -> setVisible(helpPanel));
		ntkBtn.addActionListener(e -> setVisible(ntkPanel));
		conMtoNBtn.addActionListener(e -> setVisible(mnPanel));
		conNtoMBtn.addActionListener(e -> setVisible(nmPanel));
		keyTableBtn.addActionListener(e -> new KeyTablePage().run(this.getLocation()));
		historyBtn.addActionListener(e -> {
			try {
				Desktop.getDesktop().browse(new URL("https://github.com/kamontat/MorseCode#versions").toURI());
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		});
		
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
		boolean isVisible = c.isVisible();
		c.setVisible(!isVisible);
		
		JComponent[][] allPanel = new JComponent[][]{{infoPanel}, {helpPanel, ntkPanel, mnPanel, nmPanel}};
		
		if (c.equals(allPanel[0][0])) for (JComponent panel : allPanel[1]) {
			panel.setVisible(false);
		}
		else {
			if (!c.equals(allPanel[1][0])) {
				for (int i = 1; i < allPanel[1].length; i++) {
					if (!c.equals(allPanel[1][i])) allPanel[1][i].setVisible(false);
				}
			}
			allPanel[0][0].setVisible(false);
		}
		
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
	
	private void run() {
		pack();
		setMinimumSize(new Dimension(getWidth(), getHeight()));
		setLocation(Display.getCenterLocation(getSize()));
		setVisible(true);
	}
	
	private void onOK() {
		dispose();
	}
}
