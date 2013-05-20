package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JWindow;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.MatteBorder;

import model.views.FileKindSelectionParameters;

public class MainWindow extends JFrame {
	JPanel panelBase;
	FileKindSelection fileKindSelection;
	SearchingOnSelectedValues searchingOnSelectedValue;
	public MainWindow() throws IOException {
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("img/multimedia-icone.png")));
		this.setBounds(100, 100, 594, 486);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.getContentPane().setLayout(null);
		this.setLocationRelativeTo(null);//On centre la fenêtre sur l'écran
		
		JPanel panelLanguages = new JPanel();
		panelLanguages.setBorder(null);
		panelLanguages.setBounds(515, 0, 63, 26);
		this.getContentPane().add(panelLanguages);
		
		JLabel lblEnglish = new JLabel("");
		lblEnglish.setIcon(new ImageIcon(ImageIO.read(getClass().getClassLoader().getResource("img/United-Kingdom-flag.png"))));
		panelLanguages.add(lblEnglish);
		
		JLabel lblFrench = new JLabel("");
		lblFrench.setHorizontalAlignment(SwingConstants.CENTER);
		lblFrench.setIcon(new ImageIcon(ImageIO.read(getClass().getClassLoader().getResource("img/france_flag_32.png"))));
		panelLanguages.add(lblFrench);
	
		JLabel lblSuperAplieServeur = new JLabel("Super Aplie Serveur Multimédia");
		lblSuperAplieServeur.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblSuperAplieServeur.setBounds(10, 12, 242, 14);
		this.getContentPane().add(lblSuperAplieServeur);
		
		this.panelBase = new JPanel();
		this.panelBase.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		this.panelBase.setBackground(Color.WHITE);
		this.panelBase.setBounds(10, 37, 558, 356);
		this.fileKindSelection = new FileKindSelection(this);
		this.panelBase.add(fileKindSelection);
		this.getContentPane().add(panelBase);
		
		JButton btnCancel = new JButton("Annuler");
		btnCancel.setBounds(10, 414, 89, 23);
		this.getContentPane().add(btnCancel);
		
		JButton btnPrevious = new JButton("Précédent");
		btnPrevious.setBounds(281, 414, 89, 23);
		this.getContentPane().add(btnPrevious);
		
		JButton btnNext = new JButton("Suivant");
		btnNext.setBounds(380, 414, 89, 23);
		this.getContentPane().add(btnNext);
		
		JButton btnFinish = new JButton("Terminer");
		btnFinish.setBounds(479, 414, 89, 23);
		this.getContentPane().add(btnFinish);
		
		this.setVisible(true);
		
	}

	public void getFileKindSelectionParam(FileKindSelectionParameters fileKindSelectionParameters) throws IOException {
		// TODO replace instance by a set methode
		this.searchingOnSelectedValue = new SearchingOnSelectedValues(this, fileKindSelectionParameters);
			
	    this.replaceContent(this.fileKindSelection, this.searchingOnSelectedValue);
	}

	public void getSearchingOnSelectedParam() {
		this.replaceContent(this.searchingOnSelectedValue, this.fileKindSelection);
	}
	
	private void replaceContent(JComponent oldContent, JComponent newContent) {
		this.panelBase.remove(oldContent);
		this.panelBase.add(newContent);
		this.panelBase.invalidate(); 
		this.panelBase.validate();
		this.panelBase.repaint();
	}
}
