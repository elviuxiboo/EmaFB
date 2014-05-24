package mx.ipn.escom.ema.model.entities;

import javax.persistence.metamodel.*;

@StaticMetamodel(Users.class)
public class Users_
{
    public static volatile SingularAttribute<Users, Long> serialVersionUID;
    public static volatile SingularAttribute<Users, com.google.appengine.api.datastore.Key> id;
    public static volatile SingularAttribute<Users, java.lang.String> user;
    public static volatile SingularAttribute<Users, java.lang.String> name;
    public static volatile ListAttribute<Users, java.lang.Long> projects;
    public static volatile ListAttribute<Users, com.google.appengine.api.datastore.Key> sharedProjects;
}
