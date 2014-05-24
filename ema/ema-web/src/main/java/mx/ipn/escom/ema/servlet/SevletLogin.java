
package mx.ipn.escom.ema.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.*;

import mx.ipn.escom.ema.services.UsersService;
import mx.ipn.escom.ema.services.impl.UserServiceimpl;
import mx.ipn.escom.ema.to.UsersTO;

import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

/**
 * Servlet implementation class SevletLogin
 */
 
public class SevletLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
 @Override	 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    UserService userService = UserServiceFactory.getUserService();
	    User user = userService.getCurrentUser();// Obtiene la sesion de google
	    System.out.println("context");
	    String loginURL= request.getContextPath() + "/faces/welcome_ema.xhtml";
	    String registroURL=request.getContextPath()+ "/faces/views/Register.xhtml";
	    System.out.println("antes del if");
	    if(user != null){
	      String mail = user.getEmail();
	      UsersTO userTO = new UsersTO();
	      userTO.setUser(mail);
	      UsersService userServiceEMA = new UserServiceimpl();
	      boolean userTOResult = userServiceEMA.userExists(userTO);
	      if(userTOResult){
	    	  response.sendRedirect(loginURL);
	      }else{
	    	  response.sendRedirect(registroURL);
	      }
	    }else{
	    	response.sendRedirect(userService.createLoginURL(request.getRequestURI()));
	    }
	}
	@Override 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
