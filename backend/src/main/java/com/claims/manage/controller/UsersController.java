package com.claims.manage.controller;

import com.claims.manage.exception.AlreadyExistsException;
import com.claims.manage.exception.NotFoundException;
import com.claims.manage.security.JwtHelper;
import com.claims.manage.domain.Users;
import com.claims.manage.exception.ResourceNotFoundException;
import com.claims.manage.model.JwtRequest;
import com.claims.manage.model.JwtResponse;
import com.claims.manage.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.ResponseEntity.ok;

@CrossOrigin("*")
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UsersController {
  private final UsersService usersService;
  private final AuthenticationManager authenticationManager;
  private final UserDetailsService userDetailsService;
  private final ModelMapper modelMapper;
  private final JwtHelper jwtHelper;


  //  public UsersController(UsersService userService) {
//    this.usersService = userService;
//  }
  @GetMapping("/{id}")
  public ResponseEntity<?> getUserById(@PathVariable String id) throws  NotFoundException{
    Users user;
    try {
      user = usersService.getById(id);
    } catch (NotFoundException e) {
      return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
//      return new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
    }
    return new ResponseEntity<>(user, HttpStatus.OK);
  }

  @GetMapping("/getAll")
  public ResponseEntity<?> getAllUsers() {
    try {
      List<Users> users = usersService.getAllUsers();
      return ResponseEntity.ok(users);
    } catch (ResourceNotFoundException ex) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
    }
  }

  @PostMapping("/signup")
  public ResponseEntity<?> addUser(@RequestBody Users user) {
    try {
      Users savedUser = usersService.addUser(user);
      return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    } catch (AlreadyExistsException e) {
      return new ResponseEntity<>("User already exists", HttpStatus.CONFLICT);
    }
  }

  @PostMapping("/login")
  public ResponseEntity<?> login(@RequestBody JwtRequest request) {
    try {
      this.authenticateUser(request.getUsername(), request.getPassword());
      UserDetails userDetails = this.userDetailsService.loadUserByUsername(request.getUsername());
      String token = this.jwtHelper.generatedToken(userDetails);
      JwtResponse response = new JwtResponse();
      response.setToken(token);
      response.setUser(this.modelMapper.map(userDetails, Users.class));
      return new ResponseEntity<>(response, HttpStatus.OK);
    } catch (UsernameNotFoundException e) {
      return new ResponseEntity<>("User not Found", HttpStatus.NOT_FOUND);
    } catch (BadCredentialsException e) {
      return new ResponseEntity<>("Invalid Credentials", HttpStatus.UNAUTHORIZED);
    } catch (Exception e) {
      return new ResponseEntity<>("Bad Credentials", HttpStatus.NOT_FOUND);
    }
  }

  @GetMapping("/user/{username}")
  public ResponseEntity<?> getByUsername(@PathVariable String username) {
    try {
      Users user = usersService.getByUsername(username);
      return new ResponseEntity<>(user, HttpStatus.OK);
    } catch (NotFoundException e) {
      return new ResponseEntity<>("User not Found", HttpStatus.NOT_FOUND);
    } catch (Exception e) {
      return new ResponseEntity<>("Maybe unauthorised", HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  private void authenticateUser(String Username, String Password){
    try {
      authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(Username, Password));
    }
    catch(BadCredentialsException e){
      throw new ResourceNotFoundException("Invalid username or password");
    }
    catch (DisabledException e){
      throw new ResourceNotFoundException("User is not active");
    }
  }

//  @PostMapping("/signup")
//  private ResponseEntity<String> login(@RequestBody Users user){
//    return ResponseEntity.ok(usersService.signup(user));
//  }
}
