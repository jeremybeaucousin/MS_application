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

public class TestMaite {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TestMaite window = new TestMaite();
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
	public TestMaite() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setEnabled(false);
		frame.setBounds(100, 100, 594, 486);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panellanguages = new JPanel();
		panellanguages.setBorder(null);
		panellanguages.setBounds(515, 0, 63, 26);
		frame.getContentPane().add(panellanguages);
		
		JLabel lblEnglish = new JLabel("");
		lblEnglish.setIcon(new ImageIcon("C:\\Users\\Sett\u00E4n\\git\\MS_application\\Media_Server_application_test\\imgMaite\\United-Kingdom-flag.png"));
		panellanguages.add(lblEnglish);
		
		JLabel lblFrench = new JLabel("");
		lblFrench.setHorizontalAlignment(SwingConstants.CENTER);
		lblFrench.setIcon(new ImageIcon("C:\\Users\\Sett\u00E4n\\git\\MS_application\\Media_Server_application_test\\imgMaite\\france_flag_32.png"));
		panellanguages.add(lblFrench);
		
		JButton btnTerminer = new JButton("Terminer");
		btnTerminer.setBounds(479, 414, 89, 23);
		frame.getContentPane().add(btnTerminer);
		
		JButton btnSuivant = new JButton("Suivant");
		btnSuivant.setBounds(380, 414, 89, 23);
		frame.getContentPane().add(btnSuivant);
		
		JButton btnPrcdent = new JButton("Pr\u00E9c\u00E9dent");
		btnPrcdent.setBounds(281, 414, 89, 23);
		frame.getContentPane().add(btnPrcdent);
		
		JButton btnAnnuler = new JButton("Annuler");
		btnAnnuler.setBounds(10, 414, 89, 23);
		frame.getContentPane().add(btnAnnuler);
		
		JPanel panelbase = new JPanel();
		panelbase.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		panelbase.setBackground(Color.WHITE);
		panelbase.setBounds(10, 37, 558, 356);
		frame.getContentPane().add(panelbase);
		
		JLabel lblSuperAplieServeur = new JLabel("Super Aplie Serveur Multim\u00E9dia");
		lblSuperAplieServeur.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblSuperAplieServeur.setBounds(10, 12, 242, 14);
		frame.getContentPane().add(lblSuperAplieServeur);
	}
}
