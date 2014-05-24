package mx.ipn.escom.ema.to;

import java.io.Serializable;
import java.util.List;

import com.google.appengine.api.datastore.Key;

public class TagsTO implements Serializable {
    

    private String name;
    private String description;
    private List<AttributesTO> listAttributes;
    

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
	public List<AttributesTO> getListAttributes() {
		return listAttributes;
	}
	public void setListAttributes(List<AttributesTO> listAttributes) {
		this.listAttributes = listAttributes;
	}

    

}
