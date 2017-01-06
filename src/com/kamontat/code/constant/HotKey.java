package com.kamontat.code.constant;

import org.apache.commons.lang3.SystemUtils;

import javax.swing.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

/**
 * @author kamontat
 * @version 1.0
 * @since 1/7/2017 AD - 12:25 AM
 */
public enum HotKey {
	ABOUT("About", "go to about page (contain Information of this program and How to use it)", "Every menu-bar", "Focus page", KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.ALT_MASK)),
	KEY("Key", "go to key table page", "Everywhere", "Everytime", KeyStroke.getKeyStroke(KeyEvent.VK_K, getCTRL_MASK())),
	
	ENCODE_A("Encode page with alt", "go to encode page", "Some menu-bar", "Focus page", KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.ALT_MASK)),
	ENCODE("Encode page", "go to encode page", "Main page", "Focus page", KeyStroke.getKeyStroke(KeyEvent.VK_E, 0)),
	DECODE_A("Decode page with alt", "go to decode page", "Some menu-bar", "Focus page", KeyStroke.getKeyStroke(KeyEvent.VK_D, InputEvent.ALT_MASK)),
	DECODE("Decode page", "go to decode page", "Main page", "Focus page", KeyStroke.getKeyStroke(KeyEvent.VK_D, 0)),
	
	MORSE("Morse Code", "to select all morse-code", "Show page", "Focus page", KeyStroke.getKeyStroke(KeyEvent.VK_M, getCTRL_MASK())),
	NORMAL("Normal Text", "to select all normal-text", "Show page", "Focus page", KeyStroke.getKeyStroke(KeyEvent.VK_N, getCTRL_MASK())),
	
	ENTER("Enter", "you can press enter to click", "Every page", "Focus button", KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0)),
	OK("OK", "to decode/encode", "Operation page", "Focus page", KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, getCTRL_MASK())),
	BACK("Back", "back to previous page ", "Operation page/Show page", "Focus page", KeyStroke.getKeyStroke(KeyEvent.VK_B, getCTRL_MASK())),
	DELETE("Delete", "delete 1 character", "Operation page", "Focus page", KeyStroke.getKeyStroke(KeyEvent.VK_BACK_SPACE, 0)),
	CUT("Cut", "cut text", "textField in Operation page", "Focus page", KeyStroke.getKeyStroke(KeyEvent.VK_X, getCTRL_MASK())),
	COPY("Copy", "copy text", "textField in Operation page", "Focus page", KeyStroke.getKeyStroke(KeyEvent.VK_C, getCTRL_MASK())),
	PASTE("Paste", "paste text", "textField in Operation page", "Focus page", KeyStroke.getKeyStroke(KeyEvent.VK_V, getCTRL_MASK())),
	DELETE_ALL("Delete All", "delete all text", "textField in Operation page", "Focus page", KeyStroke.getKeyStroke(KeyEvent.VK_BACK_SPACE, getCTRL_MASK()));
	
	private String name;
	private String description, position, when;
	private KeyStroke keyStroke;
	
	private HotKey(String name, String description, String position, String when, KeyStroke keyStroke) {
		this.name = name;
		this.description = description;
		this.position = position;
		this.when = when;
		this.keyStroke = keyStroke;
	}
	
	public String getName() {
		return name;
	}
	
	public String getDescription() {
		return description;
	}
	
	public String getPosition() {
		return position;
	}
	
	public String getWhen() {
		return when;
	}
	
	public KeyStroke getKeyStroke() {
		return keyStroke;
	}
	
	public String getKeyString() {
		return keyStroke.toString().replace(" pressed ", "+").replace("pressed ", "").replace("meta", "cmd").toUpperCase();
	}
	
	public String[] getAll() {
		return new String[] {name, description, position, when, getKeyString()};
	}
	
	private static int getCTRL_MASK() {
		return SystemUtils.IS_OS_MAC ? InputEvent.META_MASK: InputEvent.CTRL_MASK;
	}
	
	public static int getTotalKey() {
		return HotKey.values().length;
	}
}
