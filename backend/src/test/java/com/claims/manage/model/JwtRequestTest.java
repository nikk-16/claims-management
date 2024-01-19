package com.claims.manage.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JwtRequestTest {

    @Test
    public void testJwtRequest() {
        JwtRequest jwtRequest = new JwtRequest();

        jwtRequest.setUsername("TestUser");
        assertEquals("TestUser", jwtRequest.getUsername());

        jwtRequest.setPassword("TestPassword");
        assertEquals("TestPassword", jwtRequest.getPassword());
    }
}
