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

public class FileKindSelection extends JPanel {
	private JTextField txtEmplacementUnique;
	private JTextField textFieldMusic;
	private JTextField textFieldSeries;
	private JTextField textFieldFilm;

	/**
	 * Create the panel.
	 */
	public FileKindSelection() {
		setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		setBackground(Color.WHITE);
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(null);
		panel.setBounds(0, 0, 558, 57);
		add(panel);
		panel.setLayout(null);
		
		JEditorPane dtrpnExplainTexte = new JEditorPane();
		dtrpnExplainTexte.setBackground(UIManager.getColor("Button.background"));
		dtrpnExplainTexte.setForeground(Color.BLACK);
		dtrpnExplainTexte.setText("S\u00E9lectionez le type de m\u00E9dia que vous voulez scaner ainsi que leur emplacement. Si cet emplacement est le m\u00EAme pour tous, vous pouvez le pr\u00E9ciser");
		dtrpnExplainTexte.setBounds(10, 11, 538, 37);
		panel.add(dtrpnExplainTexte);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(null);
		panel_1.setBounds(0, 57, 558, 299);
		add(panel_1);
		panel_1.setLayout(null);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "Films", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(10, 0, 538, 71);
		panel_1.add(panel_2);
		panel_2.setLayout(null);
		
		JCheckBox chckbxRechercheApprofondie = new JCheckBox("Recherche approfondie");
		chckbxRechercheApprofondie.setBounds(395, 41, 137, 23);
		panel_2.add(chckbxRechercheApprofondie);
		
		textFieldFilm = new JTextField();
		textFieldFilm.setText("emplacement du dossier");
		textFieldFilm.setForeground(SystemColor.controlShadow);
		textFieldFilm.setColumns(10);
		textFieldFilm.setBounds(10, 42, 170, 20);
		panel_2.add(textFieldFilm);
		
		JButton button = new JButton("...");
		button.setBounds(190, 41, 30, 23);
		panel_2.add(button);
		
		JCheckBox chckbxScaner = new JCheckBox("scaner");
		chckbxScaner.setSelected(true);
		chckbxScaner.setBounds(10, 12, 97, 23);
		panel_2.add(chckbxScaner);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(null, "S\u00E9ries", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_3.setBounds(10, 70, 538, 71);
		panel_1.add(panel_3);
		panel_3.setLayout(null);
		
		JCheckBox checkBox = new JCheckBox("Recherche approfondie");
		checkBox.setBounds(395, 41, 137, 23);
		panel_3.add(checkBox);
		
		textFieldSeries = new JTextField();
		textFieldSeries.setText("emplacement du dossier");
		textFieldSeries.setForeground(SystemColor.controlShadow);
		textFieldSeries.setColumns(10);
		textFieldSeries.setBounds(10, 42, 170, 20);
		panel_3.add(textFieldSeries);
		
		JButton button_1 = new JButton("...");
		button_1.setBounds(190, 41, 30, 23);
		panel_3.add(button_1);
		
		JCheckBox checkBox_3 = new JCheckBox("scaner");
		checkBox_3.setSelected(true);
		checkBox_3.setBounds(10, 12, 97, 23);
		panel_3.add(checkBox_3);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new TitledBorder(null, "Musiques", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_4.setBounds(10, 141, 538, 71);
		panel_1.add(panel_4);
		panel_4.setLayout(null);
		
		JCheckBox checkBox_1 = new JCheckBox("Recherche approfondie");
		checkBox_1.setBounds(395, 41, 137, 23);
		panel_4.add(checkBox_1);
		
		textFieldMusic = new JTextField();
		textFieldMusic.setText("emplacement du dossier");
		textFieldMusic.setForeground(SystemColor.controlShadow);
		textFieldMusic.setColumns(10);
		textFieldMusic.setBounds(10, 42, 170, 20);
		panel_4.add(textFieldMusic);
		
		JButton button_2 = new JButton("...");
		button_2.setBounds(190, 41, 30, 23);
		panel_4.add(button_2);
		
		JCheckBox checkBox_4 = new JCheckBox("scaner");
		checkBox_4.setSelected(true);
		checkBox_4.setBounds(10, 12, 97, 23);
		panel_4.add(checkBox_4);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_5.setBounds(10, 217, 538, 71);
		panel_1.add(panel_5);
		panel_5.setLayout(null);
		
		JCheckBox chckbxEmplacementUnique = new JCheckBox("Emplacement unique");
		chckbxEmplacementUnique.setBounds(6, 7, 131, 23);
		panel_5.add(chckbxEmplacementUnique);
		
		JCheckBox checkBox_2 = new JCheckBox("Recherche approfondie");
		checkBox_2.setBounds(395, 41, 137, 23);
		panel_5.add(checkBox_2);
		
		txtEmplacementUnique = new JTextField();
		txtEmplacementUnique.setEnabled(false);
		txtEmplacementUnique.setForeground(UIManager.getColor("Button.shadow"));
		txtEmplacementUnique.setText("emplacement du dossier");
		txtEmplacementUnique.setBounds(6, 42, 170, 20);
		panel_5.add(txtEmplacementUnique);
		txtEmplacementUnique.setColumns(10);
		
		JButton button_3 = new JButton("...");
		button_3.setEnabled(false);
		button_3.setBounds(186, 41, 30, 23);
		panel_5.add(button_3);

	}
}
