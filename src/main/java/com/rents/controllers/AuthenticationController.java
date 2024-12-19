package com.rents.controllers;

import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.rents.dtos.LoginResponse;
import com.rents.dtos.UserRequest;
import com.rents.dtos.UserResponse;
import com.rents.entities.User;
import com.rents.exceptions.AlreadyExistException;
import com.rents.exceptions.NoUserInContextException;
import com.rents.exceptions.NotFoundException;
import com.rents.services.AuthenticationService;
import com.rents.services.UserService;

@RequestMapping("/api")
@Validated
@RestController
public class AuthenticationController {


    private final AuthenticationService authenticationService;
    private final UserService userService;

    public AuthenticationController(AuthenticationService authenticationService, UserService userService) {
        this.authenticationService = authenticationService;
        this.userService = userService;
    }

    @PostMapping("/auth/register")
    public LoginResponse register(@Valid @RequestBody UserRequest userRequest) throws AlreadyExistException, NotFoundException {
        userService.createUser(userRequest);
        return authenticationService.authenticate(userRequest);
    }

    @PostMapping("/auth/login")
    public LoginResponse authenticate(@Valid @RequestBody UserRequest userRequest) throws NotFoundException {
        return authenticationService.authenticate(userRequest);
    }

    @GetMapping("/auth/me")
    public UserResponse authenticatedUser() throws NoUserInContextException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        try {
            User user = (User) authentication.getPrincipal();
            return new UserResponse(user.getId(), user.getName(), user.getEmail(), user.getCreatedAt(), user.getUpdatedAt());
        } catch (Exception e) {
            throw new NoUserInContextException("Aucun utilisateur authentifié.");
        }
    }
}
