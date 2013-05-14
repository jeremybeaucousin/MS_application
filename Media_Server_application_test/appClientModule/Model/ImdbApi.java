package Model;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONTokener;

import util.ConstantString;
import util.JSONObject;
import util.StringUtil;

public class ImdbApi {
	private final static String IMDB_API = "http://imdbapi.org/";
	
	public static Object searchMovie(String movieName) throws JSONException, IOException {
		
		Object movie = null; 
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
            } else if (fisrtChar == '{') {
            	movie = new JSONObject(response);
            }
        }

		return movie;
	}         
	 
	public static Object searchMovieStudying(String movieName) throws JSONException, IOException {

		System.out.println("nom Original :" + movieName);

		//JSONArray movie = searchMovie(movieName); 
		Object movie = null;
		//if(movie == null) {
			movieName = StringUtil.transformSpecialsCharacterToSpace(movieName);
			movieName = StringUtil.deleteSurroudParts(movieName);
			movieName = StringUtil.supresseMutlipleSpace(movieName);
			movieName = StringUtil.insertSpaceBeforeCollapseUpperCaseOrInt(movieName);
			movie = searchMovie(StringUtil.transformSpecialsCharacterToSpace(movieName));
			System.out.println("nom Modifié :" + movieName);
		//}
		if(movie == null) {
			ArrayList<JSONArray> moviesFound = searchWordByWord(movieName);
			if(!moviesFound.isEmpty()) {
				movie = moviesFound.get((moviesFound.size()-1));
			}
		}
		if(movie instanceof JSONObject) {
			System.out.println(movie);
		} else {
			JSONArray responseMovie = (JSONArray) movie;
			System.out.println(movie);
		}

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
	
	//TODO 
	public static ArrayList<JSONArray> searchWordByWord(String movieName) throws JSONException, IOException {
		// TODO attantion au mot avec des entier dedans
		ArrayList<JSONArray> moviesFound = new ArrayList<JSONArray>();
		Pattern pattern = Pattern.compile("(\\w+)");
		Matcher matcher = pattern.matcher(movieName);
		StringBuffer wordAddedFromThemovieName = new StringBuffer();
		boolean MovieFound = true;
		while (matcher.find() && MovieFound) {
			wordAddedFromThemovieName.append(matcher.group() + ConstantString.SPACE);
			Object movie = searchMovie(wordAddedFromThemovieName.toString());
			System.out.println("nom Modifié :" + wordAddedFromThemovieName.toString());
			if(movie instanceof JSONArray) {
				moviesFound.add((JSONArray) movie);
			} else if(movie instanceof JSONObject && !moviesFound.isEmpty()) {
				MovieFound = false;
			}
			
		}
		return moviesFound;
	}
}
