package edu.fsu.booksmart.entity.emf;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import edu.fsu.booksmart.entity.User;
import edu.fsu.booksmart.entity.UserPost;

public class UserPostEMF extends EMF {
	
	public static List<UserPost> getUserPostsByUser(User user) {
		List<UserPost> posts = new ArrayList<UserPost>();
		return posts;
	}
	
	//We use a set in order to remove duplicates
	public static Set<UserPost> getUserPostsByType(String type, String term) {
		switch(type) {
		case "isbn":
			return new HashSet<UserPost>(getUserPostsByISBN(term));
		case "title":
			return new HashSet<UserPost>(getUserPostsByTitle(term));
		case "professor":
			return new HashSet<UserPost>(getUserPostsByProfessor(term));
		case "class":
			return new HashSet<UserPost>(getUserPostsByClass(term));
		default:
			Set<UserPost> set = new HashSet<UserPost>();
			set.addAll(getUserPostsByISBN(term));
			set.addAll(getUserPostsByTitle(term));
			set.addAll(getUserPostsByProfessor(term));
			set.addAll(getUserPostsByClass(term));
			return set;
		}
	}
	
	private static List<UserPost> getUserPostsByISBN(String isbn) {
		List<UserPost> posts = new ArrayList<UserPost>();
		return posts;
	}
	
	private static List<UserPost> getUserPostsByTitle(String title) {
		List<UserPost> posts = new ArrayList<UserPost>();
		return posts;
	}
	
	private static List<UserPost> getUserPostsByProfessor(String professor) {
		List<UserPost> posts = new ArrayList<UserPost>();
		return posts;
	}

	private static List<UserPost> getUserPostsByClass(String clazz) {
		List<UserPost> posts = new ArrayList<UserPost>();
		return posts;
	}

}
