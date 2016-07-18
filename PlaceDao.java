package com.mybiletix.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.mybiletix.entity.Place;


public class PlaceDao {
private EntityManager em;
	
	public PlaceDao(EntityManager em) {
		this.em = em;
	}

	@SuppressWarnings("unchecked")
	public List<Place> findAll() {
		Query query = em.createNativeQuery("select * from place", Place.class);
		return query.getResultList();
	}

	public void persist(Place place) {
		em.getTransaction().begin();
		em.persist(place);
		em.getTransaction().commit();
	}

	public void delete(Place place) {
		em.getTransaction().begin();
		em.remove(em.merge(place));
		em.getTransaction().commit();
	}

	public void update(Place place) {
		em.getTransaction().begin();
		em.merge(place);
		em.getTransaction().commit();
	}

	
}



