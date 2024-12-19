package com.rents.services;

import com.rents.dtos.LoginResponse;
import com.rents.dtos.UserRequest;
import com.rents.exceptions.NotFoundException;

public interface AuthenticationInterface {
    LoginResponse authenticate(UserRequest userRequest) throws NotFoundException;
}
