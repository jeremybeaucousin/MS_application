package util;

import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;

public enum CopyOfConstantString {
//	final Hashtable<String, List<String>> SURROUDER_CHARACTER = new Hashtable<String, List<String>>() {
//		{
//			put(BRACE,Arrays.asList(BRACE_OPENING, BRACE_CLOSING));
//			put(PARENTHESIS,Arrays.asList(PARENTHESIS_OPENING, PARENTHESIS_CLOSING));
//			put(SQUAREBRACKET,Arrays.asList(SQUAREBRACKET_OPENING, SQUAREBRACKET_CLOSING));
//		}
//	};
	
	 LOWER_CASE_CHARACTERS("a-z‡·‚„‰ÂÁËÈÍÎÏÌÓÔÚÛÙıˆ˘˙˚¸˝ˇÒÊú"),
	 UPPER_CASE_CHARACTERS("A-Z¿¡¬√ƒ≈«»… ÀÃÕŒœ–“”‘’÷Ÿ⁄€‹›ü—∆å"),
	 EMPTY(""), 
	 BRACE("BRACE"),
	 OPENING_PARTS("OPENING_PARTS"),
	 CLOSING_PARTS("CLOSING_PARTS"),
	 PARENTHESIS("PARENTHESIS"),
	 SQUAREBRACKET("SQUAREBRACKET"),
	
	/** special charachters **/
	 SPACE(" "), 
	 DOT("."), 
	 UNDERSCORE("_"),
	 DASH("-"),
	 BRACE_OPENING("{"),
	 BRACE_CLOSING("}"),
	 PARENTHESIS_OPENING("("),
	 PARENTHESIS_CLOSING(")"),
	 SQUAREBRACKET_OPENING("["),
	 SQUAREBRACKET_CLOSING("]"),
	 AMPERSAND("&");
	
	private final String stringValue;
	
	public final static Hashtable<String, List<String>> SURROUDER_CHARACTER = new Hashtable<String, List<String>>() {
		{
			put(BRACE.stringValue,Arrays.asList(BRACE_OPENING.stringValue, BRACE_CLOSING.stringValue));
			put(PARENTHESIS.stringValue,Arrays.asList(PARENTHESIS_OPENING.stringValue, PARENTHESIS_CLOSING.stringValue));
			put(SQUAREBRACKET.stringValue,Arrays.asList(SQUAREBRACKET_OPENING.stringValue, SQUAREBRACKET_CLOSING.stringValue));
		}
	};
	
	private CopyOfConstantString(String value) {
		this.stringValue = value;
	}

	public String getStringValue() {
		return stringValue;
	}
	
	
}
