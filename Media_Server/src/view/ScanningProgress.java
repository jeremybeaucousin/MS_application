package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.TreeMap;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.border.EtchedBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;


public final class ScanningProgress extends WindowContent {
	private TreeMap<String, JLabel> results;

	public ScanningProgress(MainWindow mainWindow, FileKindSelectionParameters fileKindSelectionParameters) {
		super(mainWindow);
		this.results = new TreeMap<String, JLabel>();
		
		this.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		this.setBackground(Color.WHITE);
		this.setBounds(10, 37, 558, 356);
		this.setLayout(null);
		
		JPanel mainPanel = new JPanel();
		mainPanel.setBounds(1, 1, 556, 354);
		this.add(mainPanel);
		mainPanel.setLayout(null);
		
		JPanel panelMovies = new JPanel();
		panelMovies.setBorder(new TitledBorder(null, "Films", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelMovies.setBounds(0, 0, 558, 90);
		mainPanel.add(panelMovies);
		panelMovies.setLayout(null);
		
		JProgressBar progressBar = new JProgressBar();
		progressBar.setBounds(248, 37, 300, 14);
		panelMovies.add(progressBar);
		
		JLabel lblScanEnCours = new JLabel("Scan en cours...");
		lblScanEnCours.setBounds(248, 12, 104, 14);
		panelMovies.add(lblScanEnCours);
		
		JLabel lblTempsRestant = new JLabel("Temps restant :");
		lblTempsRestant.setBounds(10, 37, 104, 14);
		panelMovies.add(lblTempsRestant);
		
		JLabel lblNewLabel = new JLabel("Recherche avanc\u00E9e");
		lblNewLabel.setBounds(444, 62, 104, 14);
		panelMovies.add(lblNewLabel);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "S\u00E9ries", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(0, 90, 558, 90);
		mainPanel.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblScanEnAttente = new JLabel("Scan en attente");
		lblScanEnAttente.setBounds(248, 11, 104, 14);
		panel_2.add(lblScanEnAttente);
		
		JProgressBar progressBar_1 = new JProgressBar();
		progressBar_1.setBounds(248, 36, 300, 14);
		panel_2.add(progressBar_1);
		
		JLabel label = new JLabel("Recherche avanc\u00E9e");
		label.setBounds(444, 65, 104, 14);
		panel_2.add(label);
		
		JLabel label_1 = new JLabel("Temps restant :");
		label_1.setBounds(10, 36, 104, 14);
		panel_2.add(label_1);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(null, "Musique", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_3.setBounds(0, 180, 558, 90);
		mainPanel.add(panel_3);
		panel_3.setLayout(null);
		
		JLabel label_2 = new JLabel("Scan en attente");
		label_2.setBounds(248, 11, 104, 14);
		panel_3.add(label_2);
		
		JProgressBar progressBar_2 = new JProgressBar();
		progressBar_2.setBounds(248, 36, 300, 14);
		panel_3.add(progressBar_2);
		
		JLabel label_3 = new JLabel("Recherche avanc\u00E9e");
		label_3.setBounds(444, 65, 104, 14);
		panel_3.add(label_3);
		
		JLabel label_4 = new JLabel("Temps restant :");
		label_4.setBounds(10, 36, 104, 14);
		panel_3.add(label_4);
		
		JLabel lblTempsRestant_1 = new JLabel("Temps restant :");
		lblTempsRestant_1.setBounds(198, 306, 86, 14);
		mainPanel.add(lblTempsRestant_1);
		
		JLabel lblTempscoul = new JLabel("Temps \u00E9coul\u00E9 :");
		lblTempscoul.setBounds(10, 306, 93, 14);
		mainPanel.add(lblTempscoul);
		
		JLabel lblNombreDeFichiers = new JLabel("Nombre de fichiers scann\u00E9s :");
		lblNombreDeFichiers.setBounds(10, 331, 155, 14);
		mainPanel.add(lblNombreDeFichiers);
		
		JProgressBar progressBar_3 = new JProgressBar();
		progressBar_3.setBounds(198, 281, 350, 14);
		mainPanel.add(progressBar_3);
		
		JLabel lblProgression = new JLabel("Progression :");
		lblProgression.setBounds(10, 281, 93, 14);
		mainPanel.add(lblProgression);
		
		
//		this.setBackground(Color.WHITE);
//		this.setLayout(null);
//		this.setBounds(0, 0, 558, 356);
//		this.setLayout(null);
//		
//		
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
	
//	public void setFileKindSelectionParameters(FileKindSelectionParameters fileKindSelectionParameters) {
//		this.results.get("Video_Selected").setText("Video Selected : " + fileKindSelectionParameters.isVideoSelected());
//		this.results.get("Serie_Selected").setText("Serie Selected : " + fileKindSelectionParameters.isSerieSelected());
//		this.results.get("Music_Selected").setText("Music Selected : " + fileKindSelectionParameters.isMusicSelected());
//		this.invalidate(); 
//		this.validate();
//		this.repaint();
//	}
	
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
