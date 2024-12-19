package com.rents.mappers;

import org.springframework.stereotype.Component;

import com.rents.dtos.UserResponse;
import com.rents.entities.User;

import java.util.function.Function;

@Component
public class UserDTOMapper implements Function<User, UserResponse> {
    @Override
    public UserResponse apply(User user) {
        return new UserResponse(user.getId(), user.getName(), user.getEmail(), user.getCreatedAt(), user.getUpdatedAt());
    }
}
