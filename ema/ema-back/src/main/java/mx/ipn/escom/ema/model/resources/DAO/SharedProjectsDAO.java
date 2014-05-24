package mx.ipn.escom.ema.model.resources.DAO;

import java.util.List;

import mx.ipn.escom.ema.model.entities.Projects;
import mx.ipn.escom.ema.model.entities.SharedProjects;
import mx.ipn.escom.ema.model.entities.Users;

public interface SharedProjectsDAO {
	
	public void shareProject(SharedProjects sharedResource, Projects project, Users user);
	public List<SharedProjects> showSharedResources( Users user);
	public List<Projects> showProjects(Users user);
	public void addUsersReceivers(Users userReceiver, SharedProjects sharedResource);
	public void addReferenceOfProjectReceived(SharedProjects sharedResource, Users user);
	public Users returnUser(Users user);

}
