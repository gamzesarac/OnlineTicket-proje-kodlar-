package com.mybiletix.dao;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;







import com.mybiletix.entity.Event;
import com.mybiletix.entity.Ticket;
import com.mybiletix.entity.User;


public class TicketDao {
private EntityManager em;
	
	public TicketDao(EntityManager em) {
		this.em = em;
	}
	

	public void evictAll() {
		em.getEntityManagerFactory().getCache().evictAll();
	}
	
	public void evict(Event entity) {
		em.getEntityManagerFactory().getCache().evict(Event.class, entity);
	}
	
	public void detach(Object entity) {
		em.detach(entity);
	}

	public void refresh(Object entity) {
		em.refresh(entity);
	}
/**
 * Finds all tickets from DB*/
	@SuppressWarnings("unchecked")
	public List<Ticket> findAllTickets() {
		Query query = em.createNativeQuery("select * from ticket",Ticket.class);
		return query.getResultList();
	}
	


	public void persist(Ticket ticket) {
		em.getTransaction().begin();
		em.persist(ticket);
		em.getTransaction().commit();
	}

	public void delete(Ticket ticket) {
		em.getTransaction().begin();
		em.remove(em.merge(ticket));
		em.getTransaction().commit();
	}

	public void update(Ticket ticket) {
		em.getTransaction().begin();
		em.merge(ticket);
		em.getTransaction().commit();
	}
/**
 * Finds ticket by using userId*/
	@SuppressWarnings("unchecked")
	public List<Ticket> findTicketsByUser(User user) {
		Query query = em.createNativeQuery("select * from ticket where user_id=" + user.getId(), Ticket.class);
		return query.getResultList();
	}
/**
 * Finds tickets by using ticket statu from DB
 * */
	@SuppressWarnings("unchecked")
	public List<Ticket> findTicketsByStatu(String statu) {
		Query query = em.createNativeQuery("select * from ticket where statu='" + statu + "'", Ticket.class);
		return query.getResultList();
	}
	
}
