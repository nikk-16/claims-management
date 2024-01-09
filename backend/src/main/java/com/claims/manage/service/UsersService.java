package com.claims.manage.service;

import com.claims.manage.domain.Users;
import com.claims.manage.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsersService {
  private final UsersRepository usersRepository;
  private final PasswordEncoder passwordEncoder;

  public Users getById(String id){
    return usersRepository.findById(id).get();
  }

  public Optional<Users> getByUsername(String username){
    System.out.println(usersRepository.findUsersByUsername(username));
    return usersRepository.findUsersByUsername(username);
  }

  public List<Users> getAllUsers(){
    return usersRepository.findAll();
  }

  public Users addUser(Users user){
    String pass = user.getPassword();
    String encode = this.passwordEncoder.encode(pass);
    user.setPassword(encode);
    return usersRepository.save(user);
  }

  public String login(String username, String password){
    String pass = this.passwordEncoder.encode(password);
    return usersRepository.findAll().stream()
            .filter(u -> u.getUsername().equals(username))
            .findFirst()
            .map(u -> u.getPassword().equals(pass) ? "Login successful! Welcome " + username : "Invalid credentials")
            .orElse("User does not exist");

    //  return usersRepository.findUsersByUsername(username)
    //          .map(u -> u.getPassword().equals(password) ? "Login successful" : "Invalid credentials")
    //          .orElse("User does not exist");
  }
}
