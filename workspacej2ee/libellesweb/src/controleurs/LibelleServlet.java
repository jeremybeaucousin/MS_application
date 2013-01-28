package controleurs;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import parametrage.Libelle;

/**
 * Servlet implementation class LibelleServlet
 */
public class LibelleServlet extends HttpServlet implements vues.IEcranLibelle {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public LibelleServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session=request.getSession(true);
		
		String ecran="",type="",action="";
		int iddutype=-1;
		Libelle libelleEntete=null;
		ArrayList<Libelle> entetes=Libelle.select("ENTETE");
		session.setAttribute(ATTR_LISTEENTETES,entetes);
		
		if(request.getParameter(NAME_IDDUTYPE)!=null) 
		{
			String siddutype=request.getParameter(NAME_IDDUTYPE);
			if(siddutype!=null && siddutype.length()>0) iddutype=Integer.parseInt(siddutype);
			libelleEntete=Libelle.select("ENTETE", iddutype);
			if (libelleEntete!=null) type=libelleEntete.getLibCode();
		}
		
		if (request.getParameter(NAME_ACTION)!=null){
			if (request.getParameter(NAME_ID)!=null){
				int id=new Integer(request.getParameter(NAME_ID));
				session.setAttribute(ATTR_ID,id);
				session.setAttribute(ATTR_LIBELLE,Libelle.select(type,id).getLibLibelle());
				session.setAttribute(ATTR_CODE,Libelle.select(type,id).getLibCode());
			}
			
			action=request.getParameter(NAME_ACTION);
			session.setAttribute(ATTR_BUTACTION,action);
			if(action.equals(VALUE_AJOUTER)||action.equals(VALUE_MODIFIER)||action.equals(VALUE_SUPPRIMER)){
				ecran=ATTR_ECRANSAISIE;	
			}
			
		}
		if (ecran.equals(""))	ecran=ATTR_ECRANLISTE;
		
		if (ecran.equals(ATTR_ECRANLISTE))
			session.setAttribute(ATTR_LISTE,Libelle.select(type));
		session.setAttribute(ATTR_ECRAN,ecran);
		session.setAttribute(ATTR_IDDUTYPE,iddutype);
		
		
		session.removeAttribute(NAME_ACTION);
		response.sendRedirect("parametrage/libelles.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session=request.getSession(true);
		
		String ecran="",type="",action="";
		int iddutype=-1;
		Libelle libelleEntete=null;
		ArrayList<Libelle> entetes=Libelle.select("ENTETE");
		session.setAttribute(ATTR_LISTEENTETES,entetes);
		if(request.getParameter(NAME_IDDUTYPE)!=null) 
		{
			String siddutype=request.getParameter(NAME_IDDUTYPE);
			if(siddutype!=null && siddutype.length()>0) iddutype=Integer.parseInt(siddutype);
			libelleEntete=Libelle.select("ENTETE", iddutype);
			if (libelleEntete!=null) type=libelleEntete.getLibCode();
		}
		
		if (request.getParameter(NAME_ACTION)!=null){
			action=request.getParameter(NAME_ACTION);
			
			String Lecode=request.getParameter(NAME_CODE),lelibelle=request.getParameter(NAME_LIBELLE);
			Integer Lid=new Integer(request.getParameter(NAME_ID));
			Hashtable ErreurSaisie=Libelle.verifierLibelle(Lid.toString(), Lecode, type, lelibelle);
			if(ErreurSaisie.size()==0){
				Libelle Libelletemp;
				if(Libelle.select(type,Lid)==null){
					Libelletemp=new Libelle(type,Lecode,lelibelle,Lid);
				}
				else{
					Libelletemp=Libelle.select(type,Lid);
				}
					
				if(action.equals(VALUE_VALIDER)){
					if(Lid==0){
						Libelletemp.insert();
					}
					else{	
						Libelletemp.setLibCode(Lecode);
						Libelletemp.setLibLibelle(lelibelle);
						Libelletemp.update();
					}
				}
				if(action.equals(VALUE_CONFIRMER)){
					Libelletemp.delete();
				}
			}
			else{
				session.setAttribute(ATTR_BUTACTION,action);
				session.setAttribute(ATTR_ID,Lid);
				session.setAttribute(ATTR_LIBELLE,request.getParameter(NAME_LIBELLE));
				session.setAttribute(ATTR_CODE,	request.getParameter(NAME_CODE));
				session.setAttribute(ATTR_ERREURS,ErreurSaisie);
				
				ecran=ATTR_ECRANSAISIE;	
			}	
			if(action.equals(VALUE_ANNULER)){
				ecran=ATTR_ECRANLISTE;	
				session.removeAttribute(ATTR_ERREURS);
			}	
			
		}	
		
		if (ecran.equals(""))	ecran=ATTR_ECRANLISTE;
		
		if (ecran.equals(ATTR_ECRANLISTE))
			session.setAttribute(ATTR_LISTE,Libelle.select(type));
		session.setAttribute(ATTR_ECRAN,ecran);
		session.setAttribute(ATTR_IDDUTYPE,iddutype);
		
		session.removeAttribute(NAME_ACTION);
		response.sendRedirect("parametrage/libelles.jsp");
	}
}
