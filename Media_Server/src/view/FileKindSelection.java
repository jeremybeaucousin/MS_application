package view;

import java.awt.Color;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.Popup;
import javax.swing.ToolTipManager;
import javax.swing.UIManager;
import javax.swing.border.EtchedBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.hsqldb.lib.StringUtil;

import com.mysql.jdbc.StringUtils;

import util.ConstantString;



public final class FileKindSelection extends WindowContent implements ActionListener, FocusListener {
	// Buttons AND Checkboxes and TextField //
	private JCheckBox checkboxScanMovies, checkBoxScanSeries, checkBoxScanMusic, checkboxUniqueLocation, checkboxDetailedShearchMovies, checkBoxDetailedShearchSeries, checkBoxDetailedShearchMusics, checkBoxDetailedShearchUniqueLocation;
	
	private JTextField moviesLocation, seriesLocation, musicLocation, uniqueLocation;
	
	private JButton buttonMoviesFolderSelection, buttonSeriesFolderLocation, buttonMusicFolderLocation, buttonUniqueFolderLocationLocation;
	
	// Folder Chooser // 
	private JFileChooser folderChooser = new JFileChooser();

	// Parameters to Send //
	FileKindSelectionParameters fileKindSelectionParameters;
	// textes //
	/** Contains all components panel that have a title displayed on screen **/
	private ArrayList<JPanel> panelWithTitle = new ArrayList<JPanel>();
	
	private final HashMap<String, String> DescriptionTexts = new HashMap<String, String>() {
		{
			put(IT, "TODO");
			put(DE, "TODO");
			put(ES, "Selectiona el tipo de media que quieres escanear y su localisación. Precisa el tipo de busceda que deseas para cada uno");
			put(FR, "Sélectionez le type de média que vous voulez scaner ainsi que leur emplacement. Précisez le type de recherche que vous désirez pour chacun.");
			put(EN, "Select The kind of media you want to scan and their location. Precise the kind of search for any of them.");
		}			
	};
	
	private final HashMap<String, String> moviePanelTitleTexts = new HashMap<String, String>() {
		{
			put(IT, "Filmi");
			put(DE, "TODO");
			put(ES, "Películas");
			put(FR, "Films");
			put(EN, "Movies");
		}			
	};
	
	
	private final HashMap<String, String> seriesPanelTitleTexts = new HashMap<String, String>() {
		{
			put(IT, "Seri");
			put(DE, "TODO");
			put(ES, "Series");
			put(FR, "Séries");
			put(EN, "Series");
		}			
	};
	
	private final HashMap<String, String> musicPanelTitleTexts = new HashMap<String, String>() {
		{
			put(IT, "Musiche");
			put(DE, "TODO");
			put(ES, "Músicas");
			put(FR, "Musique");
			put(EN, "Music");
		}			
	};
	
	private final HashMap<String, String> checkboxUniqueLocationTexts = new HashMap<String, String>() {
		{
			put(IT, "TODO");
			put(DE, "TODO");
			put(ES, "Localisación única");
			put(FR, "Emplacement unique");
			put(EN, "Unique location");
		}			
	};
	
	private final HashMap<String, String> scanCheckboxTexts = new HashMap<String, String>() {
		{
			put(IT, "TODO");
			put(DE, "TODO");
			put(ES, "Escanear");
			put(FR, "scanner");
			put(EN, "scan");
		}			
	};
	
	private final HashMap<String, String> locationsTexts = new HashMap<String, String>() {
		{
			put(IT, "TODO");
			put(DE, "TODO");
			put(ES, "Localisación del dosier");
			put(FR, "Emplacement du dossier");
			put(EN, "File Location");
		}			
	};
	
	private final HashMap<String, String> checkboxDetailedShearchTexts = new HashMap<String, String>() {
		{
			put(IT, "TODO");
			put(DE, "TODO");
			put(ES, "Busceda detallada");
			put(FR, "Recherche approfondie");
			put(EN, "Detailed search");
		}			
	};
	
