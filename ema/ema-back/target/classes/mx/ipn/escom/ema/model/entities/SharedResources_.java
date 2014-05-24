package mx.ipn.escom.ema.model.entities;

import javax.persistence.metamodel.*;

@StaticMetamodel(SharedResources.class)
public class SharedResources_
{
    public static volatile SingularAttribute<SharedResources, Long> serialVersionUID;
    public static volatile SingularAttribute<SharedResources, com.google.appengine.api.datastore.Key> id;
    public static volatile SingularAttribute<SharedResources, java.lang.Long> project;
    public static volatile ListAttribute<SharedResources, com.google.appengine.api.datastore.Key> userReceiver;
    public static volatile SingularAttribute<SharedResources, com.google.appengine.api.datastore.Key> userSharingProject;
}
