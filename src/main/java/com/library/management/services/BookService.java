package com.library.management.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.management.entities.BookCopies;
import com.library.management.entities.Books;
import com.library.management.repository.BookCopiesRepository;
import com.library.management.repository.BookRepository;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    // public Books addBook(Books newBooks) {
    // return bookRepository.save(newBooks);
    // }

    @Autowired
    private BookCopiesRepository bookCopiesRepository;

    public Books addBook(Books book) {
        // Save the book entity
        Books savedBook = bookRepository.save(book);

        // // Generate a unique serial number for the copy
        // String serialNumber = UUID.randomUUID().toString();
        //
        // // Create a new book copy associated with the saved book
        // BookCopies bookCopy = new BookCopies();
        //// bookCopy.setStatus("true");
        //// bookCopy.setSerialNumber(serialNumber);
        //// bookCopy.setBooks(savedBook);
        //
        // // Save the book copy entity
        // bookCopiesRepository.save(bookCopy);
        // test

        return savedBook;
    }

    public String addBookCopy(Long bookId, Long serialNumber) {
        // Find the book by ID
        Optional<Books> optionalBook = bookRepository.findById(bookId);

        if (optionalBook.isPresent()) {
            Books book = optionalBook.get();

            // Check if the serial number already exists
            if (bookCopiesRepository.existsBySerialNumber(serialNumber)) {
                return "Serial number ";
            }

            // Create a new book copy associated with the book
            BookCopies bookCopy = new BookCopies();
            bookCopy.setSerialNumber(serialNumber);
            bookCopy.setBooks(book);
            bookCopy.setStatus("Available");

            // Save the book copy entity
            bookCopiesRepository.save(bookCopy);

            return "Book copy add";
        } else {
            // Handle the case where the book ID is not found
            return "Book with ID ";
        }
    }

}
