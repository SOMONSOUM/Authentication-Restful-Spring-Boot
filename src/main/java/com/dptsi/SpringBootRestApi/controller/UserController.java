package com.dptsi.SpringBootRestApi.controller;

import com.dptsi.SpringBootRestApi.dto.request.RegisterUserRequest;
import com.dptsi.SpringBootRestApi.model.User;
import com.dptsi.SpringBootRestApi.service.impl.UserServiceImpl;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserServiceImpl userService;

    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @PostMapping("/create")
    public User createUser(@RequestBody RegisterUserRequest request) {
        return userService.createUser(request);
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }
}
