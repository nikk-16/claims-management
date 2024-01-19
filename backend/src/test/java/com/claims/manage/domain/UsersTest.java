package com.claims.manage.domain;

import org.junit.jupiter.api.Test;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UsersTest {

    @Test
    public void testUsers() {
        Users user = new Users();

        user.setId("1");
        assertEquals("1", user.getId());

        user.setUsername("Test User");
        assertEquals("Test User", user.getUsername());

        user.setEmail("test@example.com");
        assertEquals("test@example.com", user.getEmail());

        user.setMobile(1234567890L);
        assertEquals(1234567890L, user.getMobile());

        user.setPassword("testPassword");
        assertEquals("testPassword", user.getPassword());

        Collection<? extends GrantedAuthority> authorities = user.getAuthorities();
        assertTrue(authorities.contains(new SimpleGrantedAuthority("user")));

        assertTrue(user.isAccountNonExpired());
        assertTrue(user.isAccountNonLocked());
        assertTrue(user.isCredentialsNonExpired());
        assertTrue(user.isEnabled());
    }
}
