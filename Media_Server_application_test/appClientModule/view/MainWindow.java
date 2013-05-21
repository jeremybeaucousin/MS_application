package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ListIterator;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.border.MatteBorder;

import model.views.ConstantView;

public class MainWindow extends JFrame implements ActionListener, ConstantView {
	// BUTTONS //
	/** Button use to change Language in French **/
	private JToggleButton frenchButton;
	
	/** Button use to change Language in English **/
	private JToggleButton englishButton;
	
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
			put(FR, "Super Applie Serveur Multimédia");
			put(EN, "Super Multimedia  Server Apply");
		}			
	};

	private final HashMap<String, String> canceledButtonTexts = new HashMap<String, String>() {
		{
			put(FR, "Annuler");
			put(EN, "Cancel");
		}			
	};
	
	private final HashMap<String, String> previousButtonTexts = new HashMap<String, String>() {
		{
			put(FR, "Précédent");
			put(EN, "Previous");
		}			
	};
	
	private final HashMap<String, String> nextButtonTexts = new HashMap<String, String>() {
		{
			put(FR, "Suivant");
			put(EN, "Next");
		}			
	};
	
	private final HashMap<String, String> finishButtonTexts = new HashMap<String, String>() {
		{
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
	private SearchingOnSelectedValues searchingOnSelectedValue;
	
	public MainWindow() throws IOException {
		super();
		this.navigation = new ArrayList<WindowContent>();
		this.navigator = navigation.listIterator();
		
//		Double screenWidth = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
//		Double screenHeight = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		
		this.setResizable(false);
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("img/multimedia-icone.png")));
		this.setBounds(100, 100, 583, 486);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.getContentPane().setLayout(null);
		this.setLocationRelativeTo(null);
		
		JLabel lblSuperAplieServeur = new JLabel(this.TitleTexts.get(EN));
		this.componentsWithText.put(lblSuperAplieServeur, this.TitleTexts);
		lblSuperAplieServeur.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblSuperAplieServeur.setBounds(10, 12, 242, 14);
		getContentPane().add(lblSuperAplieServeur);
		
		this.frenchButton = new JToggleButton("");
		this.frenchButton.setSelectedIcon(new ImageIcon(ImageIO.read(getClass().getClassLoader().getResource("img/france_flag_32.png"))));
		this.frenchButton.setIcon(new ImageIcon(ImageIO.read(getClass().getClassLoader().getResource("img/france_flag_32.png"))));
		this.frenchButton.setBounds(478, 9, 40, 23);
		this.frenchButton.addActionListener(this);
		this.getContentPane().add(this.frenchButton);
		
		this.englishButton = new JToggleButton("");
		this.englishButton.setSelected(true);
		this.englishButton.setSelectedIcon(new ImageIcon(ImageIO.read(getClass().getClassLoader().getResource("img/United-Kingdom-flag.png"))));
		this.englishButton.setIcon(new ImageIcon(ImageIO.read(getClass().getClassLoader().getResource("img/United-Kingdom-flag.png"))));
		this.englishButton.setBounds(528, 9, 40, 23);
		this.englishButton.addActionListener(this);
		this.getContentPane().add(this.englishButton);
		
		this.fileKindSelection = new FileKindSelection(this);
		this.navigator.add(this.fileKindSelection);
		
		// main panel
		this.panelBase = this.fileKindSelection;
		this.panelBase.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		this.panelBase.setBackground(Color.WHITE);
		this.panelBase.setBounds(10, 37, 558, 356);
		this.getContentPane().add(this.panelBase);
		this.panelBase.setLayout(null);
		
		this.canceledButton =  new JButton(this.canceledButtonTexts.get(EN));
		this.componentsWithText.put(this.canceledButton, this.canceledButtonTexts);
		this.canceledButton.setBounds(10, 414, 89, 23);
		this.canceledButton.addActionListener(this);
		this.getContentPane().add(this.canceledButton);
		
		this.previousButton = new JButton(this.previousButtonTexts.get(EN));
		this.componentsWithText.put(this.previousButton, this.previousButtonTexts);
		this.previousButton.setEnabled(false);
		this.previousButton.setBounds(281, 414, 89, 23);
		this.previousButton.addActionListener(this);
		this.getContentPane().add(this.previousButton);
		
		this.nextButton =  new JButton(this.nextButtonTexts.get(EN));
		this.componentsWithText.put(this.nextButton, this.nextButtonTexts);
		this.nextButton.setBounds(380, 414, 89, 23);
		this.nextButton.addActionListener(this);
		this.getContentPane().add(this.nextButton);
		
		this.finishButton = new JButton(this.finishButtonTexts.get(EN));
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

	
	public SearchingOnSelectedValues getSearchingOnSelectedValue() {
		return searchingOnSelectedValue;
	}

	public void setSearchingOnSelectedValue(
			SearchingOnSelectedValues searchingOnSelectedValue) {
		this.searchingOnSelectedValue = searchingOnSelectedValue;
	}
	

	public void replaceContent(JComponent oldContent, JComponent newContent) {
		// TODO create Méthode
		this.getContentPane().remove(oldContent);
		this.getContentPane().add(newContent);
		WindowContent.revalidateContent(this.getContentPane());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if(source.equals(this.canceledButton)) {
			this.dispose();
		} else if(source.equals(this.nextButton)) {
			this.navigation.get(this.navigator.previousIndex()).getNextScreen(); 
		} else if(source.equals(this.previousButton)) {
			this.navigator.previous();
			this.navigation.get(this.navigator.previousIndex()).getPreviousScreen(); 
		} else if(source.equals(this.frenchButton)) {
			this.englishButton.setSelected(false);
			WindowContent.changeTextInAnotherLanguage(this.componentsWithText, FR);
			this.navigation.get(this.navigator.previousIndex()).setToFrench();
		} else if(source.equals(this.englishButton)) {
			this.frenchButton.setSelected(false);
			WindowContent.changeTextInAnotherLanguage(this.componentsWithText, EN);
			this.navigation.get(this.navigator.previousIndex()).setToEnglish();
		} 
	}
}
