package parametrage;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;

import base.Connexion;

public class Libelle implements base.Isql,base.TableLibelle
{
	private String libType,libCode,libLibelle;
	private int libId;

	private static Hashtable<String,Hashtable<Integer,Libelle>> htLibelles=null;

	public Libelle(String libType, String libCode, String libLibelle) {
		super();
		this.libType = libType;
		this.libCode = libCode;
		this.libLibelle = libLibelle;
	}
	
	public Libelle(String libType, String libCode, String libLibelle,int libId) {
		this(libType,libCode,libLibelle);
		this.libId=libId;
	}

	public void delete()
	{
		if (htLibelles==null) select();
		Connexion cn=new Connexion();
		cn.connect();
		String sql="DELETE FROM "+TABLE+" WHERE "+LIBID+"="+this.libId;
		cn.delete(sql);
		cn.close();
		if(htLibelles.containsKey(this.libType)) 
			htLibelles.get(this.libType).remove(this.libId);
	}
	
	public static void select()
	{
		htLibelles=new Hashtable<String,Hashtable<Integer,Libelle>>(); 
		String sql="SELECT "+LIBID+","+LIBCODE+","+LIBLIBELLE+","+LIBTYPE+" FROM "+TABLE;
		Connexion cn=new Connexion();
		cn.connect();
		
		ResultSet rs=cn.select(sql);
		try {
			while(rs.next())
			{
				String leType=rs.getString(LIBTYPE);
				int lid=rs.getInt(LIBID);
				if(!htLibelles.containsKey(leType))
					htLibelles.put(leType,new Hashtable<Integer,Libelle>());
				
				htLibelles.get(leType).put(lid,new Libelle(rs.getString(LIBTYPE),
						rs.getString(LIBCODE),rs.getString(LIBLIBELLE),lid));
			}
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		cn.close();
	}
	
	public static Libelle select(String type, int id){
		Libelle li=null;
		if (htLibelles==null) select();
		if (htLibelles.containsKey(type)) li= htLibelles.get(type).get(id);
		return li;
	}
	public static ArrayList<Libelle> select(String type)
	{
		ArrayList<Libelle> al=null;
		if (htLibelles==null) select();
		
		if (htLibelles.containsKey(type)) 
				al=new ArrayList<Libelle>(htLibelles.get(type).values());
		return al;
	}

	public static Hashtable<String,String> verifierLibelle(String id,String code,String type,String libelle)
	{
		Hashtable<String,String> erreurs=new Hashtable<String,String>();
		if (code==null || code.equals(""))
		{
			erreurs.put(LIBCODE,"le code est obligatoire");
		}
		if (libelle==null || libelle.equals(""))
		{
			erreurs.put(LIBLIBELLE,"le libellé est obligatoire");
		}
		if (type==null || type.equals(""))
		{
			erreurs.put(LIBTYPE,"le type est obligatoire");
		}
			
		if (code!=null && type!=null)
		{
			int lid=0;
			try
			{
				lid=Integer.parseInt(id);
			}
			catch(NumberFormatException nfe)
			{
				nfe.printStackTrace();
			}
			
			if(lid > 0) // modif
			{
				// si le libellé existe mais avec un couple type,id différent
				if (!htLibelles.containsKey(type) || (!htLibelles.get(type).containsKey(lid)))
				{
					erreurs.put(LIBTYPE,"le type ne peut changer");
				}
			}
// code déjà existant
			ArrayList <Libelle>al=Libelle.select(type);
			boolean existeDeja=false;
			int ii=0;
			while(ii < al.size() && !existeDeja)
			{
				//System.out.println(al.get(ii).getLibCode()+" "+code+" "+al.get(ii).getLibId() + " "+lid);
				if(al.get(ii).getLibCode().equals(code) && al.get(ii).getLibId()!=lid) 
				{
					existeDeja=true;
					erreurs.put(LIBCODE,"le code "+code+ " existe déjà");
				}
				else ii++;
			}
		}
		return erreurs;
	}

	public void insert()
	{
		if (htLibelles==null) select();
		Connexion cn=new Connexion();
		cn.connect();
		String sql="INSERT INTO "+TABLE+" ("+LIBCODE+","+LIBTYPE+","+LIBLIBELLE+") " +
			"VALUES('"+this.libCode+"','"+this.libType+"','"+this.libLibelle+"');";
		cn.insert(sql);
		this.libId=cn.getLastInsertId();
		cn.close();
		if (!htLibelles.containsKey(this.libType)) htLibelles.put(this.libType,new Hashtable<Integer,Libelle>());
		htLibelles.get(this.libType).put(this.libId,this);
	}
	
	public void update()
	{
		if (htLibelles==null) select();
		Connexion cn=new Connexion();
		cn.connect();
		String sql="UPDATE "+TABLE+" SET " +LIBCODE+ "='"+this.libCode+"'," +
					LIBTYPE+ "='"+this.libType+"'," +
					LIBLIBELLE + "='"+this.libLibelle+"' " +
					"WHERE "+LIBID+"="+this.libId;
		cn.update(sql);
		cn.close();
		if (htLibelles.containsKey(this.libType)) htLibelles.get(this.libType).put(this.libId,this);
	}
	public String getLibType() {
		return libType;
	}

	public void setLibType(String libType) {
		this.libType = libType;
	}

	public String getLibCode() {
		return libCode;
	}

	public void setLibCode(String libCode) {
		this.libCode = libCode;
	}

	public String getLibLibelle() {
		return libLibelle;
	}

	public void setLibLibelle(String libLibelle) {
		this.libLibelle = libLibelle;
	}

	public int getLibId() {
		return libId;
	}

	public void setLibId(int libId) {
		this.libId = libId;
	}
}

