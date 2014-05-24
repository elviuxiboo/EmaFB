package mx.ipn.escom.ema.controller;



import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import mx.ipn.escom.ema.services.TagsService;
import mx.ipn.escom.ema.services.impl.TagsServiceImpl;
import mx.ipn.escom.ema.to.TagsTO;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@ManagedBean
@SessionScoped

public class AutocompleteBean implements  Serializable {
    
    /**
     * 
     */
    private static final long serialVersionUID = -550623142168455264L;
    
    String etiqueta = "<!DOCTYPE html>"+ "\n" + "<html>" + "\n" + "<head>"+ "\n" + "<title>Hola Mundo</title>" + "\n"+ "</head>" + "\n" +"<body>" + "\n" + "<h1>Hola Mundo</h1>" +"\n" + "</body>" + "\n" + "</html>";
    List<String> results = new ArrayList<String>();
    TagsService tagService = new TagsServiceImpl();
    
    public List<String> completeArea(String query) {  
      /*  if(query.equals("PrimeFaces")) {
            results.add("PrimeFaces Rocks!!!");
            results.add("PrimeFaces has 100+ components.");
            results.add("PrimeFaces is lightweight.");
            results.add("PrimeFaces is easy to use.");
            results.add("PrimeFaces is developed with passion!");
        }
        else {
            for(int i = 0; i < 10; i++) {
                results.add(query + i);
            }
        }
       //  System.out.println();
        return results;*/
    	
    	List<TagsTO> listTags = tagService.getAllTags();
    	for(TagsTO tagsTO: listTags){
    		if(tagsTO.getName().startsWith(query)){
    			results.add(tagsTO.getName());
    		}
    	}
    	return results;
    }
    
   public String listAutoComplete(List<String> list,  String query){
        String result =null;
        list = completeArea(query);
        for(int i=0; i<list.size(); i++){
            result = list.get(i);
        }
        return result;
    }
    
    public String getEtiqueta() {
        return etiqueta;
    }

    public void setEtiqueta(String etiqueta){
    	this.etiqueta = etiqueta;
    }

    public List<String> getResults() {
        return results;
    }

    public void setResults(List<String> results) {
        this.results = results;
    }
    


}
