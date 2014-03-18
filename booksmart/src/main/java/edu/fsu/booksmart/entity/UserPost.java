package edu.fsu.booksmart.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "user_post")
public class UserPost {
	
	@Id
	@GenericGenerator(name = "increment", strategy = "increment")
	@GeneratedValue(generator = "increment")
	private Long id;

	@Column(nullable = false)
	private String longitude;
	
	@Column(nullable = false)
	private String latitude;
	
	@Column(nullable = false)
	private Double price;
	
	@OneToOne
	private User poster;
	
	@OneToOne
	private Book book;
	
	@OneToOne
	private BookInformation bookInformation;
	
	@OneToOne
	private Professor professor;
	
	@OneToOne
	private FSUClass fsuClass;
	
	private Date posted;
	
	private Date expires;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public User getPoster() {
		return poster;
	}

	public void setPoster(User poster) {
		this.poster = poster;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public BookInformation getBookInformation() {
		return bookInformation;
	}

	public void setBookInformation(BookInformation bookInformation) {
		this.bookInformation = bookInformation;
	}

	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

	public FSUClass getFsuClass() {
		return fsuClass;
	}

	public void setFsuClass(FSUClass fsuClass) {
		this.fsuClass = fsuClass;
	}

	public Date getPosted() {
		return posted;
	}

	public void setPosted(Date posted) {
		this.posted = posted;
	}

	public Date getExpires() {
		return expires;
	}

	public void setExpires(Date expires) {
		this.expires = expires;
	}

}
