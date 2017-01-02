package com.kamontat.code.constant;

/**
 * @author kamontat
 * @version 1.0
 * @since 1/2/2017 AD - 5:41 PM
 */
public enum MORSE_CHAR {
	L_A(MORSE_TYPE.LONG_CHAR, "-"),
	L_B(MORSE_TYPE.LONG_CHAR, "_"),
	L_C(MORSE_TYPE.LONG_CHAR, "—"),
	
	S_A(MORSE_TYPE.SHORT_CHAR, "."),
	S_B(MORSE_TYPE.SHORT_CHAR, "•"),
	
	C_A(MORSE_TYPE.SEPARATE, " "),
	C_B(MORSE_TYPE.SEPARATE, "|"),
	C_C(MORSE_TYPE.SEPARATE, "/"),
	C_D(MORSE_TYPE.SEPARATE, "\\");
	
	public MORSE_TYPE type;
	public String txt;
	
	private MORSE_CHAR(MORSE_TYPE t, String a) {
		txt = a;
		type =t;
	}
}
