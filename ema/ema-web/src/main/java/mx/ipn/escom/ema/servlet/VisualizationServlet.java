package mx.ipn.escom.ema.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mx.ipn.escom.ema.controller.EditHTMLResource;
import mx.ipn.escom.ema.services.HTMLResourceService;
import mx.ipn.escom.ema.services.UsersService;
import mx.ipn.escom.ema.services.impl.HTMLResourceServiceimpl;
import mx.ipn.escom.ema.services.impl.UserServiceimpl;
import mx.ipn.escom.ema.to.HTMLResourceTO;
import mx.ipn.escom.ema.to.ProjectsTO;
import mx.ipn.escom.ema.to.UsersTO;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

public class VisualizationServlet extends HttpServlet{
	
	private static final String HEAD_TEXT = "<head>";
	@Override	 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		FacesContext context = FacesContext.getCurrentInstance();
		PrintWriter out = response.getWriter();//\\\\\
	    // Para el css "text/css" 
            response.setContentType("text/html");
            String projectName = (String) request.getAttribute("projectName");
            String resourceName = (String) request.getAttribute("resourceName");
            String cssCode = "<link rel=\"stylesheet\" type=\"text/css\" href=\"./servletcss?projectName="+projectName+"&resourceName="+resourceName+"\">"; // esta no
            //String codeResource = getTextResource(projectName,resourceName);
            String codeResource = getTextResource();
           // String codeResource = "esto si lo ves?";
            codeResource = codeResource.toLowerCase().replace(HEAD_TEXT, HEAD_TEXT + '\n' + cssCode); // esta no
            out.write(codeResource);
	   
	}
	@Override 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		PrintWriter out = response.getWriter();//\\\\\
	    // Para el css "text/css" 
            response.setContentType("text/html");
            String projectName = (String) request.getAttribute("projectName");
            String resourceName = (String) request.getAttribute("resourceName");
            String cssCode = "<link rel=\"stylesheet\" type=\"text/css\" href=\"./servletcss?projectName="+projectName+"&resourceName="+resourceName+"\">"; // esta no
            //String codeResource = getTextResource(projectName,resourceName);
            String codeResource = getTextResource();
            codeResource = codeResource.toLowerCase().replace(HEAD_TEXT, HEAD_TEXT + '\n' + cssCode); // esta no
            out.write(codeResource);
	}


  //  private String getTextResource(String projectName, String resourceName){
		private String getTextResource(){
    	UsersTO userTO = new UsersTO();
    	FacesContext context = FacesContext.getCurrentInstance();
		//EditHTMLResource editHtmlResource = new EditHTMLResource();
		HTMLResourceTO htmlResource = new HTMLResourceTO();
		ProjectsTO project = new ProjectsTO();
		UserService userService = UserServiceFactory.getUserService();
	    User user = userService.getCurrentUser();// Obtiene la sesion de google
	    userTO.setUser(user.getEmail());
		//String code = context.getExternalContext().getRequestParameterMap().get("textHtmlEdit:textAreaHtmlEdit");
		htmlResource.setName("pruebacolores");
		project.setName("Prueba40mil");
		HTMLResourceService htmlService = new HTMLResourceServiceimpl();
		//Consulta que devuelve el recurso
		HTMLResourceTO htmlResult = htmlService.obtainHTML(htmlResource, userTO, project);
		return htmlResult.getCode();
		//return code;
	}


}
