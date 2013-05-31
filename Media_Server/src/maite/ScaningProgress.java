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
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 558, 356);
		add(panel);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Films", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(10, 0, 538, 90);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JProgressBar progressBar = new JProgressBar();
		progressBar.setBounds(228, 37, 300, 14);
		panel_1.add(progressBar);
		
		JLabel lblScanEnCours = new JLabel("Scan en cours...");
		lblScanEnCours.setBounds(248, 12, 104, 14);
		panel_1.add(lblScanEnCours);
		
		JLabel lblTempsRestant = new JLabel("Temps restant :");
		lblTempsRestant.setBounds(10, 37, 104, 14);
		panel_1.add(lblTempsRestant);
		
		JLabel lblNewLabel = new JLabel("Recherche avanc\u00E9e");
		lblNewLabel.setBounds(418, 62, 120, 14);
		panel_1.add(lblNewLabel);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "S\u00E9ries", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(10, 90, 538, 90);
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblScanEnAttente = new JLabel("Scan en attente");
		lblScanEnAttente.setBounds(248, 11, 104, 14);
		panel_2.add(lblScanEnAttente);
		
		JProgressBar progressBar_1 = new JProgressBar();
		progressBar_1.setBounds(228, 36, 300, 14);
		panel_2.add(progressBar_1);
		
		JLabel label = new JLabel("Recherche avanc\u00E9e");
		label.setBounds(418, 65, 120, 14);
		panel_2.add(label);
		
		JLabel label_1 = new JLabel("Temps restant :");
		label_1.setBounds(10, 36, 104, 14);
		panel_2.add(label_1);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(null, "Musique", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_3.setBounds(10, 180, 538, 90);
		panel.add(panel_3);
		panel_3.setLayout(null);
		
		JLabel label_2 = new JLabel("Scan en attente");
		label_2.setBounds(248, 11, 104, 14);
		panel_3.add(label_2);
		
		JProgressBar progressBar_2 = new JProgressBar();
		progressBar_2.setBounds(228, 36, 300, 14);
		panel_3.add(progressBar_2);
		
		JLabel label_3 = new JLabel("Recherche avanc\u00E9e");
		label_3.setBounds(418, 65, 120, 14);
		panel_3.add(label_3);
		
		JLabel label_4 = new JLabel("Temps restant :");
		label_4.setBounds(10, 36, 104, 14);
		panel_3.add(label_4);
		
		JLabel lblTempsRestant_1 = new JLabel("Temps restant :");
		lblTempsRestant_1.setBounds(198, 306, 119, 14);
		panel.add(lblTempsRestant_1);
		
		JLabel lblTempscoul = new JLabel("Temps \u00E9coul\u00E9 :");
		lblTempscoul.setBounds(10, 306, 124, 14);
		panel.add(lblTempscoul);
		
		JLabel lblNombreDeFichiers = new JLabel("Nombre de fichiers scann\u00E9s :");
		lblNombreDeFichiers.setBounds(10, 331, 170, 14);
		panel.add(lblNombreDeFichiers);
		
		JProgressBar progressBar_3 = new JProgressBar();
		progressBar_3.setBounds(198, 281, 350, 14);
		panel.add(progressBar_3);
		
		JLabel lblProgression = new JLabel("Progression :");
		lblProgression.setBounds(10, 281, 113, 14);
		panel.add(lblProgression);

	}
}
