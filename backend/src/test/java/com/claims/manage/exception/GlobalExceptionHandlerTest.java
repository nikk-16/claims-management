package com.claims.manage.exception;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.request.ServletWebRequest;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GlobalExceptionHandlerTest {

    @Test
    public void testHandlerResourceNotFoundException() {
        GlobalExceptionHandler handler = new GlobalExceptionHandler();
        ResourceNotFoundException ex = new ResourceNotFoundException("Resource not found");
        MockHttpServletRequest request = new MockHttpServletRequest();
        WebRequest webRequest = new ServletWebRequest(request);

        String response = handler.HandlerResourceNotFoundException(ex);
        assertEquals("Resource not found", response);
    }

    @Test
    public void testHandlerInvalidCredentialsException() {
        GlobalExceptionHandler handler = new GlobalExceptionHandler();
        InvalidCredentialsException ex = new InvalidCredentialsException("Invalid credentials");

        String response = handler.HandlerInvalidCredentialsException(ex);
        assertEquals("Invalid credentials", response);
    }

    @Test
    public void testHandleAlreadyExistsException() {
        GlobalExceptionHandler handler = new GlobalExceptionHandler();
        AlreadyExistsException ex = new AlreadyExistsException("Already exists");

        String response = handler.handleAlreadyExistsException(ex);
        assertEquals("Already exists", response);
    }

    @Test
    public void testHandleUserNotFoundException() {
        GlobalExceptionHandler handler = new GlobalExceptionHandler();
        NotFoundException ex = new NotFoundException("Not found");

        String response = handler.handleUserNotFoundException(ex);
        assertEquals("Not found", response);
    }
}
