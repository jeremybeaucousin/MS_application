package vue;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JWindow;
import javax.swing.border.BevelBorder;

public class MainWindow extends JWindow {
	FileKindSelection fileKindSelection;
	SearchingOnSelectedValues searchingOnSelectedValue;
	public MainWindow() throws IOException {
		//this.setTitle("Media Server Application"); //On donne un titre à l'application
		this.setSize(300, 200);//On lui donne une taille pour qu'on puisse la voir
		this.setVisible(true);//On la rend visible
		this.setLocationRelativeTo(null);//On centre la fenêtre sur l'écran
		//this.setResizable(false); //On interdit la redimensionnement de la fenêtre
		//this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); //On dit à l'application de se fermer lors du clic sur la croix
		this.setLayout(new GridBagLayout());
		
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		
		gridBagConstraints.ipadx = 4;
		
		JMenuBar menuBar = new JMenuBar();
		
		menuBar.setLayout(new GridBagLayout());
		
		gridBagConstraints.fill = GridBagConstraints.FIRST_LINE_END;
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.weightx = 0.5;
		
		BufferedImage frenchIcon = ImageIO.read(new File("./img/United-Kingdom-flag.png"));
		JButton frenchButton = new JButton(new ImageIcon(frenchIcon));
		frenchButton.setBorder(BorderFactory.createEtchedBorder(1));
		frenchButton.setSize(32, 32);
		menuBar.add(frenchButton, gridBagConstraints);
		
		gridBagConstraints.fill = GridBagConstraints.FIRST_LINE_END;
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.weightx = 0.5;
		
		BufferedImage englishIcon = ImageIO.read(new File("./img/france_flag_32.png"));
		JButton englishButton = new JButton(new ImageIcon(englishIcon));
		englishButton.setBorder(BorderFactory.createEtchedBorder(1));
		englishButton.setSize(32, 32);
		menuBar.add(englishButton, gridBagConstraints);
		
		gridBagConstraints.fill = GridBagConstraints.FIRST_LINE_END;
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		
		this.add(menuBar, gridBagConstraints);
		
		gridBagConstraints.fill = GridBagConstraints.CENTER;
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 1;
		
		this.fileKindSelection = new FileKindSelection(this);
		this.add(fileKindSelection, gridBagConstraints);
		this.setVisible(true);
	}

	public void getFileKindSelectionParam(Boolean videoIsSelected, Boolean serieIsSelected, Boolean musicIsSelected) throws IOException {
		this.remove(fileKindSelection);
		this.searchingOnSelectedValue = new SearchingOnSelectedValues(this, videoIsSelected, serieIsSelected, musicIsSelected);
		
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		
		gridBagConstraints.fill = GridBagConstraints.CENTER;
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 1;

		this.add(this.searchingOnSelectedValue, gridBagConstraints);
	    this.invalidate(); 
	    this.validate();
	    this.repaint();
	}

	public void getSearchingOnSelectedValues() {
		this.remove(this.searchingOnSelectedValue);

		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		
		gridBagConstraints.fill = GridBagConstraints.CENTER;
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 1;
		
		this.add(this.fileKindSelection, gridBagConstraints);
		this.invalidate(); 
		this.validate();
		this.repaint();
	}
}
