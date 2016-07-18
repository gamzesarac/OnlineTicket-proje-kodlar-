package com.mybiletix.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2016-07-12T10:20:36.799+0300")
@StaticMetamodel(City.class)
public class City_ {
	public static volatile SingularAttribute<City, Integer> id;
	public static volatile SingularAttribute<City, String> name;
	public static volatile ListAttribute<City, Adress> adresses;
	public static volatile SingularAttribute<City, Region> region;
	public static volatile ListAttribute<City, Place> places;
}
