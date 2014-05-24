package mx.ipn.escom.ema.model.resources.DAO;

import java.util.List;

import com.google.appengine.api.datastore.Key;

import mx.ipn.escom.ema.model.entities.Projects;
import mx.ipn.escom.ema.model.entities.SharedResources;
import mx.ipn.escom.ema.model.entities.Users;

public interface SharedResourcesDAO {
	
	/*Agregar */
	public void shareResource(SharedResources sharedResource, Projects project, Users user);
	public List<SharedResources> showSharedResources( Users user);
	public List<Projects> showProjects(Users user);
	public void addUsersReceivers(Users userReceiver, SharedResources sharedResource);
	public void addReferenceOfProjectReceived(SharedResources sharedResource, Users user);
	public Projects returnProject(Users user);

}
