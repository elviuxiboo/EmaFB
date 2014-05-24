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



import mx.ipn.escom.ema.services.CSSResourceService;
import mx.ipn.escom.ema.services.impl.CSSResourceServiceimpl;
import mx.ipn.escom.ema.to.CSSResourceTO;
import mx.ipn.escom.ema.to.ProjectsTO;
import mx.ipn.escom.ema.to.UsersTO;
import mx.ipn.escom.ema.services.HTMLResourceService;
import mx.ipn.escom.ema.services.impl.HTMLResourceServiceimpl;
import mx.ipn.escom.ema.to.HTMLResourceTO;



@ManagedBean
@SessionScoped

public class EditCSSResource implements Serializable {


    private static final long serialVersionUID = -4962063863336117198L;
    
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
    
    public CSSResourceTO saveCode() throws IOException {
        
        CodeCSSBean complete = new CodeCSSBean();
        FacesContext context = FacesContext.getCurrentInstance();
        CSSResourceTO cssResource = new CSSResourceTO();
        ProjectsTO project = new ProjectsTO();
        UsersTO userTO= new UsersTO();
        UserService userService = UserServiceFactory.getUserService();
        User user = userService.getCurrentUser();// Obtiene la sesion de google*/
        this.nameProject = getProjectParam(context);
        this.nameResource = getCSSResourceParam(context);
        code = context.getExternalContext().getRequestParameterMap().get("textCSS:textAreaCSS"); 
        cssResource.setName(nameResource);
        project.setName(nameProject);
        userTO.setUser(user.getEmail());
        CSSResourceService cssService = new CSSResourceServiceimpl();
        cssService.updateCSS(cssResource, project, userTO, code);
        return cssResource;
        
    }
      
    public String getProjectParam(FacesContext fc){
        Map<String,String> params = fc.getExternalContext().getRequestParameterMap();
        return params.get("project");

    }
    
    public String getCSSResourceParam(FacesContext fc){
        Map<String,String> params = fc.getExternalContext().getRequestParameterMap();
        return params.get("cssResource");

    }
    
    public String getCodeParam(FacesContext fc){
        Map<String,String> params = fc.getExternalContext().getRequestParameterMap();
        return params.get("code");

    }
    
    public String outcome() throws IOException{
        saveCode();
        return "/faces/views/New_Resource_CSS";
    }
}
