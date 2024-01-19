package com.claims.manage.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public String HandlerResourceNotFoundException(ResourceNotFoundException ex){
        return ex.getMessage();
    }
    @ExceptionHandler(InvalidCredentialsException.class)
    public String HandlerInvalidCredentialsException(InvalidCredentialsException ex){
        return ex.getMessage();
    }
    @ExceptionHandler(AlreadyExistsException.class)
    public String handleAlreadyExistsException(AlreadyExistsException ex) {
        return ex.getMessage();
    }
    @ExceptionHandler(NotFoundException.class)
    public String handleUserNotFoundException(NotFoundException ex) {
        return ex.getMessage();
    }
}
