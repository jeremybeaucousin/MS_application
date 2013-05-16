import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.json.JSONException;

public class Main{
	
	public static void main(String[] args) throws JSONException, IOException {
		int cpt=0;
		JFileChooser chooser = new JFileChooser();
		ArrayList<File> filesList = null;
		chooser.setDialogTitle("Sélectionner votre dossier de vidéo");
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		chooser.setAcceptAllFileFilterUsed(false);
		
		JDialog dialog = new JDialog(); 
		dialog.setTitle("Media Server Application"); //On donne un titre à l'application
		dialog.setSize(300, 200);//On lui donne une taille pour qu'on puisse la voir
		dialog.setVisible(true);//On la rend visible
		dialog.setLocationRelativeTo(null);//On centre la fenêtre sur l'écran
		dialog.setResizable(false); //On interdit la redimensionnement de la fenêtre
		dialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); //On dit à l'application de se fermer lors du clic sur la croix

		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		
		gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints.weighty = 1;
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		JLabel label = new JLabel("Quel média voulez-vous scannez : ");
		panel.add(label, gridBagConstraints);
		
		gridBagConstraints.fill = GridBagConstraints.CENTER;
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 1;
		JCheckBoxMenuItem checkbox1 = new JCheckBoxMenuItem();
		checkbox1.setText("Film");
		panel.add(checkbox1, gridBagConstraints);
		
		gridBagConstraints.fill = GridBagConstraints.CENTER;
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 2;
		JCheckBoxMenuItem checkbox2 = new JCheckBoxMenuItem();
		checkbox2.setText("Série");
		panel.add(checkbox2, gridBagConstraints);
		
		gridBagConstraints.fill = GridBagConstraints.CENTER;
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 3;
		JCheckBoxMenuItem checkbox3 = new JCheckBoxMenuItem();
		checkbox3.setText("Musique");
		panel.add(checkbox3, gridBagConstraints);
		
		gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 4;
		JButton bouton = new JButton("Cliquez ici !");
		panel.add(bouton, gridBagConstraints);
		
		dialog.setContentPane(panel);
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

	/* (non-Java-doc)
	 * @see java.lang.Object#Object()
	 */
	public Main() {
		super();
	}
}