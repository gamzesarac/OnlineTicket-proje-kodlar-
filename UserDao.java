package com.mybiletix.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.mybiletix.entity.User;
import com.mybiletix.entity.User_;
import com.mybiletix.enumaration.UserType;

public class UserDao {
private EntityManager em;
	
	public UserDao(EntityManager em) {
		this.em = em;
	}

	@SuppressWarnings("unchecked")
	public List<User> findAllUsers() {
		Query query = em.createNativeQuery("select * from user", User.class);
		return query.getResultList();
	}

	public void persist(User user) {
		em.getTransaction().begin();
		em.persist(user);
		em.getTransaction().commit();
	}

	public void delete(User user) {
		em.getTransaction().begin();
		em.remove(em.merge(user));
		em.getTransaction().commit();
	}

	public void update(User user) {
		em.getTransaction().begin();
		em.merge(user);
		em.getTransaction().commit();
	}

	@SuppressWarnings("unchecked")
	public List<User> findOrginizers() {
		Query query = em.createNativeQuery("select * from user where type = " + UserType.ORGINIZER.getValue(), User.class);
		return query.getResultList();
	}
	
	
	@SuppressWarnings("unchecked")
	public List<User> findCustomers() {
		Query query = em.createNativeQuery("select * from user where type = " + UserType.CUSTOMER.getValue(), User.class);
		return query.getResultList();
	}

	public User findUserByUserNameAndPassword(String userName, String password) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<User> query = cb.createQuery(User.class);

		Root<User> root = query.from(User.class);
		
		Predicate restriction =  cb.and(cb.equal(root.get(User_.userName), userName), cb.equal(root.get(User_.password), password));
		
		query.where(restriction);
		
		try {
			return em.createQuery(query).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}




}
