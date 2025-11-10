package com.dptsi.SpringBootRestApi.repository;

import com.dptsi.SpringBootRestApi.model.Token;
import com.dptsi.SpringBootRestApi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TokenRepository extends JpaRepository<Token, Long> {
    Optional<Token> findByToken(String token);
    Optional<Token> findByUser(User user);
}
