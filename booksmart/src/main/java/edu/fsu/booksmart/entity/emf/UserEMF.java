package edu.fsu.booksmart.entity.emf;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import edu.fsu.booksmart.entity.User;
import edu.fsu.booksmart.entity.UserDevice;

public class UserEMF extends EMF {
	
	public static User getUserByDevice(UserDevice device) {
		return getUserByDeviceId(device.getDeviceId());
	}
	
	public static User getUserByDeviceId(String deviceId) {
		User user = null;
		EntityManager manager = get().createEntityManager();
		manager.getTransaction().begin();
		TypedQuery<User> query = manager.createQuery("SELECT U FROM User U left join U.device D WHERE D.deviceId = :deviceId", User.class);
		query.setParameter("deviceId", deviceId);
		List<User> users = query.getResultList();
		if(users.size() > 0) {
			user = users.get(0);
		}
		manager.getTransaction().commit();
		manager.close();
		return user;
	}

}
