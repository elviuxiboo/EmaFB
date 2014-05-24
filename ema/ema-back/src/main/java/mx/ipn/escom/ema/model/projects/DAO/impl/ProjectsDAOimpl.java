package mx.ipn.escom.ema.model.projects.DAO.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.users.User;

import mx.ipn.escom.ema.model.entities.CSSResources;
import mx.ipn.escom.ema.model.entities.Projects;
import mx.ipn.escom.ema.model.entities.Resources;
import mx.ipn.escom.ema.model.entities.Users;
import mx.ipn.escom.ema.model.persistence.EMFService;
import mx.ipn.escom.ema.model.projects.DAO.ProjectsDAO;
import mx.ipn.escom.ema.model.resources.DAO.impl.CSSResourcesDAOimpl;
import mx.ipn.escom.ema.model.resources.DAO.impl.HTMLResourcesDAOimpl;
import mx.ipn.escom.ema.model.resources.DAO.impl.ResourcesDAOimpl;
import mx.ipn.escom.ema.model.user.DAO.UsersDAO;
import mx.ipn.escom.ema.model.user.DAO.impl.UserDAOimpl;

public class ProjectsDAOimpl implements ProjectsDAO{

	public void addProject(Projects project, Users user) {
/*	boolean rpta = validateProject(project,user);
	if(rpta){
		System.out.println("Existe");
	}else{*/
		EntityManager em = EMFService.get().createEntityManager();
		try{
			em.persist(project);
		}finally{
			em.close();
		}
	//}
	
	}

	public void updateProject(Projects project, String newName, Users user) {
		EntityManager em = EMFService.get().createEntityManager();
		Date date = new Date();
		Query qUser = em.createQuery("select e from Users e where user = :user");
		qUser.setParameter("user", user.getUser());
		Users userName = (Users)qUser.getSingleResult();
		Users userResult = em.find(Users.class, userName.getId());
		Query q = em.createQuery("select e from Projects e where name = :project and user = :keyUser");
		q.setParameter("project", project.getName());
		q.setParameter("keyUser", userResult.getId());
		Projects projectResult = (Projects) q.getSingleResult();
		projectResult.setName(newName);
		projectResult.setDate(date);
		try{
			em.persist(projectResult);
		}finally{
			em.close();
		}
	}

	public List<Projects> showProjects(Users user) {
		EntityManager em = EMFService.get().createEntityManager();
		Query qUser = em.createQuery("select e from Users e where user = :user");
		qUser.setParameter("user", user.getUser());
		Users userResult =(Users) qUser.getSingleResult();
		Query q = em.createQuery("select e from Projects e where user = :user");
		q.setParameter("user", userResult.getId());
		return q.getResultList();
	}

	public void deleteProject(Users user, Projects project) {
		EntityManager em = EMFService.get().createEntityManager();
		UsersDAO userDAO = new UserDAOimpl();
		CSSResourcesDAOimpl crdi = new CSSResourcesDAOimpl();
		HTMLResourcesDAOimpl hrdi = new HTMLResourcesDAOimpl();
		ResourcesDAOimpl rdi = new ResourcesDAOimpl();
		ProjectsDAOimpl pdi = new ProjectsDAOimpl();
		Query userQuery = em.createQuery("select e from Users e where user = :user");
		userQuery.setParameter("user", user.getUser());
		Users userResult = (Users) userQuery.getSingleResult();
		Projects projectResult = pdi.findProject(project, user);
		Key keyProject = KeyFactory.createKey(Projects.class.getSimpleName(), projectResult.getId());
		Projects updatedProject = em.find(Projects.class, keyProject);
		List<Key> resourcesList = updatedProject.getResources();
		System.out.println("antes del try");
		try{
			for(int i=0; i <resourcesList.size(); i++){
				Key keyResource = resourcesList.get(i);
				Resources resource = em.find(Resources.class, keyResource);
				if(resource.getHtmlrec() != null){
					hrdi.deleteAllHTML(resource);
					rdi.deleteResource(resource);
				}else if(resource.getCssrec() != null){
					crdi.deleteAllCSS(resource);
					rdi.deleteResource(resource);
				}
			}
			userDAO.deleteProject(updatedProject, userResult);
			em.remove(updatedProject);
		}finally{
			em.close();
		}
	}


	public void addResourcetoProjectTest(Resources resource, Projects project) {
		EntityManager em = EMFService.get().createEntityManager();
		Projects projectResult = new Projects();
		Resources resourceResult = em.find(Resources.class, resource.getId());
		Key keyProject = KeyFactory.createKey(Projects.class.getSimpleName(), project.getId());
		projectResult = em.find(Projects.class, keyProject);
		projectResult.getResources().add(resourceResult.getId());
		try{
			em.persist(projectResult);
		}finally{
			em.close();
		}
	}

