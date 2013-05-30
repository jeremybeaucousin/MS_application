package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Random;
import java.util.TreeMap;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
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


public final class ScanningProgress extends WindowContent implements PropertyChangeListener{
	private TreeMap<String, JLabel> results;
	
	private ArrayList<File> videoFileList;
	
	// TODO 
	private JProgressBar movieProgressBar;
	
	private JProgressBar seriesProgressBar;
	
	private JProgressBar generalProgressBar;
	
	private JProgressBar musicProgressBar;

	private Task generalTask;
	 
	class Task extends SwingWorker<Void, Void> {
    	private ScanningProgress scanningProgress;
    	
    	private Integer moviesProgressScan = 0;
    	
    	private Integer seriesProgressScan = 0;
    	
    	private Integer musicProgressScan = 0;
    	
    	private Integer globalProgressScan = 0;

    	Task(ScanningProgress scanningProgress) {
    		super();
    		this.scanningProgress = scanningProgress;
    	}
    	
    	int getVideoProgressScan() {
    		return this.moviesProgressScan;
    	}
    	
    	int getSeriesProgressScan() {
    		return this.seriesProgressScan;
    	}
    	
    	int getMusicProgressScan() {
    		return this.musicProgressScan;
    	}
    	
    	int getGlobalProgressScan() {
    		return this.globalProgressScan;
    	}
    	
        @Override
        public Void doInBackground() {
        	System.out.println("test");
            Random random = new Random();
            int progress = 0;
            //Initialize progress property.
            this.setProgress(0);
            while (progress < 100) {
                //Sleep for up to one second.
                try {
                    Thread.sleep(random.nextInt(1000));
                } catch (InterruptedException ignore) {}
                //Make random progress.
                Float calcul = ((this.globalProgressScan.floatValue()/300) * 100);
                progress = calcul.intValue();
                this.globalProgressScan++;
                if(this.moviesProgressScan < 100) {
                	this.firePropertyChange("moviesProgressScan", this.moviesProgressScan, this.moviesProgressScan + 1);
                	this.moviesProgressScan++;
                }
                if(this.moviesProgressScan >= 100 && this.seriesProgressScan < 100) {
                	this.firePropertyChange("seriesProgressScan", this.seriesProgressScan, this.seriesProgressScan + 1);
                	this.seriesProgressScan++;
                }
                if(this.moviesProgressScan >= 100 && this.seriesProgressScan >= 100 && this.musicProgressScan < 100) {
                	this.firePropertyChange("musicProgressScan", this.musicProgressScan, this.musicProgressScan + 1);
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
            Toolkit.getDefaultToolkit().beep();
//	            startButton.setEnabled(true);
//	            taskOutput.append("Done!\n");
        }
    }
	    
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		System.out.println(evt);
		System.out.println(this.generalTask.getVideoProgressScan());
		if ("progress".equals(evt.getPropertyName())) {
            int progress = (Integer) evt.getNewValue();
            this.generalProgressBar.setValue(progress);
            //taskOutput.append(String.format("Completed %d%% of task.\n", progress));
        } 
		if ("moviesProgressScan".equals(evt.getPropertyName())) {
            this.movieProgressBar.setValue(this.generalTask.getVideoProgressScan());
        } else if ("seriesProgressScan".equals(evt.getPropertyName())) {
            this.seriesProgressBar.setValue(this.generalTask.getSeriesProgressScan());
        } else if ("musicProgressScan".equals(evt.getPropertyName())) {
            this.musicProgressBar.setValue(this.generalTask.getMusicProgressScan());
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
		
		this.movieProgressBar = new JProgressBar();
		this.movieProgressBar.setBounds(228, 37, 300, 14);
		this.movieProgressBar.setIndeterminate(false);
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
		
		this.seriesProgressBar = new JProgressBar();
		this.seriesProgressBar.setBounds(228, 36, 300, 14);
		this.seriesProgressBar.setIndeterminate(false);
		panelSeries.add(this.seriesProgressBar);
		
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
		
		this.musicProgressBar = new JProgressBar();
		this.musicProgressBar.setBounds(228, 36, 300, 14);
		this.musicProgressBar.setIndeterminate(false);
		panelMusic.add(this.musicProgressBar);
		
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
		
		this.generalTask = new Task(this);
		generalTask.addPropertyChangeListener(this);
		generalTask.execute();
		
		
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
