package com.mybiletix.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2016-07-12T10:20:36.794+0300")
@StaticMetamodel(Category.class)
public class Category_ {
	public static volatile SingularAttribute<Category, Integer> id;
	public static volatile SingularAttribute<Category, String> name;
	public static volatile SingularAttribute<Category, Double> price;
	public static volatile SingularAttribute<Category, Event> event;
	public static volatile ListAttribute<Category, Seat> seats;
}
