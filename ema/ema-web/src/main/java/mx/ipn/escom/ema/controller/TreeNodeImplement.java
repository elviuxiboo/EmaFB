package mx.ipn.escom.ema.controller;

import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;




public class TreeNodeImplement extends DefaultTreeNode {

    
    private static final long serialVersionUID = 9109383676827701707L;

    public TreeNodeImplement(TreeNodeType type, Object data, TreeNode parent) {
        super(type.getType(), data, parent);
    }

    /**
     * Constructor which sets {@code Object} data, and parent node.
     *
     * @param data {@code Object} value stored in the node.
     * @param parent parent the {@link org.primefaces.model.TreeNode} which is
     * the parent to this object, or {@code null} if this is the
     * &quot;root&quot; node.
     */
    public TreeNodeImplement(Object data, TreeNode parent) {
        super(data, parent);
    }

    /**
     * This method returns {@link com.bluelotussoftware.example.jsf.TreeNodeType#getType()}
     * depending on whether the node is a &quot;leaf&quot; node which contains
     * no children, or a &quot;node&quot; if it contains children.
     *
     * @return {@link com.bluelotussoftware.example.jsf.TreeNodeType#getType()}
     * based on whether this node has child objects.
     */
    @Override
    public String getType() {
        if (isLeaf()) {
            return TreeNodeType.LEAF.getType();
        } else {
            return TreeNodeType.NODE.getType();
        }
    }
    
}
