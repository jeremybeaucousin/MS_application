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
import javax.swing.JTextPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTable;
import javax.swing.border.MatteBorder;

public class FileKindSelection extends JPanel {
	private JTextField textFieldUniqueLocation;
	private JTextField textFieldMusic;
	private JTextField textFieldSeries;
	private JTextField textFieldMovies;

	/**
	 * Create the panel.
	 */
	public FileKindSelection() {
		setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		setBackground(Color.WHITE);
		setLayout(null);
		
		JPanel panelIntoducing = new JPanel();
		panelIntoducing.setBackground(Color.WHITE);
		panelIntoducing.setBorder(null);
		panelIntoducing.setBounds(1, 1, 556, 58);
		add(panelIntoducing);
		panelIntoducing.setLayout(null);
		
		JTextPane txtpnSlectionezLeType = new JTextPane();
		txtpnSlectionezLeType.setEditable(false);
		txtpnSlectionezLeType.setText("S\u00E9lectionez le type de m\u00E9dia que vous voulez scaner ainsi que leur emplacement. Pr\u00E9cisez le type de recherche que vous d\u00E9sirez pour chacun.");
		txtpnSlectionezLeType.setBounds(10, 11, 538, 35);
		panelIntoducing.add(txtpnSlectionezLeType);
		
		JPanel panel = new JPanel();
		panel.setBorder(null);
		panel.setBounds(1, 59, 556, 296);
		add(panel);
		panel.setLayout(null);
		
		JPanel panelMovies = new JPanel();
		panelMovies.setBorder(new TitledBorder(null, "Films", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelMovies.setBounds(10, 0, 538, 71);
		panel.add(panelMovies);
		panelMovies.setLayout(null);
		
		JCheckBox checkboxDetailedShearchMovies = new JCheckBox("Recherche approfondie");
		checkboxDetailedShearchMovies.setBounds(312, 41, 220, 23);
		panelMovies.add(checkboxDetailedShearchMovies);
		
		textFieldMovies = new JTextField();
		textFieldMovies.setText("emplacement du dossier");
		textFieldMovies.setForeground(SystemColor.controlShadow);
		textFieldMovies.setColumns(10);
		textFieldMovies.setBounds(10, 42, 170, 20);
		panelMovies.add(textFieldMovies);
		
		JButton buttonMoviesLoc = new JButton("...");
		buttonMoviesLoc.setBounds(190, 41, 30, 23);
		panelMovies.add(buttonMoviesLoc);
		
		JCheckBox checkboxScanMovies = new JCheckBox("scanner");
		checkboxScanMovies.setSelected(true);
		checkboxScanMovies.setBounds(10, 17, 97, 23);
		panelMovies.add(checkboxScanMovies);
		
		JPanel panelSeries = new JPanel();
		panelSeries.setBorder(new TitledBorder(null, "S\u00E9ries", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelSeries.setBounds(10, 70, 538, 71);
		panel.add(panelSeries);
		panelSeries.setLayout(null);
		
		JCheckBox checkBoxDetailedShearchSeries = new JCheckBox("Recherche approfondie");
		checkBoxDetailedShearchSeries.setBounds(312, 41, 220, 23);
		panelSeries.add(checkBoxDetailedShearchSeries);
		
		textFieldSeries = new JTextField();
		textFieldSeries.setText("emplacement du dossier");
		textFieldSeries.setForeground(SystemColor.controlShadow);
		textFieldSeries.setColumns(10);
		textFieldSeries.setBounds(10, 42, 170, 20);
		panelSeries.add(textFieldSeries);
		
		JButton buttonSeriesLoc = new JButton("...");
		buttonSeriesLoc.setBounds(190, 41, 30, 23);
		panelSeries.add(buttonSeriesLoc);
		
		JCheckBox checkBoxScanSeries = new JCheckBox("scanner");
		checkBoxScanSeries.setSelected(true);
		checkBoxScanSeries.setBounds(10, 18, 97, 23);
		panelSeries.add(checkBoxScanSeries);
		
		JPanel panelMusics = new JPanel();
		panelMusics.setBorder(new TitledBorder(null, "Musiques", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelMusics.setBounds(10, 141, 538, 71);
		panel.add(panelMusics);
		panelMusics.setLayout(null);
		
		JCheckBox checkBoxDetailedShearchMusics = new JCheckBox("Recherche approfondie");
		checkBoxDetailedShearchMusics.setBounds(312, 41, 220, 23);
		panelMusics.add(checkBoxDetailedShearchMusics);
		
		textFieldMusic = new JTextField();
		textFieldMusic.setText("emplacement du dossier");
		textFieldMusic.setForeground(SystemColor.controlShadow);
		textFieldMusic.setColumns(10);
		textFieldMusic.setBounds(10, 42, 170, 20);
		panelMusics.add(textFieldMusic);
		
		JButton buttonMusicLoc = new JButton("...");
		buttonMusicLoc.setBounds(190, 41, 30, 23);
		panelMusics.add(buttonMusicLoc);
		
		JCheckBox checkBoxScanMusic = new JCheckBox("scanner");
		checkBoxScanMusic.setSelected(true);
		checkBoxScanMusic.setBounds(10, 17, 97, 23);
		panelMusics.add(checkBoxScanMusic);
		
		JPanel panelUniqueLocation = new JPanel();
		panelUniqueLocation.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panelUniqueLocation.setBounds(10, 217, 538, 71);
		panel.add(panelUniqueLocation);
		panelUniqueLocation.setLayout(null);
		
		JCheckBox checkboxUniqueLocation = new JCheckBox("Emplacement unique");
		checkboxUniqueLocation.setBounds(6, 12, 131, 23);
		panelUniqueLocation.add(checkboxUniqueLocation);
		
		JCheckBox checkBoxDetailedShearchUniqueLocation = new JCheckBox("Recherche approfondie");
		checkBoxDetailedShearchUniqueLocation.setBounds(312, 41, 220, 23);
		panelUniqueLocation.add(checkBoxDetailedShearchUniqueLocation);
		
		textFieldUniqueLocation = new JTextField();
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

	}
}
