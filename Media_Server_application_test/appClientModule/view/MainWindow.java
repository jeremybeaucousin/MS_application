package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.TreeMap;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class MainWindow extends JFrame implements ActionListener {
	private JPanel panelBase;
	private TreeMap<String, JButton> buttons;
	
	private ArrayList<WindowContent> navigation;
	private ListIterator<WindowContent> navigator;
	private FileKindSelection fileKindSelection;
	private SearchingOnSelectedValues searchingOnSelectedValue;
	
	public MainWindow() throws IOException {
		super();
		this.buttons = new TreeMap<String, JButton>();
		this.navigation = new ArrayList<WindowContent>();
		this.navigator = navigation.listIterator();

		this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("img/multimedia-icone.png")));
		this.setBounds(100, 100, 594, 486);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.getContentPane().setLayout(null);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		
		JPanel panelLanguages = new JPanel();
		panelLanguages.setBorder(null);
		panelLanguages.setBounds(515, 0, 63, 26);
		this.getContentPane().add(panelLanguages);
		
		JLabel lblEnglish = new JLabel("");
		lblEnglish.setIcon(new ImageIcon(ImageIO.read(getClass().getClassLoader().getResource("img/United-Kingdom-flag.png"))));
		panelLanguages.add(lblEnglish);
		
		JLabel lblFrench = new JLabel("");
		lblFrench.setHorizontalAlignment(SwingConstants.CENTER);
		lblFrench.setIcon(new ImageIcon(ImageIO.read(getClass().getClassLoader().getResource("img/france_flag_32.png"))));
		panelLanguages.add(lblFrench);
	
		JLabel lblSuperAplieServeur = new JLabel("Super Aplie Serveur Multimédia");
		lblSuperAplieServeur.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblSuperAplieServeur.setBounds(10, 12, 242, 14);
		this.getContentPane().add(lblSuperAplieServeur);
		
		this.fileKindSelection = new FileKindSelection(this);
		this.navigator.add(this.fileKindSelection);
		
		this.panelBase = this.fileKindSelection;
		this.panelBase.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		this.panelBase.setBackground(Color.WHITE);
		this.panelBase.setBounds(10, 37, 558, 356);

		this.getContentPane().add(panelBase);
		
		this.buttons.put("canceled", new JButton("Annuler"));
		JButton btnCancel = this.buttons.get("canceled");
		btnCancel.setBounds(10, 414, 89, 23);
		btnCancel.addActionListener(this);
		this.getContentPane().add(btnCancel);
		
		this.buttons.put("previous", new JButton("Précédent"));
		JButton btnPrevious = this.buttons.get("previous");
		btnPrevious.setEnabled(false);
		btnPrevious.setBounds(281, 414, 89, 23);
		btnPrevious.addActionListener(this);
		this.getContentPane().add(btnPrevious);
		
		this.buttons.put("next", new JButton("Suivant"));
		JButton btnNext = this.buttons.get("next");
		btnNext.setBounds(380, 414, 89, 23);
		btnNext.addActionListener(this);
		this.getContentPane().add(btnNext);
		
		this.buttons.put("finish", new JButton("Terminer"));
		JButton btnFinish = this.buttons.get("finish");
		btnFinish.setEnabled(false);
		btnFinish.setBounds(479, 414, 89, 23);
		btnFinish.addActionListener(this);
		this.getContentPane().add(btnFinish);
		
		this.setVisible(true);
		
	}
	
	public ArrayList<WindowContent> getNavigation() {
		return navigation;
	}

	public ListIterator<WindowContent> getNavigator() {
		return navigator;
	}

	public SearchingOnSelectedValues getSearchingOnSelectedValue() {
		return searchingOnSelectedValue;
	}

	public void setSearchingOnSelectedValue(
			SearchingOnSelectedValues searchingOnSelectedValue) {
		this.searchingOnSelectedValue = searchingOnSelectedValue;
	}
	

	public TreeMap<String, JButton> getButtons() {
		return buttons;
	}

	public void replaceContent(JComponent oldContent, JComponent newContent) {
		this.panelBase.remove(oldContent);
		this.panelBase.add(newContent);
		this.panelBase.invalidate(); 
		this.panelBase.validate();
		this.panelBase.repaint();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if(source == this.buttons.get("canceled")) {
			this.dispose();
		} else if(source == this.buttons.get("next")) {
			this.navigation.get(this.navigator.previousIndex()).getNextScreen(); 
		} else if(source == this.buttons.get("previous")) {
			this.navigator.previous();
			this.navigation.get(this.navigator.previousIndex()).getPreviousScreen(); 
		} 
	}

}
