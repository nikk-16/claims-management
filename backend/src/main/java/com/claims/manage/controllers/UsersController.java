package com.claims.manage.controllers;

import com.claims.manage.models.Users;
import com.claims.manage.services.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequiredArgsConstructor
public class UsersController {
  private final UsersService usersService;

//  public UsersController(UsersService userService) {
//    this.usersService = userService;
//  }
  @GetMapping("/users/{id}")
  private ResponseEntity<Users> getUserById(@RequestParam String id){
    return ResponseEntity.ok(usersService.getById(id));
  }
}
