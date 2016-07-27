package com.mybiletix.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.mybiletix.entity.Category;
import com.mybiletix.entity.Event;


public class CategoryDao {

	private EntityManager em;
	
	public CategoryDao(EntityManager em) {
		this.em = em;
	}

	@SuppressWarnings("unchecked")
	public List<Category> findAllCategories() {
		Query query = em.createNativeQuery("select * from category",Category.class);
		return query.getResultList();
	}

	public void persist(Category category) {
		em.getTransaction().begin();
		em.persist(category);
		em.getTransaction().commit();
	}

	public void delete(Category category) {
		em.getTransaction().begin();
		em.remove(em.merge(category));
		em.getTransaction().commit();
	}

	public void update(Category category) {
		em.getTransaction().begin();
		em.merge(category);
		em.getTransaction().commit();
	}
/**
 * Finds categories by using event*/
	@SuppressWarnings("unchecked")
	public List<Category> findCategoriesByEvent(Event event) {
		Query query = em.createNativeQuery("select * from category where event_id=" + event.getId(), Category.class);
		return query.getResultList();
	}


}
