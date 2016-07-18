package com.mybiletix.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the adress database table.
 * 
 */
@Entity
@NamedQuery(name="Adress.findAll", query="SELECT a FROM Adress a")
public class Adress implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	private String adress;

	private String country;

	private String name;

	@Column(name="post_code")
	private Integer postCode;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="customer_id")
	private User user;

	//bi-directional many-to-one association to City
	@ManyToOne
	private City city;

	public Adress() {
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

	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
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

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public City getCity() {
		return this.city;
	}

	public void setCity(City city) {
		this.city = city;
	}

}