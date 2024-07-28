package com.capstone.userservice.services;

import com.capstone.userservice.dtos.UserDTO;
import com.capstone.userservice.models.Token;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    public UserDTO signUp(String fullName, String email, String password );

    public Token login(String email, String password );

    void logout();

    UserDTO validateUser(String token);
}
