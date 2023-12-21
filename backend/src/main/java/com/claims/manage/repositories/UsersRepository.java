package com.claims.manage.repositories;

import com.claims.manage.models.Users;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRepository extends MongoRepository<Users, String> {
  public Optional<Users> findUsersByUsername(String username);
}
