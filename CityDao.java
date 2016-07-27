package com.mybiletix.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.mybiletix.entity.City;

public class CityDao {

private EntityManager em;
	
	public CityDao(EntityManager em) {
		this.em = em;
	}
	
/**
 * Finds cities from DB*/
	public List<City> findAll() {
		TypedQuery<City> query = em.createNamedQuery("City.findAll", City.class);
		return query.getResultList();
	}
}
