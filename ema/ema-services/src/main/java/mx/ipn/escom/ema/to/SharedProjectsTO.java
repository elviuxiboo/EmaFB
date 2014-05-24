package mx.ipn.escom.ema.to;

import java.io.Serializable;
import java.util.List;

import com.google.appengine.api.datastore.Key;


public class SharedProjectsTO implements Serializable {
    

    private ProjectsTO project;
    private UsersTO userReceiver;
    private UsersTO userSharingProject;
	public ProjectsTO getProject() {
		return project;
	}
	public void setProject(ProjectsTO project) {
		this.project = project;
	}
	public UsersTO getUserReceiver() {
		return userReceiver;
	}
	public void setUserReceiver(UsersTO userReceiver) {
		this.userReceiver = userReceiver;
	}
	public UsersTO getUserSharingProject() {
		return userSharingProject;
	}
	public void setUserSharingProject(UsersTO userSharingProject) {
		this.userSharingProject = userSharingProject;
	}
    
   
    

}
