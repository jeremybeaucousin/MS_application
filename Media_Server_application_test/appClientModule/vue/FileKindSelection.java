package vue;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class FileKindSelection extends JPanel implements ActionListener {
	MainWindow mainWindow;
	public FileKindSelection(MainWindow mainWindow) {
		this.mainWindow = mainWindow;
		this.setLayout(new GridBagLayout());
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.ipadx = 10;
		gridBagConstraints.ipady = 10;
		
		gridBagConstraints.fill = GridBagConstraints.PAGE_START;
		gridBagConstraints.gridwidth = 2;
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		JLabel label = new JLabel("Quel média voulez-vous scannez : ");
		this.add(label, gridBagConstraints);
		
		gridBagConstraints.fill = GridBagConstraints.CENTER;
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 1;
		JCheckBoxMenuItem checkbox1 = new JCheckBoxMenuItem();
		checkbox1.setText("Film");
		this.add(checkbox1, gridBagConstraints);
		
		gridBagConstraints.fill = GridBagConstraints.CENTER;
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 2;
		JCheckBoxMenuItem checkbox2 = new JCheckBoxMenuItem();
		checkbox2.setText("Série");
		this.add(checkbox2, gridBagConstraints);
		
		gridBagConstraints.fill = GridBagConstraints.CENTER;
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 3;
		JCheckBoxMenuItem checkbox3 = new JCheckBoxMenuItem();
		checkbox3.setText("Musique");
		this.add(checkbox3, gridBagConstraints);
		
		gridBagConstraints.fill = GridBagConstraints.LAST_LINE_START;
		gridBagConstraints.ipady = 0;
		gridBagConstraints.gridwidth = 1;
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 4;
		JButton bouton1 = new JButton("Annuler");
		bouton1.addActionListener(this);
		this.add(bouton1, gridBagConstraints);
		
		gridBagConstraints.fill = GridBagConstraints.LAST_LINE_END;
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 4;
		JButton bouton2 = new JButton("Suivant");
		bouton2.addActionListener(this);
		this.add(bouton2, gridBagConstraints);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		
		Component cancelButton = this.getComponent(4);
		Component nextStepButton = this.getComponent(5);
		if(source == cancelButton){
			this.mainWindow.dispose();
		} else if(source == nextStepButton){
			boolean videoIsSelected = ((JCheckBoxMenuItem) this.getComponent(1)).isSelected();
			boolean serieIsSelected = ((JCheckBoxMenuItem) this.getComponent(2)).isSelected();
			boolean musicIsSelected = ((JCheckBoxMenuItem) this.getComponent(3)).isSelected();
			this.mainWindow.getFileKindSelectionParam(videoIsSelected, serieIsSelected, musicIsSelected);
		}
	}

}
