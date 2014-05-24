package mx.ipn.escom.ema.model.resources.DAO.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

import mx.ipn.escom.ema.model.entities.CSSResources;
import mx.ipn.escom.ema.model.entities.HTMLResources;
import mx.ipn.escom.ema.model.entities.Projects;
import mx.ipn.escom.ema.model.entities.Resources;
import mx.ipn.escom.ema.model.entities.Users;
import mx.ipn.escom.ema.model.persistence.EMFService;
import mx.ipn.escom.ema.model.projects.DAO.ProjectsDAO;
import mx.ipn.escom.ema.model.projects.DAO.impl.ProjectsDAOimpl;
import mx.ipn.escom.ema.model.resources.DAO.ResourceDAOcss;
import mx.ipn.escom.ema.model.resources.DAO.ResourceDAOhtml;
import mx.ipn.escom.ema.model.resources.DAO.ResourcesDAO;

public class HTMLResourcesDAOimpl implements ResourceDAOhtml{


	public void addReferencetoResource(Resources resource, HTMLResources html) {
		EntityManager em = EMFService.get().createEntityManager();
		Resources resourceResult = em.find(Resources.class, resource.getId());
		HTMLResources htmlkey = em.find(HTMLResources.class, html.getId());
		htmlkey.setResource(resourceResult.getId());
		try{
			em.persist(htmlkey);
		}finally{
			em.close();
		}
		
	}

	public void deleteResourceHTML(HTMLResources html, Projects project,Users user) {
		EntityManager em = EMFService.get().createEntityManager();
		Query query = em.createQuery("select e from Users e where user = :user");
		query.setParameter("user", user.getUser());
		Users userResult = (Users)query.getSingleResult();
		Query userQuery = em.createQuery("select e from Projects e where name = :name and user = :user");
		userQuery.setParameter("name", project.getName());
		userQuery.setParameter("user", userResult.getId());
		Projects projectResult = (Projects) userQuery.getSingleResult();
		List<Key> listResources = new ArrayList<Key>();
		listResources = projectResult.getResources();
		ResourcesDAO resourceDAO = new ResourcesDAOimpl();
		ProjectsDAO projectDAO = new ProjectsDAOimpl();
		ResourceDAOcss cssDAO = new CSSResourcesDAOimpl();
		try{
			Query htmlQuery = em.createQuery("select e from HTMLResources e where name = :name");
			htmlQuery.setParameter("name", html.getName());
			List<HTMLResources> htmlList = (List<HTMLResources>) htmlQuery.getResultList();
			if(htmlList.size() == 0){
				CSSResources css = new CSSResources();
				css.setName(html.getName());
				cssDAO.deleteResourceCSS(css, projectResult, userResult);
			}else{
				for(int i=0; i<htmlList.size();i++){
					for(int j=0; j<listResources.size(); j++){
						HTMLResources htmlResultList = new HTMLResources();
						htmlResultList = htmlList.get(i);
						Key resourcesProject = listResources.get(j);
						Key resourcesKey = htmlResultList.getResource();
						Resources resourceResult = em.find(Resources.class, resourcesKey);
						Resources resourceResultHtml = em.find(Resources.class, resourcesProject);
						if(resourceResult == resourceResultHtml){
					 	     Query queryRemove = em.createQuery("select e from HTMLResources e where resource = :resource");
					 	     queryRemove.setParameter("resource", resourcesKey);
					 	     HTMLResources removeHtml = (HTMLResources) queryRemove.getSingleResult();
					 	     resourceDAO.deleteResourceHTML(removeHtml, projectResult);
					 	     projectDAO.deleteHTMLfromProject(projectResult, resourcesProject, user);
					 	     em.remove(removeHtml);
						}
						
					}
				}
			}
		}finally{
			em.close();
		}	
	}

