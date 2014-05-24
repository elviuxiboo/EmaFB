package mx.ipn.escom.ema.model.greeting.dao.impl;

import mx.ipn.escom.ema.model.entities.Greeting;
import mx.ipn.escom.ema.model.greeting.dao.GreetingDAO;
import mx.ipn.escom.ema.model.persistence.EMFService;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
import java.util.ArrayList;

public class GreetingDAOImpl implements GreetingDAO{    
    public List<Greeting> getAll(){
	EntityManager em = EMFService.get().createEntityManager();
	Query q = em.createQuery("select g from Greeting g");
	return new ArrayList<Greeting>(q.getResultList());
    }
    public void save(Greeting greeting) {
    	EntityManager em = EMFService.get().createEntityManager();
	try{
	    em.persist(greeting);
	}finally{
	    em.close();
	}
    }
}