package util;

import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;


public class StringUtils {
	public final static Hashtable<String, List<String>> SURROUDER_CHARACTER = new Hashtable<String, List<String>>() {{
		put(BRACE,Arrays.asList(BRACE_OPENING, BRACE_CLOSING));
		put(PARENTHESIS,Arrays.asList(PARENTHESIS_OPENING, PARENTHESIS_CLOSING));
		put(SQUAREBRACKET,Arrays.asList(SQUAREBRACKET_OPENING, SQUAREBRACKET_CLOSING));
	}};
	
	public final static String LOWER_CASE_CHARACTERS = "a-z‡·‚„‰ÂÁËÈÍÎÏÌÓÔÚÛÙıˆ˘˙˚¸˝ˇÒÊú";
	
	public final static String UPPER_CASE_CHARACTERS = "A-Z¿¡¬√ƒ≈«»… ÀÃÕŒœ–“”‘’÷Ÿ⁄€‹›ü—∆å";
	
	public final static String EMPTY = ""; 
	
	public final static String BRACE = "BRACE";
	
	public final static String OPENING_PARTS = "OPENING_PARTS";
	
	public final static String CLOSING_PARTS = "CLOSING_PARTS";
	
	public final static String PARENTHESIS = "PARENTHESIS";
	
	public final static String SQUAREBRACKET = "SQUAREBRACKET";
	
	/** special charachters **/
	public final static String SPACE = " "; 
	public final static String DOT = "."; 
	public final static String UNDERSCORE = "_";
	public final static String DASH = "-";
	public final static String BRACE_OPENING = "{";
	public final static String BRACE_CLOSING = "}";
	public final static String PARENTHESIS_OPENING = "(";
	public final static String PARENTHESIS_CLOSING = ")";
	public final static String SQUAREBRACKET_OPENING = "[";
	public final static String SQUAREBRACKET_CLOSING = "]";
	public final static String AMPERSAND = "&";
	
	public static String transformSpecialsHTTPCharacterToSpace(String name) {
		String modifiedName = new String(name);
		modifiedName = modifiedName.replace(StringUtils.AMPERSAND, StringUtils.SPACE);
		return modifiedName;
	}
}
