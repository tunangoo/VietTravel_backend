package com.example.viettravel_backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ParamInvalidException extends ResponseStatusException {

    public ParamInvalidException(String message){
        super(HttpStatus.BAD_REQUEST, message);
    }
}
