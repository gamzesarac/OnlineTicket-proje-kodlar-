package com.mybiletix.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the ticket database table.
 * 
 */
@Entity
@NamedQuery(name="Ticket.findAll", query="SELECT t FROM Ticket t")
public class Ticket implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	@Temporal(TemporalType.DATE)
	@Column(name="puchase_date")
	private Date puchaseDate;
	
	private String statu;

	//uni-directional many-to-one association to Category
	@ManyToOne
	private Category category;

	//uni-directional many-to-one association to Event
	@ManyToOne
	private Event event;

	//uni-directional many-to-one association to Seat
	@ManyToOne
	private Seat seat;

	//uni-directional many-to-one association to User
	@ManyToOne
	private User user;

	public Ticket() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getPuchaseDate() {
		return this.puchaseDate;
	}

	public void setPuchaseDate(Date puchaseDate) {
		this.puchaseDate = puchaseDate;
	}

	public String getstatu() {
		return statu;
	}
	
	public void setStatu(String statu) {
		this.statu = statu;
	}
	
	public Category getCategory() {
		return this.category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Event getEvent() {
		return this.event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public Seat getSeat() {
		return this.seat;
	}

	public void setSeat(Seat seat) {
		this.seat = seat;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}