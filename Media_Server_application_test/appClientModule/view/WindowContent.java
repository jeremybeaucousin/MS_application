package view;

import java.awt.Container;
import java.awt.LayoutManager;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;


public abstract class WindowContent extends JPanel implements WindowContentAction, ConstantView {
	private MainWindow mainWindow;
	
	/** Is the default language of the application **/
	private final static String defaultLanguage = EN;
	
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
	
	public static String getDefaultlanguage() {
		return defaultLanguage;
	}

	public HashMap<Object, HashMap<String, String>> getComponentsWithText() {
		return componentsWithText;
	}
	
	public static void changeTextInAnotherLanguage(HashMap<Object, HashMap<String, String>> componentsWithText, String language) {
		for(Object component : componentsWithText.keySet()) {
			if(component instanceof JLabel) {
				((JLabel) component).setText(componentsWithText.get(component).get(language));
			} else if(component instanceof JButton) {
				((JButton) component).setText(componentsWithText.get(component).get(language));
			} else if(component instanceof JCheckBox) {
				((JCheckBox) component).setText(componentsWithText.get(component).get(language));
			} else if(component instanceof JEditorPane) {
				((JEditorPane) component).setText(componentsWithText.get(component).get(language));
			} else if(component instanceof TitledBorder) {
				((TitledBorder) component).setTitle(componentsWithText.get(component).get(language));
			} else if(component instanceof JTextField) {
				((JTextField) component).setText(componentsWithText.get(component).get(language));
			}
		}
	}
	
	public static void revalidateContent(Container container) {
		container.invalidate(); 
		container.validate();
		container.repaint();
	}

}
