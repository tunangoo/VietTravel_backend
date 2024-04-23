package com.example.viettravel_backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ConflictDataException extends ResponseStatusException {

    public ConflictDataException(String message){
        super(HttpStatus.CONFLICT, message);
    }
}
