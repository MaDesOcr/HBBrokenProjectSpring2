package com.rents.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rents.entities.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);
}
