package com.rents.services;

import org.springframework.stereotype.Service;

import com.rents.dtos.MessageRequest;
import com.rents.dtos.MessageResponse;
import com.rents.entities.Message;
import com.rents.entities.Rental;
import com.rents.entities.User;
import com.rents.exceptions.NotFoundException;
import com.rents.repositories.MessageRepository;
import com.rents.repositories.RentalRepository;
import com.rents.repositories.UserRepository;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class MessageService implements MessageInterface {

    private final MessageRepository messageRepository;
    private final UserRepository userRepository;
    private final RentalRepository rentalRepository;

    public MessageService(MessageRepository messageRepository, UserRepository userRepository, RentalRepository rentalRepository) {
        this.messageRepository = messageRepository;
        this.userRepository = userRepository;
        this.rentalRepository = rentalRepository;
    }

    @Override
    public MessageResponse createMessage(MessageRequest messageRequest) throws NotFoundException {
        Message message = new Message();

        Optional<User> userInDB = userRepository.findById(messageRequest.getUser_id());
        if (userInDB.isPresent()) {
            message.setUser(userInDB.get());
        } else {
            throw new NotFoundException("Utilisateur non référencé.");
        }

        Optional<Rental> rentalInDB = rentalRepository.findById(messageRequest.getRental_id());
        if (rentalInDB.isPresent()) {
            message.setRental(rentalInDB.get());
        } else {
            throw new NotFoundException("Location non référencé.");
        }

        message.setMessage(messageRequest.getMessage());

        message.setCreatedAt(LocalDate.now());
        message.setUpdatedAt(LocalDate.now());

        messageRepository.save(message);
        return new MessageResponse(message.getId(), message.getRental().getId(), message.getUser().getId(), message.getMessage(), message.getCreatedAt(), message.getUpdatedAt());
    }
}
