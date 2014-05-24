package mx.ipn.escom.ema.controller;


import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped

public class CodeCSSBean implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -379870134554310461L;
    
    String etiqueta = "<!DOCTYPE html>"+ "\n" + "<html>" + "\n" + "<head>"+ "\n" + "<title>Hola Mundo</title>" + "\n"+ "<\\head>" + "\n" +"<body>" + "\n" + "<h1>Hola Mundo</h1>" +"\n" + "<\\body>" + "\n" + "<\\html>";

    public String getEtiqueta() {
        return etiqueta;
    }

    public void setEtiqueta(String etiqueta) {
        this.etiqueta = etiqueta;
    }
     

}
