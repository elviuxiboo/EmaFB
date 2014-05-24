package mx.ipn.escom.ema.model.resources.DAO.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.google.appengine.api.datastore.Key;

import mx.ipn.escom.ema.model.entities.Projects;
import mx.ipn.escom.ema.model.entities.SharedProjects;
import mx.ipn.escom.ema.model.entities.SharedResources;
import mx.ipn.escom.ema.model.entities.Users;
import mx.ipn.escom.ema.model.persistence.EMFService;
import mx.ipn.escom.ema.model.projects.DAO.impl.ProjectsDAOimpl;
import mx.ipn.escom.ema.model.resources.DAO.SharedProjectsDAO;

public class SharedProjectsDAOimpl implements SharedProjectsDAO{

	public void shareProject(SharedProjects sharedResource, Projects project,
			Users user) {
		EntityManager em = EMFService.get().createEntityManager();
		Query userQ = em.createQuery("select e from Users e where user = :user");
		userQ.setParameter("user", user.getUser());
		Users userResult = (Users) userQ.getSingleResult();
		Query q = em.createQuery("select e from Projects e where name = :name and user = :user");
		q.setParameter("name", project.getName());
		q.setParameter("user", userResult.getId());
		Projects projectResult = (Projects) q.getSingleResult();	
		Long idProject = projectResult.getId();
		Key userId = userResult.getId();
	//	SharedResources sharedResource = new SharedResources();
		try{
			sharedResource.setProject(idProject);
			sharedResource.setUserSharingProject(userId);
			em.persist(sharedResource);
		}finally{
			em.close();
		}
	}

	public List<SharedProjects> showSharedResources(Users user) {
		EntityManager em = EMFService.get().createEntityManager();
		Query q = em.createQuery("select e from Users e where user = :user");
		q.setParameter("user", user.getUser());
		Users userResult = (Users) q.getSingleResult();
		List<Key> sharedResourcesofUser = userResult.getSharedProjects();
		System.out.println(sharedResourcesofUser);
		List<SharedProjects> listSharedResources = new ArrayList<SharedProjects>();
		for(int i=0; i<sharedResourcesofUser.size(); i++){
			Key sharedResourceKey = sharedResourcesofUser.get(i);
			SharedProjects sharedResource = em.find(SharedProjects.class, sharedResourceKey);
			listSharedResources.add(sharedResource);
			System.out.println(listSharedResources);
		}
		return listSharedResources;
	}

	public List<Projects> showProjects(Users user) {
		EntityManager em = EMFService.get().createEntityManager();
		List<SharedProjects> list = showSharedResources(user);
		List<Projects> projectsList = new ArrayList<Projects>();
		for(int i =0; i<list.size(); i++){
			SharedProjects sharedResource = list.get(i);
			Long projectKey = sharedResource.getProject();
			Query q = em.createQuery("select e from Projects e where id = :id");
			q.setParameter("id", projectKey);
			Projects project = (Projects) q.getSingleResult();
			System.out.println(project);
			projectsList.add(project);
			System.out.println(projectsList);
		}
		return projectsList;
	}
	
	public Users returnUser(Users user) {
		EntityManager em = EMFService.get().createEntityManager();
		Query q = em.createQuery("select e from Users e where user = :user");
		q.setParameter("user", user.getUser());
		Users userResult = (Users) q.getSingleResult();
		return userResult;

		
	}
	

	public void addUsersReceivers(Users userReceiver,
			SharedProjects sharedResource) {
		EntityManager em = EMFService.get().createEntityManager();
		SharedProjects sharedResourcesResult = new SharedProjects();
		sharedResourcesResult = em.find(SharedProjects.class, sharedResource.getId());
		Query q = em.createQuery("select e from Users e where user = :user");
		q.setParameter("user", userReceiver.getUser());
		Users userResult = (Users) q.getSingleResult();
		sharedResourcesResult.getUserReceiver().add(userResult.getId());
		try{
			em.persist(sharedResourcesResult);
		}finally{
			em.close();
		}
		
	}

	public void addReferenceOfProjectReceived(SharedProjects sharedResource,
			Users user) {
		EntityManager em = EMFService.get().createEntityManager();
		Query q = em.createQuery("select e from Users e where user = :user");
		q.setParameter("user", user.getUser());
		Users userResult = (Users) q.getSingleResult();
		SharedProjects sharedResourceResult = em.find(SharedProjects.class, sharedResource.getId());
		try{
			userResult.getSharedProjects().add(sharedResourceResult.getId());
			em.persist(userResult);
		}finally{
			em.close();
		}
		
	}

	


	

}
