package mx.ipn.escom.ema.controller;

import java.io.IOException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import mx.ipn.escom.ema.services.UsersService;
import mx.ipn.escom.ema.services.impl.UserServiceimpl;
import mx.ipn.escom.ema.to.UsersTO;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

@ManagedBean(name="registro")
@RequestScoped

public class RegisterBean {
	
	private String email;
	private String name;
	private UsersTO userTO = new UsersTO();
	private UsersService usersServiceEMA = new UserServiceimpl();
	
	public String getEmail() {
		email = getMail();
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) throws IOException {
		UsersTO nameUser = registerUser();
		name = nameUser.toString();
		this.name = name;
	}
	
	public String getMail(){
		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();
		email =user.getEmail();
		return email;
	}
	
	public UsersTO registerUser() throws IOException{
		String url = "/faces/welcome_ema.xhtml";
		FacesContext context = FacesContext.getCurrentInstance();
		email = context.getExternalContext().getRequestParameterMap().get("formRegister:User_mail");
		name = context.getExternalContext().getRequestParameterMap().get("formRegister:User_name");
		userTO.setUser(email);
		userTO.setName(name);
		usersServiceEMA.saveUser(userTO);
		context.getExternalContext().redirect(url);
		return userTO;
	}
}
