package com.capstone.userservice.dtos;

import lombok.Data;

@Data
public class SignUpRequestDto {

    private String fullName;

    private String email;

    private String password;


}
