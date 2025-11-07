package com.dptsi.SpringBootRestApi.service.impl;

import com.dptsi.SpringBootRestApi.dto.request.LoginRequest;
import com.dptsi.SpringBootRestApi.dto.request.LogoutRequest;
import com.dptsi.SpringBootRestApi.dto.request.RefreshTokenRequest;
import com.dptsi.SpringBootRestApi.dto.response.LoginResponse;
import com.dptsi.SpringBootRestApi.dto.response.RefreshTokenResponse;
import com.dptsi.SpringBootRestApi.model.Token;
import com.dptsi.SpringBootRestApi.model.User;
import com.dptsi.SpringBootRestApi.repository.TokenRepository;
import com.dptsi.SpringBootRestApi.repository.UserRepository;
import com.dptsi.SpringBootRestApi.security.JwtService;
import com.dptsi.SpringBootRestApi.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {
    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenRepository tokenRepository;

    public AuthServiceImpl(JwtService jwtService,
                           UserRepository userRepository,
                           PasswordEncoder passwordEncoder,
                           TokenRepository tokenRepository) {
        this.jwtService = jwtService;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenRepository = tokenRepository;
    }

    @Override
    public LoginResponse login(LoginRequest request) {
        Optional<User> user = userRepository.findByEmail(request.getEmail());
        if (user.isEmpty()) {
            throw new RuntimeException("Invalid email or password");
        }

        if (!passwordEncoder.matches(request.getPassword(), user.get().getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        String accessToken = jwtService.generateAccessToken(user.get().getId());
        String refreshToken = jwtService.generateRefreshToken(user.get().getId());

        Date expirationDate = jwtService.extractExpiration(refreshToken);
        LocalDateTime expirationDateTime = expirationDate.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();


        Token token = new Token();
        token.setToken(refreshToken);
        token.setExpiresAt(expirationDateTime);
        token.setUser(user.get());

        tokenRepository.save(token);

        LoginResponse response = new LoginResponse();
        response.setAccessToken(accessToken);
        response.setRefreshToken(refreshToken);

        return response;
    }

    @Override
    public RefreshTokenResponse refreshToken(RefreshTokenRequest request) {
        Optional<Token> token = tokenRepository.findByToken(request.getRefreshToken());
        if (token.isEmpty()) {
            throw new RuntimeException("Invalid refresh token");
        }

        if (jwtService.isTokenExpired(token.get().getToken())) {
            throw new RuntimeException("Refresh token expired");
        }

        String accessToken = jwtService.generateAccessToken(token.get().getUser().getId());
        String refreshToken = jwtService.generateRefreshToken(token.get().getUser().getId());

        Date expirationDate = jwtService.extractExpiration(refreshToken);
        LocalDateTime expirationDateTime = expirationDate.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();

        token.get().setToken(refreshToken);
        token.get().setExpiresAt(expirationDateTime);

        tokenRepository.save(token.get());

        RefreshTokenResponse response = new RefreshTokenResponse();
        response.setAccessToken(accessToken);
        response.setRefreshToken(refreshToken);

        return response;
    }
    
    @Override
    public User me(HttpServletRequest request) {
        String token = request.getHeader("Authorization").substring(7);
        Long userId = jwtService.extractSubject(token);
        return userRepository.findById(userId).orElseThrow(() -> new RuntimeException("Unauthorized"));
    }

    @Override
    public void logout(@RequestBody LogoutRequest request) {
        Optional<Token> token = tokenRepository.findByToken(request.getRefreshToken());

        if (token.isEmpty()) {
            throw new RuntimeException("Invalid refresh token");
        }

        tokenRepository.delete(token.get());
    }
}
