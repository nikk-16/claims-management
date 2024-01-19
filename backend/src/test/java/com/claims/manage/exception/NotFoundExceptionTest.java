package com.claims.manage.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class NotFoundExceptionTest {

    @Test
    public void testNotFoundException() {
        NotFoundException ex1 = new NotFoundException();
        assertTrue(ex1.getMessage() == null);

        NotFoundException ex2 = new NotFoundException("Not found");
        assertEquals("Not found", ex2.getMessage());
    }
}
