package view;

import java.awt.Color;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JEditorPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.UIManager;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import util.ConstantString;



public final class FileKindSelection extends WindowContent implements ActionListener, FocusListener {
	// Buttons AND Checkboxes and TextField//
	private JCheckBox checkboxScanMovies, checkBoxScanSeries, checkBoxScanMusic, checkboxUniqueLocation;
	
	private JTextField moviesLocation, seriesLocation, musicLocation, UniqueLocation;
	
	private JButton buttonMoviesFolderSelection, buttonSeriesLocation, buttonMusicLocation, buttonUniqueLocationLoc;
	
	private JCheckBox checkboxDetailedShearchMovies, checkBoxDetailedShearchSeries, checkBoxDetailedShearchMusics, checkBoxDetailedShearchUniqueLocation;
	
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
		
		this.moviesLocation = new JTextField();
		this.moviesLocation.setText(this.locationsTexts.get(WindowContent.getDefaultlanguage()));
		this.getComponentsWithText().put(this.moviesLocation, this.locationsTexts);
		this.moviesLocation.setForeground(SystemColor.controlShadow);
		this.moviesLocation.setColumns(10);
		this.moviesLocation.setBounds(10, 42, 170, 20);
		this.moviesLocation.addFocusListener(this);
		panelMovies.add(this.moviesLocation);
		
		this.buttonMoviesFolderSelection = new JButton("...");
		this.buttonMoviesFolderSelection.setBounds(190, 41, 30, 23);
		panelMovies.add(this.buttonMoviesFolderSelection);
		
		this.checkboxDetailedShearchMovies = new JCheckBox(this.checkboxDetailedShearchTexts.get(WindowContent.getDefaultlanguage()));
		this.getComponentsWithText().put(this.checkboxDetailedShearchMovies, this.checkboxDetailedShearchTexts);
		this.checkboxDetailedShearchMovies.setBounds(395, 41, 137, 23);
		panelMovies.add(this.checkboxDetailedShearchMovies);
		
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
		
		this.seriesLocation = new JTextField();
		this.seriesLocation.setText(this.locationsTexts.get(WindowContent.getDefaultlanguage()));
		this.getComponentsWithText().put(this.seriesLocation, this.locationsTexts);
		this.seriesLocation.setForeground(SystemColor.controlShadow);
		this.seriesLocation.setColumns(10);
		this.seriesLocation.setBounds(10, 42, 170, 20);
		this.seriesLocation.addFocusListener(this);
		panelSeries.add(this.seriesLocation);
		
		
		this.buttonSeriesLocation = new JButton("...");
		this.buttonSeriesLocation.setBounds(190, 41, 30, 23);
		panelSeries.add(this.buttonSeriesLocation);
		
		 checkBoxDetailedShearchSeries = new JCheckBox(this.checkboxDetailedShearchTexts.get(WindowContent.getDefaultlanguage()));
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
		
		this.musicLocation = new JTextField();
		this.musicLocation.setText(this.locationsTexts.get(WindowContent.getDefaultlanguage()));
		this.getComponentsWithText().put(this.musicLocation, this.locationsTexts);
		this.musicLocation.setForeground(SystemColor.controlShadow);
		this.musicLocation.setColumns(10);
		this.musicLocation.setBounds(10, 42, 170, 20);
		this.musicLocation.addFocusListener(this);
		panelMusics.add(this.musicLocation);
		
		this.buttonMusicLocation = new JButton("...");
		this.buttonMusicLocation.setBounds(190, 41, 30, 23);
		panelMusics.add(this.buttonMusicLocation);
		
		this.checkBoxDetailedShearchMusics = new JCheckBox(this.checkboxDetailedShearchTexts.get(WindowContent.getDefaultlanguage()));
		this.getComponentsWithText().put(this.checkBoxDetailedShearchMusics, this.checkboxDetailedShearchTexts);
		this.checkBoxDetailedShearchMusics.setBounds(395, 41, 137, 23);
		panelMusics.add(this.checkBoxDetailedShearchMusics);
		
		// Unique location Panel
		JPanel panelUniqueLocation = new JPanel();
		panelUniqueLocation.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panelUniqueLocation.setBounds(10, 217, 538, 71);
		form.add(panelUniqueLocation);
		panelUniqueLocation.setLayout(null);
		
