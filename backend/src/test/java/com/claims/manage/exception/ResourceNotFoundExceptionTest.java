package com.claims.manage.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ResourceNotFoundExceptionTest {

    @Test
    public void testResourceNotFoundException() {
        ResourceNotFoundException ex1 = new ResourceNotFoundException();
        assertTrue(ex1.getMessage() == null);

        ResourceNotFoundException ex2 = new ResourceNotFoundException("Resource not found");
        assertEquals("Resource not found", ex2.getMessage());
    }
}
