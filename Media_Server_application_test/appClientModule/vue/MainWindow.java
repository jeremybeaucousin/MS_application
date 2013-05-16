package vue;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JWindow;

public class MainWindow extends JWindow implements ActionListener{
	FileKindSelection fileKindSelection;
	JPanel jpanel;
	public MainWindow() {
		//this.setTitle("Media Server Application"); //On donne un titre à l'application
		this.setSize(300, 200);//On lui donne une taille pour qu'on puisse la voir
		this.setVisible(true);//On la rend visible
		this.setLocationRelativeTo(null);//On centre la fenêtre sur l'écran
		//this.setResizable(false); //On interdit la redimensionnement de la fenêtre
		//this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); //On dit à l'application de se fermer lors du clic sur la croix
		this.fileKindSelection = new FileKindSelection(this);
		System.out.println(fileKindSelection);
		this.setContentPane(fileKindSelection);
		this.setVisible(true);
	}

	public void getFileKindSelectionParam(Boolean videoIsSelected, Boolean serieIsSelected, Boolean musicIsSelected) {
		this.remove(fileKindSelection);
		this.jpanel = new JPanel();
		jpanel.setLayout(new GridBagLayout());
		
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.ipadx = 10;
		gridBagConstraints.ipady = 10;
		
		gridBagConstraints.fill = GridBagConstraints.CENTER;
		gridBagConstraints.gridwidth = 2;
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		
		JLabel videoParameter = new JLabel("Video Selected : " + videoIsSelected);
		jpanel.add(videoParameter, gridBagConstraints);
		
		gridBagConstraints.fill = GridBagConstraints.CENTER;
		gridBagConstraints.gridwidth = 2;
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 1;
		
		JLabel serieParameter = new JLabel("Serie Selected : " + serieIsSelected);
		jpanel.add(serieParameter, gridBagConstraints);
		
		gridBagConstraints.fill = GridBagConstraints.CENTER;
		gridBagConstraints.gridwidth = 2;
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 2;
		
		JLabel musicParameter = new JLabel("Music Selected : " + musicIsSelected);
		jpanel.add(musicParameter, gridBagConstraints);
		
		gridBagConstraints.fill = GridBagConstraints.CENTER;
		gridBagConstraints.ipady = 0;
		gridBagConstraints.gridwidth = 1;
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 3;
		JButton bouton1 = new JButton("Annuler");
		bouton1.addActionListener(this);
		jpanel.add(bouton1, gridBagConstraints);
		
		gridBagConstraints.fill = GridBagConstraints.LAST_LINE_END;
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 3;
		JButton bouton2 = new JButton("Suivant");
		bouton2.addActionListener(this);
		jpanel.add(bouton2, gridBagConstraints);
		
		this.setContentPane(jpanel);
	    this.invalidate(); 
	    this.validate();
	    this.repaint();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// this.dispose();
		this.remove(this.jpanel);
		this.setContentPane(this.fileKindSelection);
		System.out.println(fileKindSelection);
		this.invalidate(); 
		this.validate();
		this.repaint();
	}
}
