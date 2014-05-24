package mx.ipn.escom.ema.model.resources.DAO.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

import mx.ipn.escom.ema.model.entities.CSSResources;
import mx.ipn.escom.ema.model.entities.HTMLResources;
import mx.ipn.escom.ema.model.entities.Projects;
import mx.ipn.escom.ema.model.entities.Resources;
import mx.ipn.escom.ema.model.entities.SharedResources;
import mx.ipn.escom.ema.model.entities.Users;
import mx.ipn.escom.ema.model.persistence.EMFService;
import mx.ipn.escom.ema.model.projects.DAO.impl.ProjectsDAOimpl;
import mx.ipn.escom.ema.model.resources.DAO.SharedResourcesDAO;

public class SharedResourcesDAOimpl implements SharedResourcesDAO{

	public void shareResource(SharedResources sharedResource, Projects project, Users user) {
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
		//SharedResources sharedResource = new SharedResources();
		try{
			sharedResource.setProject(idProject);
			sharedResource.setUserSharingProject(userId);
			em.persist(sharedResource);
		}finally{
			em.close();
		}
		
	}

	public List<SharedResources> showSharedResources(Users user) {
		EntityManager em = EMFService.get().createEntityManager();
		Query q = em.createQuery("select e from Users e where user = :user");
		q.setParameter("user", user.getUser());
		Users userResult = (Users) q.getSingleResult();
		List<Key> sharedResourcesofUser = userResult.getSharedProjects();
		List<SharedResources> listSharedResources = new ArrayList<SharedResources>();
		for(int i=0; i<sharedResourcesofUser.size(); i++){
			Key sharedResourceKey = sharedResourcesofUser.get(i);
			SharedResources sharedResource = em.find(SharedResources.class, sharedResourceKey);
			listSharedResources.add(sharedResource);
		}
		return listSharedResources;
	}

	public void addUsersReceivers(Users userReceiver, SharedResources sharedResource) {
		EntityManager em = EMFService.get().createEntityManager();
		SharedResources sharedResourcesResult = new SharedResources();
		sharedResourcesResult = em.find(SharedResources.class, sharedResource.getId());
	/*	Query q = em.createQuery("select e from SharedResources where id = :id");
		q.setParameter("id", sharedResource.getId());
		SharedResources sharedResourcesResult = (SharedResources) q.getSingleResult();*/
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

	public void addReferenceOfProjectReceived(SharedResources sharedResource,Users user) {
		EntityManager em = EMFService.get().createEntityManager();
		Query q = em.createQuery("select e from Users e where user = :user");
		q.setParameter("user", user.getUser());
		Users userResult = (Users) q.getSingleResult();
		SharedResources sharedResourceResult = em.find(SharedResources.class, sharedResource.getId());
		try{
			userResult.getSharedProjects().add(sharedResourceResult.getId());
			em.persist(userResult);
		}finally{
			em.close();
		}
		
	}

	public List<Projects> showProjects(Users user) {
		EntityManager em = EMFService.get().createEntityManager();
		List<SharedResources> list = showSharedResources(user);
		List<Projects> projectsList = new ArrayList<Projects>();
		for(int i =0; i<list.size(); i++){
			SharedResources sharedResource = list.get(i);
			Long projectKey = sharedResource.getProject();
			Query q = em.createQuery("select e from Projects e where id = :id");
			q.setParameter("id", projectKey);
			Projects project = (Projects) q.getSingleResult();
			projectsList.add(project);
		}
		return projectsList;
	}

	public Projects returnProject(Users user) {
		ProjectsDAOimpl pdi = new ProjectsDAOimpl();
		List<Projects> listProjects = showProjects(user);
		Projects result = new Projects();
		for(int i =0; i<listProjects.size(); i++){
			Projects projectResult = listProjects.get(i);
			System.out.println(projectResult.getId());
			Key userKey = projectResult.getUser();
			Users userProject = new Users();
			EntityManager em = EMFService.get().createEntityManager();
			userProject = em.find(Users.class, userKey);
			result = pdi.findProject(projectResult, userProject);
		}
		return result;
	}

}
