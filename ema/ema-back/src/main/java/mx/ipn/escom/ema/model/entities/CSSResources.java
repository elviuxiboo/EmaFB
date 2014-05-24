package mx.ipn.escom.ema.model.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.datanucleus.annotations.Unowned;

@Entity
public class CSSResources implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4976650560494569843L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Unowned
	private Long id;
	private String name;
	private Date date;
	private String code;
	public Key resource;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Key getResource() {
		return resource;
	}
	public void setResource(Key resource) {
		this.resource = resource;
	}
	

}
