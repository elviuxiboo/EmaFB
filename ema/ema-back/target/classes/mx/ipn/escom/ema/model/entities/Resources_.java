package mx.ipn.escom.ema.model.entities;

import javax.persistence.metamodel.*;

@StaticMetamodel(Resources.class)
public class Resources_
{
    public static volatile SingularAttribute<Resources, Long> serialVersionUID;
    public static volatile SingularAttribute<Resources, com.google.appengine.api.datastore.Key> id;
    public static volatile SingularAttribute<Resources, com.google.appengine.api.datastore.Key> htmlrec;
    public static volatile SingularAttribute<Resources, com.google.appengine.api.datastore.Key> cssrec;
    public static volatile SingularAttribute<Resources, java.lang.Long> project;
}
