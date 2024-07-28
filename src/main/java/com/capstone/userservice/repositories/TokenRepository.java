package com.capstone.userservice.repositories;

import com.capstone.userservice.models.Token;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Long> {

    public Optional<Token> findByValueAndDeletedEqualsAndExpiryDateIsGreaterThan(String token, boolean deleted, LocalDate ExpiryDate );
}
