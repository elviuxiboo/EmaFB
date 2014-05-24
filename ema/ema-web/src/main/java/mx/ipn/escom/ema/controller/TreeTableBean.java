package mx.ipn.escom.ema.controller;

import mx.ipn.escom.ema.model.entities.CSSResources;
import mx.ipn.escom.ema.model.entities.SharedProjects;
import mx.ipn.escom.ema.services.CSSResourceService;
import mx.ipn.escom.ema.services.HTMLResourceService;
import mx.ipn.escom.ema.services.ShareProjectsService;
import mx.ipn.escom.ema.services.impl.CSSResourceServiceimpl;
import mx.ipn.escom.ema.services.impl.HTMLResourceServiceimpl;
import mx.ipn.escom.ema.services.impl.SharedProjectsServiceimpl;
import mx.ipn.escom.ema.to.CSSResourceTO;
import mx.ipn.escom.ema.to.HTMLResourceTO;
import mx.ipn.escom.ema.to.ProjectsTO;
import mx.ipn.escom.ema.to.SharedProjectsTO;
import mx.ipn.escom.ema.to.UsersTO;

import org.primefaces.event.NodeCollapseEvent;
import org.primefaces.event.NodeExpandEvent;
import org.primefaces.event.NodeSelectEvent;
import org.primefaces.event.NodeUnselectEvent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;


@ManagedBean
@RequestScoped

public class TreeTableBean implements Serializable {
    
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private TreeNode root;
    private TreeNode selectedItem;
    private TreeNode selectedItemForContextMenu;
    private TreeNode[] selectedItems;
    private UsersTO userTO = new UsersTO();
    private List<ProjectsTO> listProjects = new ArrayList<ProjectsTO>(); 
    private ShareProjectsService sharedProjectService = new SharedProjectsServiceimpl();
    private String noProjectsFound;
    private List<HTMLResourceTO> listHtml = new ArrayList<HTMLResourceTO>();
    private HTMLResourceService htmlService = new HTMLResourceServiceimpl();
    private CSSResourceService cssService = new CSSResourceServiceimpl();
    private List<CSSResourceTO> listCss = new ArrayList<CSSResourceTO>();
    

    public TreeTableBean() {
        root = new DefaultTreeNode("root", null);

     /*   TreeNode node1 = new DefaultTreeNode("node", new TreeTableElement("Node1", "1st Column", "2nd Column"), root);
        TreeNode node2 = new DefaultTreeNode("node", new TreeTableElement("Node2", "1st Column", "2nd Column"), root);

        TreeNode node11 = new DefaultTreeNode("leaf", new TreeTableElement("Node1.1", "1st Column", "2nd Column"), node1);
        TreeNode node12 = new DefaultTreeNode("leaf", new TreeTableElement("Node1.2", "1st Column", "2nd Column"), node1);

        TreeNode node21 = new DefaultTreeNode("node", new TreeTableElement("Node2.1", "1st Column", "2nd Column"), node2);
        TreeNode node211 = new DefaultTreeNode("leaf", new TreeTableElement("Node2.1.1", "1st Column", "2nd Column"), node21);*/
       UserService userService = UserServiceFactory.getUserService();
	    User user = userService.getCurrentUser();// Obtiene la sesion de google
	    userTO.setUser(user.getEmail());
      //  userTO.setUser("andrea@example.com");
        listProjects = sharedProjectService.showSharedProjects(userTO);
        System.out.println(listProjects);
        for(int i=0; i<listProjects.size(); i++){
        	ProjectsTO projectTO = listProjects.get(i);
        	UsersTO userResultTO = projectTO.getUser();
        	//System.out.println("usuario " +userResultTO.getUser());
        	TreeNode node1 =  new DefaultTreeNode("node", new TreeTableElement(projectTO.getName(), "", projectTO.getUser().getUser()), root);
        	listHtml = htmlService.showHTMLResources(projectTO, userResultTO);
        	for(int j=0; j<listHtml.size(); j++){
        		HTMLResourceTO html = listHtml.get(j);
        		TreeNode node11 = new DefaultTreeNode("node", new TreeTableElement(projectTO.getName(), html.getName(), userResultTO.getUser()), node1);
        				//(html.getName(), node1);
        	}	
        	listCss = cssService.showCSSResources(projectTO, userResultTO);
        	for(int k=0; k<listCss.size();k++){
        		//System.out.println(listCss.get(k));
        		CSSResourceTO css = listCss.get(k);
        		TreeNode node12 = new DefaultTreeNode("node", new TreeTableElement(projectTO.getName(), css.getName(), userResultTO.getUser()), node1);
        	}
        }
        
    }
    
    
    public TreeNode getRoot() {
        return root;
    }


    public void setRoot(TreeNode root) {
        this.root = root;
    }
    public TreeNode getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(TreeNode selectedItem) {
        this.selectedItem = selectedItem;
    }

    public TreeNode getSelectedItemForContextMenu() {
        return selectedItemForContextMenu;
    }

    public void setSelectedItemForContextMenu(TreeNode selectedItemForContextMenu) {
        this.selectedItemForContextMenu = selectedItemForContextMenu;
    }

    public TreeNode[] getSelectedItems() {
        return selectedItems;
    }

    public void setSelectedItems(TreeNode[] selectedItems) {
        this.selectedItems = selectedItems;
    }
    
    public void onNodeExpand(NodeExpandEvent event) {

        System.out.println("NodeExpandEvent Fired");
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Expanded", event.getTreeNode().getData().toString());
        FacesContext.getCurrentInstance().addMessage(event.getComponent().getId(), msg);
    }
      
      public void onNodeCollapse(NodeCollapseEvent event){
          System.out.println("colapsado?");
          FacesMessage msg= new FacesMessage(FacesMessage.SEVERITY_INFO, "CollapseEvent", event.getTreeNode().getData().toString() );
          FacesContext.getCurrentInstance().addMessage(event.getComponent().getClientId(), msg);
      }
         
     public void onNodeSelect(NodeSelectEvent event) {
         System.out.println("Seleccion");
         FacesMessage msg=new FacesMessage(FacesMessage.SEVERITY_INFO, "SelectEvent", event.getTreeNode().getData().toString());
         FacesContext.getCurrentInstance().addMessage(event.getComponent().getClientId(), msg);
     }
     
     public void onNodeUnselected(NodeUnselectEvent event){
         System.out.println("Unselected");
         FacesMessage msg=new FacesMessage(FacesMessage.SEVERITY_INFO, "Unselected", event.getTreeNode().getData().toString());
         FacesContext.getCurrentInstance().addMessage(event.getComponent().getClientId(), msg);
     }

    public void deleteNode() {
        selectedItem.getChildren().clear();
        selectedItem.getParent().getChildren().remove(selectedItem);
        selectedItem.setParent(null);

        selectedItem = null;
    }


	public String getNoProjectsFound() {
		return noProjectsFound;
	}


	public void setNoProjectsFound(String noProjectsFound) {
		this.noProjectsFound = noProjectsFound;
	}

    

}
