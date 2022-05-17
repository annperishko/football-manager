package com.example.footballmanager.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Record with provided parameter wasn`t found")
public class RecordNotFoundException extends RuntimeException{

    public RecordNotFoundException(String message) {
        super(message);

    }
}
