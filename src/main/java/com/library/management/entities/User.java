package com.library.management.entities;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userid;
	private String username;
	private String email;
	private String password;
	
	@OneToMany(mappedBy = "user")
	private List<CheckOut> checkout;
	
//	@OneToMany(mappedBy = "user")
//	private List<Fine> fine;

	public User(Long userid, String username, String email, String password) {
		super();
		this.userid = userid;
		this.username = username;
		this.email = email;
		this.password = password;
	}

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<CheckOut> getCheckout() {
		return checkout;
	}

	public void setCheckout(List<CheckOut> checkout) {
		this.checkout = checkout;
	}

//	public List<Fine> getFine() {
//		return fine;
//	}
//
//	public void setFine(List<Fine> fine) {
//		this.fine = fine;
//	}
//	
	
	
	

}
