package com.rents.services;

import com.rents.dtos.UserRequest;
import com.rents.dtos.UserResponse;
import com.rents.exceptions.AlreadyExistException;
import com.rents.exceptions.NotFoundException;

public interface UserInterface {

    UserResponse getUser(Integer id) throws NotFoundException;

    UserResponse getUserByEmail(String email) throws NotFoundException;

    // Register
    void createUser(UserRequest userRequest) throws AlreadyExistException;
}
