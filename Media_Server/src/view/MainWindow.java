package view;

import java.awt.Component;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ListIterator;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToggleButton;


public class MainWindow extends JFrame implements ActionListener {
	JLabel lblSuperAplieServeur = new JLabel();
	
	// BUTTONS //
	/** Button use to change Languages **/
	private JToggleButton italianButton, germanButton, spanishButton, frenchButton, englishButton ;
	
	/** Button use to quit the application **/
	private JButton canceledButton = new JButton(), previousButton = new JButton(), nextButton = new JButton(), finishButton = new JButton();
	
	// textes //
	/** Contains all components keys **/
	private final String previousButtonKey = "previousButton", nextButtonKey = "nextButton", finishButtonKey = "finishButton", canceledButtonKey = "canceledButton", TitleKey = "Title";
	
	private HashMap<Object, String> componentsWithText = new HashMap<Object, String>() {
		{
			put(lblSuperAplieServeur, TitleKey);
			put(canceledButton, canceledButtonKey);
			put(previousButton, previousButtonKey);
			put(nextButton, nextButtonKey);
			put(finishButton, finishButtonKey);
		}
	};
	
	// navigation //
	/** Contain all the different screen, adding one by one during navigation **/
	private ArrayList<WindowContent> navigation;
	
	/** Used to navigate between screen **/
	private ListIterator<WindowContent> navigator;
	
	// screen //
	/** screen : choose the kind of file that will be scann  **/
	private FileKindSelection fileKindSelection;
	
	/** screen : scanning file depending on the selected value  **/
	private ScanningProgress scanningProgress;
	
