package model;

import java.io.File;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import util.StringUtils;

public abstract class Document {
	/* Attributes */
	private String documentName;
	private String extension;
	private String path;
	
	/* Constructors */
	public Document(String documentName, String documentPath) {
		this.extension = Document.getExtension(documentName);
		if(com.mysql.jdbc.StringUtils.isEmptyOrWhitespaceOnly(this.extension)) {
			this.documentName = documentName;
		} else {
			this.documentName = Document.getDocumentName(documentName);
		}
		this.path = documentPath;
	}
	
	/* getters and setters */
	public String getDocumentName() {
		return documentName;
	}

	public String getExtension() {
		return extension;
	}
	
	public String getPath() {
		return path;
	}

	/* file name manipulation*/
	/**
	 * Delete all the special characters in the movie name include in (".", "-", "_", "�")
	 * @param name
	 * 		original name of the movie
	 * @return name of the movie without special caracters.
	 */
	public void deleteSpecialsCaracters() {
		this.documentName = this.documentName.replaceAll("([\\.,-,_,�])", StringUtils.SPACE);
		this.supresseMutlipleSpace();
	}

	public void insertSpaceBeforeCollapseUpperCaseOrInt() {
		String allCharacters = StringUtils.LOWER_CASE_CHARACTERS + StringUtils.UPPER_CASE_CHARACTERS;
		Pattern pattern = Pattern.compile("[" + allCharacters + "][1-9][" + allCharacters + "]|[" + StringUtils.LOWER_CASE_CHARACTERS + "][" + StringUtils.UPPER_CASE_CHARACTERS + "]|[" + allCharacters + "][1-9]|[1-9][" + allCharacters + "]");
		Matcher matcher = pattern.matcher(this.documentName);
		while (matcher.find()) {
			String elementToSplit = matcher.group();
			String firstElement = elementToSplit.substring(0,1);
			String secondElement = elementToSplit.substring(1,2);
			if(matcher.group().length() > 2) {
				String thirdElement = elementToSplit.substring(2);
				this.documentName = this.documentName.replace(elementToSplit, firstElement + StringUtils.SPACE + secondElement + StringUtils.SPACE + thirdElement);
			} else {
				this.documentName = this.documentName.replace(elementToSplit, firstElement + StringUtils.SPACE + secondElement);
			}
			
		}
	}
	
	private void supresseMutlipleSpace() {
		this.documentName = this.documentName.replaceAll("(\\s+)", StringUtils.SPACE);
	}

	private static String identifySurrondPart(String surroundPart) {
		String partKind = new String();
		Set<String> surroundCharacterKeys = StringUtils.SURROUDER_CHARACTER.keySet();
		for(String key : surroundCharacterKeys){
			if(StringUtils.SURROUDER_CHARACTER.get(key).contains(surroundPart)) {
				partKind = key;
			}
		}
		return partKind;
	}
	
	private static Boolean isOpeningSurrondPart(String surroundPart) {
		Boolean isOpeningPart = false;
		Set<String> surroundCharacterKeys = StringUtils.SURROUDER_CHARACTER.keySet();
		for(String key : surroundCharacterKeys){
			if(StringUtils.SURROUDER_CHARACTER.get(key).contains(surroundPart) && StringUtils.SURROUDER_CHARACTER.get(key).get(0).equals(surroundPart)) {
				isOpeningPart = true;
			}
		}
		return isOpeningPart;
	}
	
