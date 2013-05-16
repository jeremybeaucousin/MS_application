package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Video {
	//Table of all the existing video file extensions 
	private final static List<String> VideoExtensionList = Arrays.asList(
			"AAF", "3GP", "GIF", "ASF", "AVCHD", "AVI", "CAM", "DAT",
			"DSH", "FLV", "M1V", "MPEG-1", "M2V", "MPEG-2", "FLA", "FLR",
			"SOL", "M4V", "MKV", "WRAP", "MNG",	"MOV", "QTCH", "MPEG", "MPEG-4",
			"MP4", "MXF", "ROQ", "NSV", "OGG",	"RM", "SVI", "SMI", "SWF");

	private static List<String> getVideoExtensionList() {
		return VideoExtensionList;
	}
	
	//test if an extension is a video type, return a boolean (true : video type, false : none)
	public static boolean isVideoExtensionType(String Extension) {
		return (Video.getVideoExtensionList().contains(Extension.toUpperCase())) ? true : false;
		
	}
}
