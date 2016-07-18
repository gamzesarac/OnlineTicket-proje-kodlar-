package com.mybiletix.entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2016-07-18T14:46:58.023+0300")
@StaticMetamodel(Event.class)
public class Event_ {
	public static volatile SingularAttribute<Event, Integer> id;
	public static volatile SingularAttribute<Event, Integer> capacity;
	public static volatile SingularAttribute<Event, String> detail;
	public static volatile SingularAttribute<Event, Date> eventDate;
	public static volatile SingularAttribute<Event, String> name;
	public static volatile ListAttribute<Event, Category> categories;
	public static volatile SingularAttribute<Event, Place> place;
	public static volatile SingularAttribute<Event, String> eventType;
}
