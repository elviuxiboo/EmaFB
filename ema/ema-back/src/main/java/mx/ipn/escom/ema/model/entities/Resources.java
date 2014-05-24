package mx.ipn.escom.ema.model.entities;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.datanucleus.annotations.Unowned;

@Entity

public class Resources implements Serializable {
	
	private static final long serialVersionUID = 5480578950942739025L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Unowned
	private Key id;
	private Key htmlrec;
	public Key cssrec;
	private Long project;

	public Key getId() {
		return id;
	}

	public void setId(Key id) {
		this.id = id;
	}

	public Long getProject() {
		return project;
	}

	public void setProject(Long project) {
		this.project = project;
	}

	public Key getCssrec() {
		return cssrec;
	}

	public void setCssrec(Key cssrec) {
		this.cssrec = cssrec;
	}

	public Key getHtmlrec() {
		return htmlrec;
	}

	public void setHtmlrec(Key htmlrec) {
		this.htmlrec = htmlrec;
	}

}
