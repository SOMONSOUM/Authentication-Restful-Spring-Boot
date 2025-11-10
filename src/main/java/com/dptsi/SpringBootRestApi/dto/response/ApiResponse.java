package com.dptsi.SpringBootRestApi.dto.response;

import org.springframework.http.HttpStatus;

public class ApiResponse<T> {
    private String message;
    private HttpStatus status;
    private T data;

    public ApiResponse(String message, HttpStatus status, T data) {
        this.message = message;
        this.status = status;
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public HttpStatus getStatusCode() {
        return status;
    }

    public void setStatusCode(HttpStatus status) {
        this.status = status;
    }
}
