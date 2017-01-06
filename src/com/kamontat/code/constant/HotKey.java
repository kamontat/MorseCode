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
public class HotKey {
	
	public static final HotKey about = new HotKey("About", "go to about page (contain Information of this program and How to use it)", "Every menu-bar", "Focus page", KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.ALT_MASK));
	
	public static final HotKey encode_a = new HotKey("Encode page with alt", "go to encode page", "Some menu-bar", "Focus page", KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.ALT_MASK));
	public static final HotKey encode = new HotKey("Encode page", "go to encode page", "Main page", "Focus page", KeyStroke.getKeyStroke(KeyEvent.VK_E, 0));
	public static final HotKey decode_a = new HotKey("Decode page with alt", "go to decode page", "Some menu-bar", "Focus page", KeyStroke.getKeyStroke(KeyEvent.VK_D, InputEvent.ALT_MASK));
	public static final HotKey decode = new HotKey("Decode page", "go to decode page", "Main page", "Focus page", KeyStroke.getKeyStroke(KeyEvent.VK_D, 0));
	
	public static final HotKey morse = new HotKey("Morse Code", "to select all morse-code", "Show page", "Focus page", KeyStroke.getKeyStroke(KeyEvent.VK_M, getCTRL_MASK()));
	public static final HotKey normal = new HotKey("Normal Text", "to select all normal-text", "Show page", "Focus page", KeyStroke.getKeyStroke(KeyEvent.VK_N, getCTRL_MASK()));
	
	public static final HotKey enter = new HotKey("Enter", "you can press enter to click", "Every page", "Focus button", KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0));
	public static final HotKey ok = new HotKey("OK", "to decode/encode", "Operation page", "Focus page", KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, getCTRL_MASK()));
	public static final HotKey back = new HotKey("Back", "back to previous page ", "Operation page/Show page", "Focus page", KeyStroke.getKeyStroke(KeyEvent.VK_B, getCTRL_MASK()));
	public static final HotKey delete = new HotKey("Delete", "delete 1 character", "Operation page", "Focus page", KeyStroke.getKeyStroke(KeyEvent.VK_BACK_SPACE, 0));
	
	public static final HotKey cut = new HotKey("Cut", "cut text", "textField in Operation page", "Focus page", KeyStroke.getKeyStroke(KeyEvent.VK_X, getCTRL_MASK()));
	public static final HotKey copy = new HotKey("Copy", "copy text", "textField in Operation page", "Focus page", KeyStroke.getKeyStroke(KeyEvent.VK_C, getCTRL_MASK()));
	public static final HotKey paste = new HotKey("Paste", "paste text", "textField in Operation page", "Focus page", KeyStroke.getKeyStroke(KeyEvent.VK_V, getCTRL_MASK()));
	public static final HotKey delete_all = new HotKey("Delete All", "delete all text", "textField in Operation page", "Focus page", KeyStroke.getKeyStroke(KeyEvent.VK_BACK_SPACE, getCTRL_MASK()));
	
	private String name;
	private String description, position, when;
	private KeyStroke keyStroke;
	
	public HotKey(String name, String description, String position, String when, KeyStroke keyStroke) {
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
	
	private static int getCTRL_MASK() {
		return SystemUtils.IS_OS_MAC ? InputEvent.META_MASK: InputEvent.CTRL_MASK;
	}
}
