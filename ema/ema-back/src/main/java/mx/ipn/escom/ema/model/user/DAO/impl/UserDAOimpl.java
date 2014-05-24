package mx.ipn.escom.ema.model.user.DAO.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

import mx.ipn.escom.ema.model.entities.Projects;
import mx.ipn.escom.ema.model.entities.Users;
import mx.ipn.escom.ema.model.persistence.EMFService;
import mx.ipn.escom.ema.model.projects.DAO.impl.ProjectsDAOimpl;
import mx.ipn.escom.ema.model.user.DAO.UsersDAO;

public class UserDAOimpl implements UsersDAO{

	public void addUser(Users user) {
		EntityManager em = EMFService.get().createEntityManager();
		try{
			em.persist(user);
		}finally{
			em.close();
		}
	}

	public void updateUser(Users user, String newName) {
		EntityManager em = EMFService.get().createEntityManager();
		Query q = em.createQuery("select e from Users e where user = :user");
		q.setParameter("user", user.getUser());
		Users userResult = (Users) q.getSingleResult();
		try{
			userResult.setName(newName);
			em.persist(userResult);
		}finally{
			em.close();
		}
		
		
	}

	public void deleteUser(Users user) {
		ProjectsDAOimpl pdi = new ProjectsDAOimpl();
		EntityManager em = EMFService.get().createEntityManager();
		Query q = em.createQuery("select e from Users e where user = :user");
		q.setParameter("user", user.getUser());
		Users userResult = (Users) q.getSingleResult();
		List<Long> listProjects = userResult.getProjects();
		try{
			if(listProjects.size()==0){
				System.out.println("primer if");
				em.remove(userResult);
			}else{
				for(int i =0; i<listProjects.size(); i++){
				/*	if(listProjects.get(i) == null){
						userResult.getProjects().remove(i);
						em.persist(userResult);
					}else{*/
						Long keyProject = listProjects.get(i);
						Key keyProjectResult = KeyFactory.createKey(Projects.class.getSimpleName(), keyProject);
						Projects updatedProject = em.find(Projects.class, keyProjectResult);
				/*		if(updatedProject == null){
							userResult.getProjects().remove(i);
							em.persist(userResult);
							em.remove(user);
						}else{*/
							pdi.deleteProject(userResult, updatedProject);
							
					//	}
				//	}
				}
				em.remove(userResult);
			}
		}finally{
			em.close();
		}
	}

	public Users findUser(Users user) {
		/*EntityManager em = EMFService.get().createEntityManager();
		Query q = em.createQuery("SELECT e FROM Users e where user = :user");
		q.setParameter("user", user.getName());
		Users userResult = (Users) q.getSingleResult();
		return user;*/
		EntityManager em = EMFService.get().createEntityManager();
		Users userResult = em.find(Users.class, user.getId());
		Users userReturn = new Users();
		userReturn.setName(userResult.getName());
		userReturn.setUser(userResult.getUser());
		return userReturn;
	}

	public void addProject(Projects project) {
		List<Projects> projectList = new ArrayList<Projects>();
		EntityManager em = EMFService.get().createEntityManager();
		Query q = em.createQuery("select e.projects from User e where name =: name");
		projectList = q.getResultList();
	}

	public void addProject(Projects project, Users user) {
		EntityManager em = EMFService.get().createEntityManager();
		Users userResult = new Users();
		Projects projectResult = new Projects();
		Query q = em.createQuery("select e from Users e where user= :user");
		q.setParameter("user", user.getUser());
		userResult = (Users) q.getSingleResult();
		Key keyProject = KeyFactory.createKey(Projects.class.getSimpleName(), project.getId());
		projectResult = em.find(Projects.class, keyProject);
		userResult.getProjects().add(projectResult.getId());
		try{
			em.persist(userResult);
		}finally{
			em.close();
		}
/*		userResult = em.find(Users.class, user.getId());
		Key keyProject = KeyFactory.createKey(Projects.class.getSimpleName(), project.getId());
		projectResult = em.find(Projects.class, keyProject);
		userResult.getProjects().add(projectResult.getId());
		try{
			em.persist(userResult);
		}finally{
			em.close();
		}*/
		
	}

	public List<Users> getAllUsers() {
		EntityManager em = EMFService.get().createEntityManager();
		Query q = em.createQuery("select e from Users e");
		List<Users> listUsers = (List<Users>) q.getResultList();
		return listUsers;
	}

	public Users validateUser(Users user) {
		EntityManager em = EMFService.get().createEntityManager();
		Query q = em.createQuery("SELECT e FROM Users e where user = :user");
		q.setParameter("user", user.getUser());
		Users userResult = new Users(); 
		try{
			userResult = (Users) q.getSingleResult();
			if(userResult != null){
				System.out.println("Existe");
			}
		}catch(Exception e){
			addUser(user);
			e.printStackTrace();
		}
		return user;
	}

	public boolean userExists(Users user) {
		boolean exists = false;
		EntityManager em = EMFService.get().createEntityManager();
		Query q = em.createQuery("SELECT e FROM Users e where user = :user");
		q.setParameter("user", user.getUser());
		try{
			Users userResult = (Users) q.getSingleResult();
			exists = true;
		}catch(NoResultException noUser){
			exists = false;
		}
		return exists;
	}

	public void deleteProject(Projects project, Users user) {
		EntityManager em = EMFService.get().createEntityManager();
		Query userQuery = em.createQuery("SELECT e FROM Users e where user = :user");
		userQuery.setParameter("user", user.getUser());
		Users userResult = (Users) userQuery.getSingleResult();
		List<Long> projectsOfUser = userResult.getProjects();
		Key keyProject = KeyFactory.createKey(Projects.class.getSimpleName(), project.getId());
		Projects projectResult = em.find(Projects.class, keyProject);
		try{
			for(int i=0; i<projectsOfUser.size();i++){
				if(projectResult.getId() == projectsOfUser.get(i)){
					userResult.getProjects().remove(i);
					em.persist(userResult);
				}
			}
		}finally{
			em.close();
		}
		
	}
}
