package com.claims.manage.controller;

import com.claims.manage.Security.JwtHelper;
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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;

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
  private ResponseEntity<Users> getUserById(@PathVariable String id){
    return ok(usersService.getById(id));
  }

  @GetMapping("/getAll")
  private ResponseEntity<List<Users>> getAllUsers(){
    return ok(usersService.getAllUsers());
  }

  @PostMapping("/signup")
  private ResponseEntity<Users> addUser(@RequestBody Users user){
    return ok(usersService.addUser(user));
  }

  @PostMapping("/login")
  private ResponseEntity<JwtResponse> login(@RequestBody JwtRequest request){
//    return ok(usersService.login(username, password));
    this.authenticateUser(request.getUsername(), request.getPassword());
    UserDetails userDetails = this.userDetailsService.loadUserByUsername(request.getUsername());
    String token = this.jwtHelper.generatedToken(userDetails);
    JwtResponse response = new JwtResponse();
    response.setToken(token);
    response.setUser(this.modelMapper.map(userDetails, Users.class));
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @GetMapping("/user/{username}")
  private ResponseEntity<Optional<Users>> getByUsername(@PathVariable String username){
    return ResponseEntity.ok(usersService.getByUsername(username));
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
