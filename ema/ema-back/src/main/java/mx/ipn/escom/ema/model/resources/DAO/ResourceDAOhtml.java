package mx.ipn.escom.ema.model.resources.DAO;


import java.util.List;

import mx.ipn.escom.ema.model.entities.CSSResources;
import mx.ipn.escom.ema.model.entities.HTMLResources;
import mx.ipn.escom.ema.model.entities.Projects;
import mx.ipn.escom.ema.model.entities.Resources;
import mx.ipn.escom.ema.model.entities.Users;

public interface ResourceDAOhtml {
	

	
	/*Muestra lista de recursos
	public List<HTMLResources> findResourcesHTML(Resources resource);*/
	
	/*Elimina recurso */
	public void deleteResourceHTML(HTMLResources html, Projects project, Users user);
	public void deleteAllHTML(Resources resource);
	
	/*Actualiza codigo del recurso*/
	public void updateResourceHTML(HTMLResources html, Projects project, Users user, String code);
	
	/* Agrega referencia del recurso creado en Resources*/
	public void addReferencetoResource(Resources resource, HTMLResources html);
	
	/*Agrega html a proyecto existente*/
	public void addResourceHTMLtoProject(HTMLResources html, Projects project, Users user);
	
	/*Busca el recurso html que tenga la referencia del recurso guardado en el proyecto*/
	public HTMLResources findHTMLofProjectofUser(HTMLResources html, Projects project, Users user);
	
	/*Encontrar html de proyecto*/
	public HTMLResources findHTMLofProject(HTMLResources html, Projects project);
	
	/*Modificar nombre del recurso*/
	public void modifyName(HTMLResources html, Projects project, Users user, String name);
	
	/*Mostrar recursos HTML del proyecto*/
	public List<HTMLResources> showHTMLResourcesFromProject(Projects project, Users user);
	
	public HTMLResources existingHTMLinProject(HTMLResources html, Projects project, Users user);
	
	//public HTMLResources findHTML(HTMLResources html, Users user);
}
