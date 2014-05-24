package mx.ipn.escom.ema.model.user.DAO;

import java.util.List;

import mx.ipn.escom.ema.model.entities.Projects;
import mx.ipn.escom.ema.model.entities.Users;

public interface UsersDAO {
	
	public void addUser(Users user);
	public void updateUser(Users user, String newName);
	public void deleteUser(Users user);
	public Users findUser(Users user);
	public void addProject(Projects project, Users user);
	public List<Users> getAllUsers();
	public Users validateUser(Users user);
	public boolean userExists(Users user);
	public void deleteProject(Projects project, Users user);
	 
}
