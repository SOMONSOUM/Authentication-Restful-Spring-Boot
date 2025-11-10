package com.dptsi.SpringBootRestApi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT, reason = "Entity already existed")
public class EntityAlreadyExistedException extends RuntimeException {
    public EntityAlreadyExistedException(String message) {
        super(message);
    }
}
