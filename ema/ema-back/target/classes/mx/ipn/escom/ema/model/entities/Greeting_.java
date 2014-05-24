package mx.ipn.escom.ema.model.entities;

import javax.persistence.metamodel.*;

@StaticMetamodel(Greeting.class)
public class Greeting_
{
    public static volatile SingularAttribute<Greeting, java.lang.Long> id;
    public static volatile SingularAttribute<Greeting, java.lang.String> guestbookName;
    public static volatile SingularAttribute<Greeting, java.util.Date> date;
    public static volatile SingularAttribute<Greeting, java.lang.String> content;
}
