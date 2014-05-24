package mx.ipn.escom.ema.services;

import java.util.List;

import mx.ipn.escom.ema.to.CSSResourceTO;
import mx.ipn.escom.ema.to.ProjectsTO;
import mx.ipn.escom.ema.to.UsersTO;

public interface CSSResourceService {

	/*Agregar css*/
	  public void addCSS(CSSResourceTO cssTO, ProjectsTO projectTO, UsersTO userTO);
	  public void addStyleSheet(CSSResourceTO cssTO, ProjectsTO projectTO, UsersTO userTO);
	 
	
	/*Eliminar css*/
	  public void deleteCSS(CSSResourceTO cssTO, ProjectsTO projectTO, UsersTO userTO);
	 
	
	/*
	 * Mostrar recursos css de proyecto*/
	 public List<CSSResourceTO> showCSSResources(ProjectsTO projectTO, UsersTO userTO);
	 
	
	/*Actualizar css*/
	  public void updateCSS(CSSResourceTO cssTO, ProjectsTO projectTO, UsersTO userTO, String newCode);
	 

	 
}
