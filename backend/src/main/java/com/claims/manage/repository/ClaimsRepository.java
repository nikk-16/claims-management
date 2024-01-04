package com.claims.manage.repository;

import com.claims.manage.domain.Claims;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClaimsRepository extends MongoRepository<Claims, String> {
}
