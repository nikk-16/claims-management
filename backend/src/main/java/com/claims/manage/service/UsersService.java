package com.claims.manage.service;

import com.claims.manage.domain.Users;
import com.claims.manage.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsersService {
  private final UsersRepository usersRepository;

  public Users getById(String id){
    return usersRepository.findById(id).get();
  }

  public List<Users> getAllUsers(){
    return usersRepository.findAll();
  }

  public Users addUser(Users user){
    return usersRepository.save(user);
  }

  public String login(String username, String password){
    return usersRepository.findAll().stream()
            .filter(u -> u.getUsername().equals(username))
            .findFirst()
            .map(u -> u.getPassword().equals(password) ? "Login successful! Welcome " + username : "Invalid credentials")
            .orElse("User does not exist");

//      return usersRepository.findUsersByUsername(username)
//              .map(u -> u.getPassword().equals(password) ? "Login successful" : "Invalid credentials")
//              .orElse("User does not exist");
  }
}