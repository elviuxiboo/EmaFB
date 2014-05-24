package mx.ipn.escom.ema.model.entities;

import javax.persistence.metamodel.*;

@StaticMetamodel(Tags.class)
public class Tags_
{
    public static volatile SingularAttribute<Tags, Long> serialVersionUID;
    public static volatile SingularAttribute<Tags, java.lang.Long> id;
    public static volatile SingularAttribute<Tags, java.lang.String> name;
    public static volatile SingularAttribute<Tags, java.lang.String> description;
    public static volatile ListAttribute<Tags, com.google.appengine.api.datastore.Key> listAttributes;
}
