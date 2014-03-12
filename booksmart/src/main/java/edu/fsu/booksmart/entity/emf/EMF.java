package edu.fsu.booksmart.entity.emf;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.google.appengine.api.utils.SystemProperty;

public class EMF {
	
	private static EntityManagerFactory emf;

	public static EntityManagerFactory get() {
		if(emf == null || !emf.isOpen()) {
			if (SystemProperty.environment.value() == SystemProperty.Environment.Value.Production) {
		    	emf = Persistence.createEntityManagerFactory("booksmart-jpa");
		    } else {
		    	emf = Persistence.createEntityManagerFactory("booksmart-jpa-dev");
		    }
		}
		return emf;
	}
	
	public static void persist(Object object) {
		EntityManager manager = get().createEntityManager();
		manager.getTransaction().begin();
		manager.persist(manager.merge(object));
		manager.getTransaction().commit();
		manager.close();
	}
	
	public static void remove(Object object) {
		EntityManager manager = get().createEntityManager();
		manager.getTransaction().begin();
		manager.remove(object);
		manager.getTransaction().commit();
		manager.close();
	}

}
