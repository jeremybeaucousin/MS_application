package util;

import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;

public interface ConstantString {
	final Hashtable<String, List<String>> SURROUDER_CHARACTER = new Hashtable<String, List<String>>() {{
		put(BRACE,Arrays.asList(BRACE_OPENING, BRACE_CLOSING));
		put(PARENTHESIS,Arrays.asList(PARENTHESIS_OPENING, PARENTHESIS_CLOSING));
		put(SQUAREBRACKET,Arrays.asList(SQUAREBRACKET_OPENING, SQUAREBRACKET_CLOSING));
	}};
	
	final String EMPTY = ""; 
	
	final String BRACE = "BRACE";
	
	final String OPENING_PARTS = "OPENING_PARTS";
	
	final String CLOSING_PARTS = "CLOSING_PARTS";
	
	final String PARENTHESIS = "PARENTHESIS";
	
	final String SQUAREBRACKET = "SQUAREBRACKET";
	
	// special charachters //
	final String SPACE = " "; 
	final String DOT = "."; 
	final String UNDERSCORE = "_";
	final String DASH = "-";
	final String BRACE_OPENING = "{";
	final String BRACE_CLOSING = "}";
	final String PARENTHESIS_OPENING = "(";
	final String PARENTHESIS_CLOSING = ")";
	final String SQUAREBRACKET_OPENING = "[";
	final String SQUAREBRACKET_CLOSING = "]";
	final String AMPERSAND = "&";
	
}
