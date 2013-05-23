package util;


public class StringUtils {
	
	public static String transformSpecialsHTTPCharacterToSpace(String name) {
		String modifiedName = new String(name);
		modifiedName = modifiedName.replace(ConstantString.AMPERSAND, ConstantString.SPACE);
		return modifiedName;
	}
}
