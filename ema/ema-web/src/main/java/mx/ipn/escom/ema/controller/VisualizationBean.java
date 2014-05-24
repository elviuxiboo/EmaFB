package mx.ipn.escom.ema.controller;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;


@ManagedBean
@RequestScoped
public class VisualizationBean implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3917935196522129011L;

	public void callVisualizationServlet(){
        String url= "/VisualizationServlet";
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            context.getExternalContext().redirect(url);;
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            context.responseComplete();
        }
    }   

}
