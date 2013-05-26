package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.border.MatteBorder;


public class MainWindow extends JFrame implements ActionListener, ConstantView {
	// BUTTONS //
	/** Button use to change Languages **/
	private JToggleButton italianButton, germanButton, spanishButton, frenchButton, englishButton ;
	
	/** Button use to quit the application **/
	private JButton canceledButton;
	
	/** Button use to return to the previous screen **/
	private JButton previousButton;
	
	/** Button use to go to the next screen **/
	private JButton nextButton;
	
	/** Button use to when the database is fill **/
	private JButton finishButton;
	
	// textes //
	/** Contains all components which have text displayed on screen **/
	private HashMap<Object, HashMap<String, String>> componentsWithText = new HashMap<Object, HashMap<String, String>>();
	
	private final HashMap<String, String> TitleTexts = new HashMap<String, String>() {
		{
			put(IT, "Super applicazione di server multimedia");
			put(DE, "TODO");
			put(ES, "Súper aplicación de servidor multimedia");
			put(FR, "Super application de serveur multimédia");
			put(EN, "Super multimedia server application");
		}			
	};

	private final HashMap<String, String> canceledButtonTexts = new HashMap<String, String>() {
		{
			put(IT, "Annula");
			put(DE, "TODO");
			put(ES, "Cancelar");
			put(FR, "Annuler");
			put(EN, "Cancel");
		}			
	};
	
	private final HashMap<String, String> previousButtonTexts = new HashMap<String, String>() {
		{
			put(IT, "Indietro");
			put(DE, "TODO");
			put(ES, "Atrás");
			put(FR, "Précédent");
			put(EN, "Previous");
		}			
	};
	
	private final HashMap<String, String> nextButtonTexts = new HashMap<String, String>() {
		{
			put(IT, "Avanti");
			put(DE, "TODO");
			put(ES, "Siguiente");
			put(FR, "Suivant");
			put(EN, "Next");
		}			
	};
	
	private final HashMap<String, String> finishButtonTexts = new HashMap<String, String>() {
		{
			put(IT, "Termina");
			put(DE, "TODO");
			put(ES, "Aceptar");
			put(FR, "Terminer");
			put(EN, "Finish");
		}			
	};
	
	// navigation //
	/** Main content. Its this content which change while navigation between screens **/
	private JPanel panelBase;
	
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
		
//		Double screenWidth = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
//		Double screenHeight = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		
		this.setResizable(false);
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("img/multimedia-icone.png")));
		this.setBounds(100, 100, 583, 486);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setLayout(null);
		this.setLocationRelativeTo(null);
		
		JLabel lblSuperAplieServeur = new JLabel(this.TitleTexts.get(WindowContent.getDefaultlanguage()));
		this.componentsWithText.put(lblSuperAplieServeur, this.TitleTexts);
		lblSuperAplieServeur.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblSuperAplieServeur.setBounds(10, 12, 242, 14);
		getContentPane().add(lblSuperAplieServeur);
		
		
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
		this.panelBase = this.fileKindSelection;
		this.getContentPane().add(this.panelBase);
		
		this.canceledButton =  new JButton(this.canceledButtonTexts.get(WindowContent.getDefaultlanguage()));
		this.componentsWithText.put(this.canceledButton, this.canceledButtonTexts);
		this.canceledButton.setBounds(10, 414, 89, 23);
		this.canceledButton.addActionListener(this);
		this.getContentPane().add(this.canceledButton);
		
		this.previousButton = new JButton(this.previousButtonTexts.get(WindowContent.getDefaultlanguage()));
		this.componentsWithText.put(this.previousButton, this.previousButtonTexts);
		this.previousButton.setEnabled(false);
		this.previousButton.setBounds(281, 414, 89, 23);
		this.previousButton.addActionListener(this);
		this.getContentPane().add(this.previousButton);
		
		this.nextButton =  new JButton(this.nextButtonTexts.get(WindowContent.getDefaultlanguage()));
		this.componentsWithText.put(this.nextButton, this.nextButtonTexts);
		this.nextButton.setBounds(380, 414, 89, 23);
		this.nextButton.addActionListener(this);
		this.getContentPane().add(this.nextButton);
		
		this.finishButton = new JButton(this.finishButtonTexts.get(WindowContent.getDefaultlanguage()));
		this.componentsWithText.put(this.finishButton, this.finishButtonTexts);
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

	public FileKindSelection getFileKindSelection() {
		return this.fileKindSelection;
	}
	
	public ScanningProgress getScanningProgress() {
		return this.scanningProgress;
	}

	public void setScanningProgress(ScanningProgress scanningProgress) {
		this.scanningProgress = scanningProgress;
	}
	

	public void replaceContent(JComponent oldContent, JComponent newContent) {
		this.getContentPane().remove(oldContent);
		this.getContentPane().add(newContent);
		WindowContent.revalidateContent(this.getContentPane());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if(source instanceof JButton) {
			if(source.equals(this.canceledButton)) {
				this.dispose();
			} else if(source.equals(this.nextButton)) {
				this.navigation.get(this.navigator.previousIndex()).getNextScreen(); 
			} else if(source.equals(this.previousButton)) {
				this.navigation.get(this.navigator.previousIndex()).getPreviousScreen(); 
				this.navigator.previous();
			}
		} else if(source instanceof JToggleButton) {
			WindowContent.getCurrentButtonLanguage().setSelected(false);
			((JToggleButton) source).setSelected(true);
			WindowContent.setCurrentButtonLanguage((JToggleButton) source);
			if(source.equals(this.italianButton)) {
				WindowContent.setCurrentLanguage(IT);
				WindowContent.changeTextInAnotherLanguage(this.componentsWithText, IT);
				this.navigation.get(this.navigator.previousIndex()).setToItalian();
			} else if(source.equals(this.germanButton)) {
				WindowContent.setCurrentLanguage(DE);
				WindowContent.changeTextInAnotherLanguage(this.componentsWithText, DE);
				this.navigation.get(this.navigator.previousIndex()).setToGerman();
			} else if(source.equals(this.spanishButton)) {
				WindowContent.setCurrentLanguage(ES);
				WindowContent.changeTextInAnotherLanguage(this.componentsWithText, ES);
				this.navigation.get(this.navigator.previousIndex()).setToSpanish();
			} else if(source.equals(this.frenchButton)) {
				WindowContent.setCurrentLanguage(FR);
				WindowContent.changeTextInAnotherLanguage(this.componentsWithText, FR);
				this.navigation.get(this.navigator.previousIndex()).setToFrench();
			} else if(source.equals(this.englishButton)) {
				WindowContent.setCurrentLanguage(EN);
				WindowContent.changeTextInAnotherLanguage(this.componentsWithText, EN);
				this.navigation.get(this.navigator.previousIndex()).setToEnglish();
			} 
		}
	}
}