	// FolderChooser //
	private final HashMap<String, String> folderChooserTexts = new HashMap<String, String>() {
		{
			put(IT, "TODO");
			put(DE, "TODO");
			put(ES, "Selecciona el dosier que escanear");
			put(FR, "Séléctionnez le dossier à scanner");
			put(EN, "Select the folder to scan");
		}			
	};
	
	// Errors Message //
	private final HashMap<String, String> errorMessageTitle = new HashMap<String, String>() {
		{
			put(IT, "Errore");
			put(DE, "Irrtum");
			put(ES, "Error");
			put(FR, "Erreur");
			put(EN, "Error");
		}			
	};
	
	private final HashMap<String, String> invalidPathError = new HashMap<String, String>() {
		{
			put(IT, "TODO");
			put(DE, "TODO");
			put(ES, "El camino instruido no existe");
			put(FR, "Le chemin rentré n'existe pas.");
			put(EN, "The entered path does not exist.");
		}			
	};
	
	private final HashMap<String, String> notAnyMediaCheckedError = new HashMap<String, String>() {
		{
			put(IT, "TODO");
			put(DE, "TODO");
			put(ES, "Ningún media está selectionado");
			put(FR, "Vous n'avez selectionné aucun media.");
			put(EN, "You haven't selected any media.");
		}			
	};
	
	private final HashMap<String, String> pathIsEmptyOrInvalidError = new HashMap<String, String>() {
		{
			put(IT, "TODO");
			put(DE, "TODO");
			put(ES, "Ningún dosier está selectionado o el camino está inválido");
			put(FR, "Vous n'avez selectionner aucun dossier ou le chemin est invalide.");
			put(EN, "You haven't selected any folder or the path does not exist.");
		}			
	};
	
