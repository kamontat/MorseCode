package com.kamontat.code.constant;

import java.util.*;
import java.util.stream.Stream;

/**
 * @author kamontat
 * @version 1.0
 * @since 1/2/2017 AD - 5:41 PM
 */
public enum MORSE_CHAR {
	L_A(MORSE_TYPE.LONG_CHAR, "-"), L_B(MORSE_TYPE.LONG_CHAR, "_"), L_C(MORSE_TYPE.LONG_CHAR, "—"),
	
	S_A(MORSE_TYPE.SHORT_CHAR, "."), S_B(MORSE_TYPE.SHORT_CHAR, "•"),
	
	C_A(MORSE_TYPE.SEPARATE_CHAR, " "), C_B(MORSE_TYPE.SEPARATE_CHAR, "|"), C_C(MORSE_TYPE.SEPARATE_CHAR, "/"), C_D(MORSE_TYPE.SEPARATE_CHAR, "\\"),
	
	W_A(MORSE_TYPE.SEPARATE_WORD, " "), W_B(MORSE_TYPE.SEPARATE_WORD, "|"), W_C(MORSE_TYPE.SEPARATE_WORD, "/"), W_D(MORSE_TYPE.SEPARATE_WORD, "\\");
	
	
	public MORSE_TYPE type;
	public String txt;
	
	private MORSE_CHAR(MORSE_TYPE t, String a) {
		txt = a;
		type = t;
	}
	
	public static Stream<MORSE_CHAR> getBy(MORSE_TYPE t) {
		return Arrays.stream(MORSE_CHAR.values()).filter(morse_char -> morse_char.type == t);
	}
	
	public boolean same(Object ob) {
		if (ob instanceof MORSE_CHAR) {
			MORSE_CHAR obj = (MORSE_CHAR) ob;
			return obj.txt.equals(txt);
		}
		return false;
	}
	
	@Override
	public String toString() {
		return txt.equals(" ") ? "1 Spacebar": txt;
	}
}
