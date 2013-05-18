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
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 558, 356);
		add(panel);
		panel.setLayout(null);
		
		JPanel panelFilmsProgress = new JPanel();
		panelFilmsProgress.setBounds(0, 0, 558, 88);
		panel.add(panelFilmsProgress);
		panelFilmsProgress.setLayout(null);
		
		JLabel lblScanDesFilms = new JLabel("Scan des films en cours...");
		lblScanDesFilms.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblScanDesFilms.setBounds(10, 11, 154, 14);
		panelFilmsProgress.add(lblScanDesFilms);
		
		JProgressBar progressBarFilms = new JProgressBar();
		progressBarFilms.setBounds(20, 36, 528, 14);
		panelFilmsProgress.add(progressBarFilms);
		
		JLabel lblResearchKindFilms = new JLabel("recherche avanc\u00E9e");
		lblResearchKindFilms.setBounds(450, 63, 98, 14);
		panelFilmsProgress.add(lblResearchKindFilms);
		
		JLabel lblTimeLeftFilms = new JLabel("Temps restant :");
		lblTimeLeftFilms.setBounds(10, 63, 130, 14);
		panelFilmsProgress.add(lblTimeLeftFilms);
		
		JPanel panelSeriesProgress = new JPanel();
		panelSeriesProgress.setBounds(0, 88, 558, 88);
		panel.add(panelSeriesProgress);
		panelSeriesProgress.setLayout(null);
		
		JLabel lblScanDesSeries = new JLabel("Scan des s\u00E9ries en attente");
		lblScanDesSeries.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblScanDesSeries.setBounds(10, 11, 154, 14);
		panelSeriesProgress.add(lblScanDesSeries);
		
		JProgressBar progressBarseries = new JProgressBar();
		progressBarseries.setBounds(20, 36, 528, 14);
		panelSeriesProgress.add(progressBarseries);
		
		JLabel lblReshearchkindSeries = new JLabel("recherche avanc\u00E9e");
		lblReshearchkindSeries.setBounds(450, 63, 98, 14);
		panelSeriesProgress.add(lblReshearchkindSeries);
		
		JLabel label_2 = new JLabel("Temps restant :");
		label_2.setBounds(10, 63, 130, 14);
		panelSeriesProgress.add(label_2);
		
		JPanel panelMusicProgress = new JPanel();
		panelMusicProgress.setBounds(0, 176, 558, 88);
		panel.add(panelMusicProgress);
		panelMusicProgress.setLayout(null);
		
		JLabel lblScanDesMusiques = new JLabel("Scan des musiques en attente");
		lblScanDesMusiques.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblScanDesMusiques.setBounds(10, 11, 176, 14);
		panelMusicProgress.add(lblScanDesMusiques);
		
		JProgressBar progressBar_2 = new JProgressBar();
		progressBar_2.setBounds(20, 36, 528, 14);
		panelMusicProgress.add(progressBar_2);
		
		JLabel label_1 = new JLabel("recherche avanc\u00E9e");
		label_1.setBounds(450, 63, 98, 14);
		panelMusicProgress.add(label_1);
		
		JLabel label_3 = new JLabel("Temps restant :");
		label_3.setBounds(10, 63, 130, 14);
		panelMusicProgress.add(label_3);
		
		JLabel label_4 = new JLabel("Temps restant :");
		label_4.setBounds(10, 331, 130, 14);
		panel.add(label_4);
		
		JLabel lblProgression = new JLabel("Progression :");
		lblProgression.setBounds(10, 275, 83, 14);
		panel.add(lblProgression);
		
		JProgressBar progressBar_3 = new JProgressBar();
		progressBar_3.setBounds(20, 300, 528, 14);
		panel.add(progressBar_3);

	}
}
