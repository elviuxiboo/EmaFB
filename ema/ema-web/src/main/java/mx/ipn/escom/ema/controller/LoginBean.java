
package mx.ipn.escom.ema.controller;


import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

@ManagedBean(name="login")
@RequestScoped

public class LoginBean {

    public void callServletIngreso(){
        String url= "/SevletLogin";
        FacesContext context = FacesContext.getCurrentInstance();
        try {
        	  System.out.println("try");
            context.getExternalContext().dispatch(url);
            System.out.println("dispatch");
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("context bean");
        }finally{
            context.responseComplete();
            System.out.println("finally context");
        }
    }
    
    public void logOut(){
    	String url = "Reception.xhtml";
    	FacesContext ctx = FacesContext.getCurrentInstance();
    	try{
    		UserService userService = UserServiceFactory.getUserService();
    		User user = userService.getCurrentUser();
            ExternalContext ectx = ctx.getExternalContext();
            HttpServletRequest request = (HttpServletRequest) ectx.getRequest();
            HttpServletResponse response = (HttpServletResponse) ectx.getResponse();
            response.sendRedirect(userService.createLogoutURL(request.getRequestURI()));
    	}catch(Exception e){
    		e.printStackTrace();
    	}finally{
    		ctx.responseComplete();
    	}
    }
    
    public String outcome(){
    	logOut();
    	return "Reception";
    }
}
