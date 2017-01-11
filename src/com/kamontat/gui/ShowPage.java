package com.kamontat.gui;

import com.kamontat.code.constant.HotKey;
import com.kamontat.code.constant.OperationType;
import com.kamontat.code.morse_code.Morse;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

import static com.kamontat.gui.KeyTablePage.addKeyTo;

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
	
	private boolean isError = false;
	
	enum TextType {
		MORSE, NORMAL;
	}
	
	public ShowPage(Frame f, String morse, String normal) {
		super(f);
		setModal(true);
		setContentPane(contentPane);
		addText(morse, normal);
		
		addBtnEvent();
		addShortKey();
		addKeyTo(this, contentPane);
	}
	
	private void addText(String m, String n) {
		checkText(m, n);
		morseTP.setText(m);
		morseTP.setPreferredSize(new Dimension(getWidth() / 2, getHeight() / 2));
		normalTP.setText(n);
	}
	
	private void checkText(String m, String n) {
		String output = getErrorChar(m, n);
		if (output != null) {
			JOptionPane.showMessageDialog(null, "Character CANNOT Convert\n" + output, "Error", JOptionPane.ERROR_MESSAGE);
			isError = true;
		}
	}
	
	private String getErrorChar(String m, String n) {
		String text = "";
		if (Morse.getOpError() == OperationType.Decode) {
			text = "Morse Code: ";
			for (Integer in : Morse.getError()) {
				if (!text.contains(Character.toString(m.charAt(in)))) text += "\"" + m.charAt(in) + "\", ";
			}
		} else if (Morse.getOpError() == OperationType.Encode) {
			text = "Normal Text: ";
			for (Integer in : Morse.getError()) {
				if (!text.contains(Character.toString(n.charAt(in)))) text += "\"" + n.charAt(in) + "\", ";
			}
		} else {
			return null;
		}
		return text;
	}
	
	private void addBtnEvent() {
		Action back = getBackAction();
		
		backBtn.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(HotKey.BACK.getKeyStroke(), "back");
		backBtn.getActionMap().put("back", back);
		backBtn.addActionListener(back);
	}
	
	private void addShortKey() {
		Action selectAllMAction = getSelectAllMAction();
		morseTP.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(HotKey.MORSE.getKeyStroke(), "selectAllMAction");
		morseTP.getActionMap().put("selectAllMAction", selectAllMAction);
		morseTP.setToolTipText("shortcut: " + HotKey.MORSE.getKeyString());
		
		Action selectAllNAction = getSelectAllNAction();
		normalTP.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(HotKey.NORMAL.getKeyStroke(), "selectAllNAction");
		normalTP.getActionMap().put("selectAllNAction", selectAllNAction);
		morseTP.setToolTipText("shortcut: " + HotKey.NORMAL.getKeyString());
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
		if (isError) {
			dispose();
			return;
		}
		
		pack();
		setMinimumSize(new Dimension(getWidth(), d.height));
		setSize(d);
		setLocation(point);
		setVisible(true);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	}
}
