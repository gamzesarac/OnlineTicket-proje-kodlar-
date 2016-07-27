package com.mybiletix.entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2016-07-24T12:55:41.030+0300")
@StaticMetamodel(Ticket.class)
public class Ticket_ {
	public static volatile SingularAttribute<Ticket, Integer> id;
	public static volatile SingularAttribute<Ticket, Date> puchaseDate;
	public static volatile SingularAttribute<Ticket, String> statu;
	public static volatile SingularAttribute<Ticket, Category> category;
	public static volatile SingularAttribute<Ticket, Event> event;
	public static volatile SingularAttribute<Ticket, Seat> seat;
	public static volatile SingularAttribute<Ticket, User> user;
}
