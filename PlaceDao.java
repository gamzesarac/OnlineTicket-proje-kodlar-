package com.mybiletix.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.mybiletix.entity.Place;
import com.mybiletix.entity.User;


public class PlaceDao {
private EntityManager em;
	
	public PlaceDao(EntityManager em) {
		this.em = em;
	}
/**
 * Finds all places from DB*/
	@SuppressWarnings("unchecked")
	public List<Place> findAll() {
		Query query = em.createNativeQuery("select * from place", Place.class);
		return query.getResultList();
	}
/**
 * Entity Manager's method
 * */
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

	@SuppressWarnings("unchecked")
	public List<Place> findPlacesByOrginizer(User user) {
		Query query = em.createNativeQuery("select * from place where orginizer_id=" + user.getId(), Place.class);
		return query.getResultList();
	}

	
}



