package controleurs;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.Hashtable;

import parametrage.Personne;
import parametrage.Libelle;
import vues.IEcranPersonne;

/**
 * Servlet implementation class PersonneServlet
 */
public class PersonneServlet extends HttpServlet implements vues.IEcranPersonne{
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PersonneServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session=request.getSession(true);
		
		
		//Initialisation des variables
		ArrayList<Personne> listePersonne=null;
		
		int id=0,civiliteId=0,paysId=0,nationaliteId=0;
		String action="",ecran="",nom="",prenom="",commentaire="",recherche="";
		
		//Recuperation des données reçus par le formulaire
		if(request.getParameter(NAME_ACTION)!=null) action=request.getParameter(NAME_ACTION);
		if(request.getParameter(NAME_ID)!=null) id= new Integer(request.getParameter(NAME_ID)).intValue();
		if(request.getParameter(NAME_RECHERCHE)!=null){
			recherche=request.getParameter(NAME_RECHERCHE);
			listePersonne=Personne.select(recherche);
		}
		
			
		//Test d'action
		if(action.equals(VALUE_COMPLETER)) {
			/*ServletOutputStream sos= response.getOutputStream();		
			response.setHeader("Cache-Control", "no-Cache");
			response.setContentType("text/plain");
			response.setCharacterEncoding("iso-8859-1");
			
			String reponse;
			
			listePersonne=Personne.select(recherche);
			if(listePersonne.size()==0){
				recherche=recherche.substring(0,recherche.length()-1);	
			}
			if(listePersonne.size()==1){
				recherche=listePersonne.get(0).getPerNom();	
			}
			reponse="var resultat='"+recherche+"';";
			reponse+="var tab={";
			for(int i=0;i<listePersonne.size();i++){
				reponse+="'personne"+i+"': '"+listePersonne.get(i).getPerNom()+" "+listePersonne.get(i).getPerPrenom()+"'";
				if(i<listePersonne.size()-1){
					reponse+=",";
				}
			}
			reponse+="};";
			sos.write(reponse.getBytes());
			sos.flush();
			return;*/
			ServletOutputStream sos= response.getOutputStream();		
			response.setHeader("Cache-Control", "no-Cache");
			response.setContentType("text/xml");
			response.setCharacterEncoding("iso-8859-1");
			
			String reponse;
			
			listePersonne=Personne.select(recherche);
			if(listePersonne.size()==0){
				recherche=recherche.substring(0,recherche.length()-1);
			}
			if(listePersonne.size()==1){
				recherche=listePersonne.get(0).getPerNom();	
			}
			reponse="<?xml version=\"1.0\" encoding=\"iso-8859-1\"?>";
			reponse+="<REPONSE>";
			reponse+="<RESULTAT>"+recherche+"</RESULTAT>";
			for(int i=0;i<listePersonne.size();i++){
				reponse+="<PERSONNE><NOM>"+listePersonne.get(i).getPerNom()+"</NOM>"+"<PRENOM>"+listePersonne.get(i).getPerPrenom()+"</PRENOM></PERSONNE>";
			}
			reponse+="</REPONSE>";
			sos.write(reponse.getBytes());
			sos.flush();
			return;
		}
		if(action.equals(VALUE_MODIFIER)||action.equals(VALUE_SUPPRIMER)||action.equals(VALUE_CREER)){
			if(action.equals(VALUE_MODIFIER)||action.equals(VALUE_SUPPRIMER)){
				Personne personneTemp=new Personne(id);
				personneTemp.select();
				nom=personneTemp.getPerNom();
				prenom=personneTemp.getPerPrenom();
				civiliteId=personneTemp.getCivilité().getLibId();
				if (personneTemp.getPays()!=null) paysId=personneTemp.getPays().getLibId();
				if (personneTemp.getNationalité()!=null) nationaliteId=personneTemp.getNationalité().getLibId();
				commentaire= personneTemp.getPerCommentaires();
			}
			session.setAttribute(ATTR_BUTACTION, action);
			session.setAttribute(ATTR_ID, id);
			session.setAttribute(ATTR_NOM, nom);
			session.setAttribute(ATTR_PRENOM, prenom);
			session.setAttribute(ATTR_CIVID, civiliteId);
			session.setAttribute(ATTR_PAYID, paysId);
			session.setAttribute(ATTR_NATID, nationaliteId);
			session.setAttribute(ATTR_COMMENTAIRE, commentaire);

			session.setAttribute(ATTR_LISTECIVILITE,Libelle.select("CIV"));
			session.setAttribute(ATTR_LISTEPAYS,Libelle.select("PAY"));
			session.setAttribute(ATTR_LISTENATIONALITE,Libelle.select("NAT"));
			
			ecran=ATTR_ECRANSAISIE;
		}
		if(ecran.equals("")) ecran=ATTR_ECRANLISTE;
		