	public FileKindSelection(MainWindow mainWindow) throws IOException {
		super(mainWindow);
		
		this.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		this.setBackground(Color.WHITE);
		this.setBounds(10, 37, 558, 356);
		this.setLayout(null);
		
		this.folderChooser.setDialogTitle(this.folderChooserTexts.get(EN));
		this.getComponentsWithText().put(this.folderChooser, this.folderChooserTexts);
		this.folderChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		this.folderChooser.setAcceptAllFileFilterUsed(false);
		this.folderChooser.setLocale(Locale.ENGLISH);
		this.folderChooser.updateUI();
		
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
		introducingText.setBounds(10, 11, 538, 35);
		introducingPanel.add(introducingText);
		
		// Main Panel
		JPanel form = new JPanel();
		form.setBorder(null);
		form.setBounds(1, 59, 556, 296);
		this.add(form);
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
		this.checkboxScanMovies.setBounds(10, 17, 97, 23);
		this.checkboxScanMovies.addActionListener(this);
		panelMovies.add(this.checkboxScanMovies);
		
		this.moviesLocation = new JTextField();
		this.moviesLocation.setText(this.locationsTexts.get(WindowContent.getDefaultlanguage()));
		this.getComponentsWithText().put(this.moviesLocation, this.locationsTexts);
		this.moviesLocation.setForeground(SystemColor.controlShadow);
		this.moviesLocation.setColumns(10);
		this.moviesLocation.setBounds(10, 42, 220, 20);
		this.moviesLocation.addFocusListener(this);
		panelMovies.add(this.moviesLocation);
		
		this.buttonMoviesFolderSelection = new JButton("...");
		this.buttonMoviesFolderSelection.setBounds(240, 41, 30, 23);
		this.buttonMoviesFolderSelection.addActionListener(this);
		panelMovies.add(this.buttonMoviesFolderSelection);
		
		this.checkboxDetailedShearchMovies = new JCheckBox(this.checkboxDetailedShearchTexts.get(WindowContent.getDefaultlanguage()));
		this.getComponentsWithText().put(this.checkboxDetailedShearchMovies, this.checkboxDetailedShearchTexts);
		this.checkboxDetailedShearchMovies.setBounds(352, 41, 180, 23);
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
		this.checkBoxScanSeries.setBounds(10, 18, 97, 23);
		this.checkBoxScanSeries.addActionListener(this);
		panelSeries.add(this.checkBoxScanSeries);
		
		this.seriesLocation = new JTextField();
		this.seriesLocation.setText(this.locationsTexts.get(WindowContent.getDefaultlanguage()));
		this.getComponentsWithText().put(this.seriesLocation, this.locationsTexts);
		this.seriesLocation.setForeground(SystemColor.controlShadow);
		this.seriesLocation.setColumns(10);
		this.seriesLocation.setBounds(10, 42, 220, 20);
		this.seriesLocation.addFocusListener(this);
		panelSeries.add(this.seriesLocation);
		
		
		this.buttonSeriesFolderLocation = new JButton("...");
		this.buttonSeriesFolderLocation.setBounds(240, 41, 30, 23);
		this.buttonSeriesFolderLocation.addActionListener(this);
		panelSeries.add(this.buttonSeriesFolderLocation);
		
		 checkBoxDetailedShearchSeries = new JCheckBox(this.checkboxDetailedShearchTexts.get(WindowContent.getDefaultlanguage()));
		this.getComponentsWithText().put(checkBoxDetailedShearchSeries, this.checkboxDetailedShearchTexts);
		checkBoxDetailedShearchSeries.setBounds(352, 41, 180, 23);
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
		this.checkBoxScanMusic.setBounds(10, 17, 97, 23);
		this.checkBoxScanMusic.addActionListener(this);
		panelMusics.add(this.checkBoxScanMusic);
		
		this.musicLocation = new JTextField();
		this.musicLocation.setText(this.locationsTexts.get(WindowContent.getDefaultlanguage()));
		this.getComponentsWithText().put(this.musicLocation, this.locationsTexts);
		this.musicLocation.setForeground(SystemColor.controlShadow);
		this.musicLocation.setColumns(10);
		this.musicLocation.setBounds(10, 42, 220, 20);
		this.musicLocation.addFocusListener(this);
		panelMusics.add(this.musicLocation);
		
		this.buttonMusicFolderLocation = new JButton("...");
		this.buttonMusicFolderLocation.setBounds(240, 41, 30, 23);
		this.buttonMusicFolderLocation.addActionListener(this);
		panelMusics.add(this.buttonMusicFolderLocation);
		
		this.checkBoxDetailedShearchMusics = new JCheckBox(this.checkboxDetailedShearchTexts.get(WindowContent.getDefaultlanguage()));
		this.getComponentsWithText().put(this.checkBoxDetailedShearchMusics, this.checkboxDetailedShearchTexts);
		this.checkBoxDetailedShearchMusics.setBounds(352, 41, 180, 23);
		panelMusics.add(this.checkBoxDetailedShearchMusics);
		
		// Unique location Panel
		JPanel panelUniqueLocation = new JPanel();
		panelUniqueLocation.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panelUniqueLocation.setBounds(10, 217, 538, 71);
		form.add(panelUniqueLocation);
		panelUniqueLocation.setLayout(null);
		
		this.checkboxUniqueLocation = new JCheckBox(this.checkboxUniqueLocationTexts.get(WindowContent.getDefaultlanguage()));
		this.getComponentsWithText().put(this.checkboxUniqueLocation, this.checkboxUniqueLocationTexts);
		this.checkboxUniqueLocation.setBounds(6, 12, 131, 23);
		this.checkboxUniqueLocation.addActionListener(this);
		panelUniqueLocation.add(this.checkboxUniqueLocation);
		
		this.uniqueLocation = new JTextField();
		this.uniqueLocation.setEnabled(false);
		this.uniqueLocation.setForeground(UIManager.getColor("Button.shadow"));
		this.uniqueLocation.setText(this.locationsTexts.get(WindowContent.getDefaultlanguage()));
		this.getComponentsWithText().put(this.uniqueLocation, this.locationsTexts);
		this.uniqueLocation.setBounds(6, 42, 224, 20);
		this.uniqueLocation.setColumns(10);
		this.uniqueLocation.addFocusListener(this);
		panelUniqueLocation.add(this.uniqueLocation);
		
		this.buttonUniqueFolderLocationLocation = new JButton("...");
		this.buttonUniqueFolderLocationLocation.setEnabled(false);
		this.buttonUniqueFolderLocationLocation.setBounds(240, 41, 30, 23);
		this.buttonUniqueFolderLocationLocation.addActionListener(this);
		panelUniqueLocation.add(this.buttonUniqueFolderLocationLocation);
		
		this.checkBoxDetailedShearchUniqueLocation = new JCheckBox(this.checkboxDetailedShearchTexts.get(WindowContent.getDefaultlanguage()));
		this.getComponentsWithText().put(this.checkBoxDetailedShearchUniqueLocation, this.checkboxDetailedShearchTexts);
		this.checkBoxDetailedShearchUniqueLocation.setEnabled(false);
		this.checkBoxDetailedShearchUniqueLocation.setBounds(352, 41, 180, 23);
		panelUniqueLocation.add(this.checkBoxDetailedShearchUniqueLocation);
	}
	/**
	 * Revalid a panel with title wish just change for displaying the new one.  
	 */
	private void revalidatePanelWithTitle() {
		for(JPanel panel : this.panelWithTitle) {
			WindowContent.revalidateContent(panel);
		}
	}
	