		this.checkboxUniqueLocation = new JCheckBox(this.checkboxUniqueLocationTexts.get(WindowContent.getDefaultlanguage()));
		this.getComponentsWithText().put(this.checkboxUniqueLocation, this.checkboxUniqueLocationTexts);
		this.checkboxUniqueLocation.setBounds(6, 7, 131, 23);
		this.checkboxUniqueLocation.addActionListener(this);
		panelUniqueLocation.add(this.checkboxUniqueLocation);
		
		this.UniqueLocation = new JTextField();
		this.UniqueLocation.setEnabled(false);
		this.UniqueLocation.setForeground(UIManager.getColor("Button.shadow"));
		this.UniqueLocation.setText(this.locationsTexts.get(WindowContent.getDefaultlanguage()));
		this.getComponentsWithText().put(this.UniqueLocation, this.locationsTexts);
		this.UniqueLocation.setBounds(6, 42, 170, 20);
		this.UniqueLocation.setColumns(10);
		this.UniqueLocation.addFocusListener(this);
		panelUniqueLocation.add(this.UniqueLocation);
		
		this.buttonUniqueLocationLoc = new JButton("...");
		this.buttonUniqueLocationLoc.setEnabled(false);
		this.buttonUniqueLocationLoc.setBounds(186, 41, 30, 23);
		panelUniqueLocation.add(this.buttonUniqueLocationLoc);
		
		this.checkBoxDetailedShearchUniqueLocation = new JCheckBox(this.checkboxDetailedShearchTexts.get(WindowContent.getDefaultlanguage()));
		this.getComponentsWithText().put(this.checkBoxDetailedShearchUniqueLocation, this.checkboxDetailedShearchTexts);
		this.checkBoxDetailedShearchUniqueLocation.setEnabled(false);
		this.checkBoxDetailedShearchUniqueLocation.setBounds(395, 41, 137, 23);
		panelUniqueLocation.add(this.checkBoxDetailedShearchUniqueLocation);
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

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if(source.equals(this.checkboxUniqueLocation)) {
			if(this.checkboxUniqueLocation.isSelected()) {
				moviesLocation.setEnabled(false);
				buttonMoviesFolderSelection.setEnabled(false);
				checkboxDetailedShearchMovies.setEnabled(false);
				seriesLocation.setEnabled(false);
				buttonSeriesLocation.setEnabled(false);
				checkBoxDetailedShearchSeries.setEnabled(false);
				musicLocation.setEnabled(false);
				buttonMusicLocation.setEnabled(false);
				checkBoxDetailedShearchMusics.setEnabled(false);
				UniqueLocation.setEnabled(true);
				buttonUniqueLocationLoc.setEnabled(true);
				checkBoxDetailedShearchUniqueLocation.setEnabled(true);
			} else {
				moviesLocation.setEnabled(true);
				buttonMoviesFolderSelection.setEnabled(true);
				checkboxDetailedShearchMovies.setEnabled(true);
				seriesLocation.setEnabled(true);
				buttonSeriesLocation.setEnabled(true);
				checkBoxDetailedShearchSeries.setEnabled(true);
				musicLocation.setEnabled(true);
				buttonMusicLocation.setEnabled(true);
				checkBoxDetailedShearchMusics.setEnabled(true);
				UniqueLocation.setEnabled(false);
				buttonUniqueLocationLoc.setEnabled(false);
				checkBoxDetailedShearchUniqueLocation.setEnabled(false);
			}
		}
	}

	@Override
	public void focusGained(FocusEvent e) {
		Object source = e.getSource();
		if(source instanceof JTextField) {
			if(this.getComponentsWithText().get(source).containsValue(((JTextField) source).getText())) {
				((JTextField) source).setText("");
				((JTextField) source).setForeground(null);
			}
		}
	}

	@Override
	public void focusLost(FocusEvent e) {
		Object source = e.getSource();
		if(source instanceof JTextField) {
			if(((JTextField) source).getText().equals(ConstantString.EMPTY)) {
				((JTextField) source).setText(this.getComponentsWithText().get(source).get(WindowContent.getCurrentLanguage()));
				((JTextField) source).setForeground(SystemColor.controlShadow);
			} else {
				// TODO check if path exist
				System.out.println(new File(((JTextField) source).getText()));
			}
		}
		
	}
}
