package mx.ipn.escom.ema.to;

import java.io.Serializable;
import java.util.Date;

public class HTMLResourceTO implements Serializable {
    
    private String name;
    private Date date;
    private String code;
    private ProjectsTO project;
    
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
	public ProjectsTO getProject() {
		return project;
	}
	public void setProject(ProjectsTO project) {
		this.project = project;
	}
    
    

}
