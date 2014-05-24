package mx.ipn.escom.ema.services.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import mx.ipn.escom.ema.model.entities.Projects;
import mx.ipn.escom.ema.model.entities.Users;
import mx.ipn.escom.ema.model.projects.DAO.ProjectsDAO;
import mx.ipn.escom.ema.model.projects.DAO.impl.ProjectsDAOimpl;
import mx.ipn.escom.ema.model.user.DAO.UsersDAO;
import mx.ipn.escom.ema.model.user.DAO.impl.UserDAOimpl;
import mx.ipn.escom.ema.services.CSSResourceService;
import mx.ipn.escom.ema.services.HTMLResourceService;
import mx.ipn.escom.ema.services.ProjectsService;
import mx.ipn.escom.ema.to.CSSResourceTO;
import mx.ipn.escom.ema.to.HTMLResourceTO;
import mx.ipn.escom.ema.to.ProjectsTO;
import mx.ipn.escom.ema.to.UsersTO;

public class ProjectServiceimpl implements ProjectsService, Serializable{

	/*
	 * Agregar proyecto*/
	  public void addProject(ProjectsTO projectTO, UsersTO userTO){
	  Projects project = new Projects();
	  project.setName(projectTO.getName());
	  Users user = new Users();
	  user.setUser(userTO.getUser());
	  UsersDAO userDAO = new UserDAOimpl();
	  ProjectsDAO projectDAO = new ProjectsDAOimpl();
	  projectDAO.validateProject(project, user);
	  userDAO.addProject(project, user);
	  }
	 
	
	/*
	 * Eliminar proyecto*/
	  public void deleteProject(ProjectsTO projectTO, UsersTO userTO){
	  Projects project = new Projects();
	  project.setName(projectTO.getName());
	  Users user = new Users();
	  user.setUser(userTO.getUser());
	  ProjectsDAO projectDAO = new ProjectsDAOimpl();
	  projectDAO.deleteProject(user, project);
	  }
	
	/*
	 * Actualizar proyecto*/
	  public void updateProject(ProjectsTO projectTO, UsersTO userTO, String newName){
	  Projects project = new Projects();
	  project.setName(projectTO.getName());
	  Users user = new Users();
	  user.setUser(userTO.getUser());
	  ProjectsDAO projectDAO = new ProjectsDAOimpl();
	  projectDAO.updateProject(project, newName, user);
	  }
	
	/*
	 * Mostrar todos los proyectos del usuario*/
	 public List<ProjectsTO> showProjects(UsersTO userTO){
	/*  ProjectsDAO projectDAO = new ProjectsDAOimpl();
	  Users user = new Users();
	  user.setUser(userTO.getUser());
	  List<Projects> projectsKey = new ArrayList<Projects>();
	  List<ProjectsTO> projects = new ArrayList<ProjectsTO>();
	  ProjectsTO projectTO = null;
	  projectsKey = projectDAO.showProjects(user);
	  List<CSSResourceTO> cssList = new ArrayList<CSSResourceTO>();
	  List<HTMLResourceTO> htmlList = new ArrayList<HTMLResourceTO>();
	  CSSResourceService cssService = new CSSResourceServiceimpl();
	  HTMLResourceService htmlService = new HTMLResourceServiceimpl();
	  for(int i =0; i< projectsKey.size(); i++){
	  	Projects projectResult = projectsKey.get(i);
	  	projectTO.setName(projectResult.getName());
	  	projectTO.setUser(userTO);
	  	cssList = cssService.showCSSResources(projectTO, userTO);
	  	htmlList = htmlService.showHTMLResources(projectTO, userTO);
	  	projectTO.setHtml(htmlList);
	  	projectTO.setCss(cssList);
	  	projects.add(projectTO);
	  	
	  }*/
	/*	 ProjectsTO projectTO = new ProjectsTO();
		 ProjectsDAO projectDAO = new ProjectsDAOimpl();
		 Users user = new Users();
		 user.setUser(userTO.getUser());
		 List<Projects> listProjects = new ArrayList<Projects>();
		 listProjects = projectDAO.showProjects(user);
		 List<ProjectsTO> projects = new ArrayList<ProjectsTO>();
		 for(int i=0; i<listProjects.size(); i++){
			 Projects projectResult = listProjects.get(i);
			 projectTO.setName(projectResult.getName());
			 projects.add(projectTO);
		 }*/
		 ProjectsDAO projectDAO = new ProjectsDAOimpl();
		 Users user = new Users();
		 user.setUser(userTO.getUser());
		 List<ProjectsTO> projectList = new ArrayList<ProjectsTO>();
		 for(Projects project: projectDAO.showProjects(user)){
			 ProjectsTO projectTO = new ProjectsTO();
			 projectTO.setName(project.getName());
			 projectList.add(projectTO);
	    	}
	  return projectList;
	  }
}
