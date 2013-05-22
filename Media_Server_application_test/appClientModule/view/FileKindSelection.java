package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.SystemColor;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JEditorPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;



public final class FileKindSelection extends WindowContent {
	// textes //
	/** Contains all components panel that have a title displayed on screen **/
	private ArrayList<JPanel> panelWithTitle = new ArrayList<JPanel>();
	
	private final HashMap<String, String> DescriptionTexts = new HashMap<String, String>() {
		{
			put(FR, "Sélectionez le type de média que vous voulez scaner ainsi que leur emplacement. Précisez le type de recherche que vous désirez pour chacun.");
			put(EN, "Select The kind of media you want to scan and their location. Precise the kind of search for any of them.");
		}			
	};
	
	private final HashMap<String, String> moviePanelTitleTexts = new HashMap<String, String>() {
		{
			put(FR, "Films");
			put(EN, "Movies");
		}			
	};
	
	
	private final HashMap<String, String> seriesPanelTitleTexts = new HashMap<String, String>() {
		{
			put(FR, "Séries");
			put(EN, "Series");
		}			
	};
	
	private final HashMap<String, String> musicPanelTitleTexts = new HashMap<String, String>() {
		{
			put(FR, "Musique");
			put(EN, "Music");
		}			
	};
	
	private final HashMap<String, String> checkboxUniqueLocationTexts = new HashMap<String, String>() {
		{
			put(FR, "Emplacement unique");
			put(EN, "Unique location");
		}			
	};
	
	private final HashMap<String, String> scanCheckboxTexts = new HashMap<String, String>() {
		{
			put(FR, "scanner");
			put(EN, "scan");
		}			
	};
	
	private final HashMap<String, String> locationsTexts = new HashMap<String, String>() {
		{
			put(FR, "Emplacement du dossier");
			put(EN, "File Location");
		}			
	};
	
	private final HashMap<String, String> checkboxDetailedShearchTexts = new HashMap<String, String>() {
		{
			put(FR, "Recherche approfondie");
			put(EN, "Detailed search");
		}			
	};
	
