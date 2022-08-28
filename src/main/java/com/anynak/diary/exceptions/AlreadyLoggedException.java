package com.anynak.diary.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class AlreadyLoggedException extends RuntimeException{
    public AlreadyLoggedException(String message) {
        super(message);
    }
}
