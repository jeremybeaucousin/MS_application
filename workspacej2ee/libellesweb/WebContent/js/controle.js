	
var btn="";

function verif(doc,listErreur){

	var formOk=true;
	var ok=true,lid,e;
	
	if(btn=="annuler") return true;
	
	for(lid in listErreur){
		var elem=doc.getElementById(lid), fonc=listErreur[lid];
		try
		{
			ok=eval(fonc+"('"+elem.value+"')");
		}
		catch (e) 
		{ 
			alert(e); 
			ok=true; 
		}
		if (!ok)
		{
			// ajoute erreur dans la classe
			if(elem.className.indexOf(" erreur") ==-1) elem.className += " erreur";
			formOk=false;
		}
		// enleve erreur dans la classe
		else elem.className=elem.className.replace(" erreur","");
	}
	return formOk;
}

function estCode(val)
{
	return testeRegExp("^[A-Z]{1,10}$",val);
}
function estRenseigne(val)
{
	return testeRegExp("^.*\\S.*",val);
}
function testeRegExp(exp, val)
{
	var re = new RegExp(exp);
	return (re.test(val));
}	
	
function test(url)
{
	var requete=( window.XMLHttpRequest ?
			new XMLHttpRequest() :
			new ActiveXObject("Microsoft.XMLHTTP"));
	
	
	requete.open("GET",url,true);
	requete.onreadystatechange=function() {reception(requete);};
	requete.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
	requete.send("null");
	
}
function reception(req)
{
	/*var reponse;
	if (req.readyState==4)
	{
		if (req.status==200)
		{
			reponse=req.responseText;
			eval(reponse);
			var listepropose=document.getElementById("listepropose");	
			var recherche=document.getElementById("recherche");
			
			listepropose.innerHTML="";
			for(personne in tab){
				listepropose.innerHTML+="<DIV>"+tab[personne]+"</DIV>";
			}
			
			recherche.value=resultat;
		}
		else
		{
			alert('erreur HTTP : ' + req.status);
		}
	}	*/
	var reponse;
	if (req.readyState==4)
	{
		if (req.status==200)
		{
			reponse=req.responseXML;
			if(reponse.getElementsByTagName("RESULTAT")[0].firstChild!=null){ 
				resultat=reponse.getElementsByTagName("RESULTAT")[0].firstChild.nodeValue;
			}
			else{
				resultat="";
			}
			var listepropose=document.getElementById("listepropose");	
			var recherche=document.getElementById("recherche");
			
			recherche.value=resultat;
			var tab=reponse.getElementsByTagName("PERSONNE");
			
			listepropose.innerHTML="";
		
			var innerhtml="";		
			
			for(ii=0;ii<tab.length;ii++){
				innerhtml+="<DIV onclick=\"javascrip:return remplissageInput(this.innerHTML)\">";
				entite = tab[ii].childNodes;
				for(jj=0;jj<entite.length;jj++){
					innerhtml+=entite[jj].firstChild.nodeValue+" ";
				}	
				innerhtml+="</DIV>";
			}
			listepropose.innerHTML+=innerhtml;
			
		}
		else
		{
			alert('erreur HTTP : ' + req.status);
		}
	}
}
function remplissageInput(nom){
	var recherche=document.getElementById("recherche");
	var indiceEspace=-1;
	var i=0;
	
	while (indiceEspace<0){
		if(nom.substring(i,i+1)==" "){
			indiceEspace=i;
		}
		i++;
	}
	recherche.value=nom.substring(0,indiceEspace);
	
}