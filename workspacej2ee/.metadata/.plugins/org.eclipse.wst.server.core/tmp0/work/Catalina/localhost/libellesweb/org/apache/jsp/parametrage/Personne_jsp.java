package org.apache.jsp.parametrage;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import base.TablePersonne;
import vues.IEcranPersonne;
import parametrage.Personne;
import parametrage.Libelle;
import java.util.ArrayList;
import java.util.Hashtable;

public final class Personne_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List _jspx_dependants;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.AnnotationProcessor _jsp_annotationprocessor;

  public Object getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_annotationprocessor = (org.apache.AnnotationProcessor) getServletConfig().getServletContext().getAttribute(org.apache.AnnotationProcessor.class.getName());
  }

  public void _jspDestroy() {
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=ISO-8859-1");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=ISO-8859-1\">\r\n");
      out.write("<title>Personnes</title>\r\n");
      out.write("<link href=\"../style/personne.css\" rel=\"stylesheet\" type=\"text/css\"   >\r\n");
      out.write("<script language=\"javascript\" type=\"text/javascript\" src=\"../js/controle.js\"></script>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("\t");
 
	//initialisation des variables
	String recherche="",ecran=(String)session.getAttribute(IEcranPersonne.ATTR_ECRAN);
	
	session.removeAttribute(IEcranPersonne.ATTR_ECRAN);
	
	if(session.getAttribute(IEcranPersonne.ATTR_RECHERCHE)!=null) recherche=(String)session.getAttribute(IEcranPersonne.ATTR_RECHERCHE).toString();
	if(ecran==null) ecran=IEcranPersonne.ATTR_ECRANLISTE;
	
	if(ecran.equals(IEcranPersonne.ATTR_ECRANLISTE)){

		if(session.getAttribute(IEcranPersonne.ATTR_RECHERCHE)!=null) recherche=(String)session.getAttribute(IEcranPersonne.ATTR_RECHERCHE);
	
      out.write("\r\n");
      out.write("\t<FORM method=\"get\" action=\"../PersonneServlet\" >\r\n");
      out.write("\t\t");
 
		//initialisation des variables
		ArrayList<Personne> listePersonne=new ArrayList<Personne>();
		String urlDebut,urlModif,urlSuppr,urlFin;
		if(session.getAttribute(IEcranPersonne.ATTR_LISTEPERSONNE)!=null) listePersonne=(ArrayList<Personne>)session.getAttribute(IEcranPersonne.ATTR_LISTEPERSONNE);
		
		
      out.write("\r\n");
      out.write("\t\t<H1>Recherche</H1>\r\n");
      out.write("\t\t<INPUT onkeyup=\"javascript:return test('../PersonneServlet?");
      out.print( IEcranPersonne.NAME_ACTION);
      out.write('=');
      out.print( IEcranPersonne.VALUE_COMPLETER);
      out.write('&');
      out.print( IEcranPersonne.NAME_RECHERCHE);
      out.write("='+this.value);\" autocomplete=\"off\" TYPE=\"TEXT\" ID=\"recherche\" NAME=\"");
      out.print( IEcranPersonne.NAME_RECHERCHE );
      out.write("\" VALUE=\"");
      out.print( recherche );
      out.write("\"/>\r\n");
      out.write("\t\t<DIV ID=\"listepropose\">\r\n");
      out.write("\t\t</DIV>\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t<INPUT TYPE=\"SUBMIT\" NAME=\"");
      out.print( IEcranPersonne.NAME_ACTION );
      out.write("\" VALUE=\"");
      out.print(IEcranPersonne.VALUE_RECHERCHER);
      out.write("\"/>\r\n");
      out.write("\t\t<INPUT TYPE=\"SUBMIT\" NAME=\"");
      out.print( IEcranPersonne.NAME_ACTION );
      out.write("\" VALUE=\"");
      out.print(IEcranPersonne.VALUE_CREER);
      out.write("\"/>\r\n");
      out.write("\t\t");
if(listePersonne.size()>0){
      out.write("\r\n");
      out.write("\t\t<DIV class=\"listeprincipale\">\r\n");
      out.write("\t\t<UL class=\"liste\">\r\n");
      out.write("\t\t\t");

			urlDebut="../PersonneServlet?"+ IEcranPersonne.NAME_ACTION +"=";
			urlFin="&"+IEcranPersonne.NAME_ID+"=";
			urlModif=urlDebut+IEcranPersonne.VALUE_MODIFIER+urlFin;
			urlSuppr=urlDebut+IEcranPersonne.VALUE_SUPPRIMER+urlFin;
			
			for(int ii=0;ii<listePersonne.size();ii++){
			String classe="";
			if (ii%2==0) classe="paire"; else classe="impaire";
			
      out.write("  \r\n");
      out.write("\t\t\t<LI class=\"");
      out.print( classe );
      out.write("\">\r\n");
      out.write("\t\t\t\t<SPAN CLASS=\"infopersonne\">\r\n");
      out.write("\t\t\t\t\t");
      out.print(
					listePersonne.get(ii).getCivilité().getLibLibelle()+ "&nbsp" +
					listePersonne.get(ii).getPerNom()+ "&nbsp" +
					listePersonne.get(ii).getPerPrenom() 
					);
      out.write("\r\n");
      out.write("\t\t\t\t</SPAN>\r\n");
      out.write("\t\t\t\t<a href=\"");
      out.print(urlSuppr+listePersonne.get(ii).getPerID() );
      out.write("\" >\r\n");
      out.write("\t\t\t\t\t<IMG src=\"../img/delete.png\" alt=\"supprimer\"/>\r\n");
      out.write("\t\t\t\t</a>\r\n");
      out.write("\t\t\t\t<a href=\"");
      out.print(urlModif+listePersonne.get(ii).getPerID() );
      out.write("\" >\r\n");
      out.write("\t\t\t\t\t<IMG src=\"../img/edit.png\" alt=\"Éditer\"/>\r\n");
      out.write("\t\t\t\t</a>\r\n");
      out.write("\t\t\t</LI>\r\n");
      out.write("\t\t\t");

			}
			session.removeAttribute(IEcranPersonne.ATTR_LISTEPERSONNE);
			
      out.write("\r\n");
      out.write("\t\t</UL>\t\r\n");
      out.write("\t\t</DIV>\r\n");
      out.write("\t\t");
}
      out.write("\t\r\n");
      out.write("\t</FORM>\r\n");
      out.write("\t");

	}
	if(ecran.equals(IEcranPersonne.ATTR_ECRANSAISIE)){
	
      out.write("\r\n");
      out.write("\t<FORM method=\"POST\" action=\"../PersonneServlet\">\r\n");
      out.write("\t");

		Hashtable<String,String> tableErreurs=(Hashtable<String,String>)session.getAttribute(IEcranPersonne.ATTR_ERREURS);
		ArrayList<Libelle> listeCivilite=(ArrayList<Libelle>)session.getAttribute(IEcranPersonne.ATTR_LISTECIVILITE);
		ArrayList<Libelle> listePays=(ArrayList<Libelle>)session.getAttribute(IEcranPersonne.ATTR_LISTEPAYS);
		ArrayList<Libelle> listeNationalite=(ArrayList<Libelle>)session.getAttribute(IEcranPersonne.ATTR_LISTENATIONALITE);
		
		Integer id=((Integer)session.getAttribute(IEcranPersonne.ATTR_ID));
		
		Libelle civilite=Libelle.select("CIV",((Integer)session.getAttribute(IEcranPersonne.ATTR_CIVID)).intValue()),
		pays=Libelle.select("PAY",((Integer)session.getAttribute(IEcranPersonne.ATTR_PAYID)).intValue()),
		nationalite=Libelle.select("NAT",((Integer)session.getAttribute(IEcranPersonne.ATTR_NATID)).intValue());
		
		String selectedLibelle="",nom=(String)session.getAttribute(IEcranPersonne.ATTR_NOM),
		prenom=(String)session.getAttribute(IEcranPersonne.ATTR_PRENOM),
		commentaire=(String)session.getAttribute(IEcranPersonne.ATTR_COMMENTAIRE),
		action=(String)session.getAttribute(IEcranPersonne.ATTR_BUTACTION),
		validation=IEcranPersonne.VALUE_VALIDER,
		erreurNom="",erreurPrenom="",erreurCivilite="";
		
		if(action==null) action="";
		if(action.equals(IEcranPersonne.VALUE_SUPPRIMER)) validation=IEcranPersonne.VALUE_CONFIRMER;
		if(tableErreurs!=null){
			if(tableErreurs.containsKey(TablePersonne.PERNOM)) erreurNom=tableErreurs.get(TablePersonne.PERNOM);
			if(tableErreurs.containsKey(TablePersonne.PERPRENOM)) erreurPrenom=tableErreurs.get(TablePersonne.PERPRENOM);
			if(tableErreurs.containsKey(TablePersonne.PERLIBIDCIV)) erreurCivilite=tableErreurs.get(TablePersonne.PERLIBIDCIV);			
		}
	
      out.write("\r\n");
      out.write("\t\t<H1>Saisie</H1>\r\n");
      out.write("\t\t<INPUT TYPE=\"hidden\" NAME=\"");
      out.print( IEcranPersonne.NAME_BUTACTION );
      out.write("\" VALUE=\"");
      out.print( action);
      out.write("\" />\r\n");
      out.write("\t\t<INPUT TYPE=\"hidden\" NAME=\"");
      out.print( IEcranPersonne.NAME_ID );
      out.write("\" VALUE=\"");
      out.print( id);
      out.write("\" />\r\n");
      out.write("\t\t<P CLASS=\"champNom\" >Nom : <INPUT  CLASS=\"champ\" TYPE=\"text\" NAME=\"");
      out.print( IEcranPersonne.NAME_NOM );
      out.write("\" VALUE=\"");
      out.print( nom );
      out.write("\" />\r\n");
      out.write("\t\t\t<SPAN CLASS=\"codeerreur\">&nbsp &nbsp ");
      out.print( erreurNom );
      out.write("</SPAN>\r\n");
      out.write("\t\t</P>\r\n");
      out.write("\t\t<P  CLASS=\"champNom\" >Prénom : <INPUT  CLASS=\"champ\" TYPE=\"text\" NAME=\"");
      out.print( IEcranPersonne.NAME_PRENOM );
      out.write("\" VALUE=\"");
      out.print( prenom);
      out.write("\" />\r\n");
      out.write("\t\t\t<SPAN CLASS=\"codeerreur\">&nbsp &nbsp ");
      out.print( erreurPrenom );
      out.write("</SPAN>\r\n");
      out.write("\t\t</P>\r\n");
      out.write("\t\t<P  CLASS=\"champNom\" >Civilité : <SELECT  CLASS=\"champ\" NAME=\"");
      out.print( IEcranPersonne.NAME_CIVID );
      out.write("\">\r\n");
      out.write("\t\t");

		for(int ii=0;ii<listeCivilite.size();ii++){
			if (!action.equals(IEcranPersonne.VALUE_CREER )){
				if(listeCivilite.get(ii).getLibId()==civilite.getLibId()){
					selectedLibelle="selected=\"selected\"" ;
				}
			}
		
      out.write("\r\n");
      out.write("\t\t<OPTION VALUE=\"");
      out.print( listeCivilite.get(ii).getLibId());
      out.write('"');
      out.write(' ');
      out.print( selectedLibelle );
      out.write(" >\r\n");
      out.write("\t\t\t");
      out.print( listeCivilite.get(ii).getLibLibelle() );
      out.write("\r\n");
      out.write("\t\t</OPTION>\r\n");
      out.write("\t\t");

		selectedLibelle="" ;
		}
		
      out.write("\t\r\n");
      out.write("\t\t</SELECT><SPAN CLASS=\"codeerreur\">&nbsp &nbsp ");
      out.print( erreurCivilite);
      out.write("</SPAN></P>\r\n");
      out.write("\t\t<P CLASS=\"champNom\" >Pays :<SELECT  CLASS=\"champ\" NAME=\"");
      out.print( IEcranPersonne.NAME_PAYID );
      out.write("\">\r\n");
      out.write("\t\t\t<OPTION  ></OPTION>\r\n");
      out.write("\t\t");

		for(int ii=0;ii<listePays.size();ii++){
			if (pays!=null ){
				if(listePays.get(ii).getLibId()==pays.getLibId()){
					selectedLibelle="selected=\"selected\"" ;
				}
			}
		
      out.write("\r\n");
      out.write("\t\t\t<OPTION VALUE=\"");
      out.print( listePays.get(ii).getLibId());
      out.write('"');
      out.write(' ');
      out.print( selectedLibelle );
      out.write(" >\r\n");
      out.write("\t\t\t\t");
      out.print( listePays.get(ii).getLibLibelle() );
      out.write("\r\n");
      out.write("\t\t\t</OPTION>\t\r\n");
      out.write("\t\t");

		selectedLibelle="" ;
		}
		
      out.write("\r\n");
      out.write("\t\t</SELECT></P>\r\n");
      out.write("\t\t<P  CLASS=\"champNom\" >Nationalité : <SELECT  CLASS=\"champ\" NAME=\"");
      out.print( IEcranPersonne.NAME_NATID );
      out.write("\">\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t\t<OPTION  ></OPTION>\r\n");
      out.write("\t\t");

		for(int ii=0;ii<listeNationalite.size();ii++){
			if (nationalite!=null){
				if(listeNationalite.get(ii).getLibId()==nationalite.getLibId()){
					selectedLibelle="selected=\"selected\"" ;
				}
			}
		
      out.write("\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t\t<OPTION VALUE=\"");
      out.print( listeNationalite.get(ii).getLibId());
      out.write('"');
      out.write(' ');
      out.print( selectedLibelle );
      out.write(">\r\n");
      out.write("\t\t\t\t");
      out.print( listeNationalite.get(ii).getLibLibelle() );
      out.write("\r\n");
      out.write("\t\t\t</OPTION>\t\r\n");
      out.write("\t\t");

		selectedLibelle="" ;
		}
		
      out.write("\r\n");
      out.write("\t\t</SELECT ></P>\r\n");
      out.write("\t\t<P  CLASS=\"champNom\" >Commentaires : <TEXTAREA  CLASS=\"champ\" NAME=\"");
      out.print( IEcranPersonne.NAME_COMMENTAIRE );
      out.write('"');
      out.write(' ');
      out.write('>');
      out.print(commentaire );
      out.write("</TEXTAREA></P>\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t<INPUT CLASS=\"submit\" TYPE=\"submit\" NAME=\"");
      out.print( IEcranPersonne.NAME_ACTION );
      out.write("\" value=\"");
      out.print( IEcranPersonne.VALUE_ANNULER );
      out.write("\"/>\r\n");
      out.write("\t\t<INPUT CLASS=\"submit\" TYPE=\"submit\" NAME=\"");
      out.print( IEcranPersonne.NAME_ACTION );
      out.write("\" value=\"");
      out.print( validation );
      out.write("\"/>\r\n");
      out.write("\t</FORM>\r\n");
      out.write("\t");
 
		session.removeAttribute(IEcranPersonne.ATTR_ID );
		session.removeAttribute(IEcranPersonne.ATTR_NOM );
		session.removeAttribute(IEcranPersonne.ATTR_PRENOM );
		session.removeAttribute(IEcranPersonne.ATTR_CIVID );
		session.removeAttribute(IEcranPersonne.ATTR_PAYID );
		session.removeAttribute(IEcranPersonne.ATTR_NATID );
		session.removeAttribute(IEcranPersonne.ATTR_COMMENTAIRE );
		session.removeAttribute(IEcranPersonne.ATTR_ERREURS );
		session.removeAttribute(IEcranPersonne.ATTR_BUTACTION );
	}
	session.removeAttribute(IEcranPersonne.ATTR_LISTEPERSONNE);
	session.removeAttribute(IEcranPersonne.ATTR_LISTECIVILITE);
	session.removeAttribute(IEcranPersonne.ATTR_LISTEPAYS);
	session.removeAttribute(IEcranPersonne.ATTR_LISTENATIONALITE);
	
      out.write("\r\n");
      out.write("\t<FOOTER>\r\n");
      out.write("\t\t<A href=\"../index.jsp\">Page d'accueil</A>\r\n");
      out.write("\t</FOOTER>\r\n");
      out.write("</body>\r\n");
      out.write("</html>");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
