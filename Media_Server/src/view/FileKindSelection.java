package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EtchedBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;

import org.hsqldb.lib.StringUtil;

import util.StringUtils;
// FIXME Géstion des erreurs WindowContent.getLanguage
public final class FileKindSelection extends WindowContent implements ActionListener, FocusListener {
	
	JEditorPane introducingText = new JEditorPane();
	
	JPanel panelMovies = new JPanel(), panelSeries = new JPanel(), panelMusics = new JPanel();
	
	TitledBorder moviePanelTitle = new TitledBorder(null, StringUtils.EMPTY, TitledBorder.LEADING, TitledBorder.TOP, null, null),
			seriesPanelTitle = new TitledBorder(null, StringUtils.EMPTY, TitledBorder.LEADING, TitledBorder.TOP, null, null),
			musicPanelTitle = new TitledBorder(null, StringUtils.EMPTY, TitledBorder.LEADING, TitledBorder.TOP, null, null);
	
	// Buttons AND Checkboxes and TextField //
	private JCheckBox checkboxScanMovies = new JCheckBox(), checkBoxScanSeries = new JCheckBox(), checkBoxScanMusic = new JCheckBox(),
			checkboxUniqueLocation = new JCheckBox(), checkboxDetailedShearchMovies = new JCheckBox(), 
			checkBoxDetailedShearchSeries = new JCheckBox(), checkBoxDetailedShearchMusics = new JCheckBox(), 
			checkBoxDetailedShearchUniqueLocation = new JCheckBox();
	
	private JTextField moviesLocation = new JTextField(), seriesLocation = new JTextField(), musicLocation = new JTextField(), uniqueLocation = new JTextField();
	
	private JButton buttonMoviesFolderSelection = new JButton(), buttonSeriesFolderLocation = new JButton(), 
			buttonMusicFolderLocation = new JButton(), buttonUniqueFolderLocationLocation = new JButton();
	
	// Folder Chooser // 
	private JFileChooser folderChooser = new JFileChooser();

	// Parameters to Send //
	FileKindSelectionParameters fileKindSelectionParameters;
	// textes //
	/** Contains all components keys **/
	private final String DescriptionKey = "Description", moviePanelTitleKey = "moviePanelTitle", 
			seriesPanelTitleKey = "seriesPanelTitle", musicPanelTitleKey = "musicPanelTitle", 
			checkboxUniqueLocationKey = "checkboxUniqueLocation", scanCheckboxKey = "scanCheckbox", 
			locationsKey = "locations", checkboxDetailedShearchKey = "checkboxDetailedShearch", 
			folderChooserKey = "folderChooser", errorMessageTitleKey = "errorMessageTitle", 
			invalidPathErrorKey = "invalidPathError", notAnyMediaCheckedErrorKey = "notAnyMediaCheckedError", 
			pathIsEmptyOrInvalidErrorKey = "pathIsEmptyOrInvalidError";

	private final HashMap<Object, String> componentsWithText = new HashMap<Object, String>() {
		{
			put(folderChooser, folderChooserKey);
			put(introducingText, DescriptionKey);
			put(moviePanelTitle, moviePanelTitleKey);
			put(checkboxScanMovies, scanCheckboxKey);
			put(moviesLocation, locationsKey);
			put(checkboxDetailedShearchMovies, checkboxDetailedShearchKey);
			put(seriesPanelTitle, seriesPanelTitleKey);
			put(checkBoxScanSeries, scanCheckboxKey);
			put(seriesLocation, locationsKey);
			put(checkBoxDetailedShearchSeries, checkboxDetailedShearchKey);
			put(musicPanelTitle, musicPanelTitleKey);
			put(checkBoxScanSeries, scanCheckboxKey);
			put(musicLocation, locationsKey);
			put(checkBoxDetailedShearchMusics, checkboxDetailedShearchKey);
			put(checkboxUniqueLocation, scanCheckboxKey);
			put(uniqueLocation, locationsKey);
			put(checkBoxDetailedShearchUniqueLocation, checkboxDetailedShearchKey);
		}
	};
	
	/** Contains all components panel that have a title displayed on screen **/
	private final ArrayList<JPanel> panelWithTitle = new ArrayList<JPanel>() {
		{
			add(panelMovies);
			add(panelSeries);
			add(panelMusics);
		}
	};
	
