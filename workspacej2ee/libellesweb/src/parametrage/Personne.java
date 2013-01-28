package parametrage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Hashtable;


import base.Connexion;

public class Personne implements base.Isql,base.TablePersonne{
	private int perID;
	private String perNom;
	private String perPrenom;
	private Libelle civilit�;
	private Libelle pays;
	private Libelle nationalit�;
	private String perCommentaires;
	
	//Conctructeurs
	public Personne(int lid,String leNom,String lePrenom,Libelle lacivilit�,Libelle lepays,Libelle lanationalit�,String leCommentaires){
		this.perID=lid;
		this.perNom=leNom;
		this.perPrenom=lePrenom;
		this.civilit�=lacivilit�;
		this.pays=lepays;
		this.nationalit�=lanationalit�;
		this.perCommentaires=leCommentaires;
	}
	public Personne(int lid){
		this.perID=lid;
	}
	
	//Methode MDL
	public void insert(){
		
		Connexion cn=new Connexion();
		cn.connect();
		String sql="INSERT INTO "+TABLE+" ("+
			PERID+","+
			PERNOM+","+
			PERPRENOM+","+
			PERLIBIDCIV+","+
			PERLIBIDPAYS+","+
			PERLIBIDNATIO+","+
			PERCOMMENTAIRES+") " +
			"VALUES('"+
			this.perID+"','"+
			this.perNom+"','"+
			this.perPrenom+"','"+
			this.civilit�.getLibId()+"',";
		
		if(this.pays!=null){
			sql+="'"+this.pays.getLibId()+"',";	
		}
		else
			sql+=this.pays+",";
		
		if(this.nationalit�!=null){
			sql+="'"+this.nationalit�.getLibId()+"','";
		}
		else{
			sql+=this.nationalit�+",'";
		}
		
		sql+=this.perCommentaires+"');";
		cn.insert(sql);
		this.perID=cn.getLastInsertId();
		cn.close();
	}
	public void delete(){
		Connexion cn=new Connexion();
		cn.connect();
		String sql="DELETE FROM "+TABLE+" WHERE "+PERID+"="+this.perID;
		cn.delete(sql);
		cn.close();
	}
	public void update(){
		
		Connexion cn=new Connexion();
		cn.connect();
		String sql="UPDATE "+TABLE+" SET " +PERID+ "='"+this.perID+"'," +
					PERNOM+ "='"+this.perNom+"'," +
					PERPRENOM + "='"+this.perPrenom+"'," +
					PERLIBIDCIV + "='"+this.civilit�.getLibId()+"',";
		if(this.pays!=null){
			sql+=PERLIBIDPAYS +"='"+this.pays.getLibId()+"',";	
		}
		else
			sql+=PERLIBIDPAYS +"="+this.pays+",";
		
		if(this.nationalit�!=null){
			sql+=PERLIBIDNATIO+"='"+this.nationalit�.getLibId()+"',";
		}
		else{
			sql+=PERLIBIDNATIO+"="+this.nationalit�+",";
		}
		
		sql+=PERCOMMENTAIRES+"='"+this.perCommentaires+"'"+"WHERE "+PERID+"="+this.perID+";";	
		cn.update(sql);
		cn.close();
	}
	
	//Methode de r�cuperation de donn�es
	public void select(){
		String sql="SELECT "+PERNOM+","+PERPRENOM+","+PERLIBIDCIV+","+PERLIBIDPAYS+","+PERLIBIDNATIO+","+PERCOMMENTAIRES+" FROM "+TABLE+" WHERE "+PERID+" = " +this.perID;
		Connexion cn=new Connexion();
		cn.connect();
		
		ResultSet rs=cn.select(sql);
		try {
			while(rs.next())
			{
				this.perNom=rs.getString(PERNOM);
				this.perPrenom=rs.getString(PERPRENOM);
				this.civilit�=Libelle.select("CIV",rs.getInt(PERLIBIDCIV));
				this.pays=Libelle.select("PAY",rs.getInt(PERLIBIDPAYS));
				this.nationalit�=Libelle.select("NAT",rs.getInt(PERLIBIDNATIO));
				this.perCommentaires=rs.getString(PERCOMMENTAIRES);
				
			}
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		cn.close();
	}
	public static ArrayList<Personne>select(String debutNom){
		String sql="SELECT "+PERID+","+PERNOM+","+PERPRENOM+","+PERLIBIDCIV+","+PERLIBIDPAYS+","+PERLIBIDNATIO+","+PERCOMMENTAIRES+" " +
				"	FROM "+TABLE+" WHERE "+PERNOM+" like " +"'"+debutNom+"%"+"'"+
				" ORDER BY " + PERNOM+","+PERPRENOM;
		ArrayList<Personne> listePersonne = new ArrayList<Personne>();
		Connexion cn=new Connexion();
		cn.connect();
		ResultSet rs=cn.select(sql);
		try {
			while(rs.next())
			{
				Personne personneTemp=new Personne(
						rs.getInt(PERID),
						rs.getString(PERNOM),
						rs.getString(PERPRENOM),
						Libelle.select("CIV",rs.getInt(PERLIBIDCIV)),
						Libelle.select("PAY",rs.getInt(PERLIBIDPAYS)),
						Libelle.select("NAT",rs.getInt(PERLIBIDNATIO)),
						rs.getString(PERCOMMENTAIRES));	
				
				listePersonne.add(personneTemp);
			}
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		cn.close();
	
		return listePersonne;
	}
	
	
	// Methode de test
	public static Hashtable verifierPersonne(String lid,String leNom,String lePrenom,String lacivilit�,String Pays,String Nationalit�,String Commentaire){
		Hashtable<String,String> erreurs=new Hashtable<String,String>();
		if (lid==null || lid.equals(""))
		{
			erreurs.put(PERID,"l' ID est obligatoire");
		}
		if (leNom==null || leNom.equals(""))
		{
			erreurs.put(PERNOM,"le nom est obligatoire");
		}
		if (lePrenom==null || lePrenom.equals(""))
		{
			erreurs.put(PERPRENOM,"le Pr�nom est obligatoire");
		}
		if (lacivilit�==null || lacivilit�.equals(""))
		{
			erreurs.put(PERLIBIDCIV,"la civilit� est obligatoire");
		}	
		return erreurs;
	}
	
	
	//Setters and Getters
	public int getPerID() {
		return perID;
	}
	public void setPerID(int perID) {
		this.perID = perID;
	}
	public String getPerNom() {
		return perNom;
	}
	public void setPerNom(String perNom) {
		this.perNom = perNom;
	}
	public String getPerPrenom() {
		return perPrenom;
	}
	public void setPerPrenom(String perPrenom) {
		this.perPrenom = perPrenom;
	}
	public Libelle getCivilit�() {
		return civilit�;
	}
	public void setCivilit�(Libelle civilit�) {
		this.civilit� = civilit�;
	}
	public Libelle getPays() {
		return pays;
	}
	public void setPays(Libelle pays) {
		this.pays = pays;
	}
	public Libelle getNationalit�() {
		return nationalit�;
	}
	public void setNationalit�(Libelle nationalit�) {
		this.nationalit� = nationalit�;
	}
	public String getPerCommentaires() {
		return perCommentaires;
	}
	public void setPerCommentaires(String perCommentaires) {
		this.perCommentaires = perCommentaires;
	}
}
