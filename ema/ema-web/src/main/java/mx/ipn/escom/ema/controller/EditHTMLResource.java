package mx.ipn.escom.ema.controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import mx.ipn.escom.ema.services.HTMLResourceService;
import mx.ipn.escom.ema.services.impl.HTMLResourceServiceimpl;
import mx.ipn.escom.ema.to.HTMLResourceTO;
import mx.ipn.escom.ema.to.ProjectsTO;
import mx.ipn.escom.ema.to.UsersTO;


@ManagedBean
@SessionScoped
public class EditHTMLResource implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7117710613956320320L;
	private String nameProject;
	private String nameResource;
	private String code;
	
	public String getNameProject() {
		return nameProject;
	}
	public void setNameProject(String nameProject) {
		this.nameProject = nameProject;
	}
	public String getNameResource() {
		return nameResource;
	}
	public void setNameResource(String nameResource) {
		this.nameResource = nameResource;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	public HTMLResourceTO saveCode() throws IOException{
		FacesContext context = FacesContext.getCurrentInstance();
		HTMLResourceTO htmlResource = new HTMLResourceTO();
		ProjectsTO project = new ProjectsTO();
		UsersTO userTO = new UsersTO();
		UserService userService = UserServiceFactory.getUserService();
	    User user = userService.getCurrentUser();// Obtiene la sesion de google*/
		//nameProject = context.getExternalContext().getRequestParameterMap().get("project");
	    this.nameProject = getProjectParam(context);
		//nameResource = context.getExternalContext().getRequestParameterMap().get("htmlResource");	
	    this.nameResource = getHTMLResourceParam(context);
		code = context.getExternalContext().getRequestParameterMap().get("textHtml:textAreaHtml");	
	  //  String codeResult = complete.etiqueta;
		htmlResource.setName(nameResource);
		project.setName(nameProject);
		userTO.setUser(user.getEmail());
		HTMLResourceService htmlService = new HTMLResourceServiceimpl();
		htmlService.updateHTML(htmlResource, project, userTO, code);
		return htmlResource;
	}
	
	public String getProjectParam(FacesContext fc){
		Map<String,String> params = fc.getExternalContext().getRequestParameterMap();
		return params.get("project");

	}
	
	public String getHTMLResourceParam(FacesContext fc){
		Map<String,String> params = fc.getExternalContext().getRequestParameterMap();
		return params.get("htmlResource");

	}
	
	public String getCodeParam(FacesContext fc){
		Map<String,String> params = fc.getExternalContext().getRequestParameterMap();
		return params.get("code");

	}
	
	public String outcome() throws IOException{
		saveCode();
		return "/faces/views/New_Resource_HTML";
	}
	
	

	
	
	

}
