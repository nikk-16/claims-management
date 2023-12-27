package com.claims.manage.controllers;

import com.claims.manage.models.Users;
import com.claims.manage.services.UsersService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@CrossOrigin("*")
@RestController
@RequiredArgsConstructor
public class UsersController {
  private final UsersService usersService;

//  public UsersController(UsersService userService) {
//    this.usersService = userService;
//  }
  @GetMapping("/users/{id}")
  private ResponseEntity<Users> getUserById(@PathVariable String id){
    return ResponseEntity.ok(usersService.getById(id));
  }

  @GetMapping("/getAll")
  private ResponseEntity<List<Users>> getAllUsers(){
    return ResponseEntity.ok(usersService.getAllUsers());
  }

  @PostMapping("/signup")
  private ResponseEntity<Users> addUser(@RequestBody Users user){
    return ResponseEntity.ok(usersService.addUser(user));
  }

  @GetMapping("/login")
  private ResponseEntity<String> login(@RequestParam String username, @RequestParam String password){
    return ResponseEntity.ok(usersService.login(username, password));
  }

//  @PostMapping("/signup")
//  private ResponseEntity<String> login(@RequestBody Users user){
//    return ResponseEntity.ok(usersService.signup(user));
//  }
}
