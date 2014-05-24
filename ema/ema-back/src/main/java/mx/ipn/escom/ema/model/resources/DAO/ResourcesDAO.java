package mx.ipn.escom.ema.model.resources.DAO;

import java.util.List;

import com.google.appengine.api.datastore.Key;

import mx.ipn.escom.ema.model.entities.CSSResources;
import mx.ipn.escom.ema.model.entities.HTMLResources;
import mx.ipn.escom.ema.model.entities.Projects;
import mx.ipn.escom.ema.model.entities.Resources;
import mx.ipn.escom.ema.model.entities.Users;

public interface ResourcesDAO {
	
	public void addResource(Resources resource);
	public void deleteResourceCSS(Resources resource);
	public void deleteResourceHTML(HTMLResources resource, Projects project);
	public Resources findResourcebyId(Key id);
	/* Estos son los que uso para agregar la referencia del recurso al Recurso css o html */
	public void addReferenceOfCSS(CSSResources css, Resources resource);
	public void addReferenceOfHTML(HTMLResources html, Resources resource);
	public void addReferenceOfProject(Projects project, Resources resource);
	public Resources findResourceFromProject(Projects project);
	public List<Key> findResourceListFromProject(Projects project);
	public List<Key> findResourceListFromUserProject(Projects project, Users user);
}
