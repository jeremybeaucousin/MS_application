package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.TreeMap;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import model.Document;
import model.Movie;
import model.apis.ImdbApi;
import model.apis.TheMovieDB;


public final class ScanningProgress extends WindowContent {
	private TreeMap<String, JLabel> results;
	
	private ArrayList<File> videoFileList;
	
	private JProgressBar generalProgressBar;
	public ScanningProgress(MainWindow mainWindow, FileKindSelectionParameters fileKindSelectionParameters) {
		super(mainWindow);
		this.results = new TreeMap<String, JLabel>();
		
		this.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		this.setBackground(Color.WHITE);
		this.setBounds(10, 37, 558, 356);
		this.setLayout(null);
		
		// Main Panel
		JPanel mainPanel = new JPanel();
		mainPanel.setBackground(Color.WHITE);
		mainPanel.setBounds(1, 1, 556, 354);
		this.add(mainPanel);
		mainPanel.setLayout(null);
		
		// Movie Panel
		JPanel panelMovies = new JPanel();
		TitledBorder panelMoviesTitle = new TitledBorder(null, "Films", TitledBorder.LEADING, TitledBorder.TOP, null, null);
		panelMovies.setBorder(panelMoviesTitle);
		panelMovies.setBounds(10, 0, 538, 90);
		mainPanel.add(panelMovies);
		panelMovies.setLayout(null);

		JLabel movieScanState = new JLabel("Scan en cours...");
		movieScanState.setBounds(248, 12, 104, 14);
		panelMovies.add(movieScanState);
		
		JLabel movieLibelleTimeLeft = new JLabel("Temps restant :");
		movieLibelleTimeLeft.setBounds(10, 37, 104, 14);
		panelMovies.add(movieLibelleTimeLeft);
		
		JProgressBar movieProgressBar = new JProgressBar();
		movieProgressBar.setBounds(228, 37, 300, 14);
		panelMovies.add(movieProgressBar);
		
		JLabel movieLibelleDetailedSearch = new JLabel("Recherche avanc\u00E9e");
		movieLibelleDetailedSearch.setBounds(418, 62, 120, 14);
		panelMovies.add(movieLibelleDetailedSearch);
		
		if(!fileKindSelectionParameters.isVideoSelected()) {
			panelMoviesTitle.setTitleColor(SystemColor.controlShadow);
			for(Component component : panelMovies.getComponents()) {
				component.setForeground(SystemColor.controlShadow);
			}
		}
		
		// Series Panel
		JPanel panelSeries = new JPanel();
		TitledBorder panelSeriesTitle = new TitledBorder(null, "S\u00E9ries", TitledBorder.LEADING, TitledBorder.TOP, null, null);
		panelSeries.setBorder(panelSeriesTitle);
		panelSeries.setBounds(10, 90, 538, 90);
		mainPanel.add(panelSeries);
		panelSeries.setLayout(null);
		
		JLabel seriesScanState = new JLabel("Scan en attente");
		seriesScanState.setBounds(248, 11, 104, 14);
		panelSeries.add(seriesScanState);
		
		JLabel seriesLibelleTimeLeft = new JLabel("Temps restant :");
		seriesLibelleTimeLeft.setBounds(10, 36, 104, 14);
		panelSeries.add(seriesLibelleTimeLeft);
		
		JProgressBar seriesProgressBar = new JProgressBar();
		seriesProgressBar.setBounds(228, 36, 300, 14);
		panelSeries.add(seriesProgressBar);
		
		JLabel seriesLibelleDetailedSearch = new JLabel("Recherche avanc\u00E9e");
		seriesLibelleDetailedSearch.setBounds(418, 65, 120, 14);
		panelSeries.add(seriesLibelleDetailedSearch);
		
		if(!fileKindSelectionParameters.isSerieSelected()) {
			panelSeriesTitle.setTitleColor(SystemColor.controlShadow);
			for(Component component : panelSeries.getComponents()) {
				component.setForeground(SystemColor.controlShadow);
			}
		}

		// panelMusic
		JPanel panelMusic = new JPanel();
		TitledBorder panelMusicTitle = new TitledBorder(null, "Musique", TitledBorder.LEADING, TitledBorder.TOP, null, null);
		panelMusic.setBorder(panelMusicTitle);
		panelMusic.setBounds(10, 180, 538, 90);
		mainPanel.add(panelMusic);
		panelMusic.setLayout(null);
		
		JLabel musicScanState = new JLabel("Scan en attente");
		musicScanState.setBounds(248, 11, 104, 14);
		panelMusic.add(musicScanState);
		
		JLabel musicLibelleTimeLeft = new JLabel("Temps restant :");
		musicLibelleTimeLeft.setBounds(10, 36, 104, 14);
		panelMusic.add(musicLibelleTimeLeft);
		
		JProgressBar musicProgressBar = new JProgressBar();
		musicProgressBar.setBounds(228, 36, 300, 14);
		panelMusic.add(musicProgressBar);
		
		JLabel musicLibelleDetailedSearch = new JLabel("Recherche avanc\u00E9e");
		musicLibelleDetailedSearch.setBounds(418, 65, 120, 14);
		panelMusic.add(musicLibelleDetailedSearch);
		
		if(!fileKindSelectionParameters.isMusicSelected()) {
			panelMusicTitle.setTitleColor(SystemColor.controlShadow);
			for(Component component : panelMusic.getComponents()) {
				component.setForeground(SystemColor.controlShadow);
			}
		}
		
		// General Statistic
		JLabel genaralProgressText = new JLabel("Progression :");
		genaralProgressText.setBounds(10, 281, 113, 14);
		mainPanel.add(genaralProgressText);
		
		this.generalProgressBar = new JProgressBar();
		generalProgressBar.setBounds(198, 281, 350, 14);
		mainPanel.add(generalProgressBar);
		
		JLabel generalTimeElapsed = new JLabel("Temps \u00E9coul\u00E9 :");
		generalTimeElapsed.setBounds(10, 306, 124, 14);
		mainPanel.add(generalTimeElapsed);

		JLabel generalTimeLeft = new JLabel("Temps restant :");
		generalTimeLeft.setBounds(198, 306, 119, 14);
		mainPanel.add(generalTimeLeft);
		
		JLabel generalNumberOfFileText = new JLabel("Nombre de fichiers scann\u00E9s :");
		generalNumberOfFileText.setBounds(10, 331, 170, 14);
		mainPanel.add(generalNumberOfFileText);
		
		// TODO define content
		this.videoFileList = Document.FolderScannerVideo(fileKindSelectionParameters.getVideoFileChosen());

		this.generalProgressBar.setMinimum(0);
		this.generalProgressBar.setMaximum(videoFileList.size());
		this.generalProgressBar.setValue(0);
		this.generalProgressBar.setStringPainted(true);
		
//		this.results.put("Video_Selected", new JLabel("Video Selected : " + fileKindSelectionParameters.isVideoSelected()));
//		this.results.get("Video_Selected").setBounds(10, 36, 200, 14);
//		this.add(this.results.get("Video_Selected"));
//		
//		this.results.put("Serie_Selected", new JLabel("Serie Selected : " + fileKindSelectionParameters.isSerieSelected()));
//		this.results.get("Serie_Selected").setBounds(10, 66, 200, 14);
//		this.add(this.results.get("Serie_Selected"));
//		
//		this.results.put("Music_Selected", new JLabel("Music Selected : " + fileKindSelectionParameters.isMusicSelected()));
//		this.results.get("Music_Selected").setBounds(10, 96, 200, 14);
//		this.add(this.results.get("Music_Selected"));
	}
	
