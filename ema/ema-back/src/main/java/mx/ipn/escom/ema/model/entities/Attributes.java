package mx.ipn.escom.ema.model.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;

import com.google.appengine.api.datastore.Key;

@Entity

public class Attributes implements Serializable{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Key id;
	private String name;
	private String description;
	private List<Key> listTags;
	
	public Key getId() {
		return id;
	}
	public void setId(Key id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<Key> getListTags() {
		return listTags;
	}
	public void setListTags(List<Key> listTags) {
		this.listTags = listTags;
	}
	
	
	

}
