package Model;

import javax.swing.JFileChooser;

import util.ConstantString;

import java.io.File;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Document {
	/* Attributes */
	private String documentName;
	private String extension;
	
	/* Constructors */
	public Document(String documentName) {
		this.documentName = Document.getDocumentName(documentName);
		this.extension = Document.getExtension(documentName);
	}
	
	/* getters and setters */
	public String getDocumentName() {
		return documentName;
	}

	public String getExtension() {
		return extension;
	}

	/**
	 * Delete all the special characters in the movie name include in (".", "-", "_")
	 * @param name
	 * 		original name of the movie
	 * @return name of the movie without special caracters.
	 */
	public void deleteSpecialsCaracters() {
		this.documentName = this.documentName.replaceAll("([\\.,\\-,\\_])", ConstantString.SPACE);
		this.documentName = supresseMutlipleSpace(this.documentName);
	}
	
	public void insertSpaceBeforeCollapseUpperCaseOrInt() {
		// TODO attantion au mot su style "4Mot" entier suivi dun mot "([a-z][A-Z+])|([a-z][1-9+])|([1-9+][a-z])"
		Pattern pattern = Pattern.compile("([a-z][A-Z])|([a-z]?[1-9][a-z]?)");
		Matcher matcher = pattern.matcher(this.documentName);
		while (matcher.find()) {
			System.out.println(matcher.group());
			if(matcher.group().length() > 2) {
				this.documentName = this.documentName.replace(matcher.group(), matcher.group().substring(0,1) + ConstantString.SPACE + matcher.group().substring(1,2) + ConstantString.SPACE + matcher.group().substring(2));
			} else {
				this.documentName = this.documentName.replace(matcher.group(), matcher.group().substring(0,1) + ConstantString.SPACE + matcher.group().substring(1));
			}
		}
		this.documentName = supresseMutlipleSpace(this.documentName);
	}
	
	private static String supresseMutlipleSpace(String name) {
		return name.replaceAll("(\\s+)", ConstantString.SPACE);
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