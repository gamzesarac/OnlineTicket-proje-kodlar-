package com.mybiletix.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the seat database table.
 * 
 */
@Entity
@NamedQuery(name="Seat.findAll", query="SELECT s FROM Seat s")
public class Seat implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(updatable=false)
	private Integer id;

	private Integer number;

	//bi-directional many-to-one association to Category
	@ManyToOne
	private Category category;

	public Seat() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getNumber() {
		return this.number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public Category getCategory() {
		return this.category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

}