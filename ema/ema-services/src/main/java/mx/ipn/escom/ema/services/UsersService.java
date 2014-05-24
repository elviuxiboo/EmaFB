package mx.ipn.escom.ema.services;

import java.util.List;

import mx.ipn.escom.ema.to.UsersTO;

public interface UsersService {
	/*Agregar un usuario*/
	  public void saveUser(UsersTO userTO);
	
	/*
	 * Actualizar nombre de usuario*/
	  public void updateUserName(UsersTO userTO, String name);
	 
	
	/*
	 * Elminar usuario*/
	  public void deleteUser(UsersTO userTO);
	 
	
	/*
	 *Buscar Usuario*/
	 public UsersTO findUser(UsersTO userTO);
	 
	
	/*Mostrar todos los usuarios*/
	  public List<UsersTO> getAllUsers();
	  
	/*Existe usuario*/
	  public boolean userExists(UsersTO userTO);
	 
}
