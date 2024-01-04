package com.claims.manage.repository;

import com.claims.manage.domain.Insurance;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InsuranceRepository extends MongoRepository<Insurance, String> {
    List<Insurance> findInsurancesByUsername(String username);
    boolean findByUsername(String username);
}
