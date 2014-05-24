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

public class AutocompleteCSSBean implements Serializable {
    
    /**
     * 
     */
    private static final long serialVersionUID = -3895754221023026470L;
    String etiqueta = "body{" + "\n" + "}";
    List<String> results = new ArrayList<String>();
    TagsService tagService = new TagsServiceImpl();
    
    public List<String> completeArea(String query) {  
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
