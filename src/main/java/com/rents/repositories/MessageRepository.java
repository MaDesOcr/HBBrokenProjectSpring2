package com.rents.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rents.entities.Message;



public interface MessageRepository extends JpaRepository<Message, Integer> {
}
