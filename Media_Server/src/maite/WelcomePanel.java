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
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.DropMode;

public class WelcomePanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public WelcomePanel() {
		setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		setBackground(Color.WHITE);
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 558, 356);
		add(panel);
		panel.setLayout(null);
		
		JPanel panelMessage = new JPanel();
		panelMessage.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelMessage.setBackground(Color.WHITE);
		panelMessage.setBounds(150, 11, 398, 334);
		panel.add(panelMessage);
		panelMessage.setLayout(null);
		
		JLabel lblSuccesfullScan = new JLabel("Bienvenue");
		lblSuccesfullScan.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblSuccesfullScan.setBounds(10, 85, 378, 25);
		panelMessage.add(lblSuccesfullScan);
		
		JLabel lblClicNext = new JLabel("Cliquez sur Suivant pour comencer le scan.");
		lblClicNext.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblClicNext.setBounds(10, 298, 378, 25);
		panelMessage.add(lblClicNext);
		
		JTextPane txtpnCetteApplicationScan = new JTextPane();
		txtpnCetteApplicationScan.setBackground(Color.WHITE);
		txtpnCetteApplicationScan.setEditable(false);
		txtpnCetteApplicationScan.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtpnCetteApplicationScan.setText("\tCette application scan vos fichiers audio et video, les identifient \u00E0 fin de les mettre en relacion avec les informations les concernants et de cr\u00E9er une base de donn\u00E9es pour acceder aisement \u00E0 la fois aux fichiers, aux informations et \u00E0 un moteur de recherche.");
		txtpnCetteApplicationScan.setBounds(10, 121, 378, 140);
		panelMessage.add(txtpnCetteApplicationScan);
		
		JLabel labelImage = new JLabel("");
		labelImage.setIcon(new ImageIcon(WelcomePanel.class.getResource("/img/multimedia-icone.png")));
		labelImage.setBounds(10, 11, 130, 124);
		panel.add(labelImage);

	}
}
