package com.mybiletix.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the city database table.
 * 
 */
@Entity
@NamedQuery(name="City.findAll", query="SELECT c FROM City c")
public class City implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	private String name;

	//bi-directional many-to-one association to Adress
	@OneToMany(mappedBy="city")
	private List<Adress> adresses;

	//bi-directional many-to-one association to Region
	@ManyToOne
	private Region region;

	//bi-directional many-to-one association to Place
	@OneToMany(mappedBy="city")
	private List<Place> places;

	public City() {
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

	public List<Adress> getAdresses() {
		return this.adresses;
	}

	public void setAdresses(List<Adress> adresses) {
		this.adresses = adresses;
	}

	public Adress addAdress(Adress adress) {
		getAdresses().add(adress);
		adress.setCity(this);

		return adress;
	}

	public Adress removeAdress(Adress adress) {
		getAdresses().remove(adress);
		adress.setCity(null);

		return adress;
	}

	public Region getRegion() {
		return this.region;
	}

	public void setRegion(Region region) {
		this.region = region;
	}

	public List<Place> getPlaces() {
		return this.places;
	}

	public void setPlaces(List<Place> places) {
		this.places = places;
	}

	public Place addPlace(Place place) {
		getPlaces().add(place);
		place.setCity(this);

		return place;
	}

	public Place removePlace(Place place) {
		getPlaces().remove(place);
		place.setCity(null);

		return place;
	}

}