package view;

import java.awt.LayoutManager;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import model.views.ConstantView;

public abstract class WindowContent extends JPanel implements WindowContentAction, ConstantView {
	private MainWindow mainWindow;
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
	
	public static HashMap<String, String> setComponentsTexts(String textInFrench, String TextInEnglish) {
		HashMap<String, String> componentTexts = new HashMap<String, String>();
		componentTexts.put(FR, textInFrench);
		componentTexts.put(EN, TextInEnglish);
		return componentTexts;
	}
	
	public static void changeTextInAnotherLanguage(HashMap<Object, HashMap<String, String>> componentsWithText, String language) {
		for(Object component : componentsWithText.keySet()) {
			if(component instanceof JLabel) {
				((JLabel) component).setText(componentsWithText.get(component).get(language));
			} else if(component instanceof JButton) {
				((JButton) component).setText(componentsWithText.get(component).get(language));
			} else if(component instanceof JEditorPane) {
				((JEditorPane) component).setText(componentsWithText.get(component).get(language));
			} else if(component instanceof TitledBorder) {
				((TitledBorder) component).setTitle(componentsWithText.get(component).get(language));
			}
		}
	}

}
