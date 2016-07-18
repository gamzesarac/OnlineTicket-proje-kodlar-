package com.mybiletix.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.mybiletix.entity.Adress;


public class AdressDao {
private EntityManager em;
	
	public AdressDao(EntityManager em) {
		this.em = em;
	}

	@SuppressWarnings("unchecked")
	public List<Adress> findAllAdresses() {
		Query query = em.createNativeQuery("select * from adress", Adress.class);
		return query.getResultList();
	}

	public void persist(Adress address) {
		em.getTransaction().begin();
		em.persist(address);
		em.getTransaction().commit();
	}

	public void delete(Adress address) {
		em.getTransaction().begin();
		em.remove(em.merge(address));
		em.getTransaction().commit();
	}

	public void update(Adress address) {
		em.getTransaction().begin();
		em.merge(address);
		em.getTransaction().commit();
	}




}
