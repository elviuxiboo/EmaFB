package mx.ipn.escom.ema.services;

import java.util.List;

import mx.ipn.escom.ema.to.ProjectsTO;
import mx.ipn.escom.ema.to.UsersTO;

public interface ProjectsService {
	
	/*
	 * Agregar proyecto*/
	  public void addProject(ProjectsTO projectTO, UsersTO userTO);
	 
	
	/*
	 * Eliminar proyecto*/
	  public void deleteProject(ProjectsTO projectTO, UsersTO userTO);
	 
	
	/*
	 * Actualizar proyecto*/
	  public void updateProject(ProjectsTO projectTO, UsersTO userTO, String newName);
	 
	
	/*
	 * Mostrar todos los proyectos del usuario*/
	 public List<ProjectsTO> showProjects(UsersTO userTO);
	 
}
