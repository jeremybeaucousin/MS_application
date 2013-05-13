package Model;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONTokener;

import util.JSONObject;
import util.StringUtil;

public class ImdbApi {
	private final static String IMDB_API = "http://imdbapi.org/";
	
	public static JSONArray searchMovie(String movieName) throws JSONException, IOException {
		
		JSONArray movie = null; 
        movieName = StringUtil.transformSpecialsHTTPCharacterToSpace(movieName);
   
        URL urlImdbApiSearching = buildURL("title=" + movieName);	
        
        HttpURLConnection httpConn = (HttpURLConnection) urlImdbApiSearching.openConnection();
        httpConn.setRequestProperty("User-Agent", "Mozilla/4.5"); 
        if (httpConn.getResponseCode() >= 400) {
        	System.out.println(httpConn.getErrorStream());
        } else {
        	JSONTokener response = new JSONTokener(httpConn.getInputStream());
            char fisrtChar = response.next();
            response.back();
			if(fisrtChar == '[') {
				movie = new JSONArray(response);
				System.out.println("trouvé");
            } else if (fisrtChar == '{') {
            	System.out.println("non trouvé");
            }
            	
        }

		return movie;
	}         
	 
	public static JSONArray searchMovieStudying(String movieName) throws JSONException, IOException {

		System.out.println("nom Original :" + movieName);

		//JSONArray movie = searchMovie(movieName); 
		JSONArray movie = null;
		//if(movie == null) {
			movieName = StringUtil.transformSpecialsCharacterToSpace(movieName);
			movieName = StringUtil.deleteSurroudParts(movieName);
			movieName = StringUtil.supresseMutlipleSpace(movieName);
			movie = searchMovie(StringUtil.transformSpecialsCharacterToSpace(movieName));
			System.out.println("nom Modifié :" + movieName);
		//}
		
		return movie;
	}
	
	private static URL buildURL(String request) {
		URL urlTheMovieDbSearching = null;

		try {
			URI uri = new URI(null, null, IMDB_API, request + "&type=json&plot=full&episode=0&limit=1&yg=0&mt=M&lang=en-US&offset=&aka=simple&release=simple&business=0&tech=0", null);
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