	public void startScan() {
		for(int ii = 0 ; ii < videoFileList.size(); ii++) {
			Movie movie = new Movie(videoFileList.get(ii).getName(), videoFileList.get(ii).getAbsolutePath());
			try {
				Integer responseMovie = TheMovieDB.searchMovieStudying(movie);
			} catch (JSONException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(new DecimalFormat("###.##").format(new Double(ii)/videoFileList.size()*100) + "%");
			System.out.println(ii);
			this.generalProgressBar.setValue(ii);
		}
	}
	public void setFileKindSelectionParameters(FileKindSelectionParameters fileKindSelectionParameters) {
		// TODO set content depending on the previous screen when this one has been instanced
		this.results.get("Video_Selected").setText("Video Selected : " + fileKindSelectionParameters.isVideoSelected());
		this.results.get("Serie_Selected").setText("Serie Selected : " + fileKindSelectionParameters.isSerieSelected());
		this.results.get("Music_Selected").setText("Music Selected : " + fileKindSelectionParameters.isMusicSelected());
		this.invalidate(); 
		this.validate();
		this.repaint();
	}
	
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		
		Component cancelButton = this.getComponent(3);
		Component previousButton = this.getComponent(4);
		Component nextStepButton = this.getComponent(5);
		if(source == cancelButton){
			this.getMainWindow().dispose();
		} else if(source == nextStepButton){
			System.out.println("suivant");
		} else if(source == previousButton){
			//this.mainWindow.getSearchingOnSelectedParam();
		}
	}

	@Override
	public void getNextScreen() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void getPreviousScreen() {
		this.getMainWindow().getPreviousButton().setEnabled(false);
		this.getMainWindow().replaceContent(this, this.getMainWindow().getFileKindSelection());
		
	}

	@Override
	public void setToItalian() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void setToGerman() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void setToSpanish() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void setToFrench() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setToEnglish() {
		// TODO Auto-generated method stub
		
	}
}
