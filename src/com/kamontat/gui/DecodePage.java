package com.kamontat.gui;

import com.kamontat.code.constant.MORSE_CHAR;
import com.kamontat.code.constant.MORSE_TYPE;
import com.kamontat.code.controller.TopMenu;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.KeyEvent;
import java.util.stream.Stream;

/**
 * @author kamontat
 * @version 1.0
 * @since 1/2/2017 AD - 7:08 PM
 */
public class DecodePage extends JFrame {
	private JPanel ContentPane;
	private JComboBox L_ComboBox;
	private JComboBox S_ComboBox;
	private JComboBox SW_ComboBox;
	private JComboBox SC_ComboBox;
	private JTextField textField;
	private JButton okBtn;
	private JButton moreBtn;
	private JButton sBtn;
	private JButton lBtn;
	private JPanel morePanel;
	private JButton ncBtn;
	private JButton nwBtn;
	private JPanel selectedPanel;
	private JButton deleteBtn;
	
	public DecodePage() {
		super("Decode Page");
		setContentPane(ContentPane);
		addComboboxItem();
		addMenu();
		addMoreEvent();
		addTextFieldDoc();
		addBtnAction();
	}
	
	private void addMenu() {
		JMenuBar menu = new JMenuBar();
		JMenu actions = new JMenu("(L)ink");
		
		actions.setMnemonic(KeyEvent.VK_L);
		actions.getAccessibleContext().setAccessibleDescription("This Menu will map you going to some other page...");
		actions.add(TopMenu.back(this));
		actions.add(TopMenu.encodePage(this));
		
		menu.add(actions);
		setJMenuBar(menu);
	}
	
	private void addComboboxItem() {
		MORSE_CHAR.getBy(MORSE_TYPE.SEPARATE_WORD).forEach(morse_char -> SW_ComboBox.addItem(morse_char));
		MORSE_CHAR.getBy(MORSE_TYPE.SEPARATE_CHAR).forEach(morse_char -> SC_ComboBox.addItem(morse_char));
		MORSE_CHAR.getBy(MORSE_TYPE.LONG_CHAR).forEach(morse_char -> L_ComboBox.addItem(morse_char));
		MORSE_CHAR.getBy(MORSE_TYPE.SHORT_CHAR).forEach(morse_char -> S_ComboBox.addItem(morse_char));
		
		SW_ComboBox.setSelectedIndex(1);
		
		SW_ComboBox.addItemListener(e -> {
			MORSE_CHAR chr = (MORSE_CHAR) SC_ComboBox.getSelectedItem();
			if (chr.sameC(SW_ComboBox.getSelectedItem()))
				SC_ComboBox.setSelectedIndex(SC_ComboBox.getSelectedIndex() + 1 == SC_ComboBox.getItemCount() ? 0: SC_ComboBox.getSelectedIndex() + 1);
			
			if (e.getStateChange() == ItemEvent.SELECTED) {
				textField.setText(convert(textField.getText(), (MORSE_CHAR) e.getItem()));
				inputAction();
			}
		});
		SC_ComboBox.addItemListener(e -> {
			MORSE_CHAR chr = (MORSE_CHAR) SW_ComboBox.getSelectedItem();
			if (chr.sameC(SC_ComboBox.getSelectedItem()))
				SW_ComboBox.setSelectedIndex(SW_ComboBox.getSelectedIndex() + 1 == SW_ComboBox.getItemCount() ? 0: SW_ComboBox.getSelectedIndex() + 1);
			
			if (e.getStateChange() == ItemEvent.SELECTED) {
				textField.setText(convert(textField.getText(), (MORSE_CHAR) e.getItem()));
				inputAction();
			}
		});
		L_ComboBox.addItemListener(e -> {
			if (e.getStateChange() == ItemEvent.SELECTED) {
				textField.setText(convert(textField.getText(), (MORSE_CHAR) e.getItem()));
				inputAction();
			}
		});
		S_ComboBox.addItemListener(e -> {
			if (e.getStateChange() == ItemEvent.SELECTED) {
				textField.setText(convert(textField.getText(), (MORSE_CHAR) e.getItem()));
				inputAction();
			}
		});
	}
	
