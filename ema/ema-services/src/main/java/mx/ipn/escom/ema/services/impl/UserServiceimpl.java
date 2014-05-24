package mx.ipn.escom.ema.services.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import mx.ipn.escom.ema.model.entities.Users;
import mx.ipn.escom.ema.model.user.DAO.UsersDAO;
import mx.ipn.escom.ema.model.user.DAO.impl.UserDAOimpl;
import mx.ipn.escom.ema.services.UsersService;
import mx.ipn.escom.ema.to.UsersTO;

public class UserServiceimpl implements UsersService, Serializable{
	
	/*Agregar un usuario*/
	  public void saveUser(UsersTO userTO){
	  Users user = new Users();
	  user.setUser(userTO.getUser());
	  user.setName(userTO.getName());
	  UsersDAO userDAO = new UserDAOimpl();
	  userDAO.validateUser(user);
	  }
	

	/*
	 * Actualizar nombre de usuario*/
	 public void updateUserName(UsersTO userTO, String name){
	  Users user = new Users();
	  user.setUser(userTO.getUser());
	  UsersDAO userDAO = new UserDAOimpl();
	  userDAO.updateUser(user, name);
	  }
	 
	 

	/*
	 * Elminar usuario*/
	  public void deleteUser(UsersTO userTO){
	  Users user = new Users();
	  user.setUser(userTO.getUser());
	  UsersDAO userDAO = new UserDAOimpl();
	  userDAO.deleteUser(user);
	  }
	
	
	/*
	 *Buscar Usuario*/
	  public UsersTO findUser(UsersTO userTO){
	  Users user = new Users();
	  UsersTO userTOResult = new UsersTO();
	  user.setUser(userTO.getUser());
	  UsersDAO userDAO = new UserDAOimpl();
	  Users userResult = userDAO.validateUser(user);
	  userTOResult.setUser(userResult.getUser());
	  return userTOResult;
	  }


	public List<UsersTO> getAllUsers() {
		List<UsersTO> usersListTO = new ArrayList<UsersTO>();
		 UsersDAO userDAO = new UserDAOimpl();
		/* user = userDAO.getAllUsers();
		 for(int i=0; i<user.size(); i++){
			 Users userResult = user.get(i);
			 userTO.setName(userResult.getName());
			 userTO.setUser(userResult.getUser());
			 usersListTO.add(userTO);
		 }
		 return usersListTO;*/
		 for(Users user : userDAO.getAllUsers()){
			 UsersTO userTO = new UsersTO();
			 userTO.setName(user.getName());
			 userTO.setUser(user.getUser());
			 usersListTO.add(userTO);
		 }
		 return usersListTO;
	}


	public boolean userExists(UsersTO userTO) {
		Users user = new Users();
		user.setUser(userTO.getUser());
		UsersDAO userDAO = new UserDAOimpl();
		boolean exists = false;
		try{
			exists = userDAO.userExists(user);
		}catch(Exception e){
			e.printStackTrace();
		}
		return exists;
	}
	
	
}
