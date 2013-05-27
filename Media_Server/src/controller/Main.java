package controller;
import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.WindowConstants;

import model.base.HSQLInteraction;

import org.apache.commons.io.IOUtils;
import org.hsqldb.server.Server;

import view.MainWindow;


public class Main {

	public Main() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param args
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws IOException 
	 */
	public static void main(String[] args) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException, IOException{
		// TODO Dynamic web project ryby on rails
		
		int cpt=0;
		JFileChooser chooser = new JFileChooser();
		ArrayList<File> filesList = null;
		chooser.setDialogTitle("Sélectionner votre dossier de vidéo");
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		chooser.setAcceptAllFileFilterUsed(false);
		
//		JFrame frame = new JFrame();
//		frame.setLayout(new FlowLayout());
//		frame.setLocationRelativeTo(null);
//		frame.setSize(200, 200);
//		JProgressBar progressBar = new JProgressBar();
//		progressBar.setMaximum(0);
//		progressBar.setMaximum(150000000);
//		progressBar.setStringPainted(true);
//		frame.add(progressBar);
//		frame.setVisible(true);
//
//		for(int ii = 0; ii <= 150000000; ii++) {
//			progressBar.setValue(ii);
//		}

		final MainWindow mainWindow = new MainWindow();
	
		
		// TODO Exemple Select
//		HSQLInteraction hsqlDatase = new HSQLInteraction();
//		hsqlDatase.start();
//		Statement statement = hsqlDatase.getStatement();
//		ResultSet result = null;
//		//statement.executeUpdate("INSERT INTO PUBLIC.CONTENT_ADVISORY (ID_CONTENT_ADVISORY, CONTENT_ADVISORY_WORDING ) VALUES (2 , '-16');");
//		result = statement.executeQuery("SELECT ID_COUNTRY, COUNTRY_WORDING FROM MEDIA_SERVER.COUNTRY;");
//		JFrame jframe = new JFrame();
//		jframe.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
//		jframe.setLocationRelativeTo(null);
//		jframe.setSize(300, 300);
//		jframe.setLayout(new FlowLayout());
//		JLabel jlabel = new JLabel("test");
//		jlabel.setLayout(new FlowLayout());
//		jframe.add(jlabel);
//		if(result != null) {
//			while(result.next()) {
//				JLabel jlabel2 = new JLabel(result.getInt("ID_COUNTRY") + " " + result.getString("COUNTRY_WORDING"));
//				jlabel2.setLayout(new FlowLayout());
//				jframe.add(jlabel2);
//			}
//		}
//		jframe.setVisible(true);	
//		hsqlDatase.stop();
		
//        //Create and set up the window.
//        JFrame frame = new JFrame("TopLevelDemo");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//        //Create the menu bar.  Make it have a green background.
//        JMenuBar greenMenuBar = new JMenuBar();
//        greenMenuBar.setOpaque(true);
//        //greenMenuBar.setBackground(new Color(154, 165, 127));
//        greenMenuBar.setPreferredSize(new Dimension(200, 20));
//
//        //Create a yellow label to put in the content pane.
//        JLabel yellowLabel = new JLabel();
//        yellowLabel.setOpaque(true);
//        //yellowLabel.setBackground(new Color(248, 213, 131));
//        yellowLabel.setPreferredSize(new Dimension(200, 180));
//
//        //Set the menu bar and add the label to the content pane.
//        frame.setJMenuBar(greenMenuBar);
//        frame.getContentPane().add(yellowLabel, BorderLayout.CENTER);
//
//        //adding progress bar
//        JProgressBar progressBar = new JProgressBar(0, 100);
//        progressBar.setValue(0);
//        progressBar.setStringPainted(true);
//        frame.add(progressBar);
//		
//        //Display the window.
//        frame.pack();
//        frame.setVisible(true);
		
		/* à coder
		JSONObject film = TheMovieDB.getMovie(880);
		JSONArray keyset = film.names();
		System.out.println(keyset);
		System.out.println("**************************");
		JSONObject.jsonBrowser(film);
		System.out.println("**************************");	
		
		System.out.println(keyset);
		Hashtable<String, Object> ObjectMap = film.jsonToHachtable();
		Set<String> objectMapKey = ObjectMap.keySet();
		for(Object object : objectMapKey) {
			if(ObjectMap.get(object) instanceof String) {
				System.out.println("String : " + object + " " + ObjectMap.get(object));
			} else if(ObjectMap.get(object) instanceof Integer) {
				System.out.println("Integer : " + object + " "  + ObjectMap.get(object));
			} else if(ObjectMap.get(object) instanceof Double) {
				System.out.println("Double : " + object + " "  + ObjectMap.get(object));
			} else if(ObjectMap.get(object) instanceof Boolean) {
				System.out.println("Boolean : " + object + " "  + ObjectMap.get(object));
			} else if(ObjectMap.get(object).getClass().isAssignableFrom(Object[].class)) {
				Object[] array = (Object[]) ObjectMap.get(object);
				for(Object object1 : array) {
					System.out.println("Tableau : " + object + " "  + object1);
				}
			} else {
				System.out.println("Autre : " + ObjectMap.get(object));
			}
		}
		*/
		
		
		// System.out.println(Media.getMediaID(Media.VIDEO));
		
		// FOR THE IMDBAPI	
//		if(chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
//			chooser.setCurrentDirectory(chooser.getSelectedFile());
//			filesList = Document.FolderScannerVideo(chooser.getCurrentDirectory());
//		}
//		
//		if(filesList != null) {
//			for(int ii = 0; ii < filesList.size(); ii++) {
//				Movie movie = new Movie(filesList.get(ii).getName(), filesList.get(ii).getAbsolutePath());
//				Object responseMovie = ImdbApi.searchMovieStudying(movie);
//				if(responseMovie instanceof JSONArray) {
//					System.out.println(movie);
//				} else if(responseMovie instanceof JSONObject) {
//					System.out.println(movie);
//				}
//				System.out.println(new DecimalFormat("###.##").format(new Double(ii)/filesList.size()*100) + "%");
//				if(movie == null ) {
//					cpt++;
//				}
//			}
//			System.out.println("total de film : " + filesList.size());
//			System.out.println("total reconnus : " + (filesList.size() - cpt));
//			System.out.println("total non reconnus : " + (cpt));
//			System.out.println("Ratio de réussite : " + (new DecimalFormat("###.##").format(new Double((filesList.size() - cpt))/filesList.size()*100) + "%"));
//		}	
		
		// FOR THE MOVIE DB		
//		if(chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
//			chooser.setCurrentDirectory(chooser.getSelectedFile());
//			filesList = Document.FolderScannerVideo(chooser.getCurrentDirectory());
//		}
//		if(filesList != null) {
//			for(int ii = 0; ii < filesList.size(); ii++) {
//				Movie movie = new Movie(filesList.get(ii).getName(), filesList.get(ii).getAbsolutePath());
//				Integer movieId = TheMovieDB.searchMovieStudying(movie);
//				System.out.println(new DecimalFormat("###.##").format(new Double(ii)/filesList.size()*100) + "%");
//				if(movieId < 0 ) {
//					cpt++;
//				} else {
//					System.out.println(TheMovieDB.getMovie(movieId).get("original_title"));
//				}
//			}
//			
//			System.out.println("total de film : " + filesList.size());
//			System.out.println("total reconnus : " + (filesList.size() - cpt));
//			System.out.println("total non reconnus : " + (cpt));
//			System.out.println("Ratio de réussite : " + (new DecimalFormat("###.##").format(new Double((filesList.size() - cpt))/filesList.size()*100) + "%"));
//		}
		
		
		//request exemple http://api.themoviedb.org/3/search/movie?api_key=1acc7c1593ee8145d2d390f1d419a573&query=shrek
		//request exemple http://api.themoviedb.org/3/movie/15357?api_key=1acc7c1593ee8145d2d390f1d419a573&
		//http://d3gtl9l2a4fn1j.cloudfront.net/t/p/w185/eDUy9octF4WpC9IIfxek0ZZPMhT.jpg

	}

}
