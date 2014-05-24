

package mx.ipn.escom.ema.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import mx.ipn.escom.ema.model.entities.SharedProjects;
import mx.ipn.escom.ema.services.ProjectsService;
import mx.ipn.escom.ema.services.ShareProjectsService;
import mx.ipn.escom.ema.services.UsersService;
import mx.ipn.escom.ema.services.impl.ProjectServiceimpl;
import mx.ipn.escom.ema.services.impl.SharedProjectsServiceimpl;
import mx.ipn.escom.ema.services.impl.UserServiceimpl;
import mx.ipn.escom.ema.to.ProjectsTO;
import mx.ipn.escom.ema.to.SharedProjectsTO;
import mx.ipn.escom.ema.to.UsersTO;



/**
 *
 * @author Alejandra
 */
@ManagedBean(name="shareProject")
@RequestScoped
public class SharingProjectBean implements Serializable {


    
    private static final long serialVersionUID = 2264980296154279701L;

    
    private String project;
    private String txt;
    private UsersTO userTO = new UsersTO();
    private UsersTO userTOReceiver = new UsersTO();
    private ProjectsTO projectTO = new ProjectsTO();
	private ProjectsService projectService = new ProjectServiceimpl();
	private List<String> projectList = new ArrayList<String>();
	private UsersService userService = new UserServiceimpl(); 
	private List<String> userList = new ArrayList<String>();
	private ShareProjectsService shareProjectService = new SharedProjectsServiceimpl(); 
	private SharedProjectsTO sharedProject = new SharedProjectsTO();
	
    public ProjectsTO getProjectTO() {
		return projectTO;
	}


	public void setProjectTO(ProjectsTO projectTO) {
		this.projectTO = projectTO;
	}


	public List<String> getProjectList() {
		return projectList;
	}


	public void setProjectList(List<String> projectList) {
		this.projectList = projectList;
	}


	public String getProject() {
        return project;
    }


    public void setProject(String project) {
        this.project = project;
    }


    public String getTxt() {
        return txt;
    }


    public void setTxt(String txt) {
        this.txt = txt;
    }

    
   public List<String> getProjects(){
	   UserService userService = UserServiceFactory.getUserService();
	    User user = userService.getCurrentUser();// Obtiene la sesion de google
	    userTO.setUser(user.getEmail());
  	//userTO.setUser("andrea@example.com");
	List<ProjectsTO> listProjects = projectService.showProjects(userTO);
	System.out.println(listProjects);
	for(ProjectsTO projectResult: listProjects){
			projectList.add(projectResult.getName());
		
	}
	 return projectList;  
   }
   
   public List<String> getUsers(String query){
	  // List<UsersTO> listUsers = userService.getAllUsers();
	   for(UsersTO userTOResult : userService.getAllUsers()){
		   if(userTOResult.getUser().startsWith(query)){
			   userList.add(userTOResult.getUser());
		   }
	   }
	   return userList;
   }
   
   public SharedProjectsTO shareProject(){
	   UserService userService = UserServiceFactory.getUserService();
	    User user = userService.getCurrentUser();// Obtiene la sesion de google
	    userTO.setUser(user.getEmail());
	//   userTO.setUser("andrea@example.com");
	   projectTO.setName(project);
	   userTOReceiver.setUser(txt);
	   sharedProject.setUserReceiver(userTOReceiver);
	   sharedProject.setProject(projectTO);
	   sharedProject.setUserSharingProject(userTO);
	   shareProjectService.shareProject(userTO, projectTO, userTOReceiver);
	   return sharedProject;
   }
}