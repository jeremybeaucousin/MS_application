package Model;

import javax.swing.JFileChooser;
import java.io.File;
import java.util.ArrayList;

public class Document {
	
	//Get the extension in the name of a file without the '.' (ex : 'Shrek.avi' become 'avi')
	public static String getExtension(String fileName){
		if(fileName.lastIndexOf(".") != -1) {
			return fileName.substring(fileName.lastIndexOf(".") + 1);
		} else {
			return "";
		}	
	}
	
	public static String getDocumentName(String fileName){
		return fileName.substring(0, fileName.lastIndexOf("."));
	}
	
	//scan the selected folder and all it subfolder, add all file (any kind) in a list 
	//nb: we tought about add a filter in argument (ex : FolderScanner('folder',all) or FolderScanner('folder',video))
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
	
	//scan the selected folder and all it subfolder, add all file (any kind) in a list 
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
}