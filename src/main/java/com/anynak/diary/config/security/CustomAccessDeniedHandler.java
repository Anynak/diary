package com.anynak.diary.config.security;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {

        //System.out.println("!!!!!!!CustomAccessDeniedHandler");
        //String servletPath = request.getServletPath();
//
        //System.out.println(request.getServletPath());
        //System.out.println(accessDeniedException.getMessage());
        //ResponseEntity responseEntity = new ResponseEntity(HttpStatus.FORBIDDEN);

        response.sendRedirect(request.getContextPath() + "/accessDenied");

    }
}
