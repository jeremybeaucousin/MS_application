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
		
		JPanel panelMessage = new JPanel();
		panelMessage.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelMessage.setBackground(Color.WHITE);
		panelMessage.setBounds(177, 11, 371, 334);
		panel.add(panelMessage);
		panelMessage.setLayout(null);
		
		JLabel lblSuccesfullScan = new JLabel("Votre scan s'est effectu\u00E9 avec succ\u00E8s !");
		lblSuccesfullScan.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblSuccesfullScan.setBounds(10, 85, 358, 38);
		panelMessage.add(lblSuccesfullScan);
		
		JLabel lblClicNext = new JLabel("Cliquez sur Terminer pour lancer l'application.");
		lblClicNext.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblClicNext.setBounds(10, 298, 358, 25);
		panelMessage.add(lblClicNext);
		
		JLabel labelImage = new JLabel("");
		labelImage.setIcon(new ImageIcon(ResultsPanel.class.getResource("/img/multimedia-icone.png")));
		labelImage.setBounds(10, 11, 130, 124);
		panel.add(labelImage);
		
		JPanel panelStatistics = new JPanel();
		panelStatistics.setBounds(10, 136, 157, 209);
		panel.add(panelStatistics);
		panelStatistics.setLayout(null);
		
		JList listStatistics = new JList();
		listStatistics.setEnabled(false);
		listStatistics.setBounds(10, 11, 137, 187);
		panelStatistics.add(listStatistics);

	}
}
