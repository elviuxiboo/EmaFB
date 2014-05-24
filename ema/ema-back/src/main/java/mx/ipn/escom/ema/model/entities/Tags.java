package mx.ipn.escom.ema.model.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.datanucleus.annotations.Unowned;

/**
 * @author Andy
 *
 */
@Entity
public class Tags implements Serializable{
	
	private static final long serialVersionUID = -2777580638398945829L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Unowned
	private Long id;
	private String name;
	private String description;
	private List<Key> listAttributes;
	
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<Key> getListAttributes() {
		return listAttributes;
	}
	public void setListAttributes(List<Key> listAttributes) {
		this.listAttributes = listAttributes;
	}
	
	
	
}
