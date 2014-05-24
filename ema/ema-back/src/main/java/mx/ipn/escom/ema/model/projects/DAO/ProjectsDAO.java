package mx.ipn.escom.ema.model.projects.DAO;

import java.util.List;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.users.User;

import mx.ipn.escom.ema.model.entities.Projects;
import mx.ipn.escom.ema.model.entities.Resources;
import mx.ipn.escom.ema.model.entities.Users;


public interface ProjectsDAO {
	
	public void addProject(Projects project, Users user);
	/* Este se va a usar cuando se una con la logica
	public void updateProject(String oldName, String newName, Users user);*/
	public void updateProject(Projects project, String newName, Users user);
	public List<Projects> showProjects(Users user);
	public void deleteProject(Users user, Projects project);
	/*Agrega referencia del recurso creado al proyecto*/;
	public void addResourcetoProjectTest(Resources resource, Projects project);
	/*Agrega referencia de usuario al proyecto*/
	public void addReferenceOfUser(Users user, Projects project);
	
	public Projects findProject(Projects project, Users user);
	public Projects validateProject(Projects project, Users user);
	
	/*Elimina recurso del proyecto*/
	public void deleteCSSfromProject(Projects project, Key resource, Users user);
	public void deleteHTMLfromProject(Projects project, Key resource, Users user);
	
	
	/*Elimina recurso del proyecto*/
	public void deleteCSSfromProjectTest(Projects project, Users user);
}
