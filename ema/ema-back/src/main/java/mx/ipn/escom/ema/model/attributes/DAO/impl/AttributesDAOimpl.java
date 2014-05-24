package mx.ipn.escom.ema.model.attributes.DAO.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

import mx.ipn.escom.ema.model.attributes.DAO.AttributesDAO;
import mx.ipn.escom.ema.model.entities.Attributes;
import mx.ipn.escom.ema.model.entities.Tags;
import mx.ipn.escom.ema.model.persistence.EMFService;
import mx.ipn.escom.ema.model.tags.DAO.impl.TagsDAOimpl;

public class AttributesDAOimpl implements AttributesDAO{

	public void addAttribute(Attributes a) {
		EntityManager em = EMFService.get().createEntityManager();
		try{
			em.merge(a);
		}finally{
			em.close();
		}
		
	}

	public Attributes findAttribute(String name) {
		EntityManager em = EMFService.get().createEntityManager();
		Query q = em.createQuery("SELECT e FROM Attributes e where e.name = :nombre");
		q.setParameter("nombre", name);
		Attributes etiq = (Attributes) q.getSingleResult();
		em.close();
		return etiq;
	}



	public void addTagToAttribute(String nameTag, String nameAttribute) {
		EntityManager em = EMFService.get().createEntityManager();
		AttributesDAOimpl adi = new AttributesDAOimpl();
		TagsDAOimpl tdi = new TagsDAOimpl();
		Attributes attribute = adi.findAttribute(nameAttribute);
		Tags tag = tdi.findTag(nameTag);
		Key keyTag = KeyFactory.createKey(Tags.class.getSimpleName(), tag.getId());
		Attributes attributeResult = em.find(Attributes.class, attribute.getId());
		attributeResult.getListTags().add(keyTag);
		try{
			em.persist(attributeResult);
		}finally{
			em.close();
		}
		
	}

	public List<Attributes> getAttributeOfTag(String nombre) {
		EntityManager em = EMFService.get().createEntityManager();
		TagsDAOimpl tdi = new TagsDAOimpl();
		List<Attributes> listAttributesOfTag = tdi.getAttributes(nombre);
		Attributes attribute = new Attributes();
		List<Attributes> list = new ArrayList<Attributes>();
		for(int i =0; i<listAttributesOfTag.size(); i++){
			attribute = listAttributesOfTag.get(i);
			list.add(attribute);
		}
		return list;
	}

	public List<String> attributesName(String name) {
		AttributesDAOimpl adi = new AttributesDAOimpl();
		List<Attributes> list = adi.getAttributeOfTag(name);
		List<String> listName = new ArrayList<String>();
		System.out.println(list);
		for(int i=0; i<list.size(); i++){
			Attributes nameAttribute = list.get(i);
			System.out.println(nameAttribute.getName());
			listName.add(nameAttribute.getName());
		}
		return listName;
	}

	
}
