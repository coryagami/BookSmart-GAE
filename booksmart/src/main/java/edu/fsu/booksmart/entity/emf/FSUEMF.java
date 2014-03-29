package edu.fsu.booksmart.entity.emf;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import edu.fsu.booksmart.entity.FSUClass;
import edu.fsu.booksmart.entity.Professor;

public class FSUEMF extends EMF {
	
	public static FSUClass getClass(String semester, String classNumber) {
		FSUClass fsuClass = null;
		EntityManager manager = get().createEntityManager();
		manager.getTransaction().begin();
		TypedQuery<FSUClass> query = manager.createQuery("SELECT F FROM FSUClass F WHERE F.semester = :semester AND F.classNumber = :classNumber", FSUClass.class);
		query.setParameter("semester", semester);
		query.setParameter("classNumber", classNumber);
		List<FSUClass> classes = query.getResultList();
		if(classes.size() > 0) {
			fsuClass = classes.get(0);
		}
		manager.getTransaction().commit();
		manager.close();
		return fsuClass;
	}
	
	public static Professor getProfessor(String firstName, String middleName, String lastName) {
		Professor professor =  null;
		EntityManager manager = get().createEntityManager();
		manager.getTransaction().begin();
		TypedQuery<Professor> query = manager.createQuery("SELECT P FROM Professor P WHERE P.firstName = :firstName AND P.middleName = :middleName AND P.lastNAme = :lastName", Professor.class);
		query.setParameter("firstName", firstName);
		query.setParameter("middleName", middleName);
		query.setParameter("lastName", lastName);
		List<Professor> profs = query.getResultList();
		if(profs.size() > 0) {
			professor = profs.get(0);
		}
		manager.getTransaction().commit();
		manager.close();
		return professor;
	}

}
