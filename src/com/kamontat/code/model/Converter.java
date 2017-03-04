package com.kamontat.code.model;

import java.io.IOException;
import java.util.*;

/**
 * @author kamontat
 * @version 1.0
 * @since Sun 05/Mar/2017 - 3:15 AM
 */
public class Converter {
	private static final String config = "config";
	
	public static void main(String[] args) throws IOException {
		ResourceBundle bundle = ResourceBundle.getBundle(config);
	}
}
