package com.dptsi.SpringBootRestApi.controller;

import com.dptsi.SpringBootRestApi.dto.request.LoginRequest;
import com.dptsi.SpringBootRestApi.dto.request.LogoutRequest;
import com.dptsi.SpringBootRestApi.dto.request.RefreshTokenRequest;
import com.dptsi.SpringBootRestApi.dto.request.RegisterUserRequest;
import com.dptsi.SpringBootRestApi.dto.response.ApiResponse;
import com.dptsi.SpringBootRestApi.dto.response.LoginResponse;
import com.dptsi.SpringBootRestApi.dto.response.RefreshTokenResponse;
import com.dptsi.SpringBootRestApi.model.User;
import com.dptsi.SpringBootRestApi.service.AuthService;
import com.dptsi.SpringBootRestApi.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@Tag(name = "Authentication")
public class AuthController {
    private final AuthService authService;
    private final UserService userService;

    public AuthController(AuthService authService, UserService userService) {
        this.authService = authService;
        this.userService = userService;
    }

    @Operation(summary = "Login")
    @PostMapping("/login")
    public ResponseEntity<ApiResponse<LoginResponse>> login(@Valid @RequestBody LoginRequest request) {
        LoginResponse response = authService.login(request);

        ApiResponse<LoginResponse> apiResponse = new ApiResponse<>(
                "Login successfully",
                HttpStatus.OK,
                response
        );

        return new ResponseEntity<>(apiResponse, HttpStatus.OK);

    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<User>> register(@RequestBody RegisterUserRequest request) {
        User user = userService.createUser(request);

        ApiResponse<User> response = new ApiResponse<>(
                "User registered successfully",
                HttpStatus.OK,
                user
        );
        return  new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<ApiResponse<RefreshTokenResponse>> refreshToken(@Valid @RequestBody RefreshTokenRequest request) {
        RefreshTokenResponse refreshTokenResponse = authService.refreshToken(request);

        ApiResponse<RefreshTokenResponse> response = new ApiResponse<>(
                "Refresh token successfully",
                HttpStatus.OK,
                refreshTokenResponse
        );
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/me")
    public ResponseEntity<ApiResponse<User>> me(HttpServletRequest request) {
        User user = authService.me(request);

        ApiResponse<User> response = new ApiResponse<>(
                "Get me successfully",
                HttpStatus.OK,
                user
        );
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/logout")
    public void logout(@Valid @RequestBody LogoutRequest request) {
        authService.logout(request);
    }
}
