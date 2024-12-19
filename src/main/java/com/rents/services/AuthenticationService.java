package com.rents.services;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import com.rents.dtos.LoginResponse;
import com.rents.dtos.UserRequest;
import com.rents.entities.User;
import com.rents.exceptions.NotFoundException;
import com.rents.repositories.UserRepository;

import java.util.Optional;

@Service
public class AuthenticationService implements AuthenticationInterface {
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthenticationService(
            UserRepository userRepository,
            AuthenticationManager authenticationManager, JwtService jwtService
    ) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.jwtService = jwtService;
    }

    @Override
    public LoginResponse authenticate(UserRequest userRequest) throws NotFoundException {
        Optional<User> userInDB = userRepository.findByEmail(userRequest.getEmail());
        if (userInDB.isPresent()) {
            User user = userInDB.get();
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            userRequest.getEmail(),
                            userRequest.getPassword()
                    )
            );

            String jwtToken = jwtService.generateToken(user);

            LoginResponse loginResponse = new LoginResponse();
            loginResponse.setToken(jwtToken);
            loginResponse.setExpiresIn(jwtService.getExpirationTime());

            return loginResponse;
        } else {
            throw new NotFoundException("Utilisateur non référencé.");
        }
    }
}