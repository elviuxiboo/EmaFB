package mx.ipn.escom.ema.model.entities;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.io.Serializable;

@Entity
public class Greeting implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String guestbookName;
    private Date date;
    private String content;

    public Long getId(){
	return id;
    }
    public void setId(Long id ){
	this.id = id;
    }
    public String getGuestbookName(){
	return guestbookName;
    }
    public void setGuestbookName(String guestbookName){
	this.guestbookName = guestbookName;
    }
    public String getContent(){
	return content;
    }
    public void setContent(String content){
	this.content = content;
    }
    public Date getDate(){
	return date;
    }
    public void setDate(Date date){
	this.date = date;
    }
}