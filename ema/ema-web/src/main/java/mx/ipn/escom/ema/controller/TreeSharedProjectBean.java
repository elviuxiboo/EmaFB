package mx.ipn.escom.ema.controller;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.event.NodeCollapseEvent;
import org.primefaces.event.NodeExpandEvent;
import org.primefaces.event.NodeSelectEvent;
import org.primefaces.model.TreeNode;

public class TreeSharedProjectBean implements Serializable {
    
    private TreeNode root;
    private TreeNode selectedNode;

    /**
     * Default constructor
     */
    public TreeSharedProjectBean() {
        root = new TreeNodeImplement("Root", null);
        TreeNode node0 = new TreeNodeImplement("Segment 0", root);
        TreeNode node1 = new TreeNodeImplement("Segment 1", root);
        TreeNode node2 = new TreeNodeImplement("Segment 2", root);
        TreeNode node00 = new TreeNodeImplement("Segment 0.0", node0);
        TreeNode node01 = new TreeNodeImplement("Segment 0.1", node0);
        TreeNode node10 = new TreeNodeImplement("Segment 1.0", node1);
        TreeNode node11 = new TreeNodeImplement("Segment 1.1", node1);
        TreeNode node000 = new TreeNodeImplement("Segment 0.0.0", node00);
        TreeNode node001 = new TreeNodeImplement("Segment 0.0.1", node00);
        TreeNode node010 = new TreeNodeImplement("Segment 0.1.0", node01);
        TreeNode node100 = new TreeNodeImplement("Segment 1.0.0", node10);

    }

    /**
     * This method returns the tree model based on the root node.
     *
     * @return root node.
     */
    public TreeNode getModel() {
        return root;
    }

    /**
     * Gets the selected node in the tree.
     *
     * @return selected node in tree.
     */
    public TreeNode getSelectedNode() {
        return selectedNode;
    }

    /**
     * Sets the selected node in the tree.
     *
     * @param selectedNode node to be set as selected.
     */
    public void setSelectedNode(TreeNode selectedNode) {
        this.selectedNode = selectedNode;
    }

    /**
     * {@inheritDoc }
     *
     * Adds a {@link javax.faces.application.FacesMessage} with event data to
     * the {@link javax.faces.context.FacesContext}.
     */
    public void onNodeSelect(NodeSelectEvent event) {
        System.out.println("NodeSelectEvent Fired");
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Selected", event.getTreeNode().getData().toString());
        FacesContext.getCurrentInstance().addMessage(event.getComponent().getId(), msg);
    }

    /**
     * {@inheritDoc}
     *
     * Adds a {@link javax.faces.application.FacesMessage} with event data to
     * the {@link javax.faces.context.FacesContext}.
     */
    public void onNodeExpand(NodeExpandEvent event) {

        System.out.println("NodeExpandEvent Fired");
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Expanded", event.getTreeNode().getData().toString());
        FacesContext.getCurrentInstance().addMessage(event.getComponent().getId(), msg);
    }

    /**
     * {@inheritDoc}
     *
     * Adds a {@link javax.faces.application.FacesMessage} with event data to
     * the {@link javax.faces.context.FacesContext}.
     * @param event
     */
    public void onNodeCollapse(NodeCollapseEvent event) {
        System.out.println("NodeCollapseEvent Fired");
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Collapsed", event.getTreeNode().getData().toString());
        FacesContext.getCurrentInstance().addMessage(event.getComponent().getId(), msg);
    }

}
