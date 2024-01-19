package com.claims.manage.model;

import com.claims.manage.domain.Users;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JwtResponseTest {

    @Test
    public void testJwtResponse() {
        JwtResponse jwtResponse = new JwtResponse();

        jwtResponse.setToken("TestToken");
        assertEquals("TestToken", jwtResponse.getToken());

        Users testUser = new Users(); // Assuming Users class has a default constructor
        jwtResponse.setUser(testUser);
        assertEquals(testUser, jwtResponse.getUser());
    }
}
