package com.kamontat.gui;

import com.kamontat.code.constant.HotKey;
import com.kamontat.code.constant.MORSE_CHAR;
import com.kamontat.code.constant.MORSE_TYPE;
import com.kamontat.code.constant.OperationType;
import com.kamontat.code.controller.TopMenu;
import com.kamontat.code.morse_code.Morse;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;

import static com.kamontat.gui.KeyTablePage.addKeyTo;

/**
 * @author kamontat
 * @version 1.0
 * @since 1/2/2017 AD - 7:08 PM
 */
public class opPage extends JFrame {
	private JPanel contentPane;
	private JComboBox L_ComboBox;
	private JComboBox S_ComboBox;
	private JComboBox SW_ComboBox;
	private JComboBox SC_ComboBox;
	private JTextArea textArea;
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
	private JButton settingBtn;
	
	private OperationType t;
	private final Morse morse = Morse.getInstance();
	
	public opPage(OperationType type) {
		super(type == OperationType.Decode ? "Decode Page": "Encode Page");
		setContentPane(contentPane);
		
		t = type;
		addLb();
		addComboboxItem();
		addBtnEvent();
		addMenu();
		
		addKeyTo(this, contentPane);
		
		textArea.setLineWrap(true);
		
		if (t == OperationType.Decode) {
			addTextFieldDoc();
			addMoreEvent();
			addBtnMoreEvent();
			moreBtn.setVisible(true);
		} else {
			moreBtn.setVisible(false);
		}
	}
	
	private void addLb() {
		titleLb.setText(t == OperationType.Decode ? "De Morse Code": "En Morse Code");
		desLb.setText(t == OperationType.Decode ? "Change Morse-Code to Normal-Text": "Change Normal-Text to Morse-Code");
	}
	
