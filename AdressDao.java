package com.mybiletix.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.mybiletix.entity.Adress;
import com.mybiletix.entity.User;


public class AdressDao {
private EntityManager em;
	
	public AdressDao(EntityManager em) {
		this.em = em;
	}
/**Finds all addresses from DB*/
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
/**
 * Finds addresses by using customerId*/
	@SuppressWarnings("unchecked")
	public List<Adress> findAddressesByUser(User user) {
		Query query = em.createNativeQuery("select * from adress where customer_id=" + user.getId(),Adress.class);
		return query.getResultList();
	}

}
