package com.library.management.entities;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Books {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long bookId;
	private String bookTitle;
	private String author;
	
	@OneToMany(mappedBy = "books")
	private List<BookCopies> bookcopies;

	public Books(Long bookId, String bookTitle, String author, List<BookCopies> bookcopies) {
		super();
		this.bookId = bookId;
		this.bookTitle = bookTitle;
		this.author = author;
		this.bookcopies = bookcopies;
	}

	public Books() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getBookId() {
		return bookId;
	}

	public void setBookId(Long bookId) {
		this.bookId = bookId;
	}

	public String getBookTitle() {
		return bookTitle;
	}

	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public List<BookCopies> getBookcopies() {
		return bookcopies;
	}

	public void setBookcopies(List<BookCopies> bookcopies) {
		this.bookcopies = bookcopies;
	}
	

}
