package mx.ipn.escom.ema.model.entities;

import javax.persistence.metamodel.*;

@StaticMetamodel(Attributes.class)
public class Attributes_
{
    public static volatile SingularAttribute<Attributes, com.google.appengine.api.datastore.Key> id;
    public static volatile SingularAttribute<Attributes, java.lang.String> name;
    public static volatile SingularAttribute<Attributes, java.lang.String> description;
    public static volatile ListAttribute<Attributes, com.google.appengine.api.datastore.Key> listTags;
}
