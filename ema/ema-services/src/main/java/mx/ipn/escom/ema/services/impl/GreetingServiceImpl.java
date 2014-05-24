package mx.ipn.escom.ema.services.impl;

import mx.ipn.escom.ema.to.GreetingTO;
import mx.ipn.escom.ema.services.GreetingService;
import mx.ipn.escom.ema.model.entities.Greeting;
import mx.ipn.escom.ema.model.greeting.dao.GreetingDAO;
import mx.ipn.escom.ema.model.greeting.dao.impl.GreetingDAOImpl;

import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import java.io.Serializable;

public class GreetingServiceImpl implements GreetingService, Serializable{
    public void saveGreeting(GreetingTO greetingTO){
	Greeting greeting = new Greeting();
	greeting.setContent(greetingTO.getContent());
	greeting.setDate(new Date());
	GreetingDAO greetingDAO = new GreetingDAOImpl();
	greetingDAO.save(greeting);
    }

    public List<GreetingTO> getGreetings(){
	ArrayList<GreetingTO> greetings = new ArrayList<GreetingTO>();
	GreetingTO greetingTO = null;
	GreetingDAO greetingDAO = new GreetingDAOImpl();

	for(Greeting greeting: greetingDAO.getAll()){
	    greetingTO = new GreetingTO();
	    greetingTO.setContent(greeting.getContent());
	    greetingTO.setDate(greeting.getDate());
	    greetings.add(greetingTO);
	}

	return greetings;
    }
}