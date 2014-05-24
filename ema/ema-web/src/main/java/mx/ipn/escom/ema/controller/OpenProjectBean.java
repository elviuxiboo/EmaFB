package mx.ipn.escom.ema.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import mx.ipn.escom.ema.model.entities.CSSResources;
import mx.ipn.escom.ema.model.entities.HTMLResources;
import mx.ipn.escom.ema.model.entities.Projects;
import mx.ipn.escom.ema.model.entities.Resources;
import mx.ipn.escom.ema.model.entities.Users;
import mx.ipn.escom.ema.model.persistence.EMFService;
import mx.ipn.escom.ema.model.resources.DAO.ResourceDAOcss;
import mx.ipn.escom.ema.model.resources.DAO.impl.CSSResourcesDAOimpl;
import mx.ipn.escom.ema.services.HTMLResourceService;
import mx.ipn.escom.ema.services.impl.HTMLResourceServiceimpl;
import mx.ipn.escom.ema.to.HTMLResourceTO;
import mx.ipn.escom.ema.to.ProjectsTO;
import mx.ipn.escom.ema.to.UsersTO;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;


@ManagedBean
@SessionScoped
public class OpenProjectBean implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3862953914377550818L;
	private String projectName;
	private String resourceName;
	private UsersTO userTO = new UsersTO();
	private HTMLResourceTO html = new HTMLResourceTO();
	private HTMLResourceService htmlService = new HTMLResourceServiceimpl();
	private ProjectsTO projectTO = new ProjectsTO();
	private String code;
	
	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getResourceName() {
		return resourceName;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

	public String getProjectParam(FacesContext fc){
		Map<String,String> params = fc.getExternalContext().getRequestParameterMap();
		return params.get("project");

	}
	
	public String getHTMLResourceParam(FacesContext fc){
		Map<String,String> params = fc.getExternalContext().getRequestParameterMap();
		return params.get("resource");

	}
	
	public HTMLResourceTO updateExistingHTML(){
		FacesContext context = FacesContext.getCurrentInstance();
		HTMLResourceTO htmlResource = new HTMLResourceTO();
		ProjectsTO project = new ProjectsTO();
		UsersTO userTO = new UsersTO();
		UserService userService = UserServiceFactory.getUserService();
	    User user = userService.getCurrentUser();// Obtiene la sesion de google*/
	    this.projectName = getProjectParam(context);
	    this.resourceName = getHTMLResourceParam(context);
		code = context.getExternalContext().getRequestParameterMap().get("textHtmlEdit:textAreaHtmlEdit");	
		FacesMessage message2 = new FacesMessage(FacesMessage.SEVERITY_INFO, "Selected", projectName + resourceName);
 	    FacesContext.getCurrentInstance().addMessage(null, message2);
		htmlResource.setName(resourceName);
		project.setName(projectName);
		userTO.setUser(user.getEmail());
		HTMLResourceService htmlService = new HTMLResourceServiceimpl();
		htmlService.updateHTML(htmlResource, project, userTO, code);
		return htmlResource;
	}
	
	
}
