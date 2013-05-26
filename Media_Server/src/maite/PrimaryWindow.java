package maite;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;
import java.awt.Toolkit;
import javax.swing.JToggleButton;
import javax.swing.border.TitledBorder;
import javax.swing.JCheckBox;
import javax.swing.JTextField;
import java.awt.SystemColor;
import javax.swing.JTextPane;
import javax.swing.border.EtchedBorder;

public class PrimaryWindow {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PrimaryWindow window = new PrimaryWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public PrimaryWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Sett\u00E4n\\git\\MS_application\\Media_Server_application_test\\imgMaite\\multimedia-icone.png"));
		frame.setBounds(100, 100, 583, 486);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnFinish = new JButton("Terminer");
		btnFinish.setBounds(479, 414, 89, 23);
		frame.getContentPane().add(btnFinish);
		
		JButton btnNext = new JButton("Suivant");
		btnNext.setBounds(380, 414, 89, 23);
		frame.getContentPane().add(btnNext);
		
		JButton btnPrevious = new JButton("Pr\u00E9c\u00E9dent");
		btnPrevious.setBounds(281, 414, 89, 23);
		frame.getContentPane().add(btnPrevious);
		
		JButton btnCancel = new JButton("Annuler");
		btnCancel.setBounds(10, 414, 89, 23);
		frame.getContentPane().add(btnCancel);
		
		JPanel panelbase = new JPanel();
		panelbase.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		panelbase.setBackground(Color.WHITE);
		panelbase.setBounds(10, 37, 558, 356);
		frame.getContentPane().add(panelbase);
		panelbase.setLayout(null);
		
		JLabel lblSuperAplieServeur = new JLabel("Super Aplie Serveur Multim\u00E9dia");
		lblSuperAplieServeur.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblSuperAplieServeur.setBounds(10, 12, 242, 14);
		frame.getContentPane().add(lblSuperAplieServeur);
		
		JToggleButton englishButton = new JToggleButton("");
		englishButton.setSelectedIcon(new ImageIcon(PrimaryWindow.class.getResource("/img/United-Kingdom-flag-icon-selected.png")));
		englishButton.setIcon(new ImageIcon(PrimaryWindow.class.getResource("/img/United-Kingdom-flag-icon.png")));
		englishButton.setBounds(538, 9, 30, 23);
		frame.getContentPane().add(englishButton);
		
		JToggleButton frenchButton = new JToggleButton("");
		frenchButton.setSelectedIcon(new ImageIcon(PrimaryWindow.class.getResource("/img/France-Flag-icon-selected.png")));
		frenchButton.setIcon(new ImageIcon(PrimaryWindow.class.getResource("/img/France-Flag-icon.png")));
		frenchButton.setBounds(498, 9, 30, 23);
		frame.getContentPane().add(frenchButton);
		
		JToggleButton spanishButton = new JToggleButton("");
		spanishButton.setSelectedIcon(new ImageIcon(PrimaryWindow.class.getResource("/img/Spain-Flag-icon-selected.png")));
		spanishButton.setIcon(new ImageIcon(PrimaryWindow.class.getResource("/img/Spain-Flag-icon.png")));
		spanishButton.setBounds(458, 9, 30, 23);
		frame.getContentPane().add(spanishButton);
		
		JToggleButton germanButton = new JToggleButton("");
		germanButton.setSelectedIcon(new ImageIcon(PrimaryWindow.class.getResource("/img/Germany-Flag-icon-selected.png")));
		germanButton.setIcon(new ImageIcon(PrimaryWindow.class.getResource("/img/Germany-Flag-icon.png")));
		germanButton.setBounds(418, 9, 30, 23);
		frame.getContentPane().add(germanButton);
		
		JToggleButton italianButton = new JToggleButton("");
		italianButton.setIcon(new ImageIcon(PrimaryWindow.class.getResource("/img/Italy-Flag-icon.png")));
		italianButton.setSelectedIcon(new ImageIcon(PrimaryWindow.class.getResource("/img/Italy-Flag-icon-selected.png")));
		italianButton.setBounds(378, 9, 30, 23);
		frame.getContentPane().add(italianButton);
	}
}
