package com.rents.controllers;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.rents.dtos.MessageRequest;
import com.rents.dtos.MessageResponse;
import com.rents.exceptions.NotFoundException;
import com.rents.services.MessageService;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Validated
@RequestMapping("/api")
public class MessageController {
    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @ResponseStatus(value = HttpStatus.CREATED)
    @GetMapping(value = "/messages", consumes = APPLICATION_JSON_VALUE)
    public MessageResponse createUser(@Valid @RequestBody MessageRequest messageRequest) throws NotFoundException {
        return messageService.createMessage(messageRequest);
    }
}
