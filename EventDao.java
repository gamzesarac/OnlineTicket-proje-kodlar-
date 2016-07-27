package com.mybiletix.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.mybiletix.entity.Event;
import com.mybiletix.entity.Event_;
import com.mybiletix.entity.Place;



public class EventDao {

	private EntityManager em;
	
	public EventDao(EntityManager em) {
		this.em = em;
	}
	
	public Event find(Integer id) {
		return em.find(Event.class, id);
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

	@SuppressWarnings("unchecked")
	public List<Event> findAllEvents() {
		Query query = em.createNativeQuery("select * from event",Event.class);
		return query.getResultList();
	}
	/**
	 * Finds events by getting event type and region
	 * In main screen searching is done by using eventTypes and regions
	 * */
	@SuppressWarnings("unchecked")
	public List<Event> findEvents(String eventType, Integer regionId) {
		String selectSql = "select * from event ";
				
		if ((eventType != null && !eventType.equals("")) && (regionId != null)) {
			selectSql += "join place on event.place_id = place.id "
					+ "join city on place.city_id = city.id "
					+ "join region on city.region_id = region.id "
					+ "where event_type='" + eventType + "' and region.id = " + regionId + " order by event_date desc";
		} else if (eventType != null && !eventType.equals("")) {
			selectSql += " where event_type='" + eventType + "' order by event_date desc";
		} else if (regionId != null) {
			selectSql += "join place on event.place_id = place.id "
					+ "join city on place.city_id = city.id "
					+ "join region on city.region_id = region.id "
					+ "where region.id=" + regionId + " order by event_date desc";
		} else {
			selectSql += " order by event_date desc";
		}

		Query query = em.createNativeQuery(selectSql, Event.class);
		
		return query.getResultList();
	}


	public void persist(Event event) {
		em.getTransaction().begin();
		em.persist(event);
		em.getTransaction().commit();
	}

	public void delete(Event event) {
		em.getTransaction().begin();
		em.remove(em.merge(event));
		em.getTransaction().commit();
	}

	public void update(Event event) {
		em.getTransaction().begin();
		em.merge(event);
		em.getTransaction().commit();
	}
/**
 * Finds all events from DB
 * */
	@SuppressWarnings("unchecked")
	public List<Event> findEvents() {
		Query query = em.createNativeQuery("select * from event" , Event.class);
		return query.getResultList();
		
	}
/**
 * In same date and in same place there is not any event more than 1
 * This controlled is provided.*/
	public boolean isPlaceAvailable(Place place, Date eventDate, Integer currentInstantId) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Event> query = cb.createQuery(Event.class);

		Root<Event> root = query.from(Event.class);
		
		Predicate restriction =  cb.and(cb.equal(root.get(Event_.place), place), cb.equal(root.get(Event_.eventDate), eventDate));
		
		if (currentInstantId != null) {
			restriction = cb.and(restriction, cb.notEqual(root.get(Event_.id), currentInstantId));
		}
		query.where(restriction);
		
		try {
			Event event = em.createQuery(query).getSingleResult();
			return event == null ? true : false;
		} catch (NoResultException e) {
			return true;
		}
	}
/**Finds events by using eventId*/
	public Event findEventById(Integer eventId) {
		return em.find(Event.class, eventId);
	}

}
