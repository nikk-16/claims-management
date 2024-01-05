package com.claims.manage.controller;

import com.claims.manage.domain.Users;
import com.claims.manage.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@CrossOrigin("*")
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UsersController {
  private final UsersService usersService;

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

  @GetMapping("/login")
  private ResponseEntity<String> login(@RequestParam String username, @RequestParam String password){
    return ok(usersService.login(username, password));
  }

//  @PostMapping("/signup")
//  private ResponseEntity<String> login(@RequestBody Users user){
//    return ResponseEntity.ok(usersService.signup(user));
//  }
}
