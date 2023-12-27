package com.claims.manage.services;

import com.claims.manage.models.Users;
import com.claims.manage.repositories.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
            .map(u -> u.getPassword().equals(password) ? "Login successful" : "Invalid credentials")
            .orElse("User does not exist");

//      return usersRepository.findUsersByUsername(username)
//              .map(u -> u.getPassword().equals(password) ? "Login successful" : "Invalid credentials")
//              .orElse("User does not exist");
  }
}
