<%@page import="vues.IEcranLibelle"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Hashtable"%>
<%@page import="parametrage.Libelle"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Maintenance des libellés</title>
<link href="../style/personne.css" rel="stylesheet" type="text/css"   >
</head>
<body>
<%
String ecran=(String)session.getAttribute(IEcranLibelle.ATTR_ECRAN);
int iddutype=-1;
if (session.getAttribute(IEcranLibelle.ATTR_IDDUTYPE)!=null) 
	iddutype=((Integer)session.getAttribute(IEcranLibelle.ATTR_IDDUTYPE)).intValue();
session.removeAttribute(IEcranLibelle.ATTR_ECRAN);
if (ecran==null) ecran=IEcranLibelle.ATTR_ECRANLISTE;

if(ecran.equals(IEcranLibelle.ATTR_ECRANLISTE))
{%>

<form method="get" action="../LibelleServlet" id="formulaireGet">
<H1>Recherche</H1>
<%
	ArrayList<Libelle> entetes=null;
	if (session.getAttribute(IEcranLibelle.ATTR_LISTEENTETES)!=null)
			entetes=(ArrayList<Libelle>)session.getAttribute(IEcranLibelle.ATTR_LISTEENTETES);
	if (entetes!=null)
	{
		%>Choix du type : &nbsp;<select name="<%=IEcranLibelle.NAME_IDDUTYPE%>"><%
		String selected="";
		for(int ii=0 ; ii < entetes.size() ; ii++)
		{
			Libelle li=entetes.get(ii);
			selected=( li.getLibId()==iddutype ) ? "selected=\"selected\"" : "";%>
			<option value="<%=li.getLibId()%>" <%=selected%>><%=li.getLibLibelle()%></option>
		<%}%>
		</select>
		<input type="submit" name="<%=IEcranLibelle.NAME_ACTION%>" value="<%=IEcranLibelle.VALUE_RECHERCHER%>"/>
		<input type="submit" name="<%=IEcranLibelle.NAME_ACTION%>" value="<%=IEcranLibelle.VALUE_AJOUTER%>"/>
	<%}
	ArrayList<Libelle> al=(ArrayList<Libelle>)session.getAttribute(IEcranLibelle.ATTR_LISTE);
	if (al!=null)
	{%>
	<DIV class="listeprincipale">
	<table>
	<%	String debutUrl="../LibelleServlet?" + IEcranLibelle.NAME_IDDUTYPE +"="+ iddutype +"&"+ IEcranLibelle.NAME_ACTION + "=";
		String urlModif= debutUrl + IEcranLibelle.VALUE_MODIFIER + "&"+IEcranLibelle.NAME_ID+"=";
		String urlSuppr = debutUrl  + IEcranLibelle.VALUE_SUPPRIMER + "&"+IEcranLibelle.NAME_ID+"=";
	for(int ii=0 ; ii < al.size() ; ii++)
		{
			String classe="";
			if (ii%2==0) classe="paire"; else classe="impaire";
			Libelle li=al.get(ii);%>
			<tr class="<%=classe %>"><td><%=li.getLibCode()%></td>
			<td><a CLASS="modifier"href="<%=urlModif+li.getLibId()%>"><%=li.getLibLibelle()%></a></td>
			<td><a href="<%=urlSuppr+li.getLibId()%>"><img src="../img/delete.png" alt="supprimer" /></a></td>
			</tr>
		<%}%>
		</table>
		</DIV>
	<%}
	session.removeAttribute(IEcranLibelle.ATTR_LISTE);%>
</form>
<%}%>
<% if(ecran.equals(IEcranLibelle.ATTR_ECRANSAISIE)){
	
	%>
<SCRIPT language="javascript" >
	var tableau={'libCode':'estCode','libLibelle':'estRenseigne'};
</SCRIPT>

<FORM method="POST" action="../LibelleServlet"  onsubmit="javascript:return verif(document,tableau);"  id="formulairePost">
<H1>Saisie</H1>
	<%
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
		
	}%> 
	<p CLASS="champNom">Code : <% if(session.getAttribute(IEcranLibelle.ATTR_BUTACTION).equals(IEcranLibelle.VALUE_SUPPRIMER)){out.print(value_code); } %><Input   class="champ"  TYPE="<%=Typedinput %>" NAME="<%=IEcranLibelle.NAME_CODE%>" VALUE="<%=value_code %>" ID="libCode"><SPAN CLASS="codeerreur"><%= "&nbsp"+"&nbsp"+erreurCode%></SPAN></p>
	<p CLASS="champNom">Libellé : <% if(session.getAttribute(IEcranLibelle.ATTR_BUTACTION).equals(IEcranLibelle.VALUE_SUPPRIMER)){out.print(value_libelle);}%><Input class="champ"  TYPE="<%=Typedinput %>" NAME="<%=IEcranLibelle.NAME_LIBELLE%>" VALUE="<%=value_libelle %>" ID="libLibelle"><SPAN CLASS="codeerreur"><%= "&nbsp"+"&nbsp"+erreurLibelle%></SPAN></p>
	
	<Input TYPE="hidden" NAME="<%=IEcranLibelle.NAME_IDDUTYPE%>" VALUE="<%=iddutype %>">
	<Input TYPE="hidden" NAME="<%=IEcranLibelle.NAME_ID%>" VALUE="<%=value_id %>">

	<INPUT  CLASS="submit" ID="annuler" TYPE="submit" onclick="javascript:btn='annuler';" NAME="<%=IEcranLibelle.NAME_ACTION%>" VALUE="<%=IEcranLibelle.VALUE_ANNULER%>" >
	<INPUT  CLASS="submit" TYPE="submit"  onclick="javascript:btn='valider';" NAME="<%=IEcranLibelle.NAME_ACTION%>" VALUE="<%=value_bouton %>">
	
	<% 
	session.removeAttribute(IEcranLibelle.ATTR_BUTACTION);
	session.removeAttribute(IEcranLibelle.ATTR_ID);
	session.removeAttribute(IEcranLibelle.ATTR_CODE);
	session.removeAttribute(IEcranLibelle.ATTR_LIBELLE);
	session.removeAttribute(IEcranLibelle.ATTR_LISTE);
	session.removeAttribute(IEcranLibelle.ATTR_ERREURS);
	%>
</FORM>
<%} %>
<FOOTER>
	<A href="../index.jsp">Page d'accueil</A>
</FOOTER>

<SCRIPT language="javascript" type="text/javascript" src="../js/controle.js"></SCRIPT>
</body>
</html>