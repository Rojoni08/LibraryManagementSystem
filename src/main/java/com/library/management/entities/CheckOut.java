package com.library.management.entities;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class CheckOut {
	
	@Id
	private Long checkoutId;
	private LocalDate duedate;
	private LocalDate returnDate;
	private LocalDate checkoutDate;
	private Double fineAmount;
	
	@ManyToOne
	@JoinColumn(name = "userid")
	private User user;
	
	@ManyToOne
	@JoinColumn(name = "serialNumber")
	private BookCopies bookCopies;

	public CheckOut(Long checkoutId, LocalDate duedate, LocalDate returnDate, LocalDate checkoutDate, User user,
			BookCopies bookCopies,Double fineAmount) {
		super();
		this.checkoutId = checkoutId;
		this.duedate = duedate;
		this.returnDate = returnDate;
		this.checkoutDate = checkoutDate;
		this.user = user;
		this.bookCopies = bookCopies;
		this.fineAmount = fineAmount;
	}

	public CheckOut() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getCheckoutId() {
		return checkoutId;
	}

	public void setCheckoutId(Long checkoutId) {
		this.checkoutId = checkoutId;
	}

	public LocalDate getDuedate() {
		return duedate;
	}

	public void setDuedate(LocalDate duedate) {
		this.duedate = duedate;
	}

	public LocalDate getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(LocalDate returnDate) {
		this.returnDate = returnDate;
	}

	public LocalDate getCheckoutDate() {
		return checkoutDate;
	}

	public void setCheckoutDate(LocalDate localDate) {
		this.checkoutDate = localDate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public BookCopies getBookCopies() {
		return bookCopies;
	}

	public void setBookCopies(BookCopies bookCopies) {
		this.bookCopies = bookCopies;
	}

	public Double getFineAmount() {
		return fineAmount;
	}

	public void setFineAmount(Double fineAmount) {
		this.fineAmount = fineAmount;
	}

	
	
	
	


}
