package mx.ipn.escom.ema.model.tags.DAO;

import java.util.List;

import com.google.appengine.api.datastore.Key;

import mx.ipn.escom.ema.model.entities.Attributes;
import mx.ipn.escom.ema.model.entities.Tags;

public interface TagsDAO {
	
	public void addTag(Tags tag);
	public Tags findTag(String nombre);
	public void addAttributeToTag(String nameTag, String nameAttribute);
	public List<Attributes> getAttributes(String name);
	public List<Tags> getTags();

}
