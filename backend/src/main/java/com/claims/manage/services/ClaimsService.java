package com.claims.manage.services;

import com.claims.manage.repositories.ClaimsRepository;
import com.claims.manage.repositories.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClaimsService {
  private final ClaimsRepository claimsRepository;
  private final UsersRepository usersRepository;

}
