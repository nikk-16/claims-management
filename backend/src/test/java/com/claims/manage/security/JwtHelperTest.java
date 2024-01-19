package com.claims.manage.security;

import org.junit.jupiter.api.Test;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class JwtHelperTest {

    private JwtHelper jwtHelper = new JwtHelper();

    @Test
    public void testGeneratedTokenAndValidate() {
        // Arrange
        String username = "testUser";
        UserDetails userDetails = User.withDefaultPasswordEncoder()
                .username(username)
                .password("password")
                .roles("USER")
                .build();

        // Act
        String token = jwtHelper.generatedToken(userDetails);

        // Assert
        assertTrue(jwtHelper.validateToken(token, userDetails));
        assertEquals(username, jwtHelper.getUsername(token));
        assertFalse(jwtHelper.isTokenExpired(token));
        assertTrue(jwtHelper.getExpirationDateFromToken(token).after(new Date()));
    }
}
