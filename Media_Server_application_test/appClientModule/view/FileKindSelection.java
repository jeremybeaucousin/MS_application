package view;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Path2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.sound.midi.Patch;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.views.FileKindSelectionParameters;

public final class FileKindSelection extends WindowContent {

	public FileKindSelection(MainWindow mainWindow) throws IOException {
		super(mainWindow);
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
	}
	
	public FileKindSelectionParameters getFileKindSelectionParameters() {
		boolean videoIsSelected = ((JCheckBoxMenuItem) this.getComponent(1)).isSelected();
		boolean serieIsSelected = ((JCheckBoxMenuItem) this.getComponent(2)).isSelected();
		boolean musicIsSelected = ((JCheckBoxMenuItem) this.getComponent(3)).isSelected();
		FileKindSelectionParameters fileKindSelectionParameters = new FileKindSelectionParameters(videoIsSelected, serieIsSelected, musicIsSelected);
		return fileKindSelectionParameters;
	}
	
	@Override
	public void getNextScreen() {
		this.getMainWindow().getButtons().get("previous").setEnabled(true);
		FileKindSelectionParameters fileKindSelectionParameters = this.getFileKindSelectionParameters();
		if(this.getMainWindow().getSearchingOnSelectedValue() == null) {
			SearchingOnSelectedValues searchingOnSelectedValues = new SearchingOnSelectedValues(this.getMainWindow(), fileKindSelectionParameters);
			this.getMainWindow().setSearchingOnSelectedValue(searchingOnSelectedValues);
			this.getMainWindow().getNavigator().add(searchingOnSelectedValues);
		} else {
			this.getMainWindow().getNavigator().next();
			this.getMainWindow().getSearchingOnSelectedValue().setFileKindSelectionParameters(fileKindSelectionParameters);
		}
	    this.getMainWindow().replaceContent(this, this.getMainWindow().getSearchingOnSelectedValue());
	}

	@Override
	public void getPreviousScreen() {
		this.getMainWindow().getButtons().get("previous").setEnabled(false);
		this.getMainWindow().replaceContent(this.getMainWindow().getSearchingOnSelectedValue(), this);
		
	}

}
