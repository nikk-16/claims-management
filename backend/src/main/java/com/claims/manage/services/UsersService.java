package com.claims.manage.services;

import com.claims.manage.models.Users;
import com.claims.manage.repositories.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsersService {
  private final UsersRepository usersRepository;

  public Users getById(String id){
    return usersRepository.findById(id).get();
  }
}
