package view;

import java.io.File;

public final class FileKindSelectionParameters {
	final private Boolean videoIsSelected, serieIsSelected, musicIsSelected, uniqueIsSelected, detailedShearchMoviesSelected, detailedShearchSeriesSelected, detailedShearchMusicsSelected, detailedShearchUniqueLocationSelected;
	
	final private File videoFileChosen, seriesFileChosen, musicFileChosen, uniqueFileChosen;
	
	public FileKindSelectionParameters(Boolean videoIsSelected,
			Boolean serieIsSelected, Boolean musicIsSelected,Boolean uniqueIsSelected,
			Boolean detailedShearchMoviesSelected,
			Boolean detailedShearchSeriesSelected,
			Boolean detailedShearchMusicsSelected,
			Boolean detailedShearchUniqueLocationSelected,
			File videoFileChosen, File seriesFileChosen, File musicFileChosen,
			File uniqueFileChosen) {
		super();
		this.videoIsSelected = videoIsSelected;
		this.serieIsSelected = serieIsSelected;
		this.musicIsSelected = musicIsSelected;
		this.uniqueIsSelected = uniqueIsSelected;
		this.detailedShearchMoviesSelected = detailedShearchMoviesSelected;
		this.detailedShearchSeriesSelected = detailedShearchSeriesSelected;
		this.detailedShearchMusicsSelected = detailedShearchMusicsSelected;
		this.detailedShearchUniqueLocationSelected = detailedShearchUniqueLocationSelected;
		this.videoFileChosen = videoFileChosen;
		this.seriesFileChosen = seriesFileChosen;
		this.musicFileChosen = musicFileChosen;
		this.uniqueFileChosen = uniqueFileChosen;
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

	public Boolean isUniqueIsSelected() {
		return uniqueIsSelected;
	}

	public Boolean isDetailedShearchMoviesSelected() {
		return detailedShearchMoviesSelected;
	}

	public Boolean isDetailedShearchSeriesSelected() {
		return detailedShearchSeriesSelected;
	}

	public Boolean isDetailedShearchMusicsSelected() {
		return detailedShearchMusicsSelected;
	}

	public Boolean isDetailedShearchUniqueLocationSelected() {
		return detailedShearchUniqueLocationSelected;
	}
	
	public File getVideoFileChosen() {
		return videoFileChosen;
	}

	public File getSeriesFileChosen() {
		return seriesFileChosen;
	}

	public File getMusicFileChosen() {
		return musicFileChosen;
	}

	public File getUniqueFileChosen() {
		return uniqueFileChosen;
	}
	

	
}
