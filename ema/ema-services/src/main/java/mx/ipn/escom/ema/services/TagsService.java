package mx.ipn.escom.ema.services;

import java.util.List;

import mx.ipn.escom.ema.to.*;

public interface TagsService {

	public List<TagsTO> getAllTags();
	public List<AttributesTO> getAttributesofTag(TagsTO tagTO);
}
