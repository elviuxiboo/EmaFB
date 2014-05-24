package mx.ipn.escom.ema.model.attributes.DAO;

import java.util.List;

import mx.ipn.escom.ema.model.entities.Attributes;

public interface AttributesDAO {
	
	public void addAttribute(Attributes a);
	public Attributes findAttribute(String name);
	public void addTagToAttribute(String nameTag, String nameAttribute);
	public List<Attributes> getAttributeOfTag(String nombre);
	public List<String> attributesName(String name);
}
