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

public class ResultsPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public ResultsPanel() {
		setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		setBackground(Color.WHITE);
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 558, 356);
		add(panel);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Films", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(0, 0, 558, 90);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Recherche avanc\u00E9e");
		lblNewLabel.setBounds(444, 62, 104, 14);
		panel_1.add(lblNewLabel);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "S\u00E9ries", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(0, 90, 558, 90);
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel label = new JLabel("Recherche avanc\u00E9e");
		label.setBounds(444, 65, 104, 14);
		panel_2.add(label);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(null, "Musique", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_3.setBounds(0, 180, 558, 90);
		panel.add(panel_3);
		panel_3.setLayout(null);
		
		JLabel label_3 = new JLabel("Recherche avanc\u00E9e");
		label_3.setBounds(444, 65, 104, 14);
		panel_3.add(label_3);

	}
}
