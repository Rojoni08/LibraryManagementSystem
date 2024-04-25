package com.library.management.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.library.management.entities.BookCopies;
import com.library.management.entities.Books;

public interface BookCopiesRepository extends JpaRepository<BookCopies,Long> {

	boolean existsBySerialNumber(Long serialNumber);

	 List<BookCopies> findAvailableCopiesByBooks_BookIdAndStatus(Long bookId, String status);

	

}
