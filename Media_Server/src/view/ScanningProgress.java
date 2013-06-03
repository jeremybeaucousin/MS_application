package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.TreeMap;

import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingWorker;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;


public final class ScanningProgress extends WindowContent implements PropertyChangeListener{
	private final HashMap<String, String> confirmationMessageTitle = new HashMap<String, String>() {
		{
			put(IT, "TODO");
			put(DE, "TODO");
			put(ES, "TODO");
			put(FR, "Attention");
			put(EN, "Warning");
		}			
	};
	
	private final HashMap<String, String> confirmationMessage = new HashMap<String, String>() {
		{
			put(IT, "TODO");
			put(DE, "TODO");
			put(ES, "TODO");
			put(FR, "Si vous arretez le scan en cours vous perdrez les données traités.");
			put(EN, "If you stop the scanning you will loose the data processed.");
		}			
	};
	
	private final static String PROGRESS = "progress", MOVIES_PROGRESS_SCAN = "moviesprogressscan", 
			SERIES_PROGRESS_SCAN = "seriesProgressScan", MUSIC_PROGRESS_SCAN = "musicProgressScan";
	
	private TreeMap<String, JLabel> results;
	
	private ArrayList<File> videoFileList;
	
	// TODO 
	private JProgressBar movieProgressBar, seriesProgressBar, musicProgressBar, generalProgressBar;

	private JPanel panelMovies, panelSeries, panelMusic;
	
	private Task generalTask;
	 
	class Task extends SwingWorker<Void, Void> {
    	private ScanningProgress scanningProgress;
    	
    	private Integer moviesProgressScan = 0, seriesProgressScan = 0, musicProgressScan = 0, globalProgressScan = 0;

    	Task(ScanningProgress scanningProgress, File videoFileChosen, File seriesFileChosen, File musicFileChosen) {
    		super();
    		this.scanningProgress = scanningProgress;
    	}
    	
        @Override
        public Void doInBackground() {
            Random random = new Random();
            int progress = 0;
            //Initialize progress property.
            this.setProgress(0);
            while (progress < 100 && !isCancelled()) {
                //Sleep for up to one second.
                try {
                    Thread.sleep(random.nextInt(100));
                } catch (InterruptedException ignore) {}
                //Make random progress.
                Float calcul = ((this.globalProgressScan.floatValue()/300) * 100);
                progress = calcul.intValue();
                this.globalProgressScan++;
                if(this.moviesProgressScan < 100) {
                	this.firePropertyChange(MOVIES_PROGRESS_SCAN, this.moviesProgressScan, this.moviesProgressScan + 1);
                	this.moviesProgressScan++;
                }
                if(this.moviesProgressScan >= 100 && this.seriesProgressScan < 100) {
                	this.firePropertyChange(SERIES_PROGRESS_SCAN, this.seriesProgressScan, this.seriesProgressScan + 1);
                	this.seriesProgressScan++;
                }
                if(this.moviesProgressScan >= 100 && this.seriesProgressScan >= 100 && this.musicProgressScan < 100) {
                	this.firePropertyChange(MUSIC_PROGRESS_SCAN, this.musicProgressScan, this.musicProgressScan + 1);
                	this.musicProgressScan++;
                }
                this.setProgress(progress);
            }
            return null;
        }
 