	/**
	 * Get the path selected by the user from the chooser window.  
	 */
	private void getPathSelected(JTextField locationField) {
		if(this.folderChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
			locationField.setText(this.folderChooser.getSelectedFile().getPath());
			locationField.setForeground(null);
		} 
	}
	
	private void changeStateMoviePanel(Boolean state) {
		moviesLocation.setEnabled(state);
		buttonMoviesFolderSelection.setEnabled(state);
		checkboxDetailedShearchMovies.setEnabled(state);
	}
	
	private void changeStateSeriesPanel(Boolean state) {
		seriesLocation.setEnabled(state);
		buttonSeriesFolderLocation.setEnabled(state);
		checkBoxDetailedShearchSeries.setEnabled(state);
	}
	
	private void changeStateMusicPanel(Boolean state) {
		musicLocation.setEnabled(state);
		buttonMusicFolderLocation.setEnabled(state);
		checkBoxDetailedShearchMusics.setEnabled(state);
	}
	
	private void changeStateUniquePanel(Boolean state) {
		uniqueLocation.setEnabled(state);
		buttonUniqueFolderLocationLocation.setEnabled(state);
		checkBoxDetailedShearchUniqueLocation.setEnabled(state);
	}

	/**
	 * Send the parameters to the next screen.
	 * 
	 * @return the parameters entered by the user
	 */
	public FileKindSelectionParameters sendFileKindSelectionParameters() {
		boolean videoIsSelected = this.checkboxScanMovies.isSelected();
		boolean serieIsSelected = this.checkBoxScanSeries.isSelected();
		boolean musicIsSelected = this.checkBoxScanMusic.isSelected();
		boolean uniqueIsSelected = this.checkboxUniqueLocation.isSelected();
		boolean detailedShearchMoviesSelected = this.checkboxDetailedShearchMovies.isSelected();
		boolean detailedShearchSeriesSelected = this.checkBoxDetailedShearchSeries.isSelected();
		boolean detailedShearchMusicsSelected = this.checkBoxDetailedShearchMusics.isSelected();
		boolean detailedShearchUniqueLocationSelected = this.checkBoxDetailedShearchUniqueLocation.isSelected();
		File videoFileChosen = new File(this.moviesLocation.getText());
		File seriesFileChosen = new File(this.seriesLocation.getText());
		File musicFileChosen = new File(this.musicLocation.getText());
		File uniqueFileChosen = new File(this.uniqueLocation.getText());
		this.fileKindSelectionParameters = new FileKindSelectionParameters(videoIsSelected, serieIsSelected, musicIsSelected, uniqueIsSelected, detailedShearchMoviesSelected, detailedShearchSeriesSelected, detailedShearchMusicsSelected, detailedShearchUniqueLocationSelected, videoFileChosen, seriesFileChosen, musicFileChosen, uniqueFileChosen);
		return fileKindSelectionParameters;
	}
	
