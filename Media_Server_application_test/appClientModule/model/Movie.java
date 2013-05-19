package model;

import java.util.Date;

import model.base.DatabaseActionsImplemented;

public class Movie extends Document implements model.base.DatabaseActions {
	
	/** Id of the movie **/
	Integer idMovie;
	/** Id of the season **/
	Integer idSeason;
	/** Id of the content advisory **/
	Integer idContentAdvisory;
	/** Name of the movie **/
	String movieName;
	/** Date of the movie's release **/
	Date releaseDate;
	/** Synopsis of the movie **/
	String synopsis;
	/** Rating of the movie **/
	Integer movieRating;
	/** True if the movie has subtitles **/
	Boolean subtitles;
	
	
	public Movie(String documentName, String documentPath) {
		super(documentName, documentPath);
	}

	@Override
	public void insert() {
		//DatabaseActionsImplemented.insert(tableName, fieldsWithValues);
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void select(String... test) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete() {
		// TODO Auto-generated method stub
		
	}

}
