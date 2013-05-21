package view;

import java.awt.LayoutManager;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JPanel;

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
}
