package com.dptsi.SpringBootRestApi.service;

import com.dptsi.SpringBootRestApi.dto.request.LoginRequest;
import com.dptsi.SpringBootRestApi.dto.request.LogoutRequest;
import com.dptsi.SpringBootRestApi.dto.request.RefreshTokenRequest;
import com.dptsi.SpringBootRestApi.dto.response.LoginResponse;
import com.dptsi.SpringBootRestApi.dto.response.RefreshTokenResponse;
import com.dptsi.SpringBootRestApi.entity.User;
import jakarta.servlet.http.HttpServletRequest;

public interface AuthService {
    LoginResponse login(LoginRequest request);
    RefreshTokenResponse refreshToken(RefreshTokenRequest request);
    User me(HttpServletRequest request);
    void logout(LogoutRequest request);
}
