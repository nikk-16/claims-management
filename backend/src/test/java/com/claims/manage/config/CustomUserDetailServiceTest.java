package com.claims.manage.config;

import com.claims.manage.domain.Users;
import com.claims.manage.exception.ResourceNotFoundException;
import com.claims.manage.repository.UsersRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest
public class CustomUserDetailServiceTest {

    @Mock
    private UsersRepository usersRepository;

    @InjectMocks
    private CustomUserDetailService customUserDetailService;

    @Test
    public void testLoadUserByUsername() {
        // Arrange
        String username = "testUser";
        Users testUser = new Users();
        testUser.setUsername(username);
        when(usersRepository.findUsersByUsername(username)).thenReturn(Optional.of(testUser));

        // Act
        UserDetails result = customUserDetailService.loadUserByUsername(username);

        // Assert
        assertEquals(testUser, result);
    }

    @Test
    public void testLoadUserByUsername_NotFound() {
        // Arrange
        String username = "testUser";
        when(usersRepository.findUsersByUsername(username)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(ResourceNotFoundException.class, () -> customUserDetailService.loadUserByUsername(username));
    }
}