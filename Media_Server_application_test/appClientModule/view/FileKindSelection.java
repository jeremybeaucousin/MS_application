package view;

import java.awt.Color;
import java.awt.SystemColor;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JEditorPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import model.views.FileKindSelectionParameters;

public final class FileKindSelection extends WindowContent {
	private JCheckBox checkboxScanMovies;
	private JCheckBox checkBoxScanSeries;
	private JCheckBox checkBoxScanMusic;
	public FileKindSelection(MainWindow mainWindow) throws IOException {
		super(mainWindow);
		
		this.setBackground(Color.WHITE);
		this.setLayout(null);
		
		JPanel panelIntroducing = new JPanel();
		panelIntroducing.setBackground(Color.WHITE);
		panelIntroducing.setBorder(null);
		panelIntroducing.setBounds(1, 1, 556, 58);
		this.add(panelIntroducing);
		panelIntroducing.setLayout(null);
		
		JEditorPane dtrpnExplainTexte = new JEditorPane();
		dtrpnExplainTexte.setBackground(Color.WHITE);
		dtrpnExplainTexte.setForeground(Color.BLACK);
		dtrpnExplainTexte.setText("Sélectionez le type de média que vous voulez scaner ainsi que leur emplacement. Précisez le type de recherche que vous désirez pour chacun.");
		dtrpnExplainTexte.setBounds(10, 11, 538, 37);
		panelIntroducing.add(dtrpnExplainTexte);
		
		// Main Panel
		JPanel form = new JPanel();
		form.setBorder(null);
		form.setBounds(1, 59, 556, 296);
		add(form);
		form.setLayout(null);
		
		// Movie Panel
		JPanel panelMovies = new JPanel();
		panelMovies.setBorder(new TitledBorder(null, "Films", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelMovies.setBounds(10, 0, 538, 71);
		form.add(panelMovies);
		panelMovies.setLayout(null);
		
		this.checkboxScanMovies = new JCheckBox("scanner");
		this.checkboxScanMovies.setSelected(true);
		this.checkboxScanMovies.setBounds(10, 12, 97, 23);
		panelMovies.add(this.checkboxScanMovies);
		
		JTextField textFieldMovies = new JTextField();
		textFieldMovies.setText("emplacement du dossier");
		textFieldMovies.setForeground(SystemColor.controlShadow);
		textFieldMovies.setColumns(10);
		textFieldMovies.setBounds(10, 42, 170, 20);
		panelMovies.add(textFieldMovies);
		
		JButton buttonMoviesLocation = new JButton("...");
		buttonMoviesLocation.setBounds(190, 41, 30, 23);
		panelMovies.add(buttonMoviesLocation);
		
		JCheckBox checkboxDetailedShearchMovies = new JCheckBox("Recherche approfondie");
		checkboxDetailedShearchMovies.setBounds(395, 41, 137, 23);
		panelMovies.add(checkboxDetailedShearchMovies);
		
		// Serie Panel
		JPanel panelSeries = new JPanel();
		panelSeries.setBorder(new TitledBorder(null, "Séries", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelSeries.setBounds(10, 70, 538, 71);
		form.add(panelSeries);
		panelSeries.setLayout(null);
		
		this.checkBoxScanSeries = new JCheckBox("scanner");
		this.checkBoxScanSeries.setSelected(true);
		this.checkBoxScanSeries.setBounds(10, 12, 97, 23);
		panelSeries.add(this.checkBoxScanSeries);
		
		JTextField textFieldSeries = new JTextField();
		textFieldSeries.setText("emplacement du dossier");
		textFieldSeries.setForeground(SystemColor.controlShadow);
		textFieldSeries.setColumns(10);
		textFieldSeries.setBounds(10, 42, 170, 20);
		panelSeries.add(textFieldSeries);
		
		
		JButton buttonSeriesLoc = new JButton("...");
		buttonSeriesLoc.setBounds(190, 41, 30, 23);
		panelSeries.add(buttonSeriesLoc);
		
		JCheckBox checkBoxDetailedShearchSeries = new JCheckBox("Recherche approfondie");
		checkBoxDetailedShearchSeries.setBounds(395, 41, 137, 23);
		panelSeries.add(checkBoxDetailedShearchSeries);
		
		// Music Panel
		JPanel panelMusics = new JPanel();
		panelMusics.setBorder(new TitledBorder(null, "Musiques", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelMusics.setBounds(10, 141, 538, 71);
		form.add(panelMusics);
		panelMusics.setLayout(null);
		
		this.checkBoxScanMusic = new JCheckBox("scanner");
		this.checkBoxScanMusic.setSelected(true);
		this.checkBoxScanMusic.setBounds(10, 12, 97, 23);
		panelMusics.add(this.checkBoxScanMusic);
		
		JTextField textFieldMusic = new JTextField();
		textFieldMusic.setText("emplacement du dossier");
		textFieldMusic.setForeground(SystemColor.controlShadow);
		textFieldMusic.setColumns(10);
		textFieldMusic.setBounds(10, 42, 170, 20);
		panelMusics.add(textFieldMusic);
		
		JButton buttonMusicLocation = new JButton("...");
		buttonMusicLocation.setBounds(190, 41, 30, 23);
		panelMusics.add(buttonMusicLocation);
		
		JCheckBox checkBoxDetailedShearchMusics = new JCheckBox("Recherche approfondie");
		checkBoxDetailedShearchMusics.setBounds(395, 41, 137, 23);
		panelMusics.add(checkBoxDetailedShearchMusics);
		
		// Unique location Panel
		JPanel panelUniqueLocation = new JPanel();
		panelUniqueLocation.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panelUniqueLocation.setBounds(10, 217, 538, 71);
		form.add(panelUniqueLocation);
		panelUniqueLocation.setLayout(null);
		
		JCheckBox checkboxUniqueLocation = new JCheckBox("Emplacement unique");
		checkboxUniqueLocation.setBounds(6, 7, 131, 23);
		panelUniqueLocation.add(checkboxUniqueLocation);
		
		JTextField textFieldUniqueLocation = new JTextField();
		textFieldUniqueLocation.setEnabled(false);
		textFieldUniqueLocation.setForeground(UIManager.getColor("Button.shadow"));
		textFieldUniqueLocation.setText("emplacement du dossier");
		textFieldUniqueLocation.setBounds(6, 42, 170, 20);
		panelUniqueLocation.add(textFieldUniqueLocation);
		textFieldUniqueLocation.setColumns(10);
		
		JButton buttonUniqueLocationLoc = new JButton("...");
		buttonUniqueLocationLoc.setEnabled(false);
		buttonUniqueLocationLoc.setBounds(186, 41, 30, 23);
		panelUniqueLocation.add(buttonUniqueLocationLoc);
		
		JCheckBox checkBoxDetailedShearchUniqueLocation = new JCheckBox("Recherche approfondie");
		checkBoxDetailedShearchUniqueLocation.setEnabled(false);
		checkBoxDetailedShearchUniqueLocation.setBounds(395, 41, 137, 23);
		panelUniqueLocation.add(checkBoxDetailedShearchUniqueLocation);
		
//		gridBagConstraints.fill = GridBagConstraints.CENTER;
//		gridBagConstraints.gridx = 0;
//		gridBagConstraints.gridy = 1;
//		JCheckBoxMenuItem checkbox1 = new JCheckBoxMenuItem();
//		checkbox1.setText("Film");
//		this.add(checkbox1, gridBagConstraints);
//		
//		gridBagConstraints.fill = GridBagConstraints.CENTER;
//		gridBagConstraints.gridx = 0;
//		gridBagConstraints.gridy = 2;
//		JCheckBoxMenuItem checkbox2 = new JCheckBoxMenuItem();
//		checkbox2.setText("Série");
//		this.add(checkbox2, gridBagConstraints);
//		
//		gridBagConstraints.fill = GridBagConstraints.CENTER;
//		gridBagConstraints.gridx = 0;
//		gridBagConstraints.gridy = 3;
//		JCheckBoxMenuItem checkbox3 = new JCheckBoxMenuItem();
//		checkbox3.setText("Musique");
//		this.add(checkbox3, gridBagConstraints);
	}
	
	public FileKindSelectionParameters getFileKindSelectionParameters() {
		boolean videoIsSelected = this.checkboxScanMovies.isSelected();
		boolean serieIsSelected = this.checkBoxScanSeries.isSelected();
		boolean musicIsSelected = this.checkBoxScanMusic.isSelected();
		FileKindSelectionParameters fileKindSelectionParameters = new FileKindSelectionParameters(videoIsSelected, serieIsSelected, musicIsSelected);
		return fileKindSelectionParameters;
	}
	
	@Override
	public void getNextScreen() {
		this.getMainWindow().getPreviousButton().setEnabled(true);
		FileKindSelectionParameters fileKindSelectionParameters = this.getFileKindSelectionParameters();
		if(this.getMainWindow().getSearchingOnSelectedValue() == null) {
			SearchingOnSelectedValues searchingOnSelectedValues = new SearchingOnSelectedValues(this.getMainWindow(), fileKindSelectionParameters);
			this.getMainWindow().setSearchingOnSelectedValue(searchingOnSelectedValues);
			this.getMainWindow().getNavigator().add(searchingOnSelectedValues);
		} else {
			this.getMainWindow().getNavigator().next();
			this.getMainWindow().getSearchingOnSelectedValue().setFileKindSelectionParameters(fileKindSelectionParameters);
		}
	    this.getMainWindow().replaceContent(this, this.getMainWindow().getSearchingOnSelectedValue());
	}

	@Override
	public void getPreviousScreen() {
		this.getMainWindow().getPreviousButton().setEnabled(false);
		this.getMainWindow().replaceContent(this.getMainWindow().getSearchingOnSelectedValue(), this);
		
	}

	@Override
	public void setToFrench() {
		// TODO Auto-generated method stub
		System.out.println("french");
	}

	@Override
	public void setToEnglish() {
		// TODO Auto-generated method stub
		System.out.println("english");
	}

}
