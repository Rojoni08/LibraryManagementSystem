package com.library.management.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.library.management.entities.Books;
import com.library.management.services.BookService;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping("/add")
    public Books addBook(@RequestBody Books Book) {
        return bookService.addBook(Book);
    }

    @PostMapping("/{bookId}/copies/{serialNumber}")
    public ResponseEntity<String> addBookCopy(@PathVariable Long bookId, @PathVariable Long serialNumber) {
        try {
            String message = bookService.addBookCopy(bookId, serialNumber);
            return new ResponseEntity<>(message, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