	public MainWindow() throws IOException {
		super();
		this.navigation = new ArrayList<WindowContent>();
		this.navigator = navigation.listIterator();
		ResourceBundle resourceBundle = ResourceBundle.getBundle("texts/MainWindow", WindowContent.getLanguage());
//		Double screenWidth = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
//		Double screenHeight = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		
		this.setResizable(false);
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("img/multimedia-icone.png")));
		this.setBounds(100, 100, 583, 486);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setLayout(null);
		this.setLocationRelativeTo(null);
		
		this.lblSuperAplieServeur.setText(resourceBundle.getString(this.TitleKey));
		this.componentsWithText.put(lblSuperAplieServeur, this.TitleKey);
		lblSuperAplieServeur.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblSuperAplieServeur.setBounds(10, 12, 242, 14);
		this.getContentPane().add(lblSuperAplieServeur);
		
		
		this.italianButton = new JToggleButton("");
		this.italianButton.setIcon(new ImageIcon(MainWindow.class.getResource("/img/Italy-Flag-icon.png")));
		this.italianButton.setSelectedIcon(new ImageIcon(MainWindow.class.getResource("/img/Italy-Flag-icon-selected.png")));
		this.italianButton.setBounds(378, 9, 30, 23);
		this.italianButton.addActionListener(this);
		this.getContentPane().add(italianButton);
		
		this.germanButton = new JToggleButton("");
		this.germanButton.setIcon(new ImageIcon(MainWindow.class.getResource("/img/Germany-Flag-icon.png")));
		this.germanButton.setSelectedIcon(new ImageIcon(MainWindow.class.getResource("/img/Germany-Flag-icon-selected.png")));
		this.germanButton.setBounds(418, 9, 30, 23);
		this.germanButton.addActionListener(this);
		this.getContentPane().add(germanButton);
		
		this.spanishButton = new JToggleButton("");
		this.spanishButton.setIcon(new ImageIcon(MainWindow.class.getResource("/img/Spain-Flag-icon.png")));
		this.spanishButton.setSelectedIcon(new ImageIcon(MainWindow.class.getResource("/img/Spain-Flag-icon-selected.png")));
		this.spanishButton.setBounds(458, 9, 30, 23);
		this.spanishButton.addActionListener(this);
		this.getContentPane().add(spanishButton);

		this.frenchButton = new JToggleButton("");
		this.frenchButton.setIcon(new ImageIcon(MainWindow.class.getResource("/img/France-Flag-icon.png")));
		this.frenchButton.setSelectedIcon(new ImageIcon(MainWindow.class.getResource("/img/France-Flag-icon-selected.png")));
		this.frenchButton.setBounds(498, 9, 30, 23);
		this.frenchButton.addActionListener(this);
		this.getContentPane().add(this.frenchButton);
		
		this.englishButton = new JToggleButton("");
		this.englishButton.setSelected(true);
		this.englishButton.setIcon(new ImageIcon(MainWindow.class.getResource("/img/United-Kingdom-flag-icon.png")));
		this.englishButton.setSelectedIcon(new ImageIcon(MainWindow.class.getResource("/img/United-Kingdom-flag-icon-selected.png")));
		this.englishButton.setBounds(538, 9, 30, 23);
		this.englishButton.addActionListener(this);
		this.getContentPane().add(this.englishButton);
		
		// initialize first selected Button //
		WindowContent.setCurrentButtonLanguage(this.englishButton);
		
		this.fileKindSelection = new FileKindSelection(this);
		this.navigator.add(this.fileKindSelection);
		
		// main panel
		this.getContentPane().add(this.fileKindSelection);
		
		this.canceledButton.setText(resourceBundle.getString(this.canceledButtonKey));
		this.canceledButton.setBounds(10, 414, 89, 23);
		this.canceledButton.addActionListener(this);
		this.getContentPane().add(this.canceledButton);
		
		this.previousButton.setText(resourceBundle.getString(this.previousButtonKey));
		this.previousButton.setEnabled(false);
		this.previousButton.setBounds(281, 414, 89, 23);
		this.previousButton.addActionListener(this);
		this.getContentPane().add(this.previousButton);
		
		this.nextButton.setText(resourceBundle.getString(this.nextButtonKey));
		this.nextButton.setBounds(380, 414, 89, 23);
		this.nextButton.addActionListener(this);
		this.getContentPane().add(this.nextButton);
		
		this.finishButton.setText(resourceBundle.getString(this.finishButtonKey));
		this.finishButton.setEnabled(false);
		this.finishButton.setBounds(479, 414, 89, 23);
		this.finishButton.addActionListener(this);
		this.getContentPane().add(this.finishButton);
		
		this.setVisible(true);
		
	}
	
	public ArrayList<WindowContent> getNavigation() {
		return navigation;
	}

	public ListIterator<WindowContent> getNavigator() {
		return navigator;
	}

	public JButton getPreviousButton() {
		return previousButton;
	}
	
	public JButton getNextButton() {
		return nextButton;
	}
	
	public JButton getCancelButton() {
		return canceledButton;
	}

	public FileKindSelection getFileKindSelection() {
		return this.fileKindSelection;
	}
	
	public ScanningProgress getScanningProgress() {
		return this.scanningProgress;
	}

	public void setScanningProgress(ScanningProgress scanningProgress) {
		this.scanningProgress = scanningProgress;
	}
	

	public void replaceContent(JPanel oldContent, JPanel newContent) {
		this.getContentPane().remove(oldContent);
		this.getContentPane().add(newContent);
		WindowContent.revalidateContent(this.getContentPane());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if(source instanceof JButton) {
			if(source.equals(this.canceledButton)) {
				if(this.getContentPane().isAncestorOf(this.scanningProgress)) {
					if (this.scanningProgress.stopProcess()) {
						this.scanningProgress.getGeneralTask().cancel(true);
						this.dispose();
					}
				} else {
					this.dispose();
				}
			} else if(source.equals(this.nextButton)) {
				this.navigation.get(this.navigator.previousIndex()).getNextScreen(); 
			} else if(source.equals(this.previousButton)) {
				if(this.getContentPane().isAncestorOf(this.scanningProgress)) {
					if (this.scanningProgress.stopProcess()) {
						this.scanningProgress.getGeneralTask().cancel(true);
						this.navigation.get(this.navigator.previousIndex()).getPreviousScreen(); 
						this.navigator.previous();
					}
				} else {
					this.navigation.get(this.navigator.previousIndex()).getPreviousScreen(); 
					this.navigator.previous();
				}
			}
		} else if(source instanceof JToggleButton) {
			WindowContent.getCurrentButtonLanguage().setSelected(false);
			((JToggleButton) source).setSelected(true);
			WindowContent.setCurrentButtonLanguage((JToggleButton) source);
			if(source.equals(this.italianButton)) {
				WindowContent.setLanguage(Locale.ITALIAN);
				WindowContent.changeTextInAnotherLanguage(this.componentsWithText, ResourceBundle.getBundle("texts/MainWindow", Locale.ITALIAN));
				this.navigation.get(this.navigator.previousIndex()).setToItalian();
			} else if(source.equals(this.germanButton)) {
				WindowContent.setLanguage(Locale.GERMAN);
				WindowContent.changeTextInAnotherLanguage(this.componentsWithText, ResourceBundle.getBundle("texts/MainWindow", Locale.GERMAN));
				this.navigation.get(this.navigator.previousIndex()).setToGerman();
			} else if(source.equals(this.spanishButton)) {
				WindowContent.setLanguage(WindowContent.getEsp());
				WindowContent.changeTextInAnotherLanguage(this.componentsWithText, ResourceBundle.getBundle("texts/MainWindow", WindowContent.getEsp()));
				this.navigation.get(this.navigator.previousIndex()).setToSpanish();
			} else if(source.equals(this.frenchButton)) {
				WindowContent.setLanguage(Locale.FRENCH);
				WindowContent.changeTextInAnotherLanguage(this.componentsWithText, ResourceBundle.getBundle("texts/MainWindow", Locale.FRENCH));
				this.navigation.get(this.navigator.previousIndex()).setToFrench();
			} else if(source.equals(this.englishButton)) {
				WindowContent.setLanguage(Locale.ENGLISH);
				WindowContent.changeTextInAnotherLanguage(this.componentsWithText, ResourceBundle.getBundle("texts/MainWindow", Locale.ENGLISH));
				this.navigation.get(this.navigator.previousIndex()).setToEnglish();
			} 
		}
	}
}