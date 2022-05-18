package com.example.footballmanager.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN, reason = "You can`t change this field")
public class ForbiddenModificationException extends RuntimeException{
    public ForbiddenModificationException(String message) {
        super(message);
    }
}
