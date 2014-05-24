package mx.ipn.escom.ema.model.entities;

import javax.persistence.metamodel.*;

@StaticMetamodel(SharedProjects.class)
public class SharedProjects_
{
    public static volatile SingularAttribute<SharedProjects, Long> serialVersionUID;
    public static volatile SingularAttribute<SharedProjects, com.google.appengine.api.datastore.Key> id;
    public static volatile SingularAttribute<SharedProjects, java.lang.Long> project;
    public static volatile ListAttribute<SharedProjects, com.google.appengine.api.datastore.Key> userReceiver;
    public static volatile SingularAttribute<SharedProjects, com.google.appengine.api.datastore.Key> userSharingProject;
}
