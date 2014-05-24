package mx.ipn.escom.ema.model.greeting.dao;

import mx.ipn.escom.ema.model.entities.Greeting;
import java.util.List;

public interface GreetingDAO{    
    List<Greeting> getAll();
    void save(Greeting greeting);
}