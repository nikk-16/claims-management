package com.claims.manage.repositories;

import com.claims.manage.models.Insurance;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InsuranceRepository extends MongoRepository<Insurance, String> {
    List<Insurance> findInsuranceByUsername(String username);
}
