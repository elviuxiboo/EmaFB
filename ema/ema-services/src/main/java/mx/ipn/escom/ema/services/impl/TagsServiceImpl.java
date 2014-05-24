package mx.ipn.escom.ema.services.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import mx.ipn.escom.ema.model.entities.Attributes;
import mx.ipn.escom.ema.model.entities.Tags;
import mx.ipn.escom.ema.model.tags.DAO.TagsDAO;
import mx.ipn.escom.ema.model.tags.DAO.impl.TagsDAOimpl;
import mx.ipn.escom.ema.services.TagsService;
import mx.ipn.escom.ema.to.AttributesTO;
import mx.ipn.escom.ema.to.TagsTO;

public class TagsServiceImpl implements TagsService, Serializable{

	public List<TagsTO> getAllTags() {
	/*	List<Tags> listTags = new ArrayList<Tags>();
		List<TagsTO> listTagsTO = new ArrayList<TagsTO>();
		TagsDAO tagsDAO = new TagsDAOimpl();
		TagsTO tagTO = new TagsTO();
		listTags = tagsDAO.getTags();
		for(int i=0; i<listTags.size(); i++){
			Tags tag = listTags.get(i);
			tagTO.setName(tag.getName());
			tagTO.setDescription(tag.getDescription());
			listTagsTO.add(tagTO);
		}*/
		TagsDAO tagsDAO = new TagsDAOimpl();
		List<TagsTO> listTags = new ArrayList<TagsTO>();
		for(Tags tag : tagsDAO.getTags()){
			TagsTO tagTO = new TagsTO();
			tagTO.setName(tag.getName());
			listTags.add(tagTO);
		}
		return listTags;
	}

	public List<AttributesTO> getAttributesofTag(TagsTO tagTO) {
		List<Attributes> listAttributes = new 	ArrayList<Attributes>();
		AttributesTO attributeTOresult = new AttributesTO();
		List<AttributesTO> listAttributesTO = new ArrayList<AttributesTO>();
		Tags tag = new Tags();
		tag.setName(tagTO.getName());
		TagsDAO tagsDAO = new TagsDAOimpl();
		listAttributes = tagsDAO.getAttributes(tagTO.getName());
		for(int i=0; i<listAttributes.size(); i++){
			Attributes attribute = listAttributes.get(i);
			attributeTOresult.setName(attribute.getName());
			attributeTOresult.setDescription(attribute.getDescription());
			listAttributesTO.add(attributeTOresult);
		}
		return listAttributesTO;
	}

}
