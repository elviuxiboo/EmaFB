package mx.ipn.escom.ema.services.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import mx.ipn.escom.ema.model.entities.CSSResources;
import mx.ipn.escom.ema.model.entities.Projects;
import mx.ipn.escom.ema.model.entities.Users;
import mx.ipn.escom.ema.model.resources.DAO.ResourceDAOcss;
import mx.ipn.escom.ema.model.resources.DAO.impl.CSSResourcesDAOimpl;
import mx.ipn.escom.ema.services.CSSResourceService;
import mx.ipn.escom.ema.to.CSSResourceTO;
import mx.ipn.escom.ema.to.ProjectsTO;
import mx.ipn.escom.ema.to.UsersTO;

public class CSSResourceServiceimpl implements CSSResourceService, Serializable{
	
	
	/*Agregar css*/
	  public void addCSS(CSSResourceTO cssTO, ProjectsTO projectTO, UsersTO userTO){
	  Date date = new Date();
	  Users user = new Users();
	  user.setUser(userTO.getUser());
	  Projects project = new Projects();
	  project.setName(projectTO.getName());
	  CSSResources css = new CSSResources();
	  css.setName(cssTO.getName());
	  css.setDate(date);
	  css.setCode(cssTO.getCode());
	  ResourceDAOcss resourceDAOcss = new CSSResourcesDAOimpl();
	  resourceDAOcss.existingCSSinProject(css, project, user);
	  }
	
	/*Eliminar css*/
	  public void deleteCSS(CSSResourceTO cssTO, ProjectsTO projectTO, UsersTO userTO){
	  Date date = new Date();
	  Users user = new Users();
	  user.setUser(userTO.getUser());
	  Projects project = new Projects();
	  project.setName(projectTO.getName());
	  CSSResources css = new CSSResources();
	  css.setName(cssTO.getName());
	  ResourceDAOcss resourceDAOcss = new CSSResourcesDAOimpl();
	   resourceDAOcss.deleteResourceCSS(css, project, user);
	  }
	
	/*
	 * Mostrar recursos css de proyecto*/
	 public List<CSSResourceTO> showCSSResources(ProjectsTO projectTO, UsersTO userTO){
		 ResourceDAOcss cssDAO = new CSSResourcesDAOimpl();
		 Users user = new Users();
		 user.setUser(userTO.getUser());
		 Projects project = new Projects();
		 project.setName(projectTO.getName());
		 List<CSSResourceTO> listCss = new ArrayList<CSSResourceTO>();
		 for(CSSResources css: cssDAO.showCSSResourcesFromProject(project, user)){
			 CSSResourceTO cssTO = new CSSResourceTO();
			 cssTO.setName(css.getName());
			 listCss.add(cssTO);
		 }
		 return listCss;
	  }

	
	/*Actualizar css*/
	  public void updateCSS(CSSResourceTO cssTO, ProjectsTO projectTO, UsersTO userTO, String newCode){
	  ResourceDAOcss cssDAO = new CSSResourcesDAOimpl();
	  Users user = new Users();
	  user.setUser(userTO.getUser());
	  Projects project = new Projects();
	  project.setName(projectTO.getName());
	  CSSResources css = new CSSResources();
	  css.setName(cssTO.getName());
	  cssDAO.updateResourceCSS(css, project, user, newCode);
	  }

	public void addStyleSheet(CSSResourceTO cssTO, ProjectsTO projectTO,
			UsersTO userTO) {
		  Date date = new Date();
		  Users user = new Users();
		  user.setUser(userTO.getUser());
		  Projects project = new Projects();
		  project.setName(projectTO.getName());
		  CSSResources css = new CSSResources();
		  css.setName(cssTO.getName());
		  css.setCode(cssTO.getCode());
		  css.setDate(date);
		  ResourceDAOcss resourceDAOcss = new CSSResourcesDAOimpl();
		  resourceDAOcss.addResourceCSStoProject(css, project, user);
		
	}


	 
}
