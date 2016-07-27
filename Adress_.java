package com.mybiletix.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2016-07-20T10:48:08.608+0300")
@StaticMetamodel(Adress.class)
public class Adress_ {
	public static volatile SingularAttribute<Adress, Integer> id;
	public static volatile SingularAttribute<Adress, String> adress;
	public static volatile SingularAttribute<Adress, String> country;
	public static volatile SingularAttribute<Adress, String> name;
	public static volatile SingularAttribute<Adress, Integer> postCode;
	public static volatile SingularAttribute<Adress, User> user;
	public static volatile SingularAttribute<Adress, City> city;
}
