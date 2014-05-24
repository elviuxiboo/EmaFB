package mx.ipn.escom.ema.services;

import java.util.List;

import mx.ipn.escom.ema.to.ProjectsTO;
import mx.ipn.escom.ema.to.UsersTO;

public interface ShareProjectsService {
	
	/*Compartir Proyecto*/
	  public void shareProject(UsersTO userTO, ProjectsTO projectTO, UsersTO userReceiver);
	 
	
	/*Mostrar proyectos compartidos*/
	  public List<ProjectsTO> showSharedProjects(UsersTO userTO);
	  
	  /*Mostrar usuario del proyecto*/
	//  public UsersTO getUserOfProject(ProjectsTO projectTO);
	 
}
