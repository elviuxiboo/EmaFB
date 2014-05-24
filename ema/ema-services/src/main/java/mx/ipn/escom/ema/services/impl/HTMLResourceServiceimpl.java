package mx.ipn.escom.ema.services.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import mx.ipn.escom.ema.model.entities.CSSResources;
import mx.ipn.escom.ema.model.entities.HTMLResources;
import mx.ipn.escom.ema.model.entities.Projects;
import mx.ipn.escom.ema.model.entities.Users;
import mx.ipn.escom.ema.model.resources.DAO.ResourceDAOcss;
import mx.ipn.escom.ema.model.resources.DAO.ResourceDAOhtml;
import mx.ipn.escom.ema.model.resources.DAO.impl.CSSResourcesDAOimpl;
import mx.ipn.escom.ema.model.resources.DAO.impl.HTMLResourcesDAOimpl;
import mx.ipn.escom.ema.services.CSSResourceService;
import mx.ipn.escom.ema.services.HTMLResourceService;
import mx.ipn.escom.ema.to.CSSResourceTO;
import mx.ipn.escom.ema.to.HTMLResourceTO;
import mx.ipn.escom.ema.to.ProjectsTO;
import mx.ipn.escom.ema.to.UsersTO;

public class HTMLResourceServiceimpl implements HTMLResourceService, Serializable{
	
	
	/*Agregar html*/
	  public HTMLResourceTO addHTML(HTMLResourceTO htmlTO, ProjectsTO projectTO, UsersTO userTO){
      Date date = new Date();
	  Users user = new Users();
	  user.setUser(userTO.getUser());
	  Projects project = new Projects();
	  project.setName(projectTO.getName());
	  HTMLResources html = new HTMLResources();
	  html.setName(htmlTO.getName());
	  html.setDate(date);
	  html.setCode(htmlTO.getCode());
	  ResourceDAOhtml htmlDAO = new HTMLResourcesDAOimpl();
	  HTMLResources htmlResult = htmlDAO.existingHTMLinProject(html, project,user);
	  HTMLResourceTO htmlResourceResult = new HTMLResourceTO();
	  htmlResourceResult.setName(htmlResult.getName());
	  return htmlResourceResult;
	  }
	 
	
	/*Eliminar html*/
	  public void deleteHTML(HTMLResourceTO htmlTO, ProjectsTO projectTO, UsersTO userTO){
		  ResourceDAOhtml htmlDAO = new HTMLResourcesDAOimpl();
		  Users user = new Users();
		  user.setUser(userTO.getUser());
		  Projects project = new Projects();
		  project.setName(projectTO.getName());
		  HTMLResources html = new HTMLResources();
		  html.setName(htmlTO.getName());
		  htmlDAO.deleteResourceHTML(html, project, user);
	  }
	  
	  
	
	/*
	 * Mostrar htmls de proyecto*/
	  public List<HTMLResourceTO> showHTMLResources(ProjectsTO projectTO, UsersTO userTO){
		  ResourceDAOhtml htmlDAO = new HTMLResourcesDAOimpl();
		  Users user = new Users();
		  user.setUser(userTO.getUser());
		  Projects project = new Projects();
		  project.setName(projectTO.getName());
		  List<HTMLResourceTO> listHtml = new ArrayList<HTMLResourceTO>();
		  for(HTMLResources html : htmlDAO.showHTMLResourcesFromProject(project, user)){
			  HTMLResourceTO htmlTO = new HTMLResourceTO();
			  htmlTO.setName(html.getName());
			  listHtml.add(htmlTO);
		  }
		  return listHtml;
	  }

/*	Actualizar html*/
	  public void updateHTML(HTMLResourceTO htmlTO, ProjectsTO projectTO, UsersTO userTO, String newCode){
		  ResourceDAOhtml htmlDAO = new HTMLResourcesDAOimpl();	
		  Users user = new Users();
		  user.setUser(userTO.getUser());
		  Projects project = new Projects();
		  project.setName(projectTO.getName());
		  HTMLResources html = new HTMLResources();
		  html.setName(htmlTO.getName());
		  htmlDAO.updateResourceHTML(html, project, user, newCode);
	  }


	public void addIndex(HTMLResourceTO htmlTO, ProjectsTO projectTO,
			UsersTO userTO) {
		Date date = new Date();
		  Users user = new Users();
		  user.setUser(userTO.getUser());
		  Projects project = new Projects();
		  project.setName(projectTO.getName());
		  HTMLResources html = new HTMLResources();
		  html.setName(htmlTO.getName());
		  html.setDate(date);
		  html.setCode(htmlTO.getCode());
		  ResourceDAOhtml htmlDAO = new HTMLResourcesDAOimpl();
		  htmlDAO.addResourceHTMLtoProject(html, project, user);
		
	}


	public HTMLResourceTO obtainHTML(HTMLResourceTO htmlTO, UsersTO userTO, ProjectsTO projectTO) {
		HTMLResourceTO htmlResult = htmlTO;
		ResourceDAOhtml htmlDAO = new HTMLResourcesDAOimpl();
		Projects project = new Projects();
		Users user = new Users();
		HTMLResources htmlResource = new HTMLResources();
		htmlResource.setName(htmlResult.getName());
		project.setName(projectTO.getName());
		user.setUser(userTO.getUser());
		HTMLResources html = htmlDAO.findHTMLofProjectofUser(htmlResource, project, user);
		HTMLResourceTO resultOf = new HTMLResourceTO();
		if(html != null){
			resultOf.setName(html.getName());
			resultOf.setCode(html.getCode());
			resultOf.setProject(projectTO);
		}else{
			ResourceDAOcss cssDAO = new CSSResourcesDAOimpl(); 
			CSSResources cssResources = new CSSResources();
			cssResources.setName(htmlTO.getName());
			CSSResources css = cssDAO.findCSSofProjectofUser(cssResources, project, user);
			resultOf.setName(css.getName());
			resultOf.setCode(css.getCode());
			resultOf.setProject(projectTO);
		}
		return resultOf;
	}


	public ProjectsTO returnProject(HTMLResourceTO htmlTO) {
		HTMLResources html = new HTMLResources();
		html.setName(htmlTO.getName());
		ProjectsTO project = new ProjectsTO();
		return project;
	}
	 
	
}