	private static Boolean isClosingSurrondPart(String surroundPart) {
		Boolean isClosingPart = false;
		Set<String> surroundCharacterKeys = StringUtils.SURROUDER_CHARACTER.keySet();
		for(String key : surroundCharacterKeys){
			if(StringUtils.SURROUDER_CHARACTER.get(key).contains(surroundPart) && StringUtils.SURROUDER_CHARACTER.get(key).get(1).equals(surroundPart)) {
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
		containerMap.put(StringUtils.OPENING_PARTS, openingPartIndex);
		containerMap.put(StringUtils.CLOSING_PARTS, closingPartLastsIndex);
		
		return containerMap;
	}
	
	public void deleteSurroudParts() {
		Map<String, Map<Integer, String>> extractResult = extractSurroudParts(this.documentName);

		Map<Integer, String> openingPartIndex = extractResult.get(StringUtils.OPENING_PARTS);
		Map<Integer, String> closingPartIndex = extractResult.get(StringUtils.CLOSING_PARTS);
		
		Map<Integer, Integer> IndexToExtract = new TreeMap<Integer, Integer>();
		ArrayList<String> stringToExtract = new ArrayList<String>();
		Integer[] openingPartIndexKeys = openingPartIndex.keySet().toArray(new Integer[openingPartIndex.size()]);
		Integer[] closingPartIndexKeys = closingPartIndex.keySet().toArray(new Integer[closingPartIndex.size()]);
		for(int ii = 0; ii < openingPartIndexKeys.length; ii++) {
			if(ii < closingPartIndexKeys.length) {
				Integer currentClosedPart = closingPartIndexKeys[ii];
				Boolean peerNotFound = true;
				Integer maxClosedPartIndex = -1;
				int jj = 0;
				while(peerNotFound) {
					if(jj >= openingPartIndexKeys.length || currentClosedPart < openingPartIndexKeys[jj]) {
						peerNotFound = false;
					} else {
						Integer currentOpenPart = openingPartIndexKeys[jj];
						Boolean isSamePartKind = openingPartIndex.get(currentOpenPart).equals(closingPartIndex.get(currentClosedPart));
						if(openingPartIndex.containsKey(currentOpenPart) && isSamePartKind && currentClosedPart > currentOpenPart) {
							maxClosedPartIndex = currentOpenPart;
						}
					}
					jj++;
				}

				if(maxClosedPartIndex > -1) {
					IndexToExtract.put(maxClosedPartIndex, currentClosedPart);
				}
			}
		}
		Integer[] openingLinkedParts = IndexToExtract.keySet().toArray(new Integer[IndexToExtract.size()]);
		if(openingLinkedParts.length > 0) {
			Integer currentChiParentPart = openingLinkedParts[(openingLinkedParts.length -1)];
			Integer currentPqrentClosingPart = IndexToExtract.get(currentChiParentPart);
			for(int ii = (openingLinkedParts.length -2); ii > -1 ; ii--) {
				Integer currentOpeningPart = openingLinkedParts[ii];
				Integer currentClosingPart = IndexToExtract.get(currentOpeningPart);
				if(currentChiParentPart > currentOpeningPart && currentClosingPart < currentPqrentClosingPart) {
					stringToExtract.add(this.documentName.substring(currentChiParentPart, currentPqrentClosingPart));
				}
				currentChiParentPart = currentOpeningPart;
				currentPqrentClosingPart = currentClosingPart;
			}
		}
		for(int ii = 0; ii < stringToExtract.size(); ii++) {
			this.documentName = this.documentName.replace(stringToExtract.get(ii), StringUtils.EMPTY);
		}
		
		if(!openingPartIndex.isEmpty() || !closingPartIndex.isEmpty()) {
			this.documentName = this.documentName.replaceAll("([\\(,\\),\\[,\\],\\{,\\}])", StringUtils.EMPTY);
		}
		
		this.supresseMutlipleSpace();
	}
	
	
	
	private static String getDocumentName(String fileName){
		return fileName.substring(0, fileName.lastIndexOf("."));
	}
	
	/**
	 * Get the extension in the name of a file without the '.' (ex : 'Shrek.avi' become 'avi')
	 * @param fileName
	 * @return
	 */
	private static String getExtension(String fileName){
		if(fileName.lastIndexOf(".") != -1) {
			return fileName.substring(fileName.lastIndexOf(".") + 1);
		} else {
			return "";
		}	
	}

	/**
	 * can the selected folder and all it subfolder, add all file (any kind) in a list 
	 * nb: we tought about add a filter in argument (ex : FolderScanner('folder',all) or FolderScanner('folder',video))
	 * @param folderChosen
	 * @return
	 */
	public static ArrayList<File> FolderScanner(File folderChosen) {
		ArrayList<File> fileList = new ArrayList<File>();
		File[] fileListInFolder = folderChosen.listFiles();
		for (int ii = 0; ii < fileListInFolder.length; ii++) {
			if(fileListInFolder[ii].isDirectory()) {
				if (!fileListInFolder[ii].isHidden()) {
					fileList.addAll(FolderScanner(fileListInFolder[ii]));
				}
			} else {
				fileList.add(fileListInFolder[ii]);
			}
		}
		return(fileList);
	}
	
	/**
	 * scan the selected folder and all it subfolder, add all file (any kind) in a list 
	 * @param folderChosen
	 * @return
	 */
	public static ArrayList<File> FolderScannerVideo(File folderChosen) {
		ArrayList<File> fileList = new ArrayList<File>();
		File[] fileListInFolder = folderChosen.listFiles();
		for (int ii = 0; ii < fileListInFolder.length; ii++) {
			if(fileListInFolder[ii].isDirectory()) {
				if (!fileListInFolder[ii].isHidden()) {
					fileList.addAll(FolderScannerVideo(fileListInFolder[ii]));
				}
			} else {
				if(Video.isVideoExtensionType(Document.getExtension(fileListInFolder[ii].getName()))) {
					fileList.add(fileListInFolder[ii]);
				}
			}
		}
		return(fileList);
	}
	
	@Override
	public String toString() {
		return documentName;
	}
}