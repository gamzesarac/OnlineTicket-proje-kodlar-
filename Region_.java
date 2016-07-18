package com.mybiletix.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2016-07-12T10:20:36.817+0300")
@StaticMetamodel(Region.class)
public class Region_ {
	public static volatile SingularAttribute<Region, Integer> id;
	public static volatile SingularAttribute<Region, String> name;
	public static volatile ListAttribute<Region, City> cities;
}
