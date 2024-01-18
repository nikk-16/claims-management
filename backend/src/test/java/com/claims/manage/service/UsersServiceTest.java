package com.claims.manage.service;

import com.claims.manage.domain.Users;
import com.claims.manage.exception.AlreadyExistsException;
import com.claims.manage.exception.NotFoundException;
import com.claims.manage.exception.ResourceNotFoundException;
import com.claims.manage.repository.UsersRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.*;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class UsersServiceTest {
    @Autowired
    private UsersService usersService;

    @Mock
    private UsersRepository usersRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Before
    public void setup() throws NotFoundException {
        usersService = Mockito.mock(UsersService.class);
        usersRepository = Mockito.mock(UsersRepository.class);
        passwordEncoder = Mockito.mock(PasswordEncoder.class);
        this.usersService = new UsersService(this.usersRepository,this.passwordEncoder);
    }


    @Test
    public void getById_UserExists_ReturnsUser() throws NotFoundException {
        Users expectedUser = new Users();
//        UsersService usersService1 = new UsersService(this.usersRepository,this.passwordEncoder);
        expectedUser.setId("659d55c67309f0470b070a83");
        when(usersRepository.findById("659d55c67309f0470b070a83")).thenReturn(Optional.of(expectedUser));

        Users actualUser = usersService.getById("659d55c67309f0470b070a83");
        assertEquals(expectedUser, actualUser);
    }

    @Test
    public void getById_UserDoesNotExist_ThrowsNotFoundException() throws NotFoundException{
//        when(usersRepository.findById("659d55c67309f0470b070a82")).thenReturn(Optional.empty());
//
//        assertThrows(NotFoundException.class, () -> {
//            usersService.getById("659d55c67309f0470b070a82");
//        });
        String nonExistingUserId = "non-existing-user-id";

        // Simulate that the user is not found
        when(usersRepository.findById(nonExistingUserId)).thenReturn(Optional.empty());

//        assertThrows(NotFoundException.class, () -> {
//            // this should throw the NotFoundException
//
//        });

        boolean exceptionThrown = false;

        try{
            Users actualUser = usersService.getById(nonExistingUserId);
        }
        catch(Exception ex){
            exceptionThrown = true;
        }

        Assert.assertTrue(exceptionThrown);
    }

    @Test
    public void getByUsername_UserExists_ReturnsUser() throws NotFoundException {
        Users expectedUser = new Users();
        expectedUser.setUsername("nikk");
        when(usersRepository.findUsersByUsername("nikk")).thenReturn(Optional.of(expectedUser));

        Users actualUser = usersService.getByUsername("nikk");

        assertEquals(expectedUser, actualUser);
    }

    @Test
    public void getByUsername_UserDoesNotExist_ThrowsNotFoundException() {
        when(usersRepository.findUsersByUsername("nikk")).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> usersService.getByUsername("nikk"));
    }

    @Test
    public void getAllUsers_UsersExist_ReturnsUserList() {
        Users user1 = new Users();
        Users user2 = new Users();
        List<Users> expectedUsers = Arrays.asList(user1, user2);
        when(usersRepository.findAll()).thenReturn(expectedUsers);

        List<Users> actualUsers = usersService.getAllUsers();

        assertEquals(expectedUsers, actualUsers);
    }

    @Test
    public void getAllUsers_NoUsersExist_ThrowsResourceNotFoundException() {
        when(usersRepository.findAll()).thenReturn(Collections.emptyList());

        assertThrows(ResourceNotFoundException.class, () -> usersService.getAllUsers());
    }

    @Test
    public void addUser_UserDoesNotExist_AddsUser() throws AlreadyExistsException {
        Users user = new Users();
        user.setId("nikhil");
        user.setPassword("123");
        when(passwordEncoder.encode("123")).thenReturn("$2a$10$0jXZRZJ62BPfKmP6QcSsbe8eFXyM7MBr6T9npIrrvnjweyS8qX2LW");
        when(usersRepository.findById("nikhil")).thenReturn(Optional.empty());
        when(usersRepository.save(user)).thenReturn(user);

        Users actualUser = usersService.addUser(user);

        assertEquals(user, actualUser);
        assertEquals("$2a$10$0jXZRZJ62BPfKmP6QcSsbe8eFXyM7MBr6T9npIrrvnjweyS8qX2LW", actualUser.getPassword());
    }

    @Test
    public void addUser_UserExists_ThrowsAlreadyExistsException() {
        Users user = new Users();
        user.setId("nikhil");
        when(usersRepository.findById("nikhil")).thenReturn(Optional.of(user));

        assertThrows(AlreadyExistsException.class, () -> usersService.addUser(user));
    }

    @Test
    public void addUser_DataIntegrityViolation_ThrowsAlreadyExistsException() {
        Users user = new Users();
        user.setId("nikhil");
        user.setPassword("123");
        when(passwordEncoder.encode("123")).thenReturn("$2a$10$0jXZRZJ62BPfKmP6QcSsbe8eFXyM7MBr6T9npIrrvnjweyS8qX2LW");
        when(usersRepository.findById("nikhil")).thenReturn(Optional.empty());
        when(usersRepository.save(user)).thenThrow(new DataIntegrityViolationException("User already exists"));

        assertThrows(AlreadyExistsException.class, () -> usersService.addUser(user));
    }



    @Test
    public void sum_Test(){
        int a = 10;
        int b = 12;
        int exp = 22;
        int c = usersService.sum(a,b);
        Assert.assertEquals(exp, c);
    }
}
