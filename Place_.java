package com.mybiletix.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2016-07-12T10:20:36.811+0300")
@StaticMetamodel(Place.class)
public class Place_ {
	public static volatile SingularAttribute<Place, Integer> id;
	public static volatile SingularAttribute<Place, String> adress;
	public static volatile SingularAttribute<Place, String> geoCordinate;
	public static volatile SingularAttribute<Place, String> name;
	public static volatile SingularAttribute<Place, Integer> postCode;
	public static volatile ListAttribute<Place, Event> events;
	public static volatile SingularAttribute<Place, User> orginizer;
	public static volatile SingularAttribute<Place, City> city;
}
