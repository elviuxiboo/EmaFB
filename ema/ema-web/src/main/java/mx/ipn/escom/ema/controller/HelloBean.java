package mx.ipn.escom.ema.controller;
 
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import mx.ipn.escom.ema.to.GreetingTO;
import mx.ipn.escom.ema.services.GreetingService;
import mx.ipn.escom.ema.services.impl.GreetingServiceImpl;

import java.io.Serializable;
import java.util.List;
 
@ManagedBean
@SessionScoped
public class HelloBean implements Serializable {
    
    private static final long serialVersionUID = 1L;
    private GreetingTO greetingTO;
    private String content;
    private String text;

    private GreetingService greetingService = new GreetingServiceImpl();
   
    public String submitGreeting(){
	greetingTO = new GreetingTO();
	greetingTO.setContent(content);
	greetingService.saveGreeting(greetingTO);
	return "welcome";
    }
    public String getText() {
	return text;
    }
    public void setText(String text) {
	this.text = text;
    }
    public String getContent() {
	return content;
    }

    public void setContent(String content) {
	this.content = content;
    }
    public List<GreetingTO> getGreetings(){
	return 	greetingService.getGreetings();
    }
}