        /*
         * Executed in event dispatch thread
         */
        public void done() {
        	if(!this.isCancelled()) {
            	this.scanningProgress.getMainWindow().getCancelButton().setEnabled(false);
            	this.scanningProgress.getMainWindow().getPreviousButton().setEnabled(false);
            	this.scanningProgress.getMainWindow().getNextButton().setEnabled(true);
                Toolkit.getDefaultToolkit().beep();
        	}
//	            startButton.setEnabled(true);
//	            taskOutput.append("Done!\n");
        }
    }
	    
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if (PROGRESS.equals(evt.getPropertyName())) {
            this.generalProgressBar.setValue((Integer) evt.getNewValue());
            //taskOutput.append(String.format("Completed %d%% of task.\n", progress));
        } 
		if (MOVIES_PROGRESS_SCAN.equals(evt.getPropertyName())) {
            this.movieProgressBar.setValue((Integer) evt.getNewValue());
        } else if (SERIES_PROGRESS_SCAN.equals(evt.getPropertyName())) {
            this.seriesProgressBar.setValue((Integer) evt.getNewValue());
        } else if (MUSIC_PROGRESS_SCAN.equals(evt.getPropertyName())) {
            this.musicProgressBar.setValue((Integer) evt.getNewValue());
        }
	}
	
	public ScanningProgress(MainWindow mainWindow, FileKindSelectionParameters fileKindSelectionParameters) {
		super(mainWindow);
		this.results = new TreeMap<String, JLabel>();
		
		this.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		this.setBackground(Color.WHITE);
		this.setBounds(10, 37, 558, 356);
		this.setLayout(null);
		this.setOpaque(true);
		
		// Main Panel
		JPanel mainPanel = new JPanel();
		mainPanel.setBackground(Color.WHITE);
		mainPanel.setBounds(1, 1, 556, 354);
		this.add(mainPanel);
		mainPanel.setLayout(null);
		
		// Movie Panel
		this.panelMovies = new JPanel();
		TitledBorder panelMoviesTitle = new TitledBorder(null, "Films", TitledBorder.LEADING, TitledBorder.TOP, null, null);
		this.panelMovies.setBorder(panelMoviesTitle);
		this.panelMovies.setBounds(10, 11, 538, 85);
		mainPanel.add(this.panelMovies);
		this.panelMovies.setLayout(null);

		JLabel movieScanState = new JLabel("Scan en cours...");
		movieScanState.setBounds(248, 12, 104, 14);
		this.panelMovies.add(movieScanState);
		
		JLabel movieLibelleTimeLeft = new JLabel("Temps restant :");
		movieLibelleTimeLeft.setBounds(10, 37, 104, 14);
		this.panelMovies.add(movieLibelleTimeLeft);
		
		this.movieProgressBar = new JProgressBar();
		this.movieProgressBar.setBounds(228, 37, 300, 14);
		this.movieProgressBar.setIndeterminate(false);
		this.panelMovies.add(movieProgressBar);
		
		JLabel movieLibelleDetailedSearch = new JLabel("Recherche avanc\u00E9e");
		movieLibelleDetailedSearch.setBounds(418, 62, 120, 14);
		this.panelMovies.add(movieLibelleDetailedSearch);
		
		// Series Panel
		this.panelSeries = new JPanel();
		TitledBorder panelSeriesTitle = new TitledBorder(null, "S\u00E9ries", TitledBorder.LEADING, TitledBorder.TOP, null, null);
		this.panelSeries.setBorder(panelSeriesTitle);
		this.panelSeries.setBounds(10, 96, 538, 85);
		mainPanel.add(this.panelSeries);
		this.panelSeries.setLayout(null);
		
		JLabel seriesScanState = new JLabel("Scan en attente");
		seriesScanState.setBounds(248, 11, 104, 14);
		this.panelSeries.add(seriesScanState);
		
		JLabel seriesLibelleTimeLeft = new JLabel("Temps restant :");
		seriesLibelleTimeLeft.setBounds(10, 36, 104, 14);
		this.panelSeries.add(seriesLibelleTimeLeft);
		
		this.seriesProgressBar = new JProgressBar();
		this.seriesProgressBar.setBounds(228, 36, 300, 14);
		this.seriesProgressBar.setIndeterminate(false);
		this.panelSeries.add(this.seriesProgressBar);
		
		JLabel seriesLibelleDetailedSearch = new JLabel("Recherche avanc\u00E9e");
		seriesLibelleDetailedSearch.setBounds(418, 65, 120, 14);
		this.panelSeries.add(seriesLibelleDetailedSearch);
		
		// panelMusic
		this.panelMusic = new JPanel();
		TitledBorder panelMusicTitle = new TitledBorder(null, "Musique", TitledBorder.LEADING, TitledBorder.TOP, null, null);
		this.panelMusic.setBorder(panelMusicTitle);
		this.panelMusic.setBounds(10, 181, 538, 85);
		mainPanel.add(this.panelMusic);
		this.panelMusic.setLayout(null);
		
		JLabel musicScanState = new JLabel("Scan en attente");
		musicScanState.setBounds(248, 11, 104, 14);
		this.panelMusic.add(musicScanState);
		
		JLabel musicLibelleTimeLeft = new JLabel("Temps restant :");
		musicLibelleTimeLeft.setBounds(10, 36, 104, 14);
		this.panelMusic.add(musicLibelleTimeLeft);
		
		this.musicProgressBar = new JProgressBar();
		this.musicProgressBar.setBounds(228, 36, 300, 14);
		this.musicProgressBar.setIndeterminate(false);
		this.panelMusic.add(this.musicProgressBar);
		
		JLabel musicLibelleDetailedSearch = new JLabel("Recherche avanc\u00E9e");
		musicLibelleDetailedSearch.setBounds(418, 65, 120, 14);
		this.panelMusic.add(musicLibelleDetailedSearch);
		
		// General Statistic
		JLabel genaralProgressText = new JLabel("Progression :");
		genaralProgressText.setBounds(10, 281, 113, 14);
		mainPanel.add(genaralProgressText);
		
		this.generalProgressBar = new JProgressBar();
		this.generalProgressBar.setBounds(198, 281, 350, 14);
		this.generalProgressBar.setIndeterminate(false);
		
		this.generalProgressBar.setMinimum(0);
		this.generalProgressBar.setMaximum(100);
		this.generalProgressBar.setValue(0);
		this.generalProgressBar.setStringPainted(true);
		
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
		
		this.validContentDependingOnParameters(fileKindSelectionParameters);
	}
	
	public Task getGeneralTask() {
		return this.generalTask;
	}
	
	private void setPanelComponentsColor(JPanel panel, SystemColor color) {
		((TitledBorder) panel.getBorder()).setTitleColor(color);
		for(Component component : panel.getComponents()) {
			if(!(component instanceof JProgressBar)) {
				component.setForeground(color);
			}					
		}
	}
	
	public void validContentDependingOnParameters(FileKindSelectionParameters fileKindSelectionParameters) {
		if(!fileKindSelectionParameters.isVideoSelected()) {
			setPanelComponentsColor(this.panelMovies, SystemColor.controlShadow);
		} else {
			setPanelComponentsColor(this.panelMovies, null);
		}
		if(!fileKindSelectionParameters.isSerieSelected()) {
			setPanelComponentsColor(this.panelSeries, SystemColor.controlShadow);
		} else {
			setPanelComponentsColor(this.panelSeries, null);
		}
		if(!fileKindSelectionParameters.isMusicSelected()) {
			setPanelComponentsColor(this.panelMusic, SystemColor.controlShadow);
		} else {
			setPanelComponentsColor(this.panelMusic, null);
		}
		this.movieProgressBar.setValue(0);
		this.seriesProgressBar.setValue(0);
		this.musicProgressBar.setValue(0);
		this.generalProgressBar.setValue(0);
		
		this.generalTask = new Task(this, fileKindSelectionParameters.getVideoFileChosen(), fileKindSelectionParameters.getSeriesFileChosen(), fileKindSelectionParameters.getMusicFileChosen());
		this.generalTask.addPropertyChangeListener(this);
		this.generalTask.execute();
	}
	
	public Boolean stopProcess() {
		return JOptionPane.showConfirmDialog(this, this.confirmationMessage.get(WindowContent.getCurrentLanguage()), this.confirmationMessageTitle.get(WindowContent.getCurrentLanguage()), JOptionPane.INFORMATION_MESSAGE) == JFileChooser.APPROVE_OPTION;
	}
	
	@Override
	public void getNextScreen() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void getPreviousScreen() {
		this.getMainWindow().getPreviousButton().setEnabled(false);
		this.getMainWindow().getNextButton().setEnabled(true);
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
