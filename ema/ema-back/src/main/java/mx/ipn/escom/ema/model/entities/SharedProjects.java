package mx.ipn.escom.ema.model.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.datanucleus.annotations.Unowned;

@Entity
public class SharedProjects implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Unowned
	private Key id;
	private Long project;
	private List<Key> userReceiver;
	private Key userSharingProject;
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
	public Key getUserSharingProject() {
		return userSharingProject;
	}
	public void setUserSharingProject(Key userSharingProject) {
		this.userSharingProject = userSharingProject;
	}
	public List<Key> getUserReceiver() {
		return userReceiver;
	}
	public void setUserReceiver(List<Key> userReceiver) {
		this.userReceiver = userReceiver;
	}
}