	// Buttons //
	private JCheckBox checkboxScanMovies;
	private JCheckBox checkBoxScanSeries;
	private JCheckBox checkBoxScanMusic;
	public FileKindSelection(MainWindow mainWindow) throws IOException {
		super(mainWindow);
		
		this.setBackground(Color.WHITE);
		this.setLayout(null);
		
		JPanel introducingPanel = new JPanel();
		introducingPanel.setBackground(Color.WHITE);
		introducingPanel.setBorder(null);
		introducingPanel.setBounds(1, 1, 556, 58);
		this.add(introducingPanel);
		introducingPanel.setLayout(null);
		
		JEditorPane introducingText = new JEditorPane();
		introducingText.setText(this.DescriptionTexts.get(WindowContent.getDefaultlanguage()));
		this.getComponentsWithText().put(introducingText, this.DescriptionTexts);
		introducingText.setBackground(Color.WHITE);
		introducingText.setForeground(Color.BLACK);
		introducingText.setBounds(10, 11, 538, 37);
		introducingPanel.add(introducingText);
		
		// Main Panel
		JPanel form = new JPanel();
		form.setBorder(null);
		form.setBounds(1, 59, 556, 296);
		add(form);
		form.setLayout(null);
		
		// Movie Panel
		JPanel panelMovies = new JPanel();
		TitledBorder moviePanelTitle = new TitledBorder(null, this.moviePanelTitleTexts.get(WindowContent.getDefaultlanguage()), TitledBorder.LEADING, TitledBorder.TOP, null, null);
		panelMovies.setBorder(moviePanelTitle);
		this.panelWithTitle.add(panelMovies);
		this.getComponentsWithText().put(moviePanelTitle, this.moviePanelTitleTexts);
		panelMovies.setBounds(10, 0, 538, 71);
		form.add(panelMovies);
		panelMovies.setLayout(null);
		
		this.checkboxScanMovies = new JCheckBox(this.scanCheckboxTexts.get(WindowContent.getDefaultlanguage()));
		this.getComponentsWithText().put(this.checkboxScanMovies, this.scanCheckboxTexts);
		this.checkboxScanMovies.setSelected(true);
		this.checkboxScanMovies.setBounds(10, 12, 97, 23);
		panelMovies.add(this.checkboxScanMovies);
		
		JTextField moviesLocation = new JTextField();
		moviesLocation.setText(this.locationsTexts.get(WindowContent.getDefaultlanguage()));
		this.getComponentsWithText().put(moviesLocation, this.locationsTexts);
		moviesLocation.setForeground(SystemColor.controlShadow);
		moviesLocation.setColumns(10);
		moviesLocation.setBounds(10, 42, 170, 20);
		panelMovies.add(moviesLocation);
		
		JButton buttonMoviesFolderSelection = new JButton("...");
		buttonMoviesFolderSelection.setBounds(190, 41, 30, 23);
		panelMovies.add(buttonMoviesFolderSelection);
		
		JCheckBox checkboxDetailedShearchMovies = new JCheckBox(this.checkboxDetailedShearchTexts.get(WindowContent.getDefaultlanguage()));
		this.getComponentsWithText().put(checkboxDetailedShearchMovies, this.checkboxDetailedShearchTexts);
		checkboxDetailedShearchMovies.setBounds(395, 41, 137, 23);
		panelMovies.add(checkboxDetailedShearchMovies);
		
		// Serie Panel
		JPanel panelSeries = new JPanel();
		TitledBorder seriesPanelTitle = new TitledBorder(null, seriesPanelTitleTexts.get(WindowContent.getDefaultlanguage()), TitledBorder.LEADING, TitledBorder.TOP, null, null);
		panelSeries.setBorder(seriesPanelTitle);
		this.panelWithTitle.add(panelSeries);
		this.getComponentsWithText().put(seriesPanelTitle, this.seriesPanelTitleTexts);
		panelSeries.setBounds(10, 70, 538, 71);
		form.add(panelSeries);
		panelSeries.setLayout(null);
		
		this.checkBoxScanSeries = new JCheckBox(this.scanCheckboxTexts.get(WindowContent.getDefaultlanguage()));
		this.getComponentsWithText().put(this.checkBoxScanSeries, this.scanCheckboxTexts);
		this.checkBoxScanSeries.setSelected(true);
		this.checkBoxScanSeries.setBounds(10, 12, 97, 23);
		panelSeries.add(this.checkBoxScanSeries);
		
		JTextField textFieldSeries = new JTextField();
		textFieldSeries.setText(this.locationsTexts.get(WindowContent.getDefaultlanguage()));
		this.getComponentsWithText().put(textFieldSeries, this.locationsTexts);
		textFieldSeries.setForeground(SystemColor.controlShadow);
		textFieldSeries.setColumns(10);
		textFieldSeries.setBounds(10, 42, 170, 20);
		panelSeries.add(textFieldSeries);
		
		
		JButton buttonSeriesLoc = new JButton("...");
		buttonSeriesLoc.setBounds(190, 41, 30, 23);
		panelSeries.add(buttonSeriesLoc);
		
		JCheckBox checkBoxDetailedShearchSeries = new JCheckBox(this.checkboxDetailedShearchTexts.get(WindowContent.getDefaultlanguage()));
		this.getComponentsWithText().put(checkBoxDetailedShearchSeries, this.checkboxDetailedShearchTexts);
		checkBoxDetailedShearchSeries.setBounds(395, 41, 137, 23);
		panelSeries.add(checkBoxDetailedShearchSeries);
		
		// Music Panel
		JPanel panelMusics = new JPanel();
		TitledBorder musicPanelTitle = new TitledBorder(null, this.musicPanelTitleTexts.get(WindowContent.getDefaultlanguage()), TitledBorder.LEADING, TitledBorder.TOP, null, null);
		panelMusics.setBorder(musicPanelTitle);
		this.panelWithTitle.add(panelMusics);
		this.getComponentsWithText().put(musicPanelTitle, this.musicPanelTitleTexts);
		
		
		panelMusics.setBounds(10, 141, 538, 71);
		form.add(panelMusics);
		panelMusics.setLayout(null);
		
		this.checkBoxScanMusic = new JCheckBox(this.scanCheckboxTexts.get(WindowContent.getDefaultlanguage()));
		this.getComponentsWithText().put(this.checkBoxScanMusic, this.scanCheckboxTexts);
		this.checkBoxScanMusic.setSelected(true);
		this.checkBoxScanMusic.setBounds(10, 12, 97, 23);
		panelMusics.add(this.checkBoxScanMusic);
		
		JTextField textFieldMusic = new JTextField();
		textFieldMusic.setText(this.locationsTexts.get(WindowContent.getDefaultlanguage()));
		this.getComponentsWithText().put(textFieldMusic, this.locationsTexts);
		textFieldMusic.setForeground(SystemColor.controlShadow);
		textFieldMusic.setColumns(10);
		textFieldMusic.setBounds(10, 42, 170, 20);
		panelMusics.add(textFieldMusic);
		
		JButton buttonMusicLocation = new JButton("...");
		buttonMusicLocation.setBounds(190, 41, 30, 23);
		panelMusics.add(buttonMusicLocation);
		
		JCheckBox checkBoxDetailedShearchMusics = new JCheckBox(this.checkboxDetailedShearchTexts.get(WindowContent.getDefaultlanguage()));
		this.getComponentsWithText().put(checkBoxDetailedShearchMusics, this.checkboxDetailedShearchTexts);
		checkBoxDetailedShearchMusics.setBounds(395, 41, 137, 23);
		panelMusics.add(checkBoxDetailedShearchMusics);
		
		// Unique location Panel
		JPanel panelUniqueLocation = new JPanel();
		panelUniqueLocation.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panelUniqueLocation.setBounds(10, 217, 538, 71);
		form.add(panelUniqueLocation);
		panelUniqueLocation.setLayout(null);
		
		
		this.checkBoxScanMusic = new JCheckBox(this.scanCheckboxTexts.get(WindowContent.getDefaultlanguage()));
		this.getComponentsWithText().put(this.checkBoxScanMusic, this.scanCheckboxTexts);
		
		JCheckBox checkboxUniqueLocation = new JCheckBox(this.checkboxUniqueLocationTexts.get(WindowContent.getDefaultlanguage()));
		this.getComponentsWithText().put(checkboxUniqueLocation, this.checkboxUniqueLocationTexts);
		checkboxUniqueLocation.setBounds(6, 7, 131, 23);
		panelUniqueLocation.add(checkboxUniqueLocation);
		
		JTextField textFieldUniqueLocation = new JTextField();
		textFieldUniqueLocation.setEnabled(false);
		textFieldUniqueLocation.setForeground(UIManager.getColor("Button.shadow"));
		textFieldUniqueLocation.setText(this.locationsTexts.get(WindowContent.getDefaultlanguage()));
		this.getComponentsWithText().put(textFieldUniqueLocation, this.locationsTexts);
		textFieldUniqueLocation.setBounds(6, 42, 170, 20);
		panelUniqueLocation.add(textFieldUniqueLocation);
		textFieldUniqueLocation.setColumns(10);
		
		JButton buttonUniqueLocationLoc = new JButton("...");
		buttonUniqueLocationLoc.setEnabled(false);
		buttonUniqueLocationLoc.setBounds(186, 41, 30, 23);
		panelUniqueLocation.add(buttonUniqueLocationLoc);
		
		JCheckBox checkBoxDetailedShearchUniqueLocation = new JCheckBox(this.checkboxDetailedShearchTexts.get(WindowContent.getDefaultlanguage()));
		this.getComponentsWithText().put(checkBoxDetailedShearchUniqueLocation, this.checkboxDetailedShearchTexts);
		checkBoxDetailedShearchUniqueLocation.setEnabled(false);
		checkBoxDetailedShearchUniqueLocation.setBounds(395, 41, 137, 23);
		panelUniqueLocation.add(checkBoxDetailedShearchUniqueLocation);
	}
	
	private void revalidatePanelWithTitle() {
		for(JPanel panel : this.panelWithTitle) {
			WindowContent.revalidateContent(panel);
		}
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
		WindowContent.changeTextInAnotherLanguage(this.getComponentsWithText(), FR);
		this.revalidatePanelWithTitle();
	}

	@Override
	public void setToEnglish() {
		WindowContent.changeTextInAnotherLanguage(this.getComponentsWithText(), EN);
		this.revalidatePanelWithTitle();
	}

}
