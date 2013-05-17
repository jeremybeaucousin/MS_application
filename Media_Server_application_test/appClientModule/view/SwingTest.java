package view;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class SwingTest implements ActionListener {
	private JDialog dialog; 

	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		Component button1 = this.dialog.getContentPane().getComponent(4);
		Component button2 = this.dialog.getContentPane().getComponent(5);
		if(source == button1){
			this.dialog.dispose();
		} else if(source == button2){
			boolean videoIsSelected = ((JCheckBoxMenuItem) this.dialog.getContentPane().getComponent(1)).isSelected();
			boolean serieIsSelected = ((JCheckBoxMenuItem) this.dialog.getContentPane().getComponent(2)).isSelected();
			boolean musicIsSelected = ((JCheckBoxMenuItem) this.dialog.getContentPane().getComponent(3)).isSelected();
		}
	}
	
	public SwingTest() {
		this.dialog = new JDialog(); 
		dialog.setTitle("Media Server Application"); //On donne un titre à l'application
		dialog.setSize(300, 200);//On lui donne une taille pour qu'on puisse la voir
		dialog.setVisible(true);//On la rend visible
		dialog.setLocationRelativeTo(null);//On centre la fenêtre sur l'écran
		dialog.setResizable(false); //On interdit la redimensionnement de la fenêtre
		dialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); //On dit à l'application de se fermer lors du clic sur la croix
		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.ipadx = 10;
		gridBagConstraints.ipady = 10;
		
		gridBagConstraints.fill = GridBagConstraints.PAGE_START;
		gridBagConstraints.gridwidth = 2;
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		JLabel label = new JLabel("Quel média voulez-vous scannez : ");
		panel.add(label, gridBagConstraints);
		
		gridBagConstraints.fill = GridBagConstraints.CENTER;
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 1;
		JCheckBoxMenuItem checkbox1 = new JCheckBoxMenuItem();
		checkbox1.setText("Film");
		panel.add(checkbox1, gridBagConstraints);
		
		gridBagConstraints.fill = GridBagConstraints.CENTER;
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 2;
		JCheckBoxMenuItem checkbox2 = new JCheckBoxMenuItem();
		checkbox2.setText("Série");
		panel.add(checkbox2, gridBagConstraints);
		
		gridBagConstraints.fill = GridBagConstraints.CENTER;
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 3;
		JCheckBoxMenuItem checkbox3 = new JCheckBoxMenuItem();
		checkbox3.setText("Musique");
		panel.add(checkbox3, gridBagConstraints);
		
		gridBagConstraints.fill = GridBagConstraints.LAST_LINE_START;
		gridBagConstraints.ipady = 0;
		gridBagConstraints.gridwidth = 1;
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 4;
		JButton bouton1 = new JButton("Annuler");
		bouton1.addActionListener(this);
		panel.add(bouton1, gridBagConstraints);
		
		gridBagConstraints.fill = GridBagConstraints.LAST_LINE_END;
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 4;
		JButton bouton2 = new JButton("Suivant");
		bouton2.addActionListener(this);
		panel.add(bouton2, gridBagConstraints);
		
		dialog.setContentPane(panel);
	}
	
}
