package mx.ipn.escom.ema.services;

import mx.ipn.escom.ema.to.GreetingTO;
import java.util.List;

public interface GreetingService{    
    public void saveGreeting(GreetingTO greetingTO);
    public List<GreetingTO> getGreetings();
}