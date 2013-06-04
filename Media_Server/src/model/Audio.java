package model;

import java.util.Arrays;
import java.util.List;

public class Audio {
	//Table of all the existing audio file extensions 
	private final static List<String> audioExtensionList = Arrays.asList(
			"3GP", "ACT", "AIFF", "AAC", "ALAC", "AMR", "ATRAC", "AU",
			"AWB", "DCT", "DSS", "DVF", "FLAC", "GSM", "IKLAX", "IVS",
			"M4A", "M4P", "MMF", "MP3", "MPC",	"MSV", "OGG", "OPUS", "RA",
			"RM", "RAW", "TTA", "VOX", "WAV",	"WMA");

	private static List<String> getAudioExtensionList() {
		return audioExtensionList;
	}
	
	//test if an extension is a audio type, return a boolean (true : audio type, false : none)
	public static boolean isAudioExtensionType(String Extension) {
		return (Audio.getAudioExtensionList().contains(Extension.toUpperCase())) ? true : false;
		
	}
}
