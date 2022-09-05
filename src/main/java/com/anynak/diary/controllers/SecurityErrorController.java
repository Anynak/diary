package com.anynak.diary.controllers;

import com.anynak.diary.exceptions.WrongAuthDataException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SecurityErrorController {

    @GetMapping("/accessDenied")
    public void accessDenied() {
        throw new AccessDeniedException("Access denied");
    }

    @GetMapping("/authenticationError")
    public void authenticationError() {
        throw new WrongAuthDataException("Wrong authentication data");
    }
}
