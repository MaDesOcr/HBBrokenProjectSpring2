package com.rents.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rents.entities.Rental;

import java.util.Optional;

public interface RentalRepository extends JpaRepository<Rental, Integer> {
    Optional<Rental> findByName(String name);
}
