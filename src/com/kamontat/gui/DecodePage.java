package com.kamontat.gui;

import com.kamontat.code.constant.MORSE_CHAR;
import com.kamontat.code.constant.MORSE_TYPE;
import com.kamontat.code.controller.TopMenu;

import javax.swing.*;
import java.awt.*;
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
	private JButton sbBtn;
	private JButton nwBtn;
	private JPanel selectedPanel;
	
	public DecodePage() {
		super("Decode Page");
		setContentPane(ContentPane);
		addComboboxItem();
		addMenu();
		addMoreEvent();
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
		
		SC_ComboBox.setSelectedIndex(1);
		
		SW_ComboBox.addItemListener(e -> {
			MORSE_CHAR chr = (MORSE_CHAR) SC_ComboBox.getSelectedItem();
			if (chr.same(SW_ComboBox.getSelectedItem()))
				SC_ComboBox.setSelectedIndex(SC_ComboBox.getSelectedIndex() + 1 == SC_ComboBox.getItemCount() ? 0: SC_ComboBox.getSelectedIndex() + 1);
		});
		SC_ComboBox.addItemListener(e -> {
			MORSE_CHAR chr = (MORSE_CHAR) SW_ComboBox.getSelectedItem();
			if (chr.same(SC_ComboBox.getSelectedItem()))
				SW_ComboBox.setSelectedIndex(SW_ComboBox.getSelectedIndex() + 1 == SW_ComboBox.getItemCount() ? 0: SW_ComboBox.getSelectedIndex() + 1);
		});
	}
	
	private void addMoreEvent() {
		moreBtn.addActionListener(e -> toggle());
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
		
		textField.setEnabled(!toggle);
		
		pack();
		setMinimumSize(new Dimension(getWidth(), getHeight()));
	}
	
	public MORSE_CHAR getChar(MORSE_TYPE t) {
		Stream<MORSE_CHAR> stream = MORSE_CHAR.getBy(t);
		return stream.filter(morse_char -> morse_char.same(getBox(t).getSelectedItem())).findFirst().get();
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
