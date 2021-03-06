package com.kamontat.code.constant;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * @author kamontat
 * @version 1.0
 * @since 1/2/2017 AD - 5:41 PM
 */
public enum MORSE_CHAR {
	L_DEFAULT(MORSE_TYPE.LONG_CHAR, '-'), L_DOWN(MORSE_TYPE.LONG_CHAR, '_'), L_LONG(MORSE_TYPE.LONG_CHAR, '—'),
	
	S_DEFAULT(MORSE_TYPE.SHORT_CHAR, '.'), S_BIG(MORSE_TYPE.SHORT_CHAR, '•'),
	
	C_DEFAULT(MORSE_TYPE.SEPARATE_CHAR, ' '), C_PIPE(MORSE_TYPE.SEPARATE_CHAR, '|'), C_SL(MORSE_TYPE.SEPARATE_CHAR, '/'), C_BSL(MORSE_TYPE.SEPARATE_CHAR, '\\'),
	
	W_DEFAULT(MORSE_TYPE.SEPARATE_WORD, '|'), W_SPACE(MORSE_TYPE.SEPARATE_WORD, ' '), W_SL(MORSE_TYPE.SEPARATE_WORD, '/'), W_BSL(MORSE_TYPE.SEPARATE_WORD, '\\');
	
	
	public MORSE_TYPE type;
	public Character chr;
	
	private MORSE_CHAR(MORSE_TYPE t, Character a) {
		chr = a;
		type = t;
	}
	
	public static Stream getBy(Predicate<MORSE_CHAR> predicate) {
		return Arrays.stream(MORSE_CHAR.values()).filter(predicate);
	}
	
	public static Stream<MORSE_CHAR> getBy(MORSE_TYPE t) {
		return Arrays.stream(MORSE_CHAR.values()).filter(morse_char -> morse_char.type == t);
	}
	
	public static MORSE_CHAR getBy(Character txt) {
		for (MORSE_CHAR c : MORSE_CHAR.values()) {
			if (c.chr.compareTo(txt) == 0) return c;
		}
		return null;
	}
	
	public boolean sameC(Object ob) {
		if (ob instanceof MORSE_CHAR) {
			MORSE_CHAR obj = (MORSE_CHAR) ob;
			return obj.chr.compareTo(chr) == 0;
		}
		return false;
	}
	
	public boolean sameT(Object ob) {
		if (ob instanceof MORSE_CHAR) {
			MORSE_CHAR obj = (MORSE_CHAR) ob;
			return obj.type == type;
		}
		return false;
	}
	
	
	@Override
	public String toString() {
		return chr.equals(' ') ? "1 Spacebar": chr.toString();
	}
}
