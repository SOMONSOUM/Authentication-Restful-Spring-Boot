package com.dptsi.SpringBootRestApi.controller;

import com.dptsi.SpringBootRestApi.dto.request.LoginRequest;
import com.dptsi.SpringBootRestApi.dto.request.LogoutRequest;
import com.dptsi.SpringBootRestApi.dto.request.RefreshTokenRequest;
import com.dptsi.SpringBootRestApi.dto.request.RegisterUserRequest;
import com.dptsi.SpringBootRestApi.dto.response.LoginResponse;
import com.dptsi.SpringBootRestApi.dto.response.RefreshTokenResponse;
import com.dptsi.SpringBootRestApi.model.User;
import com.dptsi.SpringBootRestApi.service.AuthService;
import com.dptsi.SpringBootRestApi.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final AuthService authService;
    private final UserService userService;

    public AuthController(AuthService authService, UserService userService) {
        this.authService = authService;
        this.userService = userService;
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {
        return authService.login(request);
    }

    @PostMapping("/register")
    public User register(@RequestBody RegisterUserRequest request) {
        return userService.createUser(request);
    }

    @PostMapping("/refresh-token")
    public RefreshTokenResponse refreshToken(@RequestBody RefreshTokenRequest request) {
        return authService.refreshToken(request);
    }

    @GetMapping("/me")
    public User me(HttpServletRequest request) {
        return authService.me(request);
    }

    @PostMapping("/logout")
    public void logout(@RequestBody LogoutRequest request) {
        authService.logout(request);
    }
}
