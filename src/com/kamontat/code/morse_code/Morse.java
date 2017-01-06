package com.kamontat.code.morse_code;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kamontat.code.constant.MORSE_CHAR;

import javax.xml.stream.Location;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.regex.Pattern;

/**
 * @author kamontat
 * @version 1.0
 * @since 1/2/2017 AD - 5:33 PM
 */
public class Morse {
	private static MORSE_CHAR SEPARATE_WORD = MORSE_CHAR.W_DEFAULT;
	private static MORSE_CHAR SEPARATE_CHAR = MORSE_CHAR.C_DEFAULT;
	private static MORSE_CHAR SHORT_CHAR = MORSE_CHAR.S_DEFAULT;
	private static MORSE_CHAR LONG_CHAR = MORSE_CHAR.L_DEFAULT;
	private static TreeMap<String, LinkedHashMap<String, String>> morse_char = new TreeMap<>();
	private static TreeMap<String, String> normal_char = new TreeMap<>();
	
	private static Morse ourInstance;
	
	public static Morse getInstance() {
		if (ourInstance == null) ourInstance = new Morse();
		return ourInstance;
	}
	
	private Morse() {
		read();
	}
	
	private void read() {
		ObjectMapper map = new ObjectMapper();
		try {
			InputStream input = Location.class.getResourceAsStream("/com/resource/morse_char.json");
			morse_char = map.readValue(input, morse_char.getClass());
			
			input = Location.class.getResourceAsStream("/com/resource/normal_char.json");
			normal_char = map.readValue(input, normal_char.getClass());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void set(MORSE_CHAR word, MORSE_CHAR alphabet, MORSE_CHAR shortC, MORSE_CHAR longC) {
		SEPARATE_WORD = word;
		SEPARATE_CHAR = alphabet;
		SHORT_CHAR = shortC;
		LONG_CHAR = longC;
	}
	
	public String decode(String morse_txt) {
		double parentheses = 0;
		morse_txt = convert(convert(morse_txt, MORSE_CHAR.S_DEFAULT), MORSE_CHAR.L_DEFAULT);
		String txt = "";
		String words[] = morse_txt.split(Pattern.quote(String.valueOf(SEPARATE_WORD.chr)));
		for (String w : words) {
			String chars[] = w.split(Pattern.quote(String.valueOf(SEPARATE_CHAR.chr)));
			for (String c : chars) {
				String cc = normal_char.get(c);
				if (cc != null && cc.equals("(")) parentheses++;
				txt += cc;
			}
			txt += " ";
		}
		txt = txt.substring(0, txt.length() - 1);
		if (parentheses % 2 == 0) {
			// parentheses issue
		}
		return txt;
	}
	
	public String encode(String txt) {
		String de = "";
		
		for (String w : txt.split(" ")) {
			for (int i = 0; i < w.length(); i++) {
				Character c = w.toUpperCase().charAt(i);
				if (Character.isDigit(c)) {
					de += morse_char.get("D").get(String.valueOf(c));
				} else if (Character.isLetter(c)) {
					de += morse_char.get("L").get(String.valueOf(c));
				} else {
					de += morse_char.get("M").get(String.valueOf(c));
				}
				if (i < w.length() - 1) de += SEPARATE_CHAR.chr;
			}
			de += SEPARATE_WORD.chr;
		}
		return convert(convert(de.substring(0, de.length() - 1), SHORT_CHAR), LONG_CHAR);
	}
	
	public String convert(String txt, MORSE_CHAR xx) {
		String temp = "";
		for (char c : txt.toCharArray()) {
			MORSE_CHAR mc = MORSE_CHAR.getBy(c);
			if (mc != null) {
				if (xx.sameT(mc)) temp += xx.chr;
				else temp += String.valueOf(c);
			} else return txt;
		}
		return temp;
	}
}