package com.library.management.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.library.management.entities.User;
import com.library.management.services.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {
	
	@Autowired
    private UserService userService;

	@PostMapping("/login")
	public boolean login(@RequestBody Map<String, String> credentials) {
	    String username = credentials.get("username");
	    String password = credentials.get("password");
	    return userService.verifyLogin(username, password);
	}
    @PostMapping("/register")
    public User registerUser(@RequestBody User newUser) {
        return userService.createUser(newUser);
    }

    @PutMapping("/update")
    public User updateUser(@RequestBody User updatedUser) {
        return userService.updateUser(updatedUser);
    }
    
    @PostMapping("/borrow/{userId}/{bookId}/{checkoutId}")
    public ResponseEntity<?> borrowBook(@PathVariable Long userId, @PathVariable Long bookId, @PathVariable Long checkoutId) {
        try {
            userService.borrowBook(userId, bookId,checkoutId);
            return ResponseEntity.ok("Book borrowed successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to borrow book: " + e.getMessage());
        }
    }

    @PostMapping("/return/{checkoutId}")
    public ResponseEntity<?> returnBook(@PathVariable Long checkoutId) {
        try {
            userService.returnBook(checkoutId);
            return ResponseEntity.ok("Book returned successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to return book: " + e.getMessage());
        }
    }

}
