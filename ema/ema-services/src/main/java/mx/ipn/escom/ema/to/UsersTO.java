package mx.ipn.escom.ema.to;

import java.io.Serializable;
import java.util.List;

import com.google.appengine.api.datastore.Key;

public class UsersTO implements Serializable {
    
    //private Key id;
    private String user;
    private String name;
    
    private List<Long> projects;
    private List<Key> sharedProjects;
  
  /*  
    public Key getId() {
        return id;
    }
    public void setId(Key id) {
        this.id = id;
    }
   */ 
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
