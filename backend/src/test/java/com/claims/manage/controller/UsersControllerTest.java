package com.claims.manage.controller;

import com.claims.manage.domain.Users;
import com.claims.manage.exception.AlreadyExistsException;
import com.claims.manage.exception.NotFoundException;
import com.claims.manage.exception.ResourceNotFoundException;
import com.claims.manage.model.JwtResponse;
import com.claims.manage.security.JwtHelper;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.web.servlet.MockMvc;

import java.util.*;
import java.util.ArrayList;

import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.claims.manage.model.JwtRequest;
import com.claims.manage.service.UsersService;
import org.springframework.security.core.userdetails.UserDetails;





import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class UsersControllerTest {
    @Mock
    private AuthenticationManager authenticationManager;


    @Mock
    private UserDetailsService userDetailsService;

    @Mock
    private JwtHelper jwtHelper;

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private UsersService usersService;

    @InjectMocks
    private UsersController usersController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetUserById() throws NotFoundException {
        String id = "testId";
        Users user = new Users(); // Set up your user object as needed
        when(usersService.getById(id)).thenReturn(user);

        ResponseEntity<?> response = usersController.getUserById(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(user, response.getBody());
    }

    @Test
    public void testGetAllUsers() {
        Users user1 = new Users(); // Set up your user objects as needed
        Users user2 = new Users();
        List<Users> users = Arrays.asList(user1, user2);
        when(usersService.getAllUsers()).thenReturn(users);

        ResponseEntity<?> response = usersController.getAllUsers();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(users, response.getBody());
    }

    @Test
    public void testGetUserByIdWithNotFoundException() throws NotFoundException {
        String id = "testId";
        when(usersService.getById(id)).thenThrow(new NotFoundException("User not found"));

        ResponseEntity<?> response = usersController.getUserById(id);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("User not found", response.getBody());
    }

    @Test
    public void testGetAllUsersWithResourceNotFoundException() {
        when(usersService.getAllUsers()).thenThrow(new ResourceNotFoundException("User not found"));

        ResponseEntity<?> response = usersController.getAllUsers();

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("User not found", response.getBody());
    }

    @Test
    public void testAddUser() throws AlreadyExistsException {
        Users user = new Users(); // Set up your user object as needed
        Users savedUser = new Users(); // Set up your saved user object as needed
        when(usersService.addUser(user)).thenReturn(savedUser);

        ResponseEntity<?> response = usersController.addUser(user);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(savedUser, response.getBody());
    }

    @Test
    public void testAddUserWithAlreadyExistsException() throws AlreadyExistsException {
        Users user = new Users(); // Set up your user object as needed
        when(usersService.addUser(user)).thenThrow(new AlreadyExistsException("User already exists"));

        ResponseEntity<?> response = usersController.addUser(user);

        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
        assertEquals("User already exists", response.getBody());
    }

    @Test
    public void testGetByUsername() throws NotFoundException {
        String username = "testUsername";
        Users user = new Users(); // Set up your user object as needed
        when(usersService.getByUsername(username)).thenReturn(user);

        ResponseEntity<?> response = usersController.getByUsername(username);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(user, response.getBody());
    }

    @Test
    public void testGetByUsernameWithNotFoundException() throws NotFoundException {
        String username = "testUsername";
        when(usersService.getByUsername(username)).thenThrow(new NotFoundException("User not Found"));

        ResponseEntity<?> response = usersController.getByUsername(username);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("User not Found", response.getBody());
    }

    @Test
    public void testGetByUsernameWithException() throws NotFoundException {
        String username = "testUsername";
        when(usersService.getByUsername(username)).thenThrow(new RuntimeException("Unexpected error"));

        ResponseEntity<?> response = usersController.getByUsername(username);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Maybe unauthorised", response.getBody());
    }

    @Test
    public void testLogin() {
        JwtRequest request = new JwtRequest(); // Set up your request object as needed
//        request.setUsername("nikk");
//        request.setPassword("1234");
        UserDetails userDetails = mock(UserDetails.class);
        Users user = new Users();
        String token = "nikhil";
        when(userDetailsService.loadUserByUsername(request.getUsername())).thenReturn(userDetails);
        when(jwtHelper.generatedToken(userDetails)).thenReturn(token);
        when(modelMapper.map(userDetails, Users.class)).thenReturn(user);

        ResponseEntity<?> response = usersController.login(request);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        JwtResponse jwtResponse = (JwtResponse) response.getBody();
        assertEquals(token, jwtResponse.getToken());
        assertEquals(user, jwtResponse.getUser());
    }

    @Test
    public void testLoginWithUsernameNotFoundException() {
        JwtRequest request = new JwtRequest(); // Set up your request object as needed
        when(userDetailsService.loadUserByUsername(request.getUsername())).thenThrow(new UsernameNotFoundException("User not Found"));

        ResponseEntity<?> response = usersController.login(request);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("User not Found", response.getBody());
    }

    @Test
    public void testLoginWithBadCredentialsException() {
        JwtRequest request = new JwtRequest(); // Set up your request object as needed
        when(userDetailsService.loadUserByUsername(request.getUsername())).thenThrow(new BadCredentialsException("Invalid Credentials"));

        ResponseEntity<?> response = usersController.login(request);

        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
        assertEquals("Invalid Credentials", response.getBody());
    }

    @Test
    public void testLoginWithException() {
        JwtRequest request = new JwtRequest(); // Set up your request object as needed
        when(userDetailsService.loadUserByUsername(request.getUsername())).thenThrow(new RuntimeException("Unexpected error"));

        ResponseEntity<?> response = usersController.login(request);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Bad Credentials", response.getBody());
    }

    @Test
    public void testAuthenticateUser() {
        String username = "testUsername";
        String password = "testPassword";
        when(authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password))).thenReturn(null);

        usersController.authenticateUser(username, password);

        verify(authenticationManager, times(1)).authenticate(new UsernamePasswordAuthenticationToken(username, password));
    }

    @Test
    public void testAuthenticateUserWithBadCredentialsException() {
        String username = "testUsername";
        String password = "testPassword";
        when(authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password))).thenThrow(new BadCredentialsException("Invalid username or password"));

        assertThrows(ResourceNotFoundException.class, () -> {
            usersController.authenticateUser(username, password);
        });
    }

    @Test
    public void testAuthenticateUserWithDisabledException() {
        String username = "testUsername";
        String password = "testPassword";
        when(authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password))).thenThrow(new DisabledException("User is not active"));

        assertThrows(ResourceNotFoundException.class, () -> {
            usersController.authenticateUser(username, password);
        });
    }

}