	public FileKindSelection(MainWindow mainWindow) throws IOException {
		super(mainWindow);
		
		ResourceBundle resourceBundle = ResourceBundle.getBundle("texts/FileKindSelection", WindowContent.getLanguage());
		
		this.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		this.setBackground(Color.WHITE);
		this.setBounds(10, 37, 558, 356);
		this.setLayout(null);
		
		this.folderChooser.setDialogTitle(resourceBundle.getString(this.folderChooserKey));
		this.folderChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		this.folderChooser.setAcceptAllFileFilterUsed(false);
		this.folderChooser.setLocale(WindowContent.getLanguage());
		this.folderChooser.updateUI();
		
		JPanel introducingPanel = new JPanel();
		introducingPanel.setBackground(Color.WHITE);
		introducingPanel.setBorder(null);
		introducingPanel.setBounds(1, 1, 556, 58);
		this.add(introducingPanel);
		introducingPanel.setLayout(null);
		
		this.introducingText.setText(resourceBundle.getString(this.DescriptionKey));
		this.introducingText.setBackground(Color.WHITE);
		this.introducingText.setForeground(Color.BLACK);
		this.introducingText.setBounds(10, 11, 538, 35);
		introducingPanel.add(this.introducingText);
		
		// Main Panel
		JPanel form = new JPanel();
		form.setBorder(null);
		form.setBounds(1, 59, 556, 296);
		this.add(form);
		form.setLayout(null);
		
		// Movie Panel
		this.moviePanelTitle.setTitle(resourceBundle.getString(this.moviePanelTitleKey));
		this.panelMovies.setBorder(this.moviePanelTitle);
		this.panelMovies.setBounds(10, 0, 538, 71);
		form.add(this.panelMovies);
		this.panelMovies.setLayout(null);
		
		this.checkboxScanMovies.setText(resourceBundle.getString(this.scanCheckboxKey));
		this.checkboxScanMovies.setSelected(true);
		this.checkboxScanMovies.setBounds(10, 17, 97, 23);
		this.checkboxScanMovies.addActionListener(this);
		this.panelMovies.add(this.checkboxScanMovies);
		
		this.moviesLocation.setText(resourceBundle.getString(this.locationsKey));
		this.moviesLocation.setForeground(SystemColor.controlShadow);
		this.moviesLocation.setColumns(10);
		this.moviesLocation.setBounds(10, 42, 220, 20);
		this.moviesLocation.addFocusListener(this);
		this.panelMovies.add(this.moviesLocation);
		
		this.buttonMoviesFolderSelection.setText("...");
		this.buttonMoviesFolderSelection.setBounds(240, 41, 30, 23);
		this.buttonMoviesFolderSelection.addActionListener(this);
		this.panelMovies.add(this.buttonMoviesFolderSelection);
		
		this.checkboxDetailedShearchMovies.setText(resourceBundle.getString(this.checkboxDetailedShearchKey));
		this.checkboxDetailedShearchMovies.setBounds(352, 41, 180, 23);
		this.panelMovies.add(this.checkboxDetailedShearchMovies);
		
		// Serie Panel
		this.seriesPanelTitle.setTitle(resourceBundle.getString(this.seriesPanelTitleKey));
		this.panelSeries.setBorder(seriesPanelTitle);
		this.panelSeries.setBounds(10, 70, 538, 71);
		form.add(this.panelSeries);
		this.panelSeries.setLayout(null);
		
		this.checkBoxScanSeries = new JCheckBox();
		this.checkBoxScanSeries.setText(resourceBundle.getString(this.scanCheckboxKey));
		this.checkBoxScanSeries.setSelected(true);
		this.checkBoxScanSeries.setBounds(10, 18, 97, 23);
		this.checkBoxScanSeries.addActionListener(this);
		this.panelSeries.add(this.checkBoxScanSeries);
		
		this.seriesLocation.setText(resourceBundle.getString(this.locationsKey));
		this.seriesLocation.setForeground(SystemColor.controlShadow);
		this.seriesLocation.setColumns(10);
		this.seriesLocation.setBounds(10, 42, 220, 20);
		this.seriesLocation.addFocusListener(this);
		this.panelSeries.add(this.seriesLocation);
		
		
		this.buttonSeriesFolderLocation.setText("...");
		this.buttonSeriesFolderLocation.setBounds(240, 41, 30, 23);
		this.buttonSeriesFolderLocation.addActionListener(this);
		this.panelSeries.add(this.buttonSeriesFolderLocation);

		this.checkBoxDetailedShearchSeries.setText(resourceBundle.getString(this.checkboxDetailedShearchKey));
		this.checkBoxDetailedShearchSeries.setBounds(352, 41, 180, 23);
		this.panelSeries.add(this.checkBoxDetailedShearchSeries);
		
		// Music Panel
		this.musicPanelTitle.setTitle(resourceBundle.getString(this.musicPanelTitleKey));
		this.panelMusics.setBorder(this.musicPanelTitle);
		this.panelMusics.setBounds(10, 141, 538, 71);
		form.add(this.panelMusics);
		this.panelMusics.setLayout(null);
		
		this.checkBoxScanMusic.setText(resourceBundle.getString(this.scanCheckboxKey));
		this.checkBoxScanMusic.setSelected(true);
		this.checkBoxScanMusic.setBounds(10, 17, 97, 23);
		this.checkBoxScanMusic.addActionListener(this);
		this.panelMusics.add(this.checkBoxScanMusic);
		
		this.musicLocation.setText(resourceBundle.getString(this.locationsKey));
		this.musicLocation.setForeground(SystemColor.controlShadow);
		this.musicLocation.setColumns(10);
		this.musicLocation.setBounds(10, 42, 220, 20);
		this.musicLocation.addFocusListener(this);
		this.panelMusics.add(this.musicLocation);
		
		this.buttonMusicFolderLocation.setText("...");
		this.buttonMusicFolderLocation.setBounds(240, 41, 30, 23);
		this.buttonMusicFolderLocation.addActionListener(this);
		this.panelMusics.add(this.buttonMusicFolderLocation);
		
		this.checkBoxDetailedShearchMusics.setText(resourceBundle.getString(this.checkboxDetailedShearchKey));
		this.checkBoxDetailedShearchMusics.setBounds(352, 41, 180, 23);
		this.panelMusics.add(this.checkBoxDetailedShearchMusics);
		
		// Unique location Panel
		JPanel panelUniqueLocation = new JPanel();
		panelUniqueLocation.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panelUniqueLocation.setBounds(10, 217, 538, 71);
		form.add(panelUniqueLocation);
		panelUniqueLocation.setLayout(null);
		
		this.checkboxUniqueLocation.setText(resourceBundle.getString(this.checkboxUniqueLocationKey));
		this.checkboxUniqueLocation.setBounds(6, 12, 131, 23);
		this.checkboxUniqueLocation.addActionListener(this);
		panelUniqueLocation.add(this.checkboxUniqueLocation);
		
		this.uniqueLocation.setEnabled(false);
		this.uniqueLocation.setForeground(UIManager.getColor("Button.shadow"));
		this.uniqueLocation.setText(resourceBundle.getString(this.locationsKey));
		this.uniqueLocation.setBounds(6, 42, 224, 20);
		this.uniqueLocation.setColumns(10);
		this.uniqueLocation.addFocusListener(this);
		panelUniqueLocation.add(this.uniqueLocation);
		
		this.buttonUniqueFolderLocationLocation.setText("...");
		this.buttonUniqueFolderLocationLocation.setEnabled(false);
		this.buttonUniqueFolderLocationLocation.setBounds(240, 41, 30, 23);
		this.buttonUniqueFolderLocationLocation.addActionListener(this);
		panelUniqueLocation.add(this.buttonUniqueFolderLocationLocation);
		
		this.checkBoxDetailedShearchUniqueLocation.setText(resourceBundle.getString(this.checkboxDetailedShearchKey));
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
			message = ResourceBundle.getBundle("texts/FileKindSelection", WindowContent.getLanguage()).getString(this.notAnyMediaCheckedErrorKey);
		} else if(this.checkboxUniqueLocation.isSelected()) {
			message = this.ValidatePanel(this.uniqueLocation, message);
		} else {
			if(this.checkboxScanMovies.isSelected()) {
				message = this.ValidatePanel(this.moviesLocation, message);
			}
			if(this.checkBoxScanSeries.isSelected() && com.mysql.jdbc.StringUtils.isEmptyOrWhitespaceOnly(message)) {
				message = this.ValidatePanel(this.seriesLocation, message);
			}
			if(this.checkBoxScanMusic.isSelected() && com.mysql.jdbc.StringUtils.isEmptyOrWhitespaceOnly(message)) {
				message = this.ValidatePanel(this.musicLocation, message);
			}
		}
		return message;
	}
	
	private String ValidatePanel(JTextField textField, String message) {
		ResourceBundle resourceBundle = ResourceBundle.getBundle("texts/FileKindSelection", WindowContent.getLanguage());
		if(((resourceBundle.getString(this.locationsKey).equals(textField.getText()) || com.mysql.jdbc.StringUtils.isEmptyOrWhitespaceOnly(textField.getText()))) || !Files.exists(Paths.get(textField.getText()))) {
			message = resourceBundle.getString(this.pathIsEmptyOrInvalidErrorKey);
			textField.requestFocusInWindow();
			textField.setText(StringUtils.EMPTY);
		}
		return message;
	}
	
	private void openErrorMessage(String errorMessage) {
		JOptionPane.showMessageDialog(this, errorMessage, ResourceBundle.getBundle("texts/FileKindSelection", WindowContent.getLanguage()).getString(this.errorMessageTitleKey), JOptionPane.ERROR_MESSAGE);
	}
	
	@Override
	public void getNextScreen() {
		FileKindSelectionParameters fileKindSelectionParameters = this.sendFileKindSelectionParameters();
		String errorMessage = this.ValidateForm();
		if(StringUtil.isEmpty(errorMessage)) {
			this.getMainWindow().getPreviousButton().setEnabled(true);
			this.getMainWindow().getNextButton().setEnabled(false);
			if(this.getMainWindow().getScanningProgress() == null) {
				ScanningProgress scanningProgress = new ScanningProgress(this.getMainWindow(), fileKindSelectionParameters);
				this.getMainWindow().setScanningProgress(scanningProgress);
				this.getMainWindow().getNavigator().add(scanningProgress);
			} else {
				this.getMainWindow().getNavigator().next();
				this.getMainWindow().getScanningProgress().validContentDependingOnParameters(fileKindSelectionParameters);
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
		WindowContent.changeTextInAnotherLanguage2(this.componentsWithText, ResourceBundle.getBundle("texts/FileKindSelection", Locale.ITALIAN));
		this.folderChooser.setLocale(Locale.ITALIAN);
		this.folderChooser.updateUI();
		this.revalidatePanelWithTitle();
		
	}
	@Override
	public void setToGerman() {
		WindowContent.changeTextInAnotherLanguage2(this.componentsWithText, ResourceBundle.getBundle("texts/FileKindSelection", Locale.GERMAN));
		this.folderChooser.setLocale(Locale.GERMAN);
		this.folderChooser.updateUI();
		this.revalidatePanelWithTitle();
		
	}
	@Override
	public void setToSpanish() {
		WindowContent.changeTextInAnotherLanguage2(this.componentsWithText, ResourceBundle.getBundle("texts/FileKindSelection", WindowContent.getEsp()));
		this.folderChooser.setLocale(WindowContent.getEsp());
		this.folderChooser.updateUI();
		this.revalidatePanelWithTitle();
		
	}
	
	@Override
	public void setToFrench() {
		WindowContent.changeTextInAnotherLanguage2(this.componentsWithText, ResourceBundle.getBundle("texts/FileKindSelection", Locale.FRENCH));
		this.folderChooser.setLocale(Locale.FRENCH);
		this.folderChooser.updateUI();
		this.revalidatePanelWithTitle();
	}

	@Override
	public void setToEnglish() {
		WindowContent.changeTextInAnotherLanguage2(this.componentsWithText, ResourceBundle.getBundle("texts/FileKindSelection", Locale.ENGLISH));
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
			String defaultText = ResourceBundle.getBundle("texts/FileKindSelection", WindowContent.getLanguage()).getString(this.componentsWithText.get(source)); 
			if(defaultText.equals(((JTextField) source).getText())) {
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
			if(folderChosenField.getText().equals(StringUtils.EMPTY)) {
				folderChosenField.setText(ResourceBundle.getBundle("texts/FileKindSelection", WindowContent.getLanguage()).getString(this.componentsWithText.get(source)));
				folderChosenField.setForeground(SystemColor.controlShadow);
			} else {
				Path path = Paths.get(folderChosenField.getText());
				if(!Files.exists(path)) {
					this.openErrorMessage(ResourceBundle.getBundle("texts/FileKindSelection", WindowContent.getLanguage()).getString(this.invalidPathErrorKey));
					folderChosenField.requestFocusInWindow();
					folderChosenField.setText(StringUtils.EMPTY);
				} 
			}
		}
		
	}
}
