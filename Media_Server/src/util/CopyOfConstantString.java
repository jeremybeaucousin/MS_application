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
	
	private final String text;
	
	public final static Hashtable<String, List<String>> SURROUDER_CHARACTER = new Hashtable<String, List<String>>() {
		{
			put(BRACE.text,Arrays.asList(BRACE_OPENING.text, BRACE_CLOSING.text));
			put(PARENTHESIS.text,Arrays.asList(PARENTHESIS_OPENING.text, PARENTHESIS_CLOSING.text));
			put(SQUAREBRACKET.text,Arrays.asList(SQUAREBRACKET_OPENING.text, SQUAREBRACKET_CLOSING.text));
		}
	};
	
	private CopyOfConstantString(String value) {
		this.text = value;
	}

	public String getText() {
		return text;
	}
	
	
}