	private String ValidateForm() {
		String message = new String();
		if(!this.checkboxScanMovies.isSelected() && !this.checkBoxScanSeries.isSelected() && !this.checkBoxScanMusic.isSelected()) {
			message = this.notAnyMediaCheckedError.get(WindowContent.getCurrentLanguage());
		} else if(this.checkboxUniqueLocation.isSelected()) {
			message = this.ValidatePanel(this.uniqueLocation, message);
		} else {
			if(this.checkboxScanMovies.isSelected()) {
				message = this.ValidatePanel(this.moviesLocation, message);
			}
			if(this.checkBoxScanSeries.isSelected() && StringUtils.isEmptyOrWhitespaceOnly(message)) {
				message = this.ValidatePanel(this.seriesLocation, message);
			}
			if(this.checkBoxScanMusic.isSelected() && StringUtils.isEmptyOrWhitespaceOnly(message)) {
				message = this.ValidatePanel(this.musicLocation, message);
			}
		}
		return message;
	}
	
	private String ValidatePanel(JTextField textField, String message) {
		if(((locationsTexts.get(WindowContent.getCurrentLanguage()).equals(textField.getText()) || StringUtils.isEmptyOrWhitespaceOnly(textField.getText()))) || !Files.exists(Paths.get(textField.getText()))) {
			message = this.pathIsEmptyOrInvalidError.get(WindowContent.getCurrentLanguage());
			textField.requestFocusInWindow();
			textField.setText(ConstantString.EMPTY);
		}
		return message;
	}
	
	private void openErrorMessage(String errorMessage) {
		JOptionPane.showMessageDialog(this, errorMessage, this.errorMessageTitle.get(WindowContent.getCurrentLanguage()), JOptionPane.ERROR_MESSAGE);
	}
	
	@Override
	public void getNextScreen() {
		FileKindSelectionParameters fileKindSelectionParameters = this.sendFileKindSelectionParameters();
		String errorMessage = this.ValidateForm();
		if(StringUtil.isEmpty(errorMessage)) {
			this.getMainWindow().getPreviousButton().setEnabled(true);
			if(this.getMainWindow().getScanningProgress() == null) {
				ScanningProgress scanningProgress = new ScanningProgress(this.getMainWindow(), fileKindSelectionParameters);
				this.getMainWindow().setScanningProgress(scanningProgress);
				this.getMainWindow().getNavigator().add(scanningProgress);
			} else {
				this.getMainWindow().getNavigator().next();
				// TODO this.getMainWindow().getScanningProgress().setFileKindSelectionParameters(fileKindSelectionParameters);
			}
			this.getMainWindow().replaceContent(this, this.getMainWindow().getScanningProgress());
		} else {
			this.openErrorMessage(errorMessage);
		}
	}

	@Override
	public void getPreviousScreen() {

	}

