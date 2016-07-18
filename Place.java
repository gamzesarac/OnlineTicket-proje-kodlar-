package com.mybiletix.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the place database table.
 * 
 */
@Entity
@NamedQuery(name="Place.findAll", query="SELECT p FROM Place p")
public class Place implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	private String adress;

	@Column(name="geo_cordinate")
	private String geoCordinate;

	private String name;

	@Column(name="post_code")
	private Integer postCode;

	//bi-directional many-to-one association to Event
	@OneToMany(mappedBy="place")
	private List<Event> events;

	//bi-directional many-to-one association to User
	@ManyToOne
	private User orginizer;

	//bi-directional many-to-one association to City
	@ManyToOne
	private City city;

	public Place() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAdress() {
		return this.adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public String getGeoCordinate() {
		return this.geoCordinate;
	}

	public void setGeoCordinate(String geoCordinate) {
		this.geoCordinate = geoCordinate;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getPostCode() {
		return this.postCode;
	}

	public void setPostCode(Integer postCode) {
		this.postCode = postCode;
	}

	public List<Event> getEvents() {
		return this.events;
	}

	public void setEvents(List<Event> events) {
		this.events = events;
	}

	public Event addEvent(Event event) {
		getEvents().add(event);
		event.setPlace(this);

		return event;
	}

	public Event removeEvent(Event event) {
		getEvents().remove(event);
		event.setPlace(null);

		return event;
	}

	public User getOrginizer() {
		return this.orginizer;
	}

	public void setOrginizer(User orginizer) {
		this.orginizer = orginizer;
	}

	public City getCity() {
		return this.city;
	}

	public void setCity(City city) {
		this.city = city;
	}

}