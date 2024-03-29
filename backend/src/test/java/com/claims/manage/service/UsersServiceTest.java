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
import org.mockito.InjectMocks;
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
//    @InjectMocks
    private UsersService usersService;

    @Mock
    private UsersRepository usersRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Before
    public void setup() {
        usersService = Mockito.mock(UsersService.class);
        usersRepository = Mockito.mock(UsersRepository.class);
        passwordEncoder = Mockito.mock(PasswordEncoder.class);
        this.usersService = new UsersService(this.usersRepository,this.passwordEncoder);
    }


//    @Test
//    public void testGetByIdUserExists() throws NotFoundException {
//        Users mockUser = new Users();
//        mockUser.setId("659d55c67309f0470b070a83");
//        when(usersRepository.findById("659d55c67309f0470b070a83")).thenReturn(Optional.of(mockUser));
////        System.out.println(mockUser);
//////        Users result;
////        System.out.println(usersService.getById("659d55c67309f0470b070a83"));
//        Users result = usersService.getById("659d55c67309f0470b070a83");
////        System.out.println("Hello" + result.getId());
////        System.out.println("659d55c67309f0470b070a83".equals(result.getId()));
//        assertEquals("659d55c67309f0470b070a83", result.getId());
//        verify(usersRepository, times(1)).findById("659d55c67309f0470b070a83");
//    }
//
//    @Test
//    public void testGetByUserIdNotExists() {
//        when(usersRepository.findById("123")).thenReturn(Optional.empty());
//
//        Exception exception = assertThrows(NotFoundException.class, () -> {
//            usersService.getById("123");
//        });
//        System.out.println(exception);
//        String expectedMessage = "User not found";
//        String actualMessage = exception.getMessage();
//        System.out.println(actualMessage.contains(expectedMessage));
//        assertTrue(actualMessage.contains(expectedMessage));
//        verify(usersRepository, times(1)).findById(anyString());
//    }


    @Test
    public void getById_UserExists_ReturnsUser() throws NotFoundException {
        Users expectedUser = new Users();
        expectedUser.setId("659d55c67309f0470b070a83");
        when(usersRepository.findById("659d55c67309f0470b070a83")).thenReturn(Optional.of(expectedUser));

        Users actualUser = usersService.getById("659d55c67309f0470b070a83");
        assertEquals(expectedUser, actualUser);
    }

    @Test
    public void getById_UserDoesNotExist_ThrowsNotFoundException() throws NotFoundException{
        when(usersRepository.findById("659d55c67309f0470b070a82")).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> {
            usersService.getById("659d55c67309f0470b070a82");
        });
    }

    @Test
    public void getByUsername_UserExists_ReturnsUser() throws NotFoundException {
        Users expectedUser = new Users();
        expectedUser.setUsername("nikhil");
        when(usersRepository.findUsersByUsername("nikhil")).thenReturn(Optional.of(expectedUser));

        Users actualUser = usersService.getByUsername("nikhil");

        assertEquals(expectedUser, actualUser);

    }

    @Test
    public void getByUsername_UserDoesNotExist_ThrowsNotFoundException() {
        when(usersRepository.findUsersByUsername("hello")).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> usersService.getByUsername("hello"));
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