	public void updateResourceHTML(HTMLResources html, Projects project,Users user, String code) {
		Date date = new Date();
		EntityManager em = EMFService.get().createEntityManager();
		Query query = em.createQuery("select e from Users e where user = :user");
		query.setParameter("user", user.getUser());
		Users userResult = (Users)query.getSingleResult();
		Query userQuery = em.createQuery("select e from Projects e where name = :name and user = :user");
		userQuery.setParameter("name", project.getName());
		userQuery.setParameter("user", userResult.getId());
		Projects projectResult = (Projects) userQuery.getSingleResult();
		List<Key> listResources = new ArrayList<Key>();
		listResources = projectResult.getResources();
		ResourceDAOcss cssDAO = new CSSResourcesDAOimpl();
		try{
			Query htmlQuery = em.createQuery("select e from HTMLResources e where name = :name");
			htmlQuery.setParameter("name", html.getName());
			List<HTMLResources> htmlList = (List<HTMLResources>) htmlQuery.getResultList();
			if(htmlList.size() == 0){
				CSSResources css = new CSSResources();
				css.setName(html.getName());
				css.setCode(html.getCode());
				cssDAO.updateResourceCSS(css, projectResult, userResult, code);
			}else{
				for(int i=0; i<htmlList.size();i++){
					for(int j=0; j<listResources.size(); j++){
						HTMLResources htmlResultList = new HTMLResources();
						htmlResultList = htmlList.get(i);
						Key resourcesProject = listResources.get(j);
						Key resourcesKey = htmlResultList.getResource();
						Resources resourceResult = em.find(Resources.class, resourcesKey);
						Resources resourceResultHtml = em.find(Resources.class, resourcesProject);
						if(resourceResult == resourceResultHtml){
					 	     Query queryUpdate = em.createQuery("select e from HTMLResources e where resource = :resource");
					 	     queryUpdate.setParameter("resource", resourcesKey);
					 	     HTMLResources updateHtml = (HTMLResources) queryUpdate.getSingleResult();
					 	     updateHtml.setCode(code);
					 	     updateHtml.setDate(date);
					 	     em.persist(updateHtml);
						}
						
					}
				}
			}
		}finally{
			em.close();
		}
		
	}

	public void addResourceHTMLtoProject(HTMLResources html, Projects project,Users user) {
		ProjectsDAOimpl pdi = new ProjectsDAOimpl();
		Projects projectResult = pdi.findProject(project, user);
		ResourcesDAOimpl rdi = new ResourcesDAOimpl();
		Resources resource = new Resources();
		resource.setProject(projectResult.getId());
		rdi.addResource(resource);
		pdi.addResourcetoProjectTest(resource, projectResult);
		EntityManager em = EMFService.get().createEntityManager();
		html.setResource(resource.getId());
		try{
			em.persist(html);
		}finally{
			em.close();
		}
		rdi.addReferenceOfHTML(html, resource);	
	}

	public HTMLResources findHTMLofProject(HTMLResources html, Projects project) {
		ResourcesDAOimpl rdi = new ResourcesDAOimpl();
		List<Key> resource = rdi.findResourceListFromProject(project);
		HTMLResources htmlResult= new HTMLResources();
		EntityManager em = EMFService.get().createEntityManager();
		Query q = em.createQuery("select e from HTMLResources e where name = :name");
		q.setParameter("name", html.getName());
		htmlResult = (HTMLResources) q.getSingleResult();
		Key cssResource = htmlResult.getResource();
		for(int i =0; i<resource.size(); i++){
			if(resource.get(i) == cssResource){
				Query qR = em.createQuery("select e from CSSResources e where resource = :idResource");
				qR.setParameter("idResource", cssResource);
				htmlResult = (HTMLResources) q.getSingleResult();
			}
			
		}
		return htmlResult;
	}

	public void modifyName(HTMLResources html, Projects project, Users user,String name) {
		Date date = new Date();
		ProjectsDAOimpl pdi = new ProjectsDAOimpl();
		Projects projectResult = pdi.findProject(project, user);
		HTMLResources htmlResult = findHTMLofProjectofUser(html, projectResult, user);
		EntityManager em = EMFService.get().createEntityManager();
		Query q = em.createQuery("select e from HTMLResources e where name= :name and id= :id");
		q.setParameter("name", htmlResult.getName());
		q.setParameter("id", htmlResult.getId());
		HTMLResources nameOfResource = (HTMLResources) q.getSingleResult();
		nameOfResource.setName(name);
		nameOfResource.setDate(date);
		try{
			em.persist(nameOfResource);
		}finally{
			em.close();
		}
		
	}

