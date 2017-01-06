package com.kamontat.gui;

import com.kamontat.code.constant.MORSE_CHAR;
import com.kamontat.code.constant.MORSE_TYPE;
import com.kamontat.code.constant.PageType;
import com.kamontat.code.controller.TopMenu;
import com.kamontat.code.morse_code.Morse;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.KeyEvent;

/**
 * @author kamontat
 * @version 1.0
 * @since 1/2/2017 AD - 7:08 PM
 */
public class opPage extends JFrame {
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
	private JLabel desLb;
	private JLabel titleLb;
	
	private PageType t;
	private final Morse morse = Morse.getInstance();
	
	public opPage(PageType type) {
		super(type == PageType.Decode ? "Decode Page": "Encode Page");
		setContentPane(ContentPane);
		
		t = type;
		addLb();
		addComboboxItem();
		addBtnEvent();
		addMenu();
		
		if (t == PageType.Decode) {
			addTextFieldDoc();
			addMoreEvent();
			addBtnMoreEvent();
			moreBtn.setVisible(true);
		} else {
			moreBtn.setVisible(false);
		}
	}
	
	private void addLb() {
		titleLb.setText(t == PageType.Decode ? "De Morse Code": "En Morse Code");
		desLb.setText(t == PageType.Decode ? "Change Morse-Code to Normal-Text": "Change Normal-Text to Morse-Code");
	}
	
	private void addMenu() {
		JMenuBar menu = new JMenuBar();
		JMenu actions = new JMenu("(L)ink");
		
		actions.setMnemonic(KeyEvent.VK_L);
		actions.getAccessibleContext().setAccessibleDescription("This Menu will map you going to some other page...");
		actions.add(TopMenu.back(this));
		actions.add(t == PageType.Decode ? TopMenu.encodePage(this): TopMenu.decodePage(this));
		
		menu.add(actions);
		menu.add(Box.createHorizontalGlue());
		menu.add(TopMenu.about(this));
		
		setJMenuBar(menu);
	}
	
	private void addComboboxItem() {
		MORSE_CHAR.getBy(MORSE_TYPE.SEPARATE_WORD).forEach(morse_char -> SW_ComboBox.addItem(morse_char));
		MORSE_CHAR.getBy(MORSE_TYPE.SEPARATE_CHAR).forEach(morse_char -> SC_ComboBox.addItem(morse_char));
		MORSE_CHAR.getBy(MORSE_TYPE.LONG_CHAR).forEach(morse_char -> L_ComboBox.addItem(morse_char));
		MORSE_CHAR.getBy(MORSE_TYPE.SHORT_CHAR).forEach(morse_char -> S_ComboBox.addItem(morse_char));
		
		SW_ComboBox.addItemListener(e -> {
			MORSE_CHAR chr = (MORSE_CHAR) SC_ComboBox.getSelectedItem();
			if (chr.sameC(SW_ComboBox.getSelectedItem()))
				SC_ComboBox.setSelectedIndex(SC_ComboBox.getSelectedIndex() + 1 == SC_ComboBox.getItemCount() ? 0: SC_ComboBox.getSelectedIndex() + 1);
			
			if (e.getStateChange() == ItemEvent.SELECTED && t == PageType.Decode) {
				setTextField(morse.convert(textField.getText(), (MORSE_CHAR) e.getItem()));
			}
		});
		SC_ComboBox.addItemListener(e -> {
			MORSE_CHAR chr = (MORSE_CHAR) SW_ComboBox.getSelectedItem();
			if (chr.sameC(SC_ComboBox.getSelectedItem()))
				SW_ComboBox.setSelectedIndex(SW_ComboBox.getSelectedIndex() + 1 == SW_ComboBox.getItemCount() ? 0: SW_ComboBox.getSelectedIndex() + 1);
			
			if (e.getStateChange() == ItemEvent.SELECTED && t == PageType.Decode) {
				setTextField(morse.convert(textField.getText(), (MORSE_CHAR) e.getItem()));
			}
		});
		L_ComboBox.addItemListener(e -> {
			if (e.getStateChange() == ItemEvent.SELECTED && t == PageType.Decode) {
				setTextField(morse.convert(textField.getText(), (MORSE_CHAR) e.getItem()));
			}
		});
		S_ComboBox.addItemListener(e -> {
			if (e.getStateChange() == ItemEvent.SELECTED && t == PageType.Decode) {
				setTextField(morse.convert(textField.getText(), (MORSE_CHAR) e.getItem()));
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
	
	private void addBtnEvent() {
		deleteBtn.addActionListener(e -> {
			try {
				textField.setText(textField.getText(0, textField.getText().length() - 1));
			} catch (BadLocationException ignored) {
			}
		});
		
		okBtn.addActionListener(e -> OKEvent());
	}
	
	private void addBtnMoreEvent() {
		sBtn.addActionListener(e -> textField.setText(textField.getText() + getChar(MORSE_TYPE.SHORT_CHAR).chr));
		lBtn.addActionListener(e -> textField.setText(textField.getText() + getChar(MORSE_TYPE.LONG_CHAR).chr));
		ncBtn.addActionListener(e -> textField.setText(textField.getText() + getChar(MORSE_TYPE.SEPARATE_CHAR).chr));
		nwBtn.addActionListener(e -> textField.setText(textField.getText() + getChar(MORSE_TYPE.SEPARATE_WORD).chr));
	}
	
	private void OKEvent() {
		String m, n;
		Morse.set(getChar(MORSE_TYPE.SEPARATE_WORD), getChar(MORSE_TYPE.SEPARATE_CHAR), getChar(MORSE_TYPE.SHORT_CHAR), getChar(MORSE_TYPE.LONG_CHAR));
		String txt = (t == PageType.Decode ? morse.decode(textField.getText()): morse.encode(textField.getText()));
		m = (t == PageType.Decode ? textField.getText(): txt);
		n = (t == PageType.Decode ? txt: textField.getText());
		new ShowPage(this, m, n).run(new Point(this.getLocation().x + this.getWidth(), this.getLocation().y), getSize());
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
	
	private void setTextField(String text) {
		textField.setText(text);
		inputAction();
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
		return (MORSE_CHAR) getBox(t).getSelectedItem();
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
