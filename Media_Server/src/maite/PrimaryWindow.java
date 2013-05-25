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
		
		JToggleButton toggleButton = new JToggleButton("");
		toggleButton.setSelected(true);
		toggleButton.setSelectedIcon(new ImageIcon("C:\\Users\\Sett\u00E4n\\Desktop\\flag\\United-Kingdom-flag-icon-selected.png"));
		toggleButton.setIcon(new ImageIcon("C:\\Users\\Sett\u00E4n\\Desktop\\flag\\United-Kingdom-flag-icon.png"));
		toggleButton.setBounds(538, 9, 30, 23);
		frame.getContentPane().add(toggleButton);
		
		JToggleButton toggleButton_1 = new JToggleButton("");
		toggleButton_1.setSelectedIcon(new ImageIcon("C:\\Users\\Sett\u00E4n\\Desktop\\flag\\France-Flag-icon-selected.png"));
		toggleButton_1.setIcon(new ImageIcon("C:\\Users\\Sett\u00E4n\\Desktop\\flag\\France-Flag-icon.png"));
		toggleButton_1.setBounds(498, 9, 30, 23);
		frame.getContentPane().add(toggleButton_1);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.setEnabled(false);
		btnNewButton.setSelectedIcon(new ImageIcon("C:\\Users\\Sett\u00E4n\\Desktop\\flag\\Germany-Flag-icon-selected.png"));
		btnNewButton.setIcon(new ImageIcon("C:\\Users\\Sett\u00E4n\\Desktop\\flag\\Germany-Flag-icon.png"));
		btnNewButton.setBounds(458, 9, 30, 23);
		frame.getContentPane().add(btnNewButton);
		
		JButton button = new JButton("");
		button.setEnabled(false);
		button.setSelectedIcon(new ImageIcon("C:\\Users\\Sett\u00E4n\\Desktop\\flag\\Spain-Flag-icon-selected.png"));
		button.setIcon(new ImageIcon("C:\\Users\\Sett\u00E4n\\Desktop\\flag\\Spain-Flag-icon.png"));
		button.setBounds(418, 9, 30, 23);
		frame.getContentPane().add(button);
		
		JButton button_1 = new JButton("");
		button_1.setEnabled(false);
		button_1.setSelectedIcon(new ImageIcon("C:\\Users\\Sett\u00E4n\\Desktop\\flag\\Italy-Flag-icon-selected.png"));
		button_1.setIcon(new ImageIcon("C:\\Users\\Sett\u00E4n\\Desktop\\flag\\Italy-Flag-icon.png"));
		button_1.setBounds(378, 9, 30, 23);
		frame.getContentPane().add(button_1);
	}
}
