package com.example.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Customer;


@Repository
public interface CustomerRepo extends JpaRepository<Customer, Long> {
	Collection<Customer> findByName(String name);
	List<Customer> findAll();
}
