package view;

import java.awt.Component;
import java.awt.Container;
import java.awt.LayoutManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.border.TitledBorder;

import util.StringUtils;


public abstract class WindowContent extends JPanel implements WindowContentAction, ConstantView {
	private MainWindow mainWindow;
	
	private final static Locale esp = new Locale("es", "ES");
	
	private static Locale language = Locale.ENGLISH;
	
	private ResourceBundle texts;

	private static JToggleButton currentButtonLanguage;
	
	public void setComponentsWithText(
			HashMap<Object, HashMap<String, String>> componentsWithText) {
		this.componentsWithText = componentsWithText;
	}

	/** Contains all components which have text displayed on screen **/
	private HashMap<Object, HashMap<String, String>> componentsWithText = new HashMap<Object, HashMap<String, String>>();
	
	public WindowContent(MainWindow mainWindow) {
		super();
		this.mainWindow = mainWindow;
	}

	public WindowContent(LayoutManager layout, MainWindow mainWindow) {
		super(layout);
		this.mainWindow = mainWindow;
	}

	public WindowContent(boolean isDoubleBuffered, MainWindow mainWindow) {
		super(isDoubleBuffered);
		this.mainWindow = mainWindow;
	}

	public WindowContent(LayoutManager layout, boolean isDoubleBuffered,
			MainWindow mainWindow) {
		super(layout, isDoubleBuffered);
		this.mainWindow = mainWindow;
	}

	public MainWindow getMainWindow() {
		return mainWindow;
	}
	
	public static Locale getEsp() {
		return esp;
	}

//	public static String getDefaultlanguage() {
//		return defaultLanguage;
//	}

	public static Locale getLanguage() {
		return language;
	}

	public static void setLanguage(Locale language) {
		WindowContent.language = language;
	}

	public ResourceBundle getTexts() {
		return texts;
	}

	public void setTexts(ResourceBundle texts) {
		this.texts = texts;
	}

	public static JToggleButton getCurrentButtonLanguage() {
		return currentButtonLanguage;
	}
	
	public static void setCurrentButtonLanguage(JToggleButton currentButtonLanguage) {
		WindowContent.currentButtonLanguage = currentButtonLanguage;
	}
	
	public HashMap<Object, HashMap<String, String>> getComponentsWithText() {
		return componentsWithText;
	}
	
	public static void changeTextInAnotherLanguage(HashMap<Object, String> componentsWithText, ResourceBundle texts) {
		for(Object component : componentsWithText.keySet()) {
			if(component instanceof JLabel) {
				((JLabel) component).setText(texts.getString(componentsWithText.get(component)));
			} else if(component instanceof JButton) {
				((JButton) component).setText(texts.getString(componentsWithText.get(component)));
			} else if(component instanceof JCheckBox) {
				((JCheckBox) component).setText(texts.getString(componentsWithText.get(component)));
			} else if(component instanceof JEditorPane) {
				((JEditorPane) component).setText(texts.getString(componentsWithText.get(component)));
			} else if(component instanceof TitledBorder) {
				((TitledBorder) component).setTitle(texts.getString(componentsWithText.get(component)));
			} else if(component instanceof JTextField) {
				if(texts.getString(componentsWithText.get(component)).equals(((JTextField) component).getText()) || ((JTextField) component).getText().equals(StringUtils.EMPTY)) {
					((JTextField) component).setText(texts.getString(componentsWithText.get(component)));
				}
			} else if(component instanceof JFileChooser) {
				((JFileChooser) component).setDialogTitle(texts.getString(componentsWithText.get(component)));
			}
		}
	}
	
	public void changeTextInAnotherLanguage(HashMap<Object, String> componentsWithText) {
		ResourceBundle oldTexts = this.texts;
		this.texts = ResourceBundle.getBundle("texts/FileKindSelection", WindowContent.getLanguage());
		for(Object component : componentsWithText.keySet()) {
			if(component instanceof JLabel) {
				((JLabel) component).setText(this.texts.getString(componentsWithText.get(component)));
			} else if(component instanceof JButton) {
				((JButton) component).setText(this.texts.getString(componentsWithText.get(component)));
			} else if(component instanceof JCheckBox) {
				((JCheckBox) component).setText(this.texts.getString(componentsWithText.get(component)));
			} else if(component instanceof JEditorPane) {
				((JEditorPane) component).setText(this.texts.getString(componentsWithText.get(component)));
			} else if(component instanceof TitledBorder) {
				((TitledBorder) component).setTitle(this.texts.getString(componentsWithText.get(component)));
			} else if(component instanceof JTextField) {
				if(oldTexts.getString(componentsWithText.get(component)).equals(((JTextField) component).getText()) || ((JTextField) component).getText().equals(StringUtils.EMPTY)) {
					((JTextField) component).setText(this.texts.getString(componentsWithText.get(component)));
				}
			} else if(component instanceof JFileChooser) {
				((JFileChooser) component).setDialogTitle(this.texts.getString(componentsWithText.get(component)));
			}
		}
	}
	
	public static void revalidateContent(Container container) {
		container.invalidate(); 
		container.validate();
		container.repaint();
	}

}
