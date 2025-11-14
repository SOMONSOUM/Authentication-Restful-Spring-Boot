package com.dptsi.SpringBootRestApi.service;

import com.dptsi.SpringBootRestApi.dto.request.RegisterUserRequest;
import com.dptsi.SpringBootRestApi.entity.User;

public interface UserService {
    User createUser(RegisterUserRequest request);

    User getUserById(Long id);
}
