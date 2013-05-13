package util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.mysql.jdbc.StringUtils;

public class StringUtil {
	/**
	 * Delete all the special character in the movie name include in (".", "-", "_")
	 * @param name
	 * 		original name of the movie
	 * @return name of the movie without special caracter.
	 */
	public static String transformSpecialsCharacterToSpace(String name) {
		String modifiedName = new String(name);
		modifiedName = modifiedName.replace(ConstantString.DOT, ConstantString.SPACE);
		modifiedName = modifiedName.replace(ConstantString.DASH, ConstantString.SPACE);
		modifiedName = modifiedName.replace(ConstantString.UNDERSCORE, ConstantString.SPACE);
		return modifiedName;
	}
	
	public static String transformSpecialsHTTPCharacterToSpace(String name) {
		String modifiedName = new String(name);
		modifiedName = modifiedName.replace(ConstantString.AMPERSAND, ConstantString.SPACE);
		return modifiedName;
	}
	
	public static String supresseMutlipleSpace(String name) {
		return name.replaceAll("(\\s+)", ConstantString.SPACE);
	}
	
	private static String identifySurrondPart(String surroundPart) {
		String partKind = new String();
		Set<String> surroundCharacterKeys = ConstantString.SURROUDER_CHARACTER.keySet();
		for(String key : surroundCharacterKeys){
			if(ConstantString.SURROUDER_CHARACTER.get(key).contains(surroundPart)) {
				partKind = key;
			}
		}
		return partKind;
	}
	
	private static Boolean isOpeningSurrondPart(String surroundPart) {
		Boolean isOpeningPart = false;
		Set<String> surroundCharacterKeys = ConstantString.SURROUDER_CHARACTER.keySet();
		for(String key : surroundCharacterKeys){
			if(ConstantString.SURROUDER_CHARACTER.get(key).contains(surroundPart) && ConstantString.SURROUDER_CHARACTER.get(key).get(0).equals(surroundPart)) {
				isOpeningPart = true;
			}
		}
		return isOpeningPart;
	}
	
	private static Boolean isClosingSurrondPart(String surroundPart) {
		Boolean isClosingPart = false;
		Set<String> surroundCharacterKeys = ConstantString.SURROUDER_CHARACTER.keySet();
		for(String key : surroundCharacterKeys){
			if(ConstantString.SURROUDER_CHARACTER.get(key).contains(surroundPart) && ConstantString.SURROUDER_CHARACTER.get(key).get(1).equals(surroundPart)) {
				isClosingPart = true;
			}
		}
		return isClosingPart;
	}
	
	private static Map<String, Map<Integer, String>> extractSurroudParts(String name) {
		Map<String, Map<Integer, String>> containerMap = new TreeMap<String, Map<Integer,String>>();
		Pattern pattern = Pattern.compile("([\\(,\\),\\[,\\],\\{,\\}])");
		Matcher matcher = pattern.matcher(name);
		Map<Integer, String> openingPartIndex = new TreeMap<Integer, String>();
		Map<Integer, String> closingPartLastsIndex = new TreeMap<Integer, String>();
		while (matcher.find()) {
			if(isOpeningSurrondPart(matcher.group())) {
				openingPartIndex.put(matcher.start(), identifySurrondPart(matcher.group()));
			} else if(isClosingSurrondPart(matcher.group())) {
				closingPartLastsIndex.put(matcher.end(), identifySurrondPart(matcher.group()));
			}
		}
		containerMap.put(ConstantString.OPENING_PARTS, openingPartIndex);
		containerMap.put(ConstantString.CLOSING_PARTS, closingPartLastsIndex);
		
		return containerMap;
	}
	
	public static String deleteSurroudParts(String name) {
		Map<String, Map<Integer, String>> extractResult = extractSurroudParts(name);

		Map<Integer, String> openingPartIndex = extractResult.get(ConstantString.OPENING_PARTS);
		Map<Integer, String> closingPartIndex = extractResult.get(ConstantString.CLOSING_PARTS);
		Map<Integer, Integer> IndexToExtract = new TreeMap<Integer, Integer>();
		Object[] openingPartIndexKeys = openingPartIndex.keySet().toArray();
		Object[] closingPartIndexKeys = closingPartIndex.keySet().toArray();
		
		for(int ii=00; ii < openingPartIndexKeys.length; ii++) {
			if(ii < closingPartIndexKeys.length) {
				Integer firstClosedPart = (Integer) closingPartIndexKeys[ii];
				Boolean peerNotFound = true;
				ArrayList<Integer> openPartsBeforeTheFirstClosedPart = new ArrayList<Integer>();
				int jj = 0;
				while(peerNotFound) {
					if(jj >= openingPartIndexKeys.length || firstClosedPart < (Integer) openingPartIndexKeys[jj]) {
						peerNotFound = false;
					} else if(openingPartIndex.containsKey(openingPartIndexKeys[jj]) && openingPartIndex.get(openingPartIndexKeys[jj]).equals(closingPartIndex.get(firstClosedPart)) && firstClosedPart > (Integer) openingPartIndexKeys[jj]) {
						openPartsBeforeTheFirstClosedPart.add((Integer) openingPartIndexKeys[jj]);
					}
					jj++;
				}
				
				if(openPartsBeforeTheFirstClosedPart.size() > 0) {
					Integer maxIndex = -1;
					
					for(Integer partIndex : openPartsBeforeTheFirstClosedPart) {
						if(partIndex > maxIndex) {
							maxIndex = partIndex;
						}
					} 
					IndexToExtract.put(maxIndex, firstClosedPart);
					openingPartIndex.remove(maxIndex);
					closingPartIndex.remove(firstClosedPart);
				}
			}
		}
		
		System.out.println(IndexToExtract);
		/* TODO  Filtrer la table pour ne garder que les parents.
		for(Integer index : IndexToExtract.keySet()) {
			System.out.println(IndexToExtract.get(index));
		}
		*/
		String[] stringToExtract = new String[IndexToExtract.size()];
		int ii = 0;
		for(Integer openingPart : IndexToExtract.keySet()) {
			Integer closingPart = IndexToExtract.get(openingPart);
			stringToExtract[ii] = name.substring(openingPart, closingPart);
			ii++;
		}

		for(ii = 0; ii < stringToExtract.length; ii++) {
			System.out.println(stringToExtract[ii]);
			name = name.replace(stringToExtract[ii], ConstantString.EMPTY);
		}
		
		if(!openingPartIndex.isEmpty() || !closingPartIndex.isEmpty()) {
			name = name.replaceAll("([\\(,\\),\\[,\\],\\{,\\}])", ConstantString.EMPTY);
		}

		return name;
	}
}
