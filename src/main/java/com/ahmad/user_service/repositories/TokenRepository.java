package com.ahmad.user_service.repositories;

import com.ahmad.user_service.models.Token;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Long> {
    Optional<Token> findByValueAndDeletedEquals(String value, boolean isDeleted );
    Optional<Token> findByValue(String value);
}
