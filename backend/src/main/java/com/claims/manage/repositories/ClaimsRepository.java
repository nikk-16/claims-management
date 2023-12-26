package com.claims.manage.repositories;

import com.claims.manage.models.Claims;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClaimsRepository extends MongoRepository<Claims, String> {
}
