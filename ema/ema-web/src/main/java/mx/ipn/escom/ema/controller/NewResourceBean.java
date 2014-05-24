package mx.ipn.escom.ema.controller;


import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import mx.ipn.escom.ema.model.entities.Projects;
import mx.ipn.escom.ema.model.entities.SharedProjects;
import mx.ipn.escom.ema.model.entities.Users;
import mx.ipn.escom.ema.model.projects.DAO.ProjectsDAO;
import mx.ipn.escom.ema.model.projects.DAO.impl.ProjectsDAOimpl;
import mx.ipn.escom.ema.services.HTMLResourceService;
import mx.ipn.escom.ema.services.ProjectsService;
import mx.ipn.escom.ema.services.ShareProjectsService;
import mx.ipn.escom.ema.services.impl.HTMLResourceServiceimpl;
import mx.ipn.escom.ema.services.impl.ProjectServiceimpl;
import mx.ipn.escom.ema.services.impl.SharedProjectsServiceimpl;
import mx.ipn.escom.ema.to.HTMLResourceTO;
import mx.ipn.escom.ema.to.ProjectsTO;
import mx.ipn.escom.ema.to.UsersTO;

import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;



@ManagedBean(name="htmlResource")
@SessionScoped
public class NewResourceBean implements Serializable {

    /**
     * 
     */
	private Date date = new Date();
	private HTMLResourceTO html = new HTMLResourceTO();
	private HTMLResourceService htmlService = new HTMLResourceServiceimpl();
	private ProjectsTO projectTO = new ProjectsTO();
	private ProjectsService projectService = new ProjectServiceimpl();
	private UsersTO userTO = new UsersTO();
	private List<String> projectList = new ArrayList<String>();
	private HTMLResourceTO result = new HTMLResourceTO();
	
    private static final long serialVersionUID = 2617813082214149080L;
    
    private String nameProject;
    private String nameResourceHTML;
    private String code;
    
    public String getNameProject() {
		return nameProject;
	}

	public void setNameProject(String nameProject) {
		this.nameProject = nameProject;
	}

	public String getNameResourceHTML() {
		return nameResourceHTML;
	}

	public void setNameResourceHTML(String nameResourceHTML) {
		this.nameResourceHTML = nameResourceHTML;
	}

	public HTMLResourceTO getResult() {
		return result;
	}
	
	public void setResult(HTMLResourceTO result) throws IOException {
		this.result = result;
	}

	public String getCode() throws IOException {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	public List<String> complete(String query){
    	UserService userService = UserServiceFactory.getUserService();
	    User user = userService.getCurrentUser();// Obtiene la sesion de google
	    userTO.setUser(user.getEmail());
    	List<ProjectsTO> listProjects = projectService.showProjects(userTO);
    	System.out.println(listProjects);
    	for(ProjectsTO projectResult: listProjects){
    		if(projectResult.getName().startsWith(query)){
    			projectList.add(projectResult.getName());
    		}
    	}
    	return projectList;
    }
    
    public HTMLResourceTO createHTML() throws IOException{
    	AutocompleteBean autocompleteBean = new AutocompleteBean();
    	System.out.println("metodo");
    	FacesContext context = FacesContext.getCurrentInstance();
    	UserService userService = UserServiceFactory.getUserService();
	    User user = userService.getCurrentUser();// Obtiene la sesion de google*/
	    String genericCode = autocompleteBean.getEtiqueta();
	    userTO.setUser(user.getEmail());
	    System.out.println("name project "+nameProject);
	    nameResourceHTML = context.getExternalContext().getRequestParameterMap().get("html:regex");
	    System.out.println("name resource " +nameResourceHTML);
	    projectTO.setName(nameProject);
	    html.setName(nameResourceHTML);
	    html.setDate(date);
	    html.setCode(genericCode);
	    html.setProject(projectTO);
	    htmlService.addHTML(html, projectTO, userTO);
	    return html;
    }
    
    public String outcome() throws IOException{
    	//String url = "/faces/views/New_Resource_HTML.xhtml";
    	//FacesContext context = FacesContext.getCurrentInstance();
    	createHTML();
    	//context.getExternalContext().redirect(url);
    	return "/faces/views/New_Resource_HTML";
    }
    
    
    
 
}
