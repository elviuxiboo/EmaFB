package mx.ipn.escom.ema.to;

import java.io.Serializable;
import java.util.List;

import com.google.appengine.api.datastore.Key;

public class SharedResources  implements Serializable{
    
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
    public List<Key> getUserReceiver() {
        return userReceiver;
    }
    public void setUserReceiver(List<Key> userReceiver) {
        this.userReceiver = userReceiver;
    }
    public Key getUserSharingProject() {
        return userSharingProject;
    }
    public void setUserSharingProject(Key userSharingProject) {
        this.userSharingProject = userSharingProject;
    }
  
    

}
