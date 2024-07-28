package com.capstone.userservice.models;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class User extends BaseModel{

    private String fullName;

    private String email;

    private String phone;

    private String password;

    public void setHashedPassword(String password) {
        this.setPassword(password);
    }
}
