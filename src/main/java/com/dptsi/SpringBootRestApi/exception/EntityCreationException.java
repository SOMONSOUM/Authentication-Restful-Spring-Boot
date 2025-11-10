package com.dptsi.SpringBootRestApi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Entity creation failed")
public class EntityCreationException extends RuntimeException{
    public EntityCreationException(String message) {
        super(message);
    }
}