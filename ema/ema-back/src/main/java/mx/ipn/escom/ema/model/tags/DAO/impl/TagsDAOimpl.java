package mx.ipn.escom.ema.model.tags.DAO.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

import mx.ipn.escom.ema.model.attributes.DAO.impl.AttributesDAOimpl;
import mx.ipn.escom.ema.model.entities.Attributes;
import mx.ipn.escom.ema.model.entities.Projects;
import mx.ipn.escom.ema.model.entities.Tags;
import mx.ipn.escom.ema.model.persistence.EMFService;
import mx.ipn.escom.ema.model.tags.DAO.TagsDAO;

public class TagsDAOimpl implements TagsDAO{

	public void addTag(Tags tag) {
		EntityManager em = EMFService.get().createEntityManager();
		try{
			em.merge(tag);
		}finally{
			em.close();
		}
	}

	public Tags findTag(String nombre) {
		EntityManager em = EMFService.get().createEntityManager();
		Query q = em.createQuery("SELECT e FROM Tags e where e.name = :name");
		q.setParameter("name", nombre);
		Tags tag = (Tags) q.getSingleResult();
		em.close();
		return tag;
	}

	public void addAttributeToTag(String nameTag, String nameAttribute) {
		EntityManager em = EMFService.get().createEntityManager();
		AttributesDAOimpl adi = new AttributesDAOimpl();
		TagsDAOimpl tdi = new TagsDAOimpl();
		Attributes attribute = adi.findAttribute(nameAttribute);
		Tags tag = tdi.findTag(nameTag);
		Key keyTag = KeyFactory.createKey(Tags.class.getSimpleName(), tag.getId());
		Attributes attributeResult = em.find(Attributes.class, attribute.getId());
		Tags tagResult = em.find(Tags.class, keyTag );
		tagResult.getListAttributes().add(attributeResult.getId());
		try{
			em.persist(tagResult);
		}finally{
			em.close();
		}
	}

	public List<Attributes> getAttributes(String name) {
		List<Attributes> listAttributes = new ArrayList<Attributes>();
		EntityManager em = EMFService.get().createEntityManager();
		Query q = em.createQuery("SELECT e FROM Tags e where e.name = :name");
		q.setParameter("name", name);
		Tags tagResult = (Tags)q.getSingleResult();
		List<Key> listKey = tagResult.getListAttributes();
		for(int i=0; i<listKey.size(); i++){
			Key keyAttribute = listKey.get(i);
			Attributes attribute = em.find(Attributes.class, keyAttribute);
			listAttributes.add(attribute);
		}
		return listAttributes;
	}

	public List<Tags> getTags() {
		EntityManager em = EMFService.get().createEntityManager();
		Query q = em.createQuery("SELECT e FROM Tags e");
		List<Tags> tags = (List<Tags>) q.getResultList();
		return tags;
	}

	
	
	/*	@Override
	public void addTag(Tags tag) {
		EntityManager em = EMFService.get().createEntityManager();
		try{
			em.merge(tag);
		}finally{
			em.close();
		}
		
	}

	@Override
	public Tags findTag(String name) {
		EntityManager em = EMFService.get().createEntityManager();
		Query q = em.createQuery("SELECT e FROM Tags e where e.name = :name");
		q.setParameter("name", name);
		Tags tag = (Tags) q.getSingleResult();
		em.close();
		return tag;
	}

	@Override
	public void addAttributeToTag(String nameTag, String nameAttribute) {
		AttributesDAOimpl adi = new AttributesDAOimpl();
		EntityManager em = EMFService.get().createEntityManager();
		Tags tag = findTag(nameTag);
		Attributes attribute = adi.findAttribute(nameAttribute);
		List<Attributes> list = new ArrayList<Attributes>(); 
		list.add(attribute);
		tag.setAtributes(list);
		em.merge(tag);
		em.close();
		
	}*/



}
