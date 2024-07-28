package com.capstone.userservice.controllers;

import com.capstone.userservice.dtos.LoginRequestDTO;
import com.capstone.userservice.dtos.SignUpRequestDto;
import com.capstone.userservice.dtos.UserDTO;
import com.capstone.userservice.models.Token;
import com.capstone.userservice.models.User;
import com.capstone.userservice.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public UserDTO signUp(@RequestBody SignUpRequestDto request) {
        System.out.println("Entered the controller");
        System.out.println(request.getPassword()+" "+request.getFullName()+" " +request.getEmail());
        return userService.signUp(request.getFullName(), request.getEmail(), request.getPassword());
    }

    @PostMapping("/login")
    public Token login(@RequestBody LoginRequestDTO loginRequestDTO) {
        return userService.login(loginRequestDTO.getEmail(), loginRequestDTO.getPassword());
    }

    @PostMapping("/logout")
    public void logout(User user) {
        userService.logout();
    }

    @PostMapping("/validate/{token}")
    public UserDTO validateUser(@PathVariable("token") String token){
        return userService.validateUser(token);
    }
}
