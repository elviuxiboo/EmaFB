package mx.ipn.escom.ema.controller;

public enum TreeNodeType {
    
    LEAF("leaf"), NODE("node");
  
    private String type;
    
    
    private TreeNodeType(final String type) {
        this.type = type;
    }
 
    @Override
    public String toString(){
        return type;
    }

    public String getType(){
        return type;
    }
    
}
