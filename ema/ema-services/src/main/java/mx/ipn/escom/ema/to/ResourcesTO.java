package mx.ipn.escom.ema.to;

import java.io.Serializable;
import java.util.Date;

import com.google.appengine.api.datastore.Key;

public class ResourcesTO implements Serializable {
    
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
    public Key getHtmlrec() {
        return htmlrec;
    }
    public void setHtmlrec(Key htmlrec) {
        this.htmlrec = htmlrec;
    }
    public Key getCssrec() {
        return cssrec;
    }
    public void setCssrec(Key cssrec) {
        this.cssrec = cssrec;
    }
    public Long getProject() {
        return project;
    }
    public void setProject(Long project) {
        this.project = project;
    }

}
