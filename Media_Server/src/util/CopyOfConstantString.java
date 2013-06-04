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
	
	 LOWER_CASE_CHARACTERS("a-zабвгдезийклмнопртуфхцщъыьэясжњ"),
	 UPPER_CASE_CHARACTERS("A-ZАБВГДЕЗИЙКЛМНОПРТУФХЦЩЪЫЬЭџСЖЊ"),
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
	
	private final String value;
	
	private CopyOfConstantString(String value) {
		this.value = value;
	}
}
