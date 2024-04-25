package com.library.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.library.management.entities.User;

public interface UserRepository extends JpaRepository<User,Long> {
	
	 User findByUsername(String userName);

}
