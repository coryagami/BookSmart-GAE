package edu.fsu.booksmart.entity.emf;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import edu.fsu.booksmart.entity.Book;

public class BookEMF extends EMF {
	
	public static Book getBookByISBNNumber(String isbn) {
		Book book = null;
		EntityManager manager = get().createEntityManager();
		manager.getTransaction().begin();
		TypedQuery<Book> query = manager.createQuery("SELECT B FROM Book B left join B.isbns I WHERE I.number = :isbn", Book.class);
		query.setParameter("isbn", isbn);
		List<Book> books = query.getResultList();
		if(books.size() == 1) {
			book = books.get(0);
		}
		manager.getTransaction().commit();
		manager.close();
		return book;
	}

}
