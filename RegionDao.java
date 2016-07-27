package com.mybiletix.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.mybiletix.entity.Region;

public class RegionDao {

	private EntityManager em;
	
	public RegionDao(EntityManager em) {
		this.em = em;
	}
/**
 * Finds all regions from DB*/
	@SuppressWarnings("unchecked")
	public List<Region> findAllRegion() {
		Query query = em.createNativeQuery("select * from region", Region.class);
		return query.getResultList();
	}

	public void persist(Region region) {
		em.getTransaction().begin();
		em.persist(region);
		em.getTransaction().commit();
	}

	public void delete(Region region) {
		em.getTransaction().begin();
		em.remove(em.merge(region));
		em.getTransaction().commit();
	}

	public void update(Region region) {
		em.getTransaction().begin();
		em.merge(region);
		em.getTransaction().commit();
	}

}
