package com.mybiletix.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerSingleton {
	
	private static EntityManager instance = null;
    private EntityManagerSingleton() { }

    public static synchronized EntityManager getInstance() {
        if (instance == null) {
        	EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "MyBiletix" );
        	instance = emfactory.createEntityManager( );
        }

        return instance;
    }

}
