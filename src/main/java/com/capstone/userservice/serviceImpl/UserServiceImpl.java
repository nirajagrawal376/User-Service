package com.capstone.userservice.serviceImpl;

import com.capstone.userservice.dtos.UserDTO;
import com.capstone.userservice.models.Token;
import com.capstone.userservice.models.User;
import com.capstone.userservice.repositories.TokenRepository;
import com.capstone.userservice.repositories.UserRepository;
import com.capstone.userservice.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private BCryptPasswordEncoder bcryptPasswordEncoder;

    @Autowired
    private TokenRepository tokenRepository;

    @Override
    public UserDTO signUp(String fullName, String email, String password) {
        System.out.println("Entered the Service");
// services dont take DTOs, only controllers take DTOs
        User u = new User();
        u.setEmail(email);
        u.setFullName(fullName);
        System.out.println(password);
        u.setHashedPassword(bcryptPasswordEncoder.encode(password));
        User user = repository.save(u);
        return convertUserToUserDTO(user);
    }

    @Override
    public Token login(String email, String password) {
        // the login should return a token
        // the service will return a token and the controller will return a string
        // the password is saved as hashed password
        Optional<User> userOpt = repository.getUsersByEmail(email);
        if (userOpt.isEmpty()) {
            throw new RuntimeException("User not found");
        }
        User user = userOpt.get();
        if (!bcryptPasswordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Wrong password");
        }
        Token token = new Token();
        token.setUser(user);
        token.setValue("meau meau");
        LocalDate today = LocalDate.now();
        LocalDate thirtyDaysLater = today.plusDays(30);
        token.setExpiryDate(thirtyDaysLater);
        // 1. get the user object with the help of the email and decrypt the
        // password and match it.
        return tokenRepository.save(token);
    }

    @Override
    public void logout() {

    }

    @Override
    public UserDTO validateUser(String token) {
        // ideally the input validation should happen in controller
        // check if the token is valid or not
        Optional<Token> tokenOpt = tokenRepository.findByValueAndDeletedEqualsAndExpiryDateIsGreaterThan(token, false, LocalDate.now());
        if (tokenOpt.isEmpty()) {
            return null;
        }
        User user = tokenOpt.get().getUser();
        return convertUserToUserDTO(user);
    }

    public UserDTO convertUserToUserDTO(User user) {
        UserDTO userDTO = new UserDTO();
        if(user==null) return null; // this is a good practice
        userDTO.setEmail(user.getEmail());
        userDTO.setFullName(user.getFullName());
       // userDTO.isEmailVerified(user.getIsEmailVerified());
        return userDTO;
    }
}
