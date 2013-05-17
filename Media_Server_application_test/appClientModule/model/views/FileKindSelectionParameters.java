package model.views;

public final class FileKindSelectionParameters {
	final private Boolean videoIsSelected;
	final private Boolean serieIsSelected;
	final private Boolean musicIsSelected;
	
	public FileKindSelectionParameters(Boolean videoIsSelected, Boolean serieIsSelected,
			Boolean musicIsSelected) {
		this.videoIsSelected = videoIsSelected;
		this.serieIsSelected = serieIsSelected;
		this.musicIsSelected = musicIsSelected;
	}

	public Boolean isVideoSelected() {
		return videoIsSelected;
	}

	public Boolean isSerieSelected() {
		return serieIsSelected;
	}

	public Boolean isMusicSelected() {
		return musicIsSelected;
	}
	
}
