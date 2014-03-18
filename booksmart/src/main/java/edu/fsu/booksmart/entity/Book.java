package edu.fsu.booksmart.entity;

import java.sql.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "book")
public class Book {

	@Id
	@GenericGenerator(name = "increment", strategy = "increment")
	@GeneratedValue(generator = "increment")
	private Long id;
	
	@Column(nullable = false)
	private String title;
	
	@Column(nullable = false)
	private String description;
	
	@Column(nullable = false)
	private String publisher;
	
	@Column(nullable = false)
	private String edition;
	
	@Column(name = "publish_year", nullable = false)
	private Date publishYear;
	
	@Column(name = "copyright_year", nullable = false)
	private Date copyrightYear;
	
	@OneToMany
	private List<ISBN> isbns;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getEdition() {
		return edition;
	}

	public void setEdition(String edition) {
		this.edition = edition;
	}

	public Date getPublishYear() {
		return publishYear;
	}

	public void setPublishYear(Date publishYear) {
		this.publishYear = publishYear;
	}

	public Date getCopyrightYear() {
		return copyrightYear;
	}

	public void setCopyrightYear(Date copyrightYear) {
		this.copyrightYear = copyrightYear;
	}

	public List<ISBN> getIsbns() {
		return isbns;
	}

	public void setIsbns(List<ISBN> isbns) {
		this.isbns = isbns;
	}
	
}
