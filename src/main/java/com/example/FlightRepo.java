package com.example;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface FlightRepo extends JpaRepository<Flight, Long> {
	Collection<Flight> findByName(String name);
}
