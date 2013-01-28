package org.apache.jsp.parametrage;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import vues.IEcranLibelle;
import java.util.ArrayList;
import java.util.Hashtable;
import parametrage.Libelle;

public final class libelles_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=ISO-8859-1\">\r\n");
      out.write("<title>Maintenance des libellés</title>\r\n");
      out.write("<link href=\"../style/personne.css\" rel=\"stylesheet\" type=\"text/css\"   >\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");

String ecran=(String)session.getAttribute(IEcranLibelle.ATTR_ECRAN);
int iddutype=-1;
if (session.getAttribute(IEcranLibelle.ATTR_IDDUTYPE)!=null) 
	iddutype=((Integer)session.getAttribute(IEcranLibelle.ATTR_IDDUTYPE)).intValue();
session.removeAttribute(IEcranLibelle.ATTR_ECRAN);
if (ecran==null) ecran=IEcranLibelle.ATTR_ECRANLISTE;

if(ecran.equals(IEcranLibelle.ATTR_ECRANLISTE))
{
      out.write("\r\n");
      out.write("\r\n");
      out.write("<form method=\"get\" action=\"../LibelleServlet\" id=\"formulaireGet\">\r\n");
      out.write("<H1>Recherche</H1>\r\n");

	ArrayList<Libelle> entetes=null;
	if (session.getAttribute(IEcranLibelle.ATTR_LISTEENTETES)!=null)
			entetes=(ArrayList<Libelle>)session.getAttribute(IEcranLibelle.ATTR_LISTEENTETES);
	if (entetes!=null)
	{
		
      out.write("Choix du type : &nbsp;<select name=\"");
      out.print(IEcranLibelle.NAME_IDDUTYPE);
      out.write('"');
      out.write('>');

		String selected="";
		for(int ii=0 ; ii < entetes.size() ; ii++)
		{
			Libelle li=entetes.get(ii);
			selected=( li.getLibId()==iddutype ) ? "selected=\"selected\"" : "";
      out.write("\r\n");
      out.write("\t\t\t<option value=\"");
      out.print(li.getLibId());
      out.write('"');
      out.write(' ');
      out.print(selected);
      out.write('>');
      out.print(li.getLibLibelle());
      out.write("</option>\r\n");
      out.write("\t\t");
}
      out.write("\r\n");
      out.write("\t\t</select>\r\n");
      out.write("\t\t<input type=\"submit\" name=\"");
      out.print(IEcranLibelle.NAME_ACTION);
      out.write("\" value=\"");
      out.print(IEcranLibelle.VALUE_RECHERCHER);
      out.write("\"/>\r\n");
      out.write("\t\t<input type=\"submit\" name=\"");
      out.print(IEcranLibelle.NAME_ACTION);
      out.write("\" value=\"");
      out.print(IEcranLibelle.VALUE_AJOUTER);
      out.write("\"/>\r\n");
      out.write("\t");
}
	ArrayList<Libelle> al=(ArrayList<Libelle>)session.getAttribute(IEcranLibelle.ATTR_LISTE);
	if (al!=null)
	{
      out.write("\r\n");
      out.write("\t<DIV class=\"listeprincipale\">\r\n");
      out.write("\t<table>\r\n");
      out.write("\t");
	String debutUrl="../LibelleServlet?" + IEcranLibelle.NAME_IDDUTYPE +"="+ iddutype +"&"+ IEcranLibelle.NAME_ACTION + "=";
		String urlModif= debutUrl + IEcranLibelle.VALUE_MODIFIER + "&"+IEcranLibelle.NAME_ID+"=";
		String urlSuppr = debutUrl  + IEcranLibelle.VALUE_SUPPRIMER + "&"+IEcranLibelle.NAME_ID+"=";
	for(int ii=0 ; ii < al.size() ; ii++)
		{
			String classe="";
			if (ii%2==0) classe="paire"; else classe="impaire";
			Libelle li=al.get(ii);
      out.write("\r\n");
      out.write("\t\t\t<tr class=\"");
      out.print(classe );
      out.write("\"><td>");
      out.print(li.getLibCode());
      out.write("</td>\r\n");
      out.write("\t\t\t<td><a CLASS=\"modifier\"href=\"");
      out.print(urlModif+li.getLibId());
      out.write('"');
      out.write('>');
      out.print(li.getLibLibelle());
      out.write("</a></td>\r\n");
      out.write("\t\t\t<td><a href=\"");
      out.print(urlSuppr+li.getLibId());
      out.write("\"><img src=\"../img/delete.png\" alt=\"supprimer\" /></a></td>\r\n");
      out.write("\t\t\t</tr>\r\n");
      out.write("\t\t");
}
      out.write("\r\n");
      out.write("\t\t</table>\r\n");
      out.write("\t\t</DIV>\r\n");
      out.write("\t");
}
	session.removeAttribute(IEcranLibelle.ATTR_LISTE);
      out.write("\r\n");
      out.write("</form>\r\n");
}
      out.write('\r');
      out.write('\n');
 if(ecran.equals(IEcranLibelle.ATTR_ECRANSAISIE)){
	
	
      out.write("\r\n");
      out.write("<SCRIPT language=\"javascript\" >\r\n");
      out.write("\tvar tableau={'libCode':'estCode','libLibelle':'estRenseigne'};\r\n");
      out.write("</SCRIPT>\r\n");
      out.write("\r\n");
      out.write("<FORM method=\"POST\" action=\"../LibelleServlet\"  onsubmit=\"javascript:return verif(document,tableau);\"  id=\"formulairePost\">\r\n");
      out.write("<H1>Saisie</H1>\r\n");
      out.write("\t");

	String value_bouton=IEcranLibelle.VALUE_VALIDER,value_code="",value_libelle="",Typedinput="text";
	int value_id=0;
	if(session.getAttribute(IEcranLibelle.ATTR_BUTACTION)!=null){
		if(session.getAttribute(IEcranLibelle.ATTR_BUTACTION).equals(IEcranLibelle.VALUE_SUPPRIMER)){
			value_bouton=IEcranLibelle.VALUE_CONFIRMER;
			out.print("<P> Étes-vous sur de supprimer </P>");
			Typedinput="hidden";
		}
		if(session.getAttribute(IEcranLibelle.ATTR_ID)!=null){
			value_id=(Integer)session.getAttribute(IEcranLibelle.ATTR_ID);
			value_code=(String)session.getAttribute(IEcranLibelle.ATTR_CODE);
			value_libelle=(String)session.getAttribute(IEcranLibelle.ATTR_LIBELLE);
			
		}
	}
	String erreurCode="",erreurLibelle="";
	Hashtable<String,String> listeErreurs=null;
	if((Hashtable)session.getAttribute(IEcranLibelle.ATTR_ERREURS)!=null){
		listeErreurs=(Hashtable)session.getAttribute(IEcranLibelle.ATTR_ERREURS);
		if(listeErreurs.containsKey(base.TableLibelle.LIBCODE)){
			erreurCode=listeErreurs.get(base.TableLibelle.LIBCODE);
		}
		if(listeErreurs.containsKey(base.TableLibelle.LIBLIBELLE)){
			erreurLibelle=listeErreurs.get(base.TableLibelle.LIBLIBELLE);
		}
		
	}
      out.write(" \r\n");
      out.write("\t<p CLASS=\"champNom\">Code : ");
 if(session.getAttribute(IEcranLibelle.ATTR_BUTACTION).equals(IEcranLibelle.VALUE_SUPPRIMER)){out.print(value_code); } 
      out.write("<Input   class=\"champ\"  TYPE=\"");
      out.print(Typedinput );
      out.write("\" NAME=\"");
      out.print(IEcranLibelle.NAME_CODE);
      out.write("\" VALUE=\"");
      out.print(value_code );
      out.write("\" ID=\"libCode\"><SPAN CLASS=\"codeerreur\">");
      out.print( "&nbsp"+"&nbsp"+erreurCode);
      out.write("</SPAN></p>\r\n");
      out.write("\t<p CLASS=\"champNom\">Libellé : ");
 if(session.getAttribute(IEcranLibelle.ATTR_BUTACTION).equals(IEcranLibelle.VALUE_SUPPRIMER)){out.print(value_libelle);}
      out.write("<Input class=\"champ\"  TYPE=\"");
      out.print(Typedinput );
      out.write("\" NAME=\"");
      out.print(IEcranLibelle.NAME_LIBELLE);
      out.write("\" VALUE=\"");
      out.print(value_libelle );
      out.write("\" ID=\"libLibelle\"><SPAN CLASS=\"codeerreur\">");
      out.print( "&nbsp"+"&nbsp"+erreurLibelle);
      out.write("</SPAN></p>\r\n");
      out.write("\t\r\n");
      out.write("\t<Input TYPE=\"hidden\" NAME=\"");
      out.print(IEcranLibelle.NAME_IDDUTYPE);
      out.write("\" VALUE=\"");
      out.print(iddutype );
      out.write("\">\r\n");
      out.write("\t<Input TYPE=\"hidden\" NAME=\"");
      out.print(IEcranLibelle.NAME_ID);
      out.write("\" VALUE=\"");
      out.print(value_id );
      out.write("\">\r\n");
      out.write("\r\n");
      out.write("\t<INPUT  CLASS=\"submit\" ID=\"annuler\" TYPE=\"submit\" onclick=\"javascript:btn='annuler';\" NAME=\"");
      out.print(IEcranLibelle.NAME_ACTION);
      out.write("\" VALUE=\"");
      out.print(IEcranLibelle.VALUE_ANNULER);
      out.write("\" >\r\n");
      out.write("\t<INPUT  CLASS=\"submit\" TYPE=\"submit\"  onclick=\"javascript:btn='valider';\" NAME=\"");
      out.print(IEcranLibelle.NAME_ACTION);
      out.write("\" VALUE=\"");
      out.print(value_bouton );
      out.write("\">\r\n");
      out.write("\t\r\n");
      out.write("\t");
 
	session.removeAttribute(IEcranLibelle.ATTR_BUTACTION);
	session.removeAttribute(IEcranLibelle.ATTR_ID);
	session.removeAttribute(IEcranLibelle.ATTR_CODE);
	session.removeAttribute(IEcranLibelle.ATTR_LIBELLE);
	session.removeAttribute(IEcranLibelle.ATTR_LISTE);
	session.removeAttribute(IEcranLibelle.ATTR_ERREURS);
	
      out.write("\r\n");
      out.write("</FORM>\r\n");
} 
      out.write("\r\n");
      out.write("<FOOTER>\r\n");
      out.write("\t<A href=\"../index.jsp\">Page d'accueil</A>\r\n");
      out.write("</FOOTER>\r\n");
      out.write("\r\n");
      out.write("<SCRIPT language=\"javascript\" type=\"text/javascript\" src=\"../js/controle.js\"></SCRIPT>\r\n");
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
