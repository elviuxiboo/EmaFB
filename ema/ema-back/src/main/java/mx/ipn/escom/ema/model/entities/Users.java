package mx.ipn.escom.ema.model.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.users.User;

@Entity
public class Users implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7949988807840375048L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Key id;
	private String user;
	private String name;
	//@OneToMany
	private List<Long> projects;
	private List<Key> sharedProjects;
	
	public Key getId() {
		return id;
	}
	public void setId(Key id) {
		this.id = id;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Long> getProjects() {
		return projects;
	}
	public void setProjects(List<Long> projects) {
		this.projects = projects;
	}
	public List<Key> getSharedProjects() {
		return sharedProjects;
	}
	public void setSharedProjects(List<Key> sharedProjects) {
		this.sharedProjects = sharedProjects;
	}
	
	
	
	
}
