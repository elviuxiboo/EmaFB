package mx.ipn.escom.ema.to;

import java.io.Serializable;
import java.util.Date;

public class GreetingTO implements Serializable{

    private String content;
    private Date date;

    public void setContent(String content){
	this.content = content;
    }
    public String getContent(){    
	return content;
    }

    public void setDate(Date date){
	this.date = date;
    }
    public Date getDate(){
	return date;
    }
}