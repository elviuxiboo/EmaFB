package mx.ipn.escom.ema.to;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class ProjectsTO implements Serializable {
    
    private String name;
    private Date date;
    private UsersTO user;
    private List<CSSResourceTO> css;
    private List<HTMLResourceTO> html;
    
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
	public UsersTO getUser() {
		return user;
	}
	public void setUser(UsersTO user) {
		this.user = user;
	}
	public List<CSSResourceTO> getCss() {
		return css;
	}
	public void setCss(List<CSSResourceTO> css) {
		this.css = css;
	}
	public List<HTMLResourceTO> getHtml() {
		return html;
	}
	public void setHtml(List<HTMLResourceTO> html) {
		this.html = html;
	}
    
    

}
