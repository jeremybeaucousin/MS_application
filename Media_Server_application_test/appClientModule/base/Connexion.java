package base;


import java.sql.*;

public class Connexion 
{
	 /*** variable indiquant le nom de la base*/
	String base="media_server";
	String dbURL = "jdbc:mysql://192.168.1.34/"+base+"?user=root&password=";
	String dbDriver = "com.mysql.jdbc.Driver";
	private int lastInsertId=-1;
	private Connection dbCon;

	/****
	 * Constructeur vide
	 */
	public Connexion()
	{
	}
	
	public boolean connect()
	{
		try {
			Class.forName(dbDriver);
			try {
				dbCon = DriverManager.getConnection(dbURL);
			}
			catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();}
		return true;
	}

	public void close()
	{ 
		try {
			dbCon.close();
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ResultSet select(String sql)
	{ 
		Statement s;
		ResultSet r = null;
		try {
			s = dbCon.createStatement();
			r = s.executeQuery(sql);
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return r;
	}

	public int insert(String sql)
	{
		return (execDML(sql,Statement.RETURN_GENERATED_KEYS));
	}
	public int update(String sql)
	{
		return (execDML(sql,Statement.NO_GENERATED_KEYS));
	}
	public int delete(String sql)
	{
		return (execDML(sql,Statement.NO_GENERATED_KEYS));
	}
	private int execDML(String sql,int flag)
	{
		int r=0;
		try
		{
			Statement s = dbCon.createStatement();
			r = s.executeUpdate(sql,flag);
		
			if (flag==Statement.RETURN_GENERATED_KEYS)
			{
				ResultSet rs = s.getGeneratedKeys();
				if(rs.next())
				{
					this.lastInsertId = rs.getInt(1);
				}
				rs.close();
			}
		}
		catch(SQLException se) { se.printStackTrace(); }
		return r;
	}

	public int getLastInsertId()
	{
		return lastInsertId;
	}
}
