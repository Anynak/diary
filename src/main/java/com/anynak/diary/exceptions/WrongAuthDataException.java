package com.anynak.diary.exceptions;

import org.springframework.security.core.AuthenticationException;

public class WrongAuthDataException extends AuthenticationException {
    public WrongAuthDataException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public WrongAuthDataException(String msg) {
        super(msg);
    }
}
