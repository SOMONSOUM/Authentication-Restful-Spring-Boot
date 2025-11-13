package com.dptsi.SpringBootRestApi.controller;

import com.dptsi.SpringBootRestApi.dto.request.RegisterUserRequest;
import com.dptsi.SpringBootRestApi.dto.response.ApiResponse;
import com.dptsi.SpringBootRestApi.model.User;
import com.dptsi.SpringBootRestApi.service.impl.UserServiceImpl;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@Tag(name = "User")
public class UserController {
    private final UserServiceImpl userService;

    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<User>> createUser(@Valid @RequestBody RegisterUserRequest request) {
        User user = userService.createUser(request);

        ApiResponse<User> response = new ApiResponse<>(
                "User created successfully",
                HttpStatus.OK,
                user
        );
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<User>> getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id);

        ApiResponse<User> response = new ApiResponse<>(
                "Get user successfully",
                HttpStatus.OK,
                user
        );
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
