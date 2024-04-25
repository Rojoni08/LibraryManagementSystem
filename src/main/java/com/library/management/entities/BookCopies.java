package com.library.management.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class BookCopies {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long serialNumber;
	private String status;
	
	@ManyToOne
	@JoinColumn(name = "bookId")
	private Books books;

	public BookCopies(Long serialNumber, String status, Books books) {
		super();
		this.serialNumber = serialNumber;
		this.status = status;
		this.books = books;
	}

	public BookCopies() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(Long serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Books getBooks() {
		return books;
	}

	public void setBooks(Books books) {
		this.books = books;
	}
	

}
