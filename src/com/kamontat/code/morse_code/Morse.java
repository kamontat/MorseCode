package com.kamontat.code.morse_code;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kamontat.code.constant.MORSE_CHAR;

import javax.xml.stream.Location;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * @author kamontat
 * @version 1.0
 * @since 1/2/2017 AD - 5:33 PM
 */
public class Morse {
	public static MORSE_CHAR SEPARATE_WORD = MORSE_CHAR.C_DEFAULT;
	public static MORSE_CHAR SEPARATE_ALPHABET = MORSE_CHAR.C_B;
	public static MORSE_CHAR SHORT_CHAR = MORSE_CHAR.S_DEFAULT;
	public static MORSE_CHAR LONG_CHAR = MORSE_CHAR.L_DEFAULT;
	public static TreeMap<String, LinkedHashMap<String, String>> morse_char = new TreeMap<>();
	
	private static Morse ourInstance;
	
	public static Morse getInstance() {
		if (ourInstance == null) ourInstance = new Morse();
		return ourInstance;
	}
	
	public Morse() {
		read();
	}
	
	private void read() {
		ObjectMapper map = new ObjectMapper();
		try {
			InputStream input = Location.class.getResourceAsStream("/com/resource/morse_char.json");
			morse_char = map.readValue(input, morse_char.getClass());
		} catch (IOException e) {
			e.printStackTrace();
		}
		morse_char.forEach((s, stringStringTreeMap) -> {
			System.out.println(s + ": ");
			stringStringTreeMap.forEach((s1, s2) -> System.out.println("\t" + s1 + ":" + s2));
		});
	}
	
	public String decode(String morse_txt) {
		return null;
	}
	
	public String encode(String txt) {
		return null;
	}
}