package com.kamontat.gui;

import com.kamontat.code.constant.HotKey;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

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
		addText(morse, normal);
		
		addBtnEvent();
		addShortKey();
	}
	
	private void addText(String m, String n) {
		morseTP.setText(m);
		morseTP.setPreferredSize(new Dimension(getWidth() / 2, getHeight() / 2));
		normalTP.setText(n);
	}
	
	private void addBtnEvent() {
		Action back = getBackAction();
		
		backBtn.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(HotKey.back, "back");
		backBtn.getActionMap().put("back", back);
		backBtn.addActionListener(back);
	}
	
	private void addShortKey() {
		Action selectAllMAction = getSelectAllMAction();
		morseTP.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(HotKey.morse, "selectAllMAction");
		morseTP.getActionMap().put("selectAllMAction", selectAllMAction);
		morseTP.setToolTipText("shortcut: " + HotKey.toString(HotKey.morse));
		
		Action selectAllNAction = getSelectAllNAction();
		normalTP.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(HotKey.normal, "selectAllNAction");
		normalTP.getActionMap().put("selectAllNAction", selectAllNAction);
		morseTP.setToolTipText("shortcut: " + HotKey.toString(HotKey.normal));
	}
	
	private Action getBackAction() {
		return new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		};
	}
	
	private Action getSelectAllMAction() {
		return new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				morseTP.requestFocus();
				morseTP.selectAll();
			}
		};
	}
	
	private Action getSelectAllNAction() {
		return new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				normalTP.requestFocus();
				normalTP.selectAll();
			}
		};
	}
	
	public void run(Point point, Dimension d) {
		pack();
		setMinimumSize(new Dimension(getWidth(), d.height));
		setLocation(point);
		setVisible(true);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	}
}
