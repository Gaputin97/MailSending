package com.business.user.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class UnauthorizedException extends ResponseStatusException {

    public UnauthorizedException(HttpStatus status) {
        super(status);
    }
}
