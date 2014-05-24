package mx.ipn.escom.ema.model.resources.DAO;

import java.util.Date;
import java.util.List;

import com.google.appengine.api.datastore.Key;

import mx.ipn.escom.ema.model.entities.CSSResources;
import mx.ipn.escom.ema.model.entities.HTMLResources;
import mx.ipn.escom.ema.model.entities.Projects;
import mx.ipn.escom.ema.model.entities.Resources;
import mx.ipn.escom.ema.model.entities.Users;

public interface ResourceDAOcss {
	
	/*Agregar CSS a nuevo Proyecto*/
	public void addResourceCSS(CSSResources css);
	
	/*Modificar codigo*/
	public void updateResourceCSS(CSSResources css, Projects project, Users user, String code);
	
	/*Muestra lista de recursos*/
	public List<CSSResources> listResourcesCss(Resources resource);
	
	/*Elimina recurso */
	public void deleteResourceCSS(CSSResources css, Projects project, Users user);
	public void deleteAllCSS(Resources resource);
	
	/*Agrega css a proyecto existente*/
	public void addResourceCSStoProject(CSSResources css, Projects project, Users user);
	
	/*Agrega referencia del recurso creado a nuevo css*/
	public void addReferencetoResource(Resources resource, CSSResources idcss);
	
	/*Busca el recurso css que tenga la referencia del recurso guardado en el proyecto*/
	public CSSResources ResourcesCssFromProject(Projects project);
	
	/*Encontrar CSS de proyecto*/
	public CSSResources findCSSofProject(CSSResources css, Projects project);
	public CSSResources findCSSofProjectofUser(CSSResources css, Projects project, Users user);
	public CSSResources existingCSSinProject(CSSResources css, Projects project, Users user);
	
	/*Modificar nombre del recurso*/
	public void modifyName(CSSResources css, Projects project, Users user, String name);
	
	/*Muestra los css del proyecto*/
	public List<CSSResources> showCSSResourcesFromProject(Projects project,Users user);
}
