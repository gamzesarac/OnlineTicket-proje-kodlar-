package com.mybiletix.entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2016-07-12T10:20:36.839+0300")
@StaticMetamodel(User.class)
public class User_ {
	public static volatile SingularAttribute<User, Integer> id;
	public static volatile SingularAttribute<User, Date> birthDate;
	public static volatile SingularAttribute<User, String> email;
	public static volatile SingularAttribute<User, String> name;
	public static volatile SingularAttribute<User, String> password;
	public static volatile SingularAttribute<User, String> surname;
	public static volatile SingularAttribute<User, Integer> type;
	public static volatile SingularAttribute<User, String> userName;
	public static volatile ListAttribute<User, Adress> adresses;
	public static volatile ListAttribute<User, Place> places;
}
