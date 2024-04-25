package com.library.management.services;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.management.entities.BookCopies;
import com.library.management.entities.CheckOut;
import com.library.management.entities.User;
import com.library.management.repository.BookCopiesRepository;
import com.library.management.repository.CheckoutRepository;
import com.library.management.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BookCopiesRepository bookCopiesRepository;

	@Autowired
	private CheckoutRepository checkoutRepository;

	public boolean verifyLogin(String username, String password) {
		User user = userRepository.findByUsername(username);
		if (user != null && user.getPassword().equals(password)) {
			return true;
		}
		return false;
	}

	public User updateUser(User updatedUser) {
		User existingUser = userRepository.findById(updatedUser.getUserid()).orElse(null);
		if (existingUser != null) {
			// Update user information
			existingUser.setUsername(updatedUser.getUsername());
			existingUser.setPassword(updatedUser.getPassword());
			existingUser.setEmail(updatedUser.getEmail());
			existingUser.setUserid(updatedUser.getUserid());

			// Update other user attributes as needed
			return userRepository.save(existingUser);
		}
		return null; // User not found
	}

	public User createUser(User newUser) {
		// Check if the user name is already taken
		if (userRepository.findByUsername(newUser.getUsername()) != null) {
			return null; // User name already exists
		}
		return userRepository.save(newUser); // Save the new user to the database
	}

	@Transactional
	public void borrowBook(Long userId, Long bookId, Long checkoutId) {
		// Find user by user id
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new IllegalArgumentException("User not found"));

		// Find available book copies (status = "Available") by book id
		List<BookCopies> availableCopies = bookCopiesRepository.findAvailableCopiesByBooks_BookIdAndStatus(bookId,
				"Available");
		if (availableCopies.isEmpty()) {
			throw new IllegalStateException("No available copies for this book");
		}

		// Assign the first available copy to the user
		BookCopies selectedCopy = availableCopies.get(0);
		selectedCopy.setStatus("Borrowed");
		bookCopiesRepository.save(selectedCopy);

		// Create a new checkout entry with a null fine amount
		CheckOut checkout = new CheckOut();
		checkout.setCheckoutId(checkoutId);
		checkout.setUser(user);
		checkout.setBookCopies(selectedCopy);
		checkout.setCheckoutDate(LocalDate.now());
		checkout.setDuedate(LocalDate.now().plusDays(14)); // Example: Due date is 14 days from checkout
		checkout.setFineAmount(null); // Initialize fine amount as null
		checkoutRepository.save(checkout);
	}

	@Transactional
	public void returnBook(Long checkoutId) {
		// Find checkout by checkout id
		CheckOut checkout = checkoutRepository.findById(checkoutId)
				.orElseThrow(() -> new IllegalArgumentException("Checkout not found"));

		// Update return date
		checkout.setReturnDate(LocalDate.now());

		// fine part
		LocalDate dueDate = checkout.getDuedate();
		LocalDate returnDate = checkout.getReturnDate();
		if (returnDate.isAfter(dueDate)) {
			int daysOverdue = (int) ChronoUnit.DAYS.between(dueDate, returnDate);
			double fineAmount = daysOverdue * 5; // per day 5rs
			checkout.setFineAmount(fineAmount);
		} else {
			checkout.setFineAmount(0.0); // No fine
		}

		// Update book copy status
		BookCopies bookCopy = checkout.getBookCopies();
		bookCopy.setStatus("Available");
		bookCopiesRepository.save(bookCopy);

		checkoutRepository.save(checkout);
	}
}
