package mx.ipn.escom.ema.services.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import mx.ipn.escom.ema.model.entities.CSSResources;
import mx.ipn.escom.ema.model.entities.HTMLResources;
import mx.ipn.escom.ema.model.entities.Projects;
import mx.ipn.escom.ema.model.entities.SharedProjects;
import mx.ipn.escom.ema.model.entities.Users;
import mx.ipn.escom.ema.model.projects.DAO.ProjectsDAO;
import mx.ipn.escom.ema.model.projects.DAO.impl.ProjectsDAOimpl;
import mx.ipn.escom.ema.model.resources.DAO.ResourceDAOcss;
import mx.ipn.escom.ema.model.resources.DAO.ResourceDAOhtml;
import mx.ipn.escom.ema.model.resources.DAO.SharedProjectsDAO;
import mx.ipn.escom.ema.model.resources.DAO.impl.CSSResourcesDAOimpl;
import mx.ipn.escom.ema.model.resources.DAO.impl.HTMLResourcesDAOimpl;
import mx.ipn.escom.ema.model.resources.DAO.impl.SharedProjectsDAOimpl;
import mx.ipn.escom.ema.model.user.DAO.UsersDAO;
import mx.ipn.escom.ema.model.user.DAO.impl.UserDAOimpl;
import mx.ipn.escom.ema.services.CSSResourceService;
import mx.ipn.escom.ema.services.HTMLResourceService;
import mx.ipn.escom.ema.services.ShareProjectsService;
import mx.ipn.escom.ema.to.CSSResourceTO;
import mx.ipn.escom.ema.to.HTMLResourceTO;
import mx.ipn.escom.ema.to.ProjectsTO;
import mx.ipn.escom.ema.to.UsersTO;

public class SharedProjectsServiceimpl implements ShareProjectsService, Serializable{
	
	
	
	/*Compartir Proyecto*/
	  public void shareProject(UsersTO userTO, ProjectsTO projectTO, UsersTO userReceiver){
	  Users user = new Users();
	  user.setUser(userTO.getUser());
	  Projects project = new Projects();
	  project.setName(projectTO.getName());
	  	SharedProjects sharedProject = new SharedProjects();
		SharedProjectsDAOimpl srd = new SharedProjectsDAOimpl();
		SharedProjectsDAO sharedProjectsDAO = new SharedProjectsDAOimpl();
		sharedProjectsDAO.shareProject(sharedProject, project, user);
		Users userReceiveProject = new Users();
		userReceiveProject.setUser(userReceiver.getUser());
		sharedProjectsDAO.addUsersReceivers(userReceiveProject, sharedProject);
		sharedProjectsDAO.addReferenceOfProjectReceived(sharedProject, userReceiveProject);
	  }
	
	/*Mostrar proyectos compartidos*/
	  public List<ProjectsTO> showSharedProjects(UsersTO userTO){
	  Users user = new Users();
	  user.setUser(userTO.getUser());
	  Projects projectResult = new Projects();
	  List<ProjectsTO> listSharedProjectTO = new ArrayList<ProjectsTO>(); 
	  SharedProjectsDAO sharedProjectsDAO = new SharedProjectsDAOimpl();
	  List<Projects> list = sharedProjectsDAO.showProjects(user);
	  UsersDAO userDAO = new UserDAOimpl();
	  for(Projects projects: sharedProjectsDAO.showProjects(user)){
		  ProjectsTO projectTO = new ProjectsTO();
		  Users userSharing = new Users();
		  userSharing.setId(projects.getUser());
		  Users userResult = userDAO.findUser(userSharing);
		  UsersTO userProject = new UsersTO();
		  userProject.setUser(userResult.getUser());
		  projectTO.setName(projects.getName());
		  projectTO.setUser(userProject);
		  listSharedProjectTO.add(projectTO);
		  System.out.println("lista en servicio " + listSharedProjectTO);
	  }
	  return listSharedProjectTO;
	  }

	  /*Mostrar usuario que compartio el proyecto*/
	

}