	private void addMenu() {
		JMenuBar menu = new JMenuBar();
		JMenu actions = new JMenu("Link");
		
		actions.add(TopMenu.back(this));
		actions.add(t == OperationType.Decode ? TopMenu.encodePage(this): TopMenu.decodePage(this));
		
		menu.add(actions);
		menu.add(TopMenu.edit(textArea));
		menu.add(Box.createHorizontalGlue());
		menu.add(TopMenu.setting(this));
		
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
			
			if (e.getStateChange() == ItemEvent.SELECTED && t == OperationType.Decode) {
				setText(morse.convert(textArea.getText(), (MORSE_CHAR) e.getItem()));
			}
		});
		SC_ComboBox.addItemListener(e -> {
			MORSE_CHAR chr = (MORSE_CHAR) SW_ComboBox.getSelectedItem();
			if (chr.sameC(SC_ComboBox.getSelectedItem()))
				SW_ComboBox.setSelectedIndex(SW_ComboBox.getSelectedIndex() + 1 == SW_ComboBox.getItemCount() ? 0: SW_ComboBox.getSelectedIndex() + 1);
			
			if (e.getStateChange() == ItemEvent.SELECTED && t == OperationType.Decode) {
				setText(morse.convert(textArea.getText(), (MORSE_CHAR) e.getItem()));
			}
		});
		L_ComboBox.addItemListener(e -> {
			if (e.getStateChange() == ItemEvent.SELECTED && t == OperationType.Decode) {
				setText(morse.convert(textArea.getText(), (MORSE_CHAR) e.getItem()));
			}
		});
		S_ComboBox.addItemListener(e -> {
			if (e.getStateChange() == ItemEvent.SELECTED && t == OperationType.Decode) {
				setText(morse.convert(textArea.getText(), (MORSE_CHAR) e.getItem()));
			}
		});
	}
	
	private void addTextFieldDoc() {
		textArea.getDocument().addDocumentListener(new DocumentListener() {
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
		Action okAction = new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				OKEvent();
			}
		};
		Action deleteAction = new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				deleteText(1);
			}
		};
		
		Action settingAction = new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				toggle(false);
			}
		};
		
		okBtn.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(HotKey.OK.getKeyStroke(), "okAction");
		okBtn.getActionMap().put("okAction", okAction);
		okBtn.addActionListener(okAction);
		
		deleteBtn.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(HotKey.DELETE.getKeyStroke(), "deleteAction");
		deleteBtn.getActionMap().put("deleteAction", deleteAction);
		deleteBtn.addActionListener(deleteAction);
		
		settingBtn.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(HotKey.SETTING.getKeyStroke(), "settingAction");
		settingBtn.getActionMap().put("settingAction", settingAction);
		settingBtn.addActionListener(settingAction);
	}
	
	private void addMoreEvent() {
		Action moreAction = new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				toggle(true);
			}
		};
		moreBtn.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(HotKey.MORE.getKeyStroke(), "moreAction");
		moreBtn.getActionMap().put("moreAction", moreAction);
		moreBtn.addActionListener(moreAction);
	}
	
	private void addBtnMoreEvent() {
		Action s = getAction(MORSE_TYPE.SHORT_CHAR);
		sBtn.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(HotKey.SHORT.getKeyStroke(), "s");
		sBtn.getActionMap().put("s", s);
		sBtn.addActionListener(s);
		
		Action l = getAction(MORSE_TYPE.LONG_CHAR);
		lBtn.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(HotKey.LONG.getKeyStroke(), "l");
		lBtn.getActionMap().put("l", l);
		lBtn.addActionListener(l);
		
		Action sw = getAction(MORSE_TYPE.SEPARATE_WORD);
		nwBtn.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(HotKey.S_CHAR.getKeyStroke(), "sw");
		nwBtn.getActionMap().put("sw", sw);
		nwBtn.addActionListener(sw);
		
		Action sc = getAction(MORSE_TYPE.SEPARATE_CHAR);
		ncBtn.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(HotKey.S_WORD.getKeyStroke(), "sc");
		ncBtn.getActionMap().put("sc", sc);
		ncBtn.addActionListener(sc);
	}
	
	private void setText(MORSE_TYPE t) {
		textArea.setText(textArea.getText() + getChar(t).chr);
	}
	
	private Action getAction(MORSE_TYPE t) {
		return new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setText(t);
			}
		};
	}
	
	private void OKEvent() {
		if (okBtn.isEnabled()) {
			String m, n;
			Morse.set(getChar(MORSE_TYPE.SEPARATE_WORD), getChar(MORSE_TYPE.SEPARATE_CHAR), getChar(MORSE_TYPE.SHORT_CHAR), getChar(MORSE_TYPE.LONG_CHAR));
			String txt = (t == OperationType.Decode ? morse.decode(textArea.getText()): morse.encode(textArea.getText()));
			m = (t == OperationType.Decode ? textArea.getText(): txt);
			n = (t == OperationType.Decode ? txt: textArea.getText());
			new ShowPage(this, m, n).run(new Point(this.getLocation().x + this.getWidth(), this.getLocation().y), getSize());
		}
	}
	
	private void deleteText(int chr) {
		try {
			textArea.setText(textArea.getText(0, textArea.getText().length() - chr));
		} catch (BadLocationException ignored) {
		}
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
	
	private void setText(String text) {
		textArea.setText(text);
		inputAction();
	}
	
	private void toggle(boolean isMore) {
		
		if (!isMore) {
			showSelectedPanel(!selectedPanel.isVisible());
			showMorePanel(false);
		} else {
			showSelectedPanel(false);
			showMorePanel(!morePanel.isVisible());
		}
		pack();
		setMinimumSize(new Dimension(getWidth(), getHeight()));
	}
	
	private void showSelectedPanel(boolean a) {
		selectedPanel.setVisible(a);
	}
	
	private void showMorePanel(boolean a) {
		morePanel.setVisible(a);
		textArea.setEnabled(!a);
		moreBtn.setText(!a ? "more..": "..less");
	}
	
	private void inputAction() {
		SW_ComboBox.setEnabled(textArea.getText().isEmpty());
		SC_ComboBox.setEnabled(textArea.getText().isEmpty());
		
		if (checkValid(textArea.getText())) {
			textArea.setBackground(new Color(255, 255, 255));
			okBtn.setEnabled(true);
		} else {
			textArea.setBackground(new Color(255, 0, 0));
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