	public List<HTMLResources> showHTMLResourcesFromProject(Projects project,Users user) {
		ResourcesDAOimpl resources = new ResourcesDAOimpl();
		List<Key> resourcesList = resources.findResourceListFromUserProject(project, user);
		List<HTMLResources> htmlList = new ArrayList<HTMLResources>();
		HTMLResources html = new HTMLResources();
		EntityManager em = EMFService.get().createEntityManager();
		for(int i =0; i<resourcesList.size(); i++){
			System.out.println(resourcesList.get(i));
			Key resourceKey = resourcesList.get(i);
			Resources resource = em.find(Resources.class, resourceKey);
			if(resource.getHtmlrec() != null){
				Query q = em.createQuery("select e from HTMLResources e where resource = :resource");
				q.setParameter("resource", resource.getId());
				html = (HTMLResources) q.getSingleResult();
				htmlList.add(html);
				System.out.println(htmlList);
			}
		}
		return htmlList;
	}

	public HTMLResources findHTMLofProjectofUser(HTMLResources html, Projects project, Users user) {
		EntityManager em = EMFService.get().createEntityManager();
		Query query = em.createQuery("select e from Users e where user = :user");
		query.setParameter("user", user.getUser());
		Users userResult = (Users)query.getSingleResult();
		Query userQuery = em.createQuery("select e from Projects e where name = :name and user = :user");
		userQuery.setParameter("name", project.getName());
		userQuery.setParameter("user", userResult.getId());
		Projects projectResult = (Projects) userQuery.getSingleResult();
		List<Key> listResources = new ArrayList<Key>();
		listResources = projectResult.getResources();
		ResourceDAOcss cssDAO = new CSSResourcesDAOimpl();
		HTMLResources returnHtml = new HTMLResources();
		try{
			Query htmlQuery = em.createQuery("select e from HTMLResources e where name = :name");
			htmlQuery.setParameter("name", html.getName());
			List<HTMLResources> htmlList = (List<HTMLResources>) htmlQuery.getResultList();
			/*if(htmlList.size() == 0){
				CSSResources css = new CSSResources();
				css.setName(html.getName());
				CSSResources cssResult = cssDAO.findCSSofProjectofUser(css,project, user);
			}else{*/
			if(htmlList.size() != 0 ){
				for(int i=0; i<htmlList.size();i++){
					for(int j=0; j<listResources.size(); j++){
						HTMLResources htmlResultList = new HTMLResources();
						htmlResultList = htmlList.get(i);
						Key resourcesProject = listResources.get(j);
						Key resourcesKey = htmlResultList.getResource();
						Resources resourceResult = em.find(Resources.class, resourcesKey);
						Resources resourceResultHtml = em.find(Resources.class, resourcesProject);
						if(resourceResult == resourceResultHtml){
					 	     Query queryReturn = em.createQuery("select e from HTMLResources e where resource = :resource");
					 	     queryReturn.setParameter("resource", resourcesKey);
					 	     returnHtml = (HTMLResources) queryReturn.getSingleResult();
						}
					}
				}
			}else {
				return null;
			}
		}finally{
			em.close();
		}
		return returnHtml;	
	}
	
	public void deleteAllHTML(Resources resource) {
		EntityManager em = EMFService.get().createEntityManager();
		Query q = em.createQuery("select e from HTMLResources e where resource = :resource");
		q.setParameter("resource", resource.getId());
		HTMLResources html = (HTMLResources) q.getSingleResult();
		if(html == null){
			System.out.println("No hay HTML");
		}else{
			try{
				em.remove(html);
			}finally{
				em.close();
			}
		}
	}

	public HTMLResources existingHTMLinProject(HTMLResources html,Projects project, Users user) {
		ResourcesDAOimpl rdi = new ResourcesDAOimpl();
		List<Key> resource = rdi.findResourceListFromUserProject(project, user);
		HTMLResources htmlResult= new HTMLResources();
		EntityManager em = EMFService.get().createEntityManager();
		Query q = em.createQuery("select e from HTMLResources e where name = :name");
		q.setParameter("name", html.getName());
		try{
			htmlResult = (HTMLResources) q.getSingleResult();
			if(htmlResult !=null){
				System.out.println("Existe HTML");
				Key htmlResource = htmlResult.getResource();
				for(int i =0; i<resource.size(); i++){
					if(resource.get(i) == htmlResource){
						Query qR = em.createQuery("select e from HTMLResources e where resource = :idResource");
						qR.setParameter("idResource", htmlResource);
						htmlResult = (HTMLResources) q.getSingleResult();
					}
			}
		}
		}catch(Exception e){
			addResourceHTMLtoProject(html, project, user);
			e.printStackTrace();
		}
		return htmlResult;
	}


}
