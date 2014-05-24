package mx.ipn.escom.ema.model.resources.DAO.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

import mx.ipn.escom.ema.model.entities.CSSResources;
import mx.ipn.escom.ema.model.entities.HTMLResources;
import mx.ipn.escom.ema.model.entities.Projects;
import mx.ipn.escom.ema.model.entities.Resources;
import mx.ipn.escom.ema.model.entities.Users;
import mx.ipn.escom.ema.model.persistence.EMFService;
import mx.ipn.escom.ema.model.projects.DAO.impl.ProjectsDAOimpl;
import mx.ipn.escom.ema.model.resources.DAO.ResourcesDAO;


public class ResourcesDAOimpl implements ResourcesDAO{

	public void addResource(Resources resource) {
		EntityManager em = EMFService.get().createEntityManager();
		EntityTransaction txn = em.getTransaction();
		txn.begin();
		try{
			em.persist(resource);
			txn.commit();
		}finally{
			em.close();
		}
		
	}

	public void deleteResource(Resources resource) {
		EntityManager em = EMFService.get().createEntityManager();
		Resources resourcesResult = em.find(Resources.class, resource.getId());
		try{
			em.remove(resourcesResult);
		}finally{
			em.close();
		}
		
	}

	public Resources findResourcebyId(Key id) {
		EntityManager em = EMFService.get().createEntityManager();
		Query q = em.createQuery("select e from Resources e where id= :id");
		q.setParameter("id", id);
		Resources resource = (Resources)q.getSingleResult();
		return resource;
	}

	public void addReferenceOfCSS(CSSResources css, Resources resource) {
		EntityManager em = EMFService.get().createEntityManager();
		CSSResources cssResult = new CSSResources();
		Resources resourceResult = em.find(Resources.class, resource.getId());
		Key keyCSS = KeyFactory.createKey(CSSResources.class.getSimpleName(), css.getId());
		cssResult = em.find(CSSResources.class, keyCSS);
		resourceResult.setCssrec(keyCSS);
		try{
			em.persist(resourceResult);
		}finally{
			em.close();
		}
		
	}

	public void addReferenceOfHTML(HTMLResources html, Resources resource) {
		EntityManager em = EMFService.get().createEntityManager();
		HTMLResources htmlResult = new HTMLResources();
		Resources resourceResult = em.find(Resources.class, resource.getId());
		htmlResult = em.find(HTMLResources.class, html.getId());
		resourceResult.setHtmlrec(htmlResult.getId());
		try{
			em.persist(resourceResult);
		}finally{
			em.close();
		}
		
	}

	public void addReferenceOfProject(Projects project, Resources resource) {
		EntityManager em = EMFService.get().createEntityManager();
		Projects projectResult = new Projects();
		Resources resourceResult = em.find(Resources.class, resource.getId());
		Key keyProject = KeyFactory.createKey(Projects.class.getSimpleName(), project.getId());
		projectResult = em.find(Projects.class, keyProject);
		resourceResult.setProject(projectResult.getId());
		try{
			em.persist(resourceResult);
		}finally{
			em.close();
		}
		
	}

	public Resources findResourceFromProject(Projects project) {
		List<com.google.appengine.api.datastore.Key> listResources;
		Resources resource = new Resources();
		EntityManager em = EMFService.get().createEntityManager();
		Query q = em.createQuery("select e from Projects e where e.name= :name");
		q.setParameter("name", project.getName());
		project = (Projects)q.getSingleResult();	
		System.out.println(project);
		listResources = project.getResources();
		for(int i = 0; i<listResources.size(); i++){
			com.google.appengine.api.datastore.Key keyResource = listResources.get(i);
			resource = em.find(Resources.class, keyResource);
			System.out.println(keyResource);
			System.out.println(resource);
		}
		return resource;
	}

	public List<Key> findResourceListFromUserProject(Projects project, Users user) {
	/*	List<com.google.appengine.api.datastore.Key> listResources;
		Resources resource = new Resources();
		EntityManager em = EMFService.get().createEntityManager();
		Query q = em.createQuery("select e from Projects e where e.name= :name and e.user = user");
		q.setParameter("name", project.getName());
		Projects projectResult = (Projects) q.getSingleResult();*/
		ProjectsDAOimpl pdi = new ProjectsDAOimpl();
		Projects projectResult = pdi.findProject(project, user);
		List<Key> resources = projectResult.getResources();
		System.out.println(resources);
		return resources;
	}

	public void deleteResourceCSS(Resources resource) {
	/*	ProjectsDAOimpl pdi = new ProjectsDAOimpl();
		Projects projectResult = pdi.findProject(project, user);
		CSSResourcesDAOimpl crdi = new CSSResourcesDAOimpl();
		CSSResources cssResult = crdi.findCSSofProjectofUser(resource, projectResult, user);
		EntityManager em = EMFService.get().createEntityManager();
		Query q = em.createQuery("select e from Resources e where id= :id");
		q.setParameter("id", cssResult.getResource());
		Resources result = (Resources) q.getSingleResult();
			try{
				em.remove(result);
			}finally{
				em.close();
			}*/
		EntityManager em = EMFService.get().createEntityManager();
		/*Query resourcesQuery = em.createQuery("select e from Resources e where cssrec = :css and project = :project");
		resourcesQuery.setParameter("css", resource.getId());
		resourcesQuery.setParameter("project", project.getId());
		Resources resourceResult = (Resources)resourcesQuery.getSingleResult();*/
		Resources resourceResult = em.find(Resources.class, resource.getId());
		try{
			em.remove(resourceResult);
		}finally{
			em.close();
		}
	}

	public void deleteResourceHTML(HTMLResources resource, Projects project) {
	/*	ProjectsDAOimpl pdi = new ProjectsDAOimpl();
		Projects projectResult = pdi.findProject(project, user);
		HTMLResourcesDAOimpl hrdi = new HTMLResourcesDAOimpl();
		HTMLResources htmlResult = hrdi.findHTMLofProjectofUser(resource, projectResult, user);
		EntityManager em = EMFService.get().createEntityManager();
		//Resources result = em.find(Resources.class, htmlResult.getResource());
		/*Query q = em.createQuery("select e from Resources e where id= :id");
		q.setParameter("id", htmlResult.getResource());
		Resources result = (Resources) q.getSingleResult();*/
/*		com.google.appengine.api.datastore.Key keyResource = htmlResult.getResource();
		Query q = em.createQuery("select e from Resources e where id= :id");
		q.setParameter("id",keyResource);
		Resources result = (Resources) q.getSingleResult();
			try{
				em.remove(result);
			}finally{
				em.close();
			}*/
		EntityManager em = EMFService.get().createEntityManager();
		Query resourcesQuery = em.createQuery("select e from Resources e where htmlrec = :html and project = :project");
		resourcesQuery.setParameter("html", resource.getId());
		resourcesQuery.setParameter("project", project.getId());
		Resources resourceResult = (Resources)resourcesQuery.getSingleResult();
		try{
			em.remove(resourceResult);
		}finally{
			em.close();
		}
		
	}

	public List<Key> findResourceListFromProject(Projects project) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
}
