package com.rents.mappers;

import org.springframework.stereotype.Component;

import com.rents.dtos.MessageResponse;
import com.rents.entities.Message;

import java.util.function.Function;

// Unuseful for now, because we return all attrs. But could change.
@Component
public class MessageDTOMapper implements Function<Message, MessageResponse> {
    @Override
    public MessageResponse apply(Message message) {
        return new MessageResponse(message.getId(), message.getRental().getId(), message.getUser().getId(), message.getMessage(), message.getCreatedAt(), message.getUpdatedAt());
    }
}
