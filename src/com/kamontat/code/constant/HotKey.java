package com.kamontat.code.constant;

import com.kamontat.code.controller.SystemUtils;

import javax.swing.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

/**
 * @author kamontat
 * @version 1.0
 * @since 1/7/2017 AD - 12:25 AM
 */
public class HotKey {
	public static final KeyStroke about = KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.ALT_MASK);
	
	public static final KeyStroke encode_a = KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.ALT_MASK);
	public static final KeyStroke encode = KeyStroke.getKeyStroke(KeyEvent.VK_E, 0);
	public static final KeyStroke decode_a = KeyStroke.getKeyStroke(KeyEvent.VK_D, InputEvent.ALT_MASK);
	public static final KeyStroke decode = KeyStroke.getKeyStroke(KeyEvent.VK_D, 0);
	
	public static final KeyStroke ok = KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0);
	public static final KeyStroke back = KeyStroke.getKeyStroke(KeyEvent.VK_B, getCTRL_MASK());
	public static final KeyStroke delete = KeyStroke.getKeyStroke(KeyEvent.VK_BACK_SPACE, 0);
	
	private static int getCTRL_MASK() {
		return SystemUtils.IS_OS_MAC ? InputEvent.META_MASK: InputEvent.CTRL_MASK;
	}
	
	public static String toString(KeyStroke s) {
		return s.toString().replace(" pressed ", "+").replace("pressed ", "").replace("meta", "cmd").toUpperCase();
	}
}
