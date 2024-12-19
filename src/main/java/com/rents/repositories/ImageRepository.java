package com.rents.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rents.entities.Image;

import java.util.Optional;

public interface ImageRepository extends JpaRepository<Image,Long> {
    Optional<Image> findByName(String name);
}
