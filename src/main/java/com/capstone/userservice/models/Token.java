package com.capstone.userservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class Token extends BaseModel {

    private String value;
    @ManyToOne
    private User user;
    private LocalDate expiryDate;

}
