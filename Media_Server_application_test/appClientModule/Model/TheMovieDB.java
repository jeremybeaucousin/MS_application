package Model;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import org.json.JSONException;
import org.json.JSONTokener;

import util.JSONObject;
import util.StringUtil;

public class TheMovieDB {
	private static String api_key = "api_key=1acc7c1593ee8145d2d390f1d419a573";
	private static String theMovieDBURL = "http://api.themoviedb.org/3";
	
	public static int searchMovie(String movieName) throws JSONException, IOException {
		
        int movieId = -1; 
        movieName = StringUtil.transformSpecialsHTTPCharacterToSpace(movieName);
        
        URL urlTheMovieDbSearching = buildURL("/search/movie", "&query=" + movieName);	
        System.out.println(urlTheMovieDbSearching);
        JSONObject searchMovieResult = new JSONObject(new JSONTokener(urlTheMovieDbSearching.openStream()));

		if(searchMovieResult.getJSONArray("results").length() > 0) {
			movieId = searchMovieResult.getJSONArray("results").getJSONObject(0).getInt("id");
		}
		
		return movieId;
	}
	
	public static int searchMovieStudying(String movieName) throws JSONException, IOException {

		System.out.println("nom Original :" + movieName);

        int movieId = searchMovie(movieName); 
        
		if(movieId == -1) {
			movieName = StringUtil.transformSpecialsCharacterToSpace(movieName);
			movieId = searchMovie(StringUtil.transformSpecialsCharacterToSpace(movieName));
			System.out.println("nom Modifié :" + movieName);
		}
		if(movieId == -1) {
			movieName = StringUtil.deleteSurroudParts(movieName);
			movieName = StringUtil.supresseMutlipleSpace(movieName);
			System.out.println("nom Modifié :" + movieName);
			movieId = searchMovie(StringUtil.deleteSurroudParts(movieName));
		}
		System.out.println(movieId);
		return movieId;
	}
	
	//en cours
	public static JSONObject getMovie(int movieID) throws JSONException, IOException {
		URL urlTheMovieDbSearching = buildURL("/movie/" + movieID, "");
		return new JSONObject(new JSONTokener(urlTheMovieDbSearching.openStream()));			
	}
	
	private static URL buildURL(String requestType, String request) {
		URL urlTheMovieDbSearching = null;
		try {
			URI uri = new URI(null, null, theMovieDBURL + requestType, api_key + request, null);
			try {
				urlTheMovieDbSearching = uri.toURL();
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}

		return urlTheMovieDbSearching;
	}
}
