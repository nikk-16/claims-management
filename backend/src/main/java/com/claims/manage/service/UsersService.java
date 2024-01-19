package com.claims.manage.service;

import com.claims.manage.domain.Users;
import com.claims.manage.exception.AlreadyExistsException;
import com.claims.manage.exception.InvalidCredentialsException;

import com.claims.manage.exception.NotFoundException;
import com.claims.manage.exception.ResourceNotFoundException;
import com.claims.manage.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsersService {

  private final UsersRepository usersRepository;
  private final PasswordEncoder passwordEncoder;



    public Users getById(String id) throws NotFoundException {
      Optional<Users> userOptional = usersRepository.findById(id);
      if(userOptional.isPresent()){
          return userOptional.get();
      }else{
          throw new NotFoundException("User not found");
      }
  }
  public Users getByUsername(String username) throws NotFoundException {
    Optional<Users> optionalUser = usersRepository.findUsersByUsername(username);
    if (optionalUser.isPresent()) {
        return optionalUser.get();
    } else {
        throw new NotFoundException("User not found");
    }
  }

    public List<Users> getAllUsers() {
        List<Users> users = usersRepository.findAll();
        if (users.isEmpty()) {
            throw new ResourceNotFoundException("No users found");
        }
        return users;
    }
  public Users addUser(Users user) throws AlreadyExistsException {
    String pass = user.getPassword();
    String encode = this.passwordEncoder.encode(pass);
    user.setPassword(encode);
    // Check if user already exists
    Optional<Users> existingUser = usersRepository.findById(user.getId());
    if (existingUser.isPresent()) {
      throw new AlreadyExistsException("User already exists");
    }
    try {
      return usersRepository.save(user);
    } catch (DataIntegrityViolationException e) {
      throw new AlreadyExistsException("User already exists");
    }
  }

//  public String login(String username, String password) throws NotFoundException, InvalidCredentialsException {
//    String pass = this.passwordEncoder.encode(password);
//    return usersRepository.findAll().stream()
//            .filter(u -> u.getUsername().equals(username))
//            .findFirst()
//            .map(u -> {
//                if (u.getPassword().equals(pass)) {
//                    return "Login successful! Welcome " + username;
//                } else {
//                    throw new InvalidCredentialsException("Invalid credentials");
//                }
//            })
//            .orElseThrow(() -> new NotFoundException("User does not exist"));
//
//  }

  public int sum(int a, int b){
    return (a+b);
  }
}
