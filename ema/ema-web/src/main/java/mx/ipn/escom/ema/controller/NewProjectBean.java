package mx.ipn.escom.ema.controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import mx.ipn.escom.ema.services.CSSResourceService;
import mx.ipn.escom.ema.services.HTMLResourceService;
import mx.ipn.escom.ema.services.ProjectsService;
import mx.ipn.escom.ema.services.impl.CSSResourceServiceimpl;
import mx.ipn.escom.ema.services.impl.HTMLResourceServiceimpl;
import mx.ipn.escom.ema.services.impl.ProjectServiceimpl;
import mx.ipn.escom.ema.to.CSSResourceTO;
import mx.ipn.escom.ema.to.HTMLResourceTO;
import mx.ipn.escom.ema.to.ProjectsTO;
import mx.ipn.escom.ema.to.UsersTO;

import java.io.Serializable;
import java.util.Date;


@ManagedBean(name="newProject")
@SessionScoped

public class NewProjectBean implements Serializable {

    /**
     * 
     */
	private UsersTO userTO = new UsersTO();
	private ProjectsTO projectTO = new ProjectsTO();
	private ProjectsService projectService = new ProjectServiceimpl();
  	private Date date = new Date();
  	private HTMLResourceTO html = new HTMLResourceTO();
  	private HTMLResourceService htmlService = new HTMLResourceServiceimpl();
  	private CSSResourceTO css = new CSSResourceTO();
  	private CSSResourceService cssService = new CSSResourceServiceimpl();
	
    private static final long serialVersionUID = 8300483268320228358L;
    
    public boolean incluirCSS;
    private String nameProject;

   
    public void setIncluirCSS(boolean incluirCSS) {
		this.incluirCSS = incluirCSS;
	}

	public boolean isIncluirCSS () {
        return incluirCSS;
        
    }
    
    public String getNombreProyecto() {
        return nameProject;
    }

    public void setNombreProyecto(String nameProject) {
        this.nameProject = nameProject;
    }  
    
    public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public ProjectsTO createProject(){
		AutocompleteBean etiquetaHTML = new AutocompleteBean();
		AutocompleteCSSBean etiquetaCSS = new AutocompleteCSSBean();
		String genericHTML = etiquetaHTML.getEtiqueta();
		String genericCSS = etiquetaCSS.getEtiqueta();
    	FacesContext context = FacesContext.getCurrentInstance();
    	UserService userService = UserServiceFactory.getUserService();
	    User user = userService.getCurrentUser();// Obtiene la sesion de google
	    userTO.setUser(user.getEmail());
	    nameProject = context.getExternalContext().getRequestParameterMap().get("newProject:Project_name");
	    projectTO.setName(nameProject);
	    projectTO.setDate(date);
	    projectTO.setUser(userTO);
	    projectService.addProject(projectTO, userTO);
	    html.setName("index.html");
	    html.setDate(date);
	    html.setCode(genericHTML);
	    htmlService.addIndex(html, projectTO, userTO);
	    if(incluirCSS){
	    	css.setName("estilo.css");
	    	css.setDate(date);
	    	css.setCode(genericCSS);
	    	cssService.addStyleSheet(css, projectTO, userTO);
	    }
    	return projectTO;
    }
	
	public String outcome(){
		createProject();
		return "welcome_ema";
	}
    
}