	private void addMoreEvent() {
		moreBtn.addActionListener(e -> toggle());
	}
	
	private void addTextFieldDoc() {
		textField.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e) {
				inputAction();
			}
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				inputAction();
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				inputAction();
			}
		});
	}
	
	private void addBtnAction() {
		sBtn.addActionListener(e -> textField.setText(textField.getText() + getChar(MORSE_TYPE.SHORT_CHAR).chr));
		lBtn.addActionListener(e -> textField.setText(textField.getText() + getChar(MORSE_TYPE.LONG_CHAR).chr));
		ncBtn.addActionListener(e -> textField.setText(textField.getText() + getChar(MORSE_TYPE.SEPARATE_CHAR).chr));
		nwBtn.addActionListener(e -> textField.setText(textField.getText() + getChar(MORSE_TYPE.SEPARATE_WORD).chr));
		
		deleteBtn.addActionListener(e -> {
			try {
				textField.setText(textField.getText(0, textField.getText().length() - 1));
			} catch (BadLocationException ignored) {
			}
		});
	}
	
	private JComboBox getBox(MORSE_TYPE t) {
		switch (t) {
			case SHORT_CHAR:
				return S_ComboBox;
			case LONG_CHAR:
				return L_ComboBox;
			case SEPARATE_WORD:
				return SW_ComboBox;
			case SEPARATE_CHAR:
				return SC_ComboBox;
		}
		return null;
	}
	
	private void toggle() {
		boolean toggle = morePanel.isVisible();
		morePanel.setVisible(!toggle);
		selectedPanel.setVisible(toggle);
		
		textField.setEnabled(toggle);
		
		moreBtn.setText(toggle ? "more..": "..less");
		
		pack();
		setMinimumSize(new Dimension(getWidth(), getHeight()));
	}
	
	private void inputAction() {
		SW_ComboBox.setEnabled(textField.getText().isEmpty());
		SC_ComboBox.setEnabled(textField.getText().isEmpty());
		
		if (checkValid(textField.getText())) {
			textField.setBackground(new Color(255, 255, 255));
			okBtn.setEnabled(true);
		} else {
			textField.setBackground(new Color(255, 0, 0));
			okBtn.setEnabled(false);
		}
	}
	
	private boolean checkValid(String text) {
		MORSE_CHAR sw_c = getChar(MORSE_TYPE.SEPARATE_WORD);
		MORSE_CHAR sc_c = getChar(MORSE_TYPE.SEPARATE_CHAR);
		MORSE_CHAR l_c = getChar(MORSE_TYPE.LONG_CHAR);
		MORSE_CHAR s_c = getChar(MORSE_TYPE.SHORT_CHAR);
		
		// check every char in input String
		for (int i = 0; i < text.length(); i++) {
			char aChar = text.charAt(i);
			
			MORSE_CHAR aMC = MORSE_CHAR.getBy(aChar);
			if (aMC != null) {
				if (!aMC.sameC(sw_c) && !aMC.sameC(sc_c) && !aMC.sameC(l_c) && !aMC.sameC(s_c)) return false;
			} else {
				return false;
			}
		}
		return true;
	}
	
	public MORSE_CHAR getChar(MORSE_TYPE t) {
		Stream<MORSE_CHAR> stream = MORSE_CHAR.getBy(t);
		return stream.filter(morse_char -> morse_char.sameC(getBox(t).getSelectedItem())).findFirst().get();
	}
	
	public String convert(String txt, MORSE_CHAR xx) {
		String temp = "";
		for (char c : txt.toCharArray()) {
			MORSE_CHAR mc = MORSE_CHAR.getBy(c);
			if (mc != null) {
				
				if (xx.sameT(mc)) temp += getChar(xx.type).chr;
				else temp += String.valueOf(c);
			} else return txt;
		}
		return temp;
	}
	
	public void run(Point point, Dimension size) {
		pack();
		setMinimumSize(new Dimension(getWidth(), getHeight()));
		setSize(size);
		setLocation(point);
		setVisible(true);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}
}
