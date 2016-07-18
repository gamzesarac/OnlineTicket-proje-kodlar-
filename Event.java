package com.mybiletix.entity;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the event database table.
 * 
 */
@Entity
@NamedQuery(name="Event.findAll", query="SELECT e FROM Event e")
public class Event implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	private Integer capacity;

	private String detail;

	@Temporal(TemporalType.DATE)
	@Column(name="event_date")
	private Date eventDate;

	private String name;

	@Column(name="event_type")
	private String eventType;

	//bi-directional many-to-one association to Category
	@OneToMany(mappedBy="event")
	private List<Category> categories;

	//bi-directional many-to-one association to Place
	@ManyToOne
	private Place place;

	public Event() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCapacity() {
		return this.capacity;
	}

	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
	}

	public String getDetail() {
		return this.detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public Date getEventDate() {
		return this.eventDate;
	}

	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Category> getCategories() {
		return this.categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

	public Category addCategory(Category category) {
		getCategories().add(category);
		category.setEvent(this);

		return category;
	}

	public Category removeCategory(Category category) {
		getCategories().remove(category);
		category.setEvent(null);

		return category;
	}

	
	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	public Place getPlace() {
		return this.place;
	}

	public void setPlace(Place place) {
		this.place = place;
	}

}