	@Override
	public void setToItalian() {
		WindowContent.changeTextInAnotherLanguage(this.getComponentsWithText(), IT);
		this.folderChooser.setLocale(Locale.ITALIAN);
		this.folderChooser.updateUI();
		this.revalidatePanelWithTitle();
		
	}
	@Override
	public void setToGerman() {
		WindowContent.changeTextInAnotherLanguage(this.getComponentsWithText(), DE);
		this.folderChooser.setLocale(Locale.GERMAN);
		this.folderChooser.updateUI();
		this.revalidatePanelWithTitle();
		
	}
	@Override
	public void setToSpanish() {
		WindowContent.changeTextInAnotherLanguage(this.getComponentsWithText(), ES);
		this.folderChooser.setLocale(Locale.ENGLISH);
		this.folderChooser.updateUI();
		this.revalidatePanelWithTitle();
		
	}
	
	@Override
	public void setToFrench() {
		WindowContent.changeTextInAnotherLanguage(this.getComponentsWithText(), FR);
		this.folderChooser.setLocale(Locale.FRENCH);
		this.folderChooser.updateUI();
		this.revalidatePanelWithTitle();
	}

	@Override
	public void setToEnglish() {
		WindowContent.changeTextInAnotherLanguage(this.getComponentsWithText(), EN);
		this.folderChooser.setLocale(Locale.ENGLISH);
		this.folderChooser.updateUI();
		this.revalidatePanelWithTitle();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if(source instanceof JCheckBox) {
			if(source.equals(this.checkboxUniqueLocation)) {
				if(this.checkboxUniqueLocation.isSelected()) {
					this.changeStateMoviePanel(false);
					this.changeStateSeriesPanel(false);
					this.changeStateMusicPanel(false);
					this.changeStateUniquePanel(true);
				} else {
					if(this.checkboxScanMovies.isSelected()) {
						this.changeStateMoviePanel(true);
					}
					if(this.checkBoxScanSeries.isSelected()) {
						this.changeStateSeriesPanel(true);
					}
					if(this.checkBoxScanMusic.isSelected()) {
						this.changeStateMusicPanel(true);
					}
					this.changeStateUniquePanel(false);
				}
			} else if(!this.checkboxUniqueLocation.isSelected()) {
				if(source.equals(this.checkboxScanMovies)) {
					if(this.checkboxScanMovies.isSelected()) {
						this.changeStateMoviePanel(true);
					} else {
						this.changeStateMoviePanel(false);
					}
				} else if(source.equals(this.checkBoxScanSeries)) {
					if(this.checkBoxScanSeries.isSelected()) {
						this.changeStateSeriesPanel(true);
					} else {
						this.changeStateSeriesPanel(false);
					}
				} else if(source.equals(this.checkBoxScanMusic)) {
					if(this.checkBoxScanMusic.isSelected()) {
						this.changeStateMusicPanel(true);
					} else {
						this.changeStateMusicPanel(false);
					}
				}
			}
		} else if(source instanceof JButton) {
			if(source.equals(this.buttonMoviesFolderSelection)) {
				this.getPathSelected(this.moviesLocation);
			} else if(source.equals(this.buttonSeriesFolderLocation)) {
				this.getPathSelected(this.seriesLocation);
			} else if(source.equals(this.buttonMusicFolderLocation)) {
				this.getPathSelected(this.musicLocation);
			} else if(source.equals(this.buttonUniqueFolderLocationLocation)) {
				this.getPathSelected(this.uniqueLocation);
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
			JTextField folderChosenField = (JTextField) source;
			if(folderChosenField.getText().equals(ConstantString.EMPTY)) {
				folderChosenField.setText(this.getComponentsWithText().get(source).get(WindowContent.getCurrentLanguage()));
				folderChosenField.setForeground(SystemColor.controlShadow);
			} else {
				Path path = Paths.get(folderChosenField.getText());
				if(!Files.exists(path)) {
					this.openErrorMessage(this.invalidPathError.get(WindowContent.getCurrentLanguage()));
					folderChosenField.requestFocusInWindow();
					folderChosenField.setText(ConstantString.EMPTY);
				} 
			}
		}
		
	}
}
