package edu.fsu.booksmart.entity.emf;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import edu.fsu.booksmart.entity.User;
import edu.fsu.booksmart.entity.UserPost;


/**
 * 
 * This class is used to interact with the database
 * using the Hibernate library. This is a class that
 * simply does queries to the database and returns the
 * results as a simple UserPost or as a set of UserPost.
 * 
 * A set is used so that the lists are unique (no duplicates)
 * 
 * @author Derek Honerlaw
 */

public class UserPostEMF extends EMF {
	
	public static Set<UserPost> getUserPostsByUser(User user) {
		Set<UserPost> posts = new HashSet<UserPost>();
		EntityManager manager = get().createEntityManager();
		manager.getTransaction().begin();
		TypedQuery<UserPost> query = manager.createQuery("SELECT U FROM UserPost U left join U.poster P left join P.device D WHERE D.deviceId = :deviceId", UserPost.class);
		query.setParameter("deviceId", user.getDevice().getDeviceId());
		posts.addAll(query.getResultList());
		manager.getTransaction().commit();
		manager.close();
		return posts;
	}
	
	public static Set<UserPost> getUserPostsByType(String type, String term) {
		switch(type) {
		case "isbn":
			return new HashSet<UserPost>(getUserPostsByISBN(term));
		case "title":
			return new HashSet<UserPost>(getUserPostsByTitle(term));
		default:
			Set<UserPost> set = new HashSet<UserPost>();
			set.addAll(getUserPostsByISBN(term));
			set.addAll(getUserPostsByTitle(term));
			return set;
		}
	}
	
	private static List<UserPost> getUserPostsByISBN(String isbn) {
		List<UserPost> posts = new ArrayList<UserPost>();
		EntityManager manager = get().createEntityManager();
		manager.getTransaction().begin();
		TypedQuery<UserPost> query = manager.createQuery("SELECT U FROM UserPost U left join U.book B left join B.isbns I WHERE I.number = :isbn", UserPost.class);
		query.setParameter("isbn", isbn);
		posts.addAll(query.getResultList());
		manager.getTransaction().commit();
		manager.close();
		return posts;
	}
	
	private static List<UserPost> getUserPostsByTitle(String title) {
		List<UserPost> posts = new ArrayList<UserPost>();
		EntityManager manager = get().createEntityManager();
		manager.getTransaction().begin();
		TypedQuery<UserPost> query = manager.createQuery("SELECT U FROM UserPost U left join U.book B WHERE (B.title LIKE '%' || :title || '%')", UserPost.class);
		query.setParameter("title", title);
		posts.addAll(query.getResultList());
		manager.getTransaction().commit();
		manager.close();
		return posts;
	}

}
