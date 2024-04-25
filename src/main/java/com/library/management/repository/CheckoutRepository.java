package com.library.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.library.management.entities.CheckOut;

public interface CheckoutRepository extends JpaRepository<CheckOut,Long>{

}
