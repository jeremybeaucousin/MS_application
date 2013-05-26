package maite;

import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.border.CompoundBorder;
import javax.swing.UIManager;
import javax.swing.border.EtchedBorder;
import javax.swing.JLabel;
import javax.swing.JCheckBox;
import javax.swing.border.TitledBorder;
import javax.swing.JList;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import java.awt.SystemColor;
import java.awt.Font;
import javax.swing.JFormattedTextField;
import javax.swing.JEditorPane;
import javax.swing.border.LineBorder;
import javax.swing.JProgressBar;

public class ScaningProgress extends JPanel {

	/**
	 * Create the panel.
	 */
	public ScaningProgress() {
		setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		setBackground(Color.WHITE);
		setLayout(null);
		
		JPanel mainPanel = new JPanel();
		mainPanel.setBackground(Color.WHITE);
		mainPanel.setBounds(0, 0, 558, 356);
		add(mainPanel);
		mainPanel.setLayout(null);
		
		JPanel panelMovies = new JPanel();
		panelMovies.setBorder(new TitledBorder(null, "Films", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelMovies.setBounds(10, 0, 538, 90);
		mainPanel.add(panelMovies);
		panelMovies.setLayout(null);
		
		JProgressBar movieProgressBar = new JProgressBar();
		movieProgressBar.setBounds(228, 37, 300, 14);
		panelMovies.add(movieProgressBar);
		
		JLabel movieScanState = new JLabel("Scan en cours...");
		movieScanState.setBounds(248, 12, 104, 14);
		panelMovies.add(movieScanState);
		
		JLabel movieLibelleTimeLeft = new JLabel("Temps restant :");
		movieLibelleTimeLeft.setBounds(10, 37, 104, 14);
		panelMovies.add(movieLibelleTimeLeft);
		
		JLabel movieLibelleDetailedSearch = new JLabel("Recherche avanc\u00E9e");
		movieLibelleDetailedSearch.setBounds(418, 62, 120, 14);
		panelMovies.add(movieLibelleDetailedSearch);
		
		JPanel panelSeries = new JPanel();
		panelSeries.setBorder(new TitledBorder(null, "S\u00E9ries", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelSeries.setBounds(10, 90, 538, 90);
		mainPanel.add(panelSeries);
		panelSeries.setLayout(null);
		
		JLabel seriesScanState = new JLabel("Scan en attente");
		seriesScanState.setBounds(248, 11, 104, 14);
		panelSeries.add(seriesScanState);
		
		JProgressBar seriesProgressBar = new JProgressBar();
		seriesProgressBar.setBounds(228, 36, 300, 14);
		panelSeries.add(seriesProgressBar);
		
		JLabel seriesLibelleDetailedSearch = new JLabel("Recherche avanc\u00E9e");
		seriesLibelleDetailedSearch.setBounds(418, 65, 120, 14);
		panelSeries.add(seriesLibelleDetailedSearch);
		
		JLabel seriesLibelleTimeLeft = new JLabel("Temps restant :");
		seriesLibelleTimeLeft.setBounds(10, 36, 104, 14);
		panelSeries.add(seriesLibelleTimeLeft);
		
		JPanel panelMusic = new JPanel();
		panelMusic.setBorder(new TitledBorder(null, "Musique", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelMusic.setBounds(10, 180, 538, 90);
		mainPanel.add(panelMusic);
		panelMusic.setLayout(null);
		
		JLabel musicScanState = new JLabel("Scan en attente");
		musicScanState.setBounds(248, 11, 104, 14);
		panelMusic.add(musicScanState);
		
		JProgressBar musicProgressBar = new JProgressBar();
		musicProgressBar.setBounds(228, 36, 300, 14);
		panelMusic.add(musicProgressBar);
		
		JLabel musicLibelleDetailedSearch = new JLabel("Recherche avanc\u00E9e");
		musicLibelleDetailedSearch.setBounds(418, 65, 120, 14);
		panelMusic.add(musicLibelleDetailedSearch);
		
		JLabel musicLibelleTimeLeft = new JLabel("Temps restant :");
		musicLibelleTimeLeft.setBounds(10, 36, 104, 14);
		panelMusic.add(musicLibelleTimeLeft);
		
		JLabel generalTimeLeft = new JLabel("Temps restant :");
		generalTimeLeft.setBounds(198, 306, 119, 14);
		mainPanel.add(generalTimeLeft);
		
		JLabel generalTimeElapsed = new JLabel("Temps \u00E9coul\u00E9 :");
		generalTimeElapsed.setBounds(10, 306, 124, 14);
		mainPanel.add(generalTimeElapsed);
		
		JLabel generalNumberOfFileText = new JLabel("Nombre de fichiers scann\u00E9s :");
		generalNumberOfFileText.setBounds(10, 331, 170, 14);
		mainPanel.add(generalNumberOfFileText);
		
		JProgressBar generalProgressBar = new JProgressBar();
		generalProgressBar.setBounds(198, 281, 350, 14);
		mainPanel.add(generalProgressBar);
		
		JLabel genaralProgressText = new JLabel("Progression :");
		genaralProgressText.setBounds(10, 281, 113, 14);
		mainPanel.add(genaralProgressText);

	}
}
