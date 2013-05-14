import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JFileChooser;

import org.json.JSONArray;
import org.json.JSONException;

import util.ConstantString;
import util.JSONObject;
import util.StringUtil;

import Model.Document;
import Model.ImdbApi;
import Model.TheMovieDB;

public class Main{
	
	public static void main(String[] args) throws JSONException, IOException {
		int cpt=0;
		
		JFileChooser chooser = new JFileChooser();
		
		ArrayList<File> test = null;
		
		chooser.setDialogTitle("S�lectionner votre dossier de vid�o");
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		chooser.setAcceptAllFileFilterUsed(false);
	
		/* � coder
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
		if(chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			chooser.setCurrentDirectory(chooser.getSelectedFile());
			test = Document.FolderScannerVideo(chooser.getCurrentDirectory());
		}
		
		if(test != null) {
			for(int ii = 0; ii < test.size(); ii++) {
				Object movie = ImdbApi.searchMovieStudying(Document.getDocumentName(test.get(ii).getName()));
				if(movie instanceof JSONArray) {
					System.out.println(movie);
				} else if(movie instanceof JSONObject) {
					System.out.println(movie);
				}
				System.out.println(new DecimalFormat("###.##").format(new Double(ii)/test.size()*100) + "%");
				if(movie == null ) {
					cpt++;
				}
			}
			System.out.println("total de film : " + test.size());
			System.out.println("total reconnus : " + (test.size() - cpt));
			System.out.println("total non reconnus : " + (cpt));
			System.out.println("Ratio de r�ussite : " + (new DecimalFormat("###.##").format(new Double((test.size() - cpt))/test.size()*100) + "%"));
		}	
		
		// FOR THE MOVIE DB		
		if(chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			chooser.setCurrentDirectory(chooser.getSelectedFile());
			test = Document.FolderScannerVideo(chooser.getCurrentDirectory());
		}
		if(test != null) {
			for(int ii = 0; ii < test.size(); ii++) {
				Integer movieId = TheMovieDB.searchMovieStudying(Document.getDocumentName(test.get(ii).getName()));
				System.out.println(new DecimalFormat("###.##").format(new Double(ii)/test.size()*100) + "%");
				if(movieId < 0 ) {
					cpt++;
				} else {
					System.out.println(TheMovieDB.getMovie(movieId).get("original_title"));
				}
			}
			System.out.println("total de film : " + test.size());
			System.out.println("total reconnus : " + (test.size() - cpt));
			System.out.println("total non reconnus : " + (cpt));
			System.out.println("Ratio de r�ussite : " + (new DecimalFormat("###.##").format(new Double((test.size() - cpt))/test.size()*100) + "%"));
		}
		
		
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