package com.library.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.library.management.entities.Books;

public interface BookRepository extends JpaRepository<Books,Long> {

}
