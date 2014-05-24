package mx.ipn.escom.ema.model.entities;

import javax.persistence.metamodel.*;

@StaticMetamodel(Projects.class)
public class Projects_
{
    public static volatile SingularAttribute<Projects, Long> serialVersionUID;
    public static volatile SingularAttribute<Projects, java.lang.Long> id;
    public static volatile SingularAttribute<Projects, java.lang.String> name;
    public static volatile SingularAttribute<Projects, com.google.appengine.api.datastore.Key> user;
    public static volatile SingularAttribute<Projects, java.util.Date> date;
    public static volatile ListAttribute<Projects, com.google.appengine.api.datastore.Key> resources;
}
