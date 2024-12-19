package com.ahmad.userservice.repositories;

import com.ahmad.userservice.models.Token;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Long> {
    Optional<Token> findByValueAndDeletedEquals(String value, boolean isDeleted );
}
