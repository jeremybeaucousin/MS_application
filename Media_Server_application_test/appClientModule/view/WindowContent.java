package view;

import java.awt.LayoutManager;

import javax.swing.JPanel;

public abstract class WindowContent extends JPanel implements WindowContentAction{
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
}
