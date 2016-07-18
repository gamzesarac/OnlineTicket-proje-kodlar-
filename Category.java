package com.mybiletix.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the category database table.
 * 
 */
@Entity
@NamedQuery(name="Category.findAll", query="SELECT c FROM Category c")
public class Category implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	private String name;

	private double price;

	//bi-directional many-to-one association to Event
	@ManyToOne
	private Event event;

	//bi-directional many-to-one association to Seat
	@OneToMany(mappedBy="category")
	private List<Seat> seats;

	public Category() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return this.price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Event getEvent() {
		return this.event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public List<Seat> getSeats() {
		return this.seats;
	}

	public void setSeats(List<Seat> seats) {
		this.seats = seats;
	}

	public Seat addSeat(Seat seat) {
		getSeats().add(seat);
		seat.setCategory(this);

		return seat;
	}

	public Seat removeSeat(Seat seat) {
		getSeats().remove(seat);
		seat.setCategory(null);

		return seat;
	}

}