package view;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.views.FileKindSelectionParameters;

public final class SearchingOnSelectedValues extends JPanel implements ActionListener{
	MainWindow mainWindow;
	FileKindSelectionParameters fileKindSelectionParameters;
	public SearchingOnSelectedValues(MainWindow mainWindow, FileKindSelectionParameters fileKindSelectionParameters) throws IOException {
		this.mainWindow = mainWindow;
		this.fileKindSelectionParameters = fileKindSelectionParameters;
		
		this.setLayout(new GridBagLayout());

		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.ipadx = 10;
		gridBagConstraints.ipady = 10;
		
		gridBagConstraints.fill = GridBagConstraints.CENTER;
		gridBagConstraints.gridwidth = 2;
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		
		JLabel videoParameter = new JLabel("Video Selected : " + this.fileKindSelectionParameters.isVideoSelected());
		this.add(videoParameter, gridBagConstraints);
		
		gridBagConstraints.fill = GridBagConstraints.CENTER;
		gridBagConstraints.gridwidth = 2;
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 1;
		
		JLabel serieParameter = new JLabel("Serie Selected : " + this.fileKindSelectionParameters.isSerieSelected());
		this.add(serieParameter, gridBagConstraints);
		
		gridBagConstraints.fill = GridBagConstraints.CENTER;
		gridBagConstraints.gridwidth = 2;
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 2;
		
		JLabel musicParameter = new JLabel("Music Selected : " + this.fileKindSelectionParameters.isMusicSelected());
		this.add(musicParameter, gridBagConstraints);
		
		gridBagConstraints.fill = GridBagConstraints.CENTER;
		gridBagConstraints.ipady = 0;
		gridBagConstraints.gridwidth = 1;
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 3;
		JButton bouton1 = new JButton("Annuler");
		bouton1.addActionListener(this);
		this.add(bouton1, gridBagConstraints);
		
		gridBagConstraints.fill = GridBagConstraints.LAST_LINE_END;
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 3;
		JButton bouton2 = new JButton("Précédent");
		bouton2.addActionListener(this);
		this.add(bouton2, gridBagConstraints);
		
		gridBagConstraints.fill = GridBagConstraints.LAST_LINE_END;
		gridBagConstraints.gridx = 2;
		gridBagConstraints.gridy = 3;
		JButton bouton3 = new JButton("Suivant");
		bouton3.addActionListener(this);
		this.add(bouton3, gridBagConstraints);
	}
	
	public void actionPerformed(ActionEvent e) {
Object source = e.getSource();
		
		Component cancelButton = this.getComponent(3);
		Component previousButton = this.getComponent(4);
		Component nextStepButton = this.getComponent(5);
		if(source == cancelButton){
			this.mainWindow.dispose();
		} else if(source == nextStepButton){
			System.out.println("suivant");
		} else if(source == previousButton){
			this.mainWindow.getSearchingOnSelectedParam();
		}
	}
}
