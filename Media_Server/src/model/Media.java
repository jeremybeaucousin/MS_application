package model;

import java.sql.ResultSet;
import java.sql.SQLException;

import base.Connexion;

public class Media implements base.MediaTable{
	public static String VIDEO = "Video";
	
	public static int getMediaID(String mediaType) {
		int mediaID = -1;
		Connexion cn=new Connexion();
		cn.connect();
		ResultSet rs=cn.select("SELECT " + ID_MEDIA + " FROM " + TABLE + " WHERE " + MEDIA_WORDING + " LIKE '" + mediaType + "';");
		System.out.println("SELECT " + ID_MEDIA + " FROM " + TABLE + " WHERE " + MEDIA_WORDING + " LIKE '" + mediaType + "';");
		try {
			while(rs.next())
			{
				mediaID=rs.getInt(ID_MEDIA);
			}
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		cn.close();
		return mediaID;
	}

}
