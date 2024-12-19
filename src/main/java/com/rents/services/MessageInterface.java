package com.rents.services;

import com.rents.dtos.MessageRequest;
import com.rents.dtos.MessageResponse;
import com.rents.exceptions.AlreadyExistException;
import com.rents.exceptions.NotFoundException;

public interface MessageInterface {
    MessageResponse createMessage(MessageRequest messageRequest) throws AlreadyExistException, NotFoundException;
}
