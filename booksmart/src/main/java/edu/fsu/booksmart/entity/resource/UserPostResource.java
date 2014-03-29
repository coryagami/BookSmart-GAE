package edu.fsu.booksmart.entity.resource;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import edu.fsu.booksmart.entity.Book;
import edu.fsu.booksmart.entity.BookInformation;
import edu.fsu.booksmart.entity.FSUClass;
import edu.fsu.booksmart.entity.Professor;
import edu.fsu.booksmart.entity.User;
import edu.fsu.booksmart.entity.UserPost;
import edu.fsu.booksmart.entity.emf.BookEMF;
import edu.fsu.booksmart.entity.emf.FSUEMF;
import edu.fsu.booksmart.entity.emf.UserEMF;
import edu.fsu.booksmart.entity.emf.UserPostEMF;

/**
 * 
 * UserPostResource Module
 * 
 * This class is the "interface" that the mobile application
 * will interact with when a user wants to add a new book post
 * or when they want to get a list of book posts. This resource
 * indirectly accesses the database after grabbing parameters and
 * doing checks are the passed parameters. The database is accessed
 * using hibernate and the access methods are implemented in UserPostEMF.
 * 
 * @author Derek Honerlaw
 * @created 3/9/2014
 */

@Path("/api/user_post")
@Produces("application/json") // encode all responses into application/json
public class UserPostResource {
	
	/**
	 * Retrieves a set of UserPost objects based on the device id
	 * the posts were made with
	 * 
	 * @param device_id The user device id
	 * @return Set of UserPost objects
	 */
	
	@GET
	@Path("/user")
	public Response getPostsByUser(@FormParam("device_id") String device_id) {
		User user = UserEMF.getUserByDeviceId(device_id);
		if(user != null) {
			return Response.ok(UserPostEMF.getUserPostsByUser(user)).build();
		}
		return Response.status(Status.BAD_REQUEST).entity("User not found.").build();
	}
	
	/**
	 * Retrieves a set of UserPost objects based on the term the user searched for as
	 * well as the "type" or category that search was in.
	 * 
	 * @param type The type of search
	 * @param term The user search term
	 * @return Set of UserPost objects
	 */
	
	@GET
	@Path("/search/{type}")
	public Response getPostsBySearch(@PathParam("type") String type, @FormParam("term") String term) {
		return Response.ok(UserPostEMF.getUserPostsByType(type.toLowerCase(), term.toLowerCase())).build();
	}
	
	/**
	 * Creates a new UserPost objects and submits it into the database
	 * 
	 * @param longitude The longitude of where submitted
	 * @param latitude The latitude of where submitted
	 * @param price The price they are selling the book for
	 * @param deviceId The user device id
	 * @param condition The condition of the book
	 * @param type The type of book
	 * @param semester The semester this book is used for
	 * @param classNumber The class this book is used for
	 * @param firstName First name of the professor
	 * @param middleName Middle name of the professor
	 * @param lastName Last name of the professor
	 * @param isbn The ISBN number of the book
	 * @return The created UserPost object
	 */
	
	@POST
	@Path("/post")
	public Response addNewUserPost(@QueryParam("longitude") String longitude, @QueryParam("latitude") String latitude,
			@QueryParam("price") Double price, @QueryParam("device_id") String deviceId, @QueryParam("condition") String condition,
			@QueryParam("type") String type, @QueryParam("semester") String semester, @QueryParam("class_number") String classNumber,
			@QueryParam("first_name") String firstName, @QueryParam("middle_name") String middleName, @QueryParam("last_name") String lastName,
			@QueryParam("isbn") String isbn) {
		
		UserPost post = new UserPost();
		
		post.setLongitude(longitude);
		post.setLatitude(latitude);
		post.setPrice(price);
		
		User user = UserEMF.getUserByDeviceId(deviceId);
		if(user == null) {
			return Response.status(Status.BAD_REQUEST).entity("User not found,").build();
		}
		post.setPoster(user);
		
		Book book = BookEMF.getBookByISBNNumber(isbn);
		if(book == null) {
			
			//curl the isbn database and get the information
			
			//book (Book)
			//	check if this exists
			//	if it doesn't add it
			//		title
			//		description
			//		publisher
			//		edition
			//		publishYear (Date)
			//		copyrightYear (Date)
			//		ISBN (List<ISBN>)
			//			type
			//			number
			book = new Book();
		}
		post.setBook(book);
		
		BookInformation information = new BookInformation();
		information.setCondition(condition);
		information.setType(type);
		post.setBookInformation(information);
		FSUClass clazz = FSUEMF.getClass(semester, classNumber);
		if(clazz == null) {
			clazz = new FSUClass();
			clazz.setSemester(semester);
			clazz.setClassNumber(classNumber);
		}
		Professor professor = FSUEMF.getProfessor(firstName, middleName, lastName);
		if(professor == null) {
			professor = new Professor();
			professor.setFirstName(firstName);
			professor.setMiddleName(middleName);
			professor.setLastName(lastName);
		}
		if(clazz.getProfessor() == null) {
			clazz.setProfessor(professor);
		}
		if(professor.getClasses() == null) {
			List<FSUClass> classes = new ArrayList<FSUClass>();
			classes.add(clazz);
			professor.setClasses(classes);
		}
		if(!professor.getClasses().contains(clazz)) {
			professor.getClasses().add(clazz);
		}
		post.setFsuClass(clazz);
		post.setProfessor(professor);
		Long time = new java.util.Date().getTime();
		post.setPosted(new Date(time));
		post.setExpires(new Date(time + (14 * 24 * 60 * 60) * 1000));
		
		
		return Response.status(Status.CREATED).entity(post).build();
	}
	
}
