package view;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.TreeMap;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.views.FileKindSelectionParameters;

public final class SearchingOnSelectedValues extends WindowContent {
	private TreeMap<String, JLabel> results;

	public SearchingOnSelectedValues(MainWindow mainWindow, FileKindSelectionParameters fileKindSelectionParameters) {
		super(mainWindow);
		this.results = new TreeMap<String, JLabel>();
		
		this.setLayout(new GridBagLayout());

		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.ipadx = 10;
		gridBagConstraints.ipady = 10;
		
		gridBagConstraints.fill = GridBagConstraints.CENTER;
		gridBagConstraints.gridwidth = 2;
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;

		this.results.put("Video_Selected", new JLabel("Video Selected : " + fileKindSelectionParameters.isVideoSelected()));
		this.add(this.results.get("Video_Selected"), gridBagConstraints);
		
		gridBagConstraints.fill = GridBagConstraints.CENTER;
		gridBagConstraints.gridwidth = 2;
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 1;
		
		this.results.put("Serie_Selected", new JLabel("Serie Selected : " + fileKindSelectionParameters.isSerieSelected()));
		this.add(this.results.get("Serie_Selected"), gridBagConstraints);
		
		gridBagConstraints.fill = GridBagConstraints.CENTER;
		gridBagConstraints.gridwidth = 2;
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 2;
		
		this.results.put("Music_Selected", new JLabel("Music Selected : " + fileKindSelectionParameters.isMusicSelected()));
		this.add(this.results.get("Music_Selected"), gridBagConstraints);
		
	}
	
	public void setFileKindSelectionParameters(FileKindSelectionParameters fileKindSelectionParameters) {
		this.results.get("Video_Selected").setText("Video Selected : " + fileKindSelectionParameters.isVideoSelected());
		this.results.get("Serie_Selected").setText("Serie Selected : " + fileKindSelectionParameters.isSerieSelected());
		this.results.get("Music_Selected").setText("Music Selected : " + fileKindSelectionParameters.isMusicSelected());
		this.invalidate(); 
		this.validate();
		this.repaint();
	}
	
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		
		Component cancelButton = this.getComponent(3);
		Component previousButton = this.getComponent(4);
		Component nextStepButton = this.getComponent(5);
		if(source == cancelButton){
			this.getMainWindow().dispose();
		} else if(source == nextStepButton){
			System.out.println("suivant");
		} else if(source == previousButton){
			//this.mainWindow.getSearchingOnSelectedParam();
		}
	}

	@Override
	public void getNextScreen() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void getPreviousScreen() {
		// TODO Auto-generated method stub
		
	}
}
