package com.example.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Flight;


@Repository
public interface FlightRepo extends JpaRepository<Flight, Long> {
	Collection<Flight> findByName(String name);
}