	public void addReferenceOfUser(Users user, Projects project) {
		EntityManager em = EMFService.get().createEntityManager();
		//Users userResult = em.find(Users.class, user.getId());
		Query qUser = em.createQuery("select e from Users e where user = :user");
		qUser.setParameter("user", user.getUser());
		Users userResult = (Users) qUser.getSingleResult();
		Projects projectResult = new Projects();
		Key keyProject = KeyFactory.createKey(Projects.class.getSimpleName(), project.getId());
		projectResult = em.find(Projects.class, keyProject);
		projectResult.setUser(userResult.getId());
		try{
			em.persist(projectResult);
		}finally{
			em.close();
		}
		
	}

	public Projects findProject(Projects project, Users user) {
		EntityManager em = EMFService.get().createEntityManager();
		Query qUser = em.createQuery("select e from Users e where user = :user");
		qUser.setParameter("user", user.getUser());
		Users getUser = (Users) qUser.getSingleResult();
		Users userResult = em.find(Users.class, getUser.getId());
		Key keyUser = userResult.getId();
		Query q = em.createQuery("select e from Projects e where name = :nombre and user = :keyUser");
		q.setParameter("keyUser", keyUser);
		q.setParameter("nombre", project.getName());
		Projects projectResult = (Projects) q.getSingleResult();
		return projectResult;
	}

	public void deleteCSSfromProject(Projects project, Key resource, Users user) {
		EntityManager em = EMFService.get().createEntityManager();
		ProjectsDAOimpl pdi = new ProjectsDAOimpl();
		Projects projectResult = pdi.findProject(project, user);
		List<Key> resourcesList = projectResult.getResources();
		Resources resourceResult = new Resources();
		System.out.println("antes del for");
		System.out.println(resource);
		try{
			for(int i =0; i<resourcesList.size();i++){
				Key resourceOfList = resourcesList.get(i);
				resourceResult = em.find(Resources.class, resourceOfList);
				System.out.println(resourceResult);
				if(resourceResult == null){
					Key keyProject = KeyFactory.createKey(Projects.class.getSimpleName(), projectResult.getId());
					Projects updatedProject = em.find(Projects.class, keyProject);
					updatedProject.getResources().remove(i);
					em.persist(updatedProject);
				}
			}
		}finally{
				em.close();
				}
	}

	public void deleteCSSfromProjectTest(Projects project, Users user) {
		EntityManager em = EMFService.get().createEntityManager();
		ProjectsDAOimpl pdi = new ProjectsDAOimpl();
		Projects projectResult = pdi.findProject(project, user);
		List<Key> resourcesList = projectResult.getResources();
		Resources resourceResult = new Resources();
		try{
			for(int i =0; i<resourcesList.size();i++){
				Key resourceOfList = resourcesList.get(i);
				resourceResult = em.find(Resources.class, resourceOfList);
				System.out.println(resourceResult);
				if(resourceResult == null){
					Key keyProject = KeyFactory.createKey(Projects.class.getSimpleName(), projectResult.getId());
					Projects updatedProject = em.find(Projects.class, keyProject);
					updatedProject.getResources().remove(i);
					em.persist(updatedProject);
				}
			}
		}finally{
				em.close();
				}
		
	}

	public void deleteHTMLfromProject(Projects project, Key resource, Users user) {
		EntityManager em = EMFService.get().createEntityManager();
		ProjectsDAOimpl pdi = new ProjectsDAOimpl();
		Projects projectResult = pdi.findProject(project, user);
		List<Key> resourcesList = projectResult.getResources();
		Resources resourceResult = new Resources();
		try{
			for(int i =0; i<resourcesList.size();i++){
				Key resourceOfList = resourcesList.get(i);
				resourceResult = em.find(Resources.class, resourceOfList);
				System.out.println(resourceResult);
				if(resourceResult == null){
					Key keyProject = KeyFactory.createKey(Projects.class.getSimpleName(), projectResult.getId());
					Projects updatedProject = em.find(Projects.class, keyProject);
					updatedProject.getResources().remove(i);
					em.persist(updatedProject);
				}
			}
		}finally{
				em.close();
				}
	}

	public Projects validateProject(Projects project, Users user) {
		EntityManager em = EMFService.get().createEntityManager();
		Query qUser = em.createQuery("select e from Users e where user = :user");
		qUser.setParameter("user", user.getUser());
		Users getUser = (Users) qUser.getSingleResult();
		Users userResult = em.find(Users.class, getUser.getId());
		Key keyUser = userResult.getId();
		Query q = em.createQuery("select e from Projects e where name = :nombre and user = :keyUser");
		q.setParameter("keyUser", keyUser);
		q.setParameter("nombre", project.getName());
		Projects rpta = new Projects();
		try{
			rpta = (Projects) q.getSingleResult();
			if(rpta!=null){
				System.out.println("Existe");
			}
		}catch(Exception e){
			addProject(project,user);
			addReferenceOfUser(user, project);
			e.printStackTrace();
		}
		return rpta;
	}

}
