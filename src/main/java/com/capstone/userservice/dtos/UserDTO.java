package com.capstone.userservice.dtos;

import com.capstone.userservice.models.Role;
import lombok.Data;

import java.util.List;

@Data
public class UserDTO {

    private String fullName;

    private List<Role> roles;

    private boolean isEmailVerified;

    private String email;


}
