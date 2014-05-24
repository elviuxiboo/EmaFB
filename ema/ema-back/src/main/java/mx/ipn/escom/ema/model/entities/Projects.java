package mx.ipn.escom.ema.model.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.datanucleus.annotations.Unowned;


@Entity
public class Projects implements Serializable {

	private static final long serialVersionUID = -717939912879412176L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Unowned
	private Long id;
	private String name;
	private Key user;
	private Date date;
	
	private List<Key> resources;

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

	public List<Key> getResources() {
		return resources;
	}

	public void setResources(List<Key> resources) {
		this.resources = resources;
	}

	public Key getUser() {
		return user;
	}

	public void setUser(Key user) {
		this.user = user;
	}
	
}