		if(ecran.equals(ATTR_ECRANLISTE)){
			session.setAttribute(ATTR_LISTEPERSONNE, listePersonne);
		}
		session.setAttribute(ATTR_ECRAN, ecran);
		session.setAttribute(ATTR_RECHERCHE, recherche);
		
		response.sendRedirect("parametrage/Personne.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session=request.getSession(true);
		
		//Initialisation des variables
		ArrayList<Personne> listePersonne;
		
		Integer id=0,civiliteId=0,paysId=0,nationaliteId=0;
		String nom="",prenom="",commentaire="",action="",butAction="",ecran="",recherche;
		
		
		if(request.getParameter(NAME_ACTION)!=null) action=request.getParameter(NAME_ACTION);
		if(request.getParameter(NAME_BUTACTION)!=null) butAction=request.getParameter(NAME_BUTACTION);
		if(request.getParameter(NAME_ID)!=null) id= new Integer(request.getParameter(NAME_ID));
		
		
		if(action.equals(VALUE_VALIDER)||action.equals(VALUE_CONFIRMER)||action.equals(VALUE_ANNULER)){
			if(action.equals(VALUE_VALIDER)||action.equals(VALUE_CONFIRMER)){
				//Initialisation des variable
				Hashtable<String,String> tableErreurs;
				Personne personneTemp=null;
				if(butAction.equals(VALUE_SUPPRIMER)||butAction.equals(VALUE_MODIFIER)){
					personneTemp=new Personne(id);
					personneTemp.select();
				}
				if(butAction.equals(VALUE_CREER)||butAction.equals(VALUE_MODIFIER)){
					nom=request.getParameter(NAME_NOM);
					prenom=request.getParameter(NAME_PRENOM);
					civiliteId=new Integer(request.getParameter(NAME_CIVID));
					if(!request.getParameter(NAME_PAYID).equals("")){
						paysId=new Integer(request.getParameter(NAME_PAYID));
					}
					if(!request.getParameter(NAME_NATID).equals("")){
						nationaliteId=new Integer(request.getParameter(NAME_NATID));
					}
					commentaire=request.getParameter(NAME_COMMENTAIRE);
					tableErreurs=Personne.verifierPersonne(id.toString(), nom, prenom, civiliteId.toString(), paysId.toString(),nationaliteId.toString(), commentaire);
					
					if(tableErreurs.size()>0){
						session.setAttribute(ATTR_BUTACTION, butAction);
						session.setAttribute(ATTR_ID, id);
						session.setAttribute(ATTR_NOM, nom);
						session.setAttribute(ATTR_PRENOM, prenom);
						session.setAttribute(ATTR_CIVID, civiliteId);
						session.setAttribute(ATTR_PAYID, paysId);
						session.setAttribute(ATTR_NATID, nationaliteId);
						session.setAttribute(ATTR_COMMENTAIRE, commentaire);
						session.setAttribute(ATTR_ERREURS, tableErreurs);
						
						session.setAttribute(ATTR_LISTECIVILITE,Libelle.select("CIV"));
						session.setAttribute(ATTR_LISTEPAYS,Libelle.select("PAY"));
						session.setAttribute(ATTR_LISTENATIONALITE,Libelle.select("NAT"));
						ecran=ATTR_ECRANSAISIE;
					}
					else{
						if(butAction.equals(VALUE_CREER)){
							personneTemp=new Personne(id, nom, prenom, Libelle.select("CIV",civiliteId), Libelle.select("PAY",paysId), Libelle.select("NAT",nationaliteId), commentaire);
							personneTemp.insert();
							ecran=ATTR_ECRANLISTE;
						}
						if(butAction.equals(VALUE_MODIFIER)){
							if(id!=0){
								personneTemp.setPerNom(nom);
								personneTemp.setPerPrenom(prenom);
								personneTemp.setCivilité(Libelle.select("CIV",civiliteId));
								personneTemp.setPays(Libelle.select("PAY",paysId));
								personneTemp.setNationalité(Libelle.select("NAT",nationaliteId));
								personneTemp.setPerCommentaires(commentaire);
								personneTemp.update();	
								ecran=ATTR_ECRANLISTE;
							}
						}
					}
				}
				if(butAction.equals(VALUE_SUPPRIMER)){
					personneTemp.delete();
					ecran=ATTR_ECRANLISTE;
				}
			}
			if(action.equals(VALUE_ANNULER)){
				ecran=ATTR_ECRANLISTE;
			}
		}
		if(ecran.equals(ATTR_ECRANLISTE)){
			recherche=(String)session.getAttribute(ATTR_RECHERCHE);
			listePersonne=(Personne.select(recherche));
			session.setAttribute(ATTR_LISTEPERSONNE, listePersonne);
		}
		session.setAttribute(ATTR_ECRAN,ecran);
		response.sendRedirect("parametrage/Personne.jsp");
	}

}
