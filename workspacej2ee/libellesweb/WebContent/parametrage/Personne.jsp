<%@page import="base.TablePersonne"%>
<%@page import="vues.IEcranPersonne"%>
<%@page import="parametrage.Personne"%>
<%@page import="parametrage.Libelle"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Hashtable"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Personnes</title>
<link href="../style/personne.css" rel="stylesheet" type="text/css"   >
<script language="javascript" type="text/javascript" src="../js/controle.js"></script>
</head>
<body>
	<% 
	//initialisation des variables
	String recherche="",ecran=(String)session.getAttribute(IEcranPersonne.ATTR_ECRAN);
	
	session.removeAttribute(IEcranPersonne.ATTR_ECRAN);
	
	if(session.getAttribute(IEcranPersonne.ATTR_RECHERCHE)!=null) recherche=(String)session.getAttribute(IEcranPersonne.ATTR_RECHERCHE).toString();
	if(ecran==null) ecran=IEcranPersonne.ATTR_ECRANLISTE;
	
	if(ecran.equals(IEcranPersonne.ATTR_ECRANLISTE)){

		if(session.getAttribute(IEcranPersonne.ATTR_RECHERCHE)!=null) recherche=(String)session.getAttribute(IEcranPersonne.ATTR_RECHERCHE);
	%>
	<FORM method="get" action="../PersonneServlet" >
		<% 
		//initialisation des variables
		ArrayList<Personne> listePersonne=new ArrayList<Personne>();
		String urlDebut,urlModif,urlSuppr,urlFin;
		if(session.getAttribute(IEcranPersonne.ATTR_LISTEPERSONNE)!=null) listePersonne=(ArrayList<Personne>)session.getAttribute(IEcranPersonne.ATTR_LISTEPERSONNE);
		
		%>
		<H1>Recherche</H1>
		<INPUT onkeyup="javascript:return test('../PersonneServlet?<%= IEcranPersonne.NAME_ACTION%>=<%= IEcranPersonne.VALUE_COMPLETER%>&<%= IEcranPersonne.NAME_RECHERCHE%>='+this.value);" autocomplete="off" TYPE="TEXT" ID="recherche" NAME="<%= IEcranPersonne.NAME_RECHERCHE %>" VALUE="<%= recherche %>"/>
		<DIV ID="listepropose">
		</DIV>
		
		<INPUT TYPE="SUBMIT" NAME="<%= IEcranPersonne.NAME_ACTION %>" VALUE="<%=IEcranPersonne.VALUE_RECHERCHER%>"/>
		<INPUT TYPE="SUBMIT" NAME="<%= IEcranPersonne.NAME_ACTION %>" VALUE="<%=IEcranPersonne.VALUE_CREER%>"/>
		<%if(listePersonne.size()>0){%>
		<DIV class="listeprincipale">
		<UL class="liste">
			<%
			urlDebut="../PersonneServlet?"+ IEcranPersonne.NAME_ACTION +"=";
			urlFin="&"+IEcranPersonne.NAME_ID+"=";
			urlModif=urlDebut+IEcranPersonne.VALUE_MODIFIER+urlFin;
			urlSuppr=urlDebut+IEcranPersonne.VALUE_SUPPRIMER+urlFin;
			
			for(int ii=0;ii<listePersonne.size();ii++){
			String classe="";
			if (ii%2==0) classe="paire"; else classe="impaire";
			%>  
			<LI class="<%= classe %>">
				<SPAN CLASS="infopersonne">
					<%=
					listePersonne.get(ii).getCivilité().getLibLibelle()+ "&nbsp" +
					listePersonne.get(ii).getPerNom()+ "&nbsp" +
					listePersonne.get(ii).getPerPrenom() 
					%>
				</SPAN>
				<a href="<%=urlSuppr+listePersonne.get(ii).getPerID() %>" >
					<IMG src="../img/delete.png" alt="supprimer"/>
				</a>
				<a href="<%=urlModif+listePersonne.get(ii).getPerID() %>" >
					<IMG src="../img/edit.png" alt="Éditer"/>
				</a>
			</LI>
			<%
			}
			session.removeAttribute(IEcranPersonne.ATTR_LISTEPERSONNE);
			%>
		</UL>	
		</DIV>
		<%}%>	
	</FORM>
	<%
	}
	if(ecran.equals(IEcranPersonne.ATTR_ECRANSAISIE)){
	%>
	<FORM method="POST" action="../PersonneServlet">
	<%
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
	%>
		<H1>Saisie</H1>
		<INPUT TYPE="hidden" NAME="<%= IEcranPersonne.NAME_BUTACTION %>" VALUE="<%= action%>" />
		<INPUT TYPE="hidden" NAME="<%= IEcranPersonne.NAME_ID %>" VALUE="<%= id%>" />
		<P CLASS="champNom" >Nom : <INPUT  CLASS="champ" TYPE="text" NAME="<%= IEcranPersonne.NAME_NOM %>" VALUE="<%= nom %>" />
			<SPAN CLASS="codeerreur">&nbsp &nbsp <%= erreurNom %></SPAN>
		</P>
		<P  CLASS="champNom" >Prénom : <INPUT  CLASS="champ" TYPE="text" NAME="<%= IEcranPersonne.NAME_PRENOM %>" VALUE="<%= prenom%>" />
			<SPAN CLASS="codeerreur">&nbsp &nbsp <%= erreurPrenom %></SPAN>
		</P>
		<P  CLASS="champNom" >Civilité : <SELECT  CLASS="champ" NAME="<%= IEcranPersonne.NAME_CIVID %>">
		<%
		for(int ii=0;ii<listeCivilite.size();ii++){
			if (!action.equals(IEcranPersonne.VALUE_CREER )){
				if(listeCivilite.get(ii).getLibId()==civilite.getLibId()){
					selectedLibelle="selected=\"selected\"" ;
				}
			}
		%>
		<OPTION VALUE="<%= listeCivilite.get(ii).getLibId()%>" <%= selectedLibelle %> >
			<%= listeCivilite.get(ii).getLibLibelle() %>
		</OPTION>
		<%
		selectedLibelle="" ;
		}
		%>	
		</SELECT><SPAN CLASS="codeerreur">&nbsp &nbsp <%= erreurCivilite%></SPAN></P>
		<P CLASS="champNom" >Pays :<SELECT  CLASS="champ" NAME="<%= IEcranPersonne.NAME_PAYID %>">
			<OPTION  ></OPTION>
		<%
		for(int ii=0;ii<listePays.size();ii++){
			if (pays!=null ){
				if(listePays.get(ii).getLibId()==pays.getLibId()){
					selectedLibelle="selected=\"selected\"" ;
				}
			}
		%>
			<OPTION VALUE="<%= listePays.get(ii).getLibId()%>" <%= selectedLibelle %> >
				<%= listePays.get(ii).getLibLibelle() %>
			</OPTION>	
		<%
		selectedLibelle="" ;
		}
		%>
		</SELECT></P>
		<P  CLASS="champNom" >Nationalité : <SELECT  CLASS="champ" NAME="<%= IEcranPersonne.NAME_NATID %>">
			
			<OPTION  ></OPTION>
		<%
		for(int ii=0;ii<listeNationalite.size();ii++){
			if (nationalite!=null){
				if(listeNationalite.get(ii).getLibId()==nationalite.getLibId()){
					selectedLibelle="selected=\"selected\"" ;
				}
			}
		%>
			
			<OPTION VALUE="<%= listeNationalite.get(ii).getLibId()%>" <%= selectedLibelle %>>
				<%= listeNationalite.get(ii).getLibLibelle() %>
			</OPTION>	
		<%
		selectedLibelle="" ;
		}
		%>
		</SELECT ></P>
		<P  CLASS="champNom" >Commentaires : <TEXTAREA  CLASS="champ" NAME="<%= IEcranPersonne.NAME_COMMENTAIRE %>" ><%=commentaire %></TEXTAREA></P>
		
		
		<INPUT CLASS="submit" TYPE="submit" NAME="<%= IEcranPersonne.NAME_ACTION %>" value="<%= IEcranPersonne.VALUE_ANNULER %>"/>
		<INPUT CLASS="submit" TYPE="submit" NAME="<%= IEcranPersonne.NAME_ACTION %>" value="<%= validation %>"/>
	</FORM>
	<% 
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
	%>
	<FOOTER>
		<A href="../index.jsp">Page d'accueil</A>
	</FOOTER>
</body>
</html>