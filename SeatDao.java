package com.mybiletix.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;


import com.mybiletix.entity.Seat;

public class SeatDao {
	private EntityManager em;
	
	public SeatDao(EntityManager em) {
		this.em = em;
	}

	@SuppressWarnings("unchecked")
	public List<Seat> findAllSeats() {
		Query query = em.createNativeQuery("select * from seat",Seat.class);
		return query.getResultList();
	}

	public void persist(Seat seat) {
		em.getTransaction().begin();
		em.persist(seat);
		em.getTransaction().commit();
	}

	public void delete(Seat seat) {
		em.getTransaction().begin();
		em.remove(em.merge(seat));
		em.getTransaction().commit();
	}

	public void update(Seat seat) {
		em.getTransaction().begin();
		em.merge(seat);
		em.getTransaction().commit();
	}


}
