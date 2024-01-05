package com.claims.manage.repository;

import com.claims.manage.domain.Users;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRepository extends MongoRepository<Users, String> {
  public Optional<Users> findUsersByUsername(String username);
}
