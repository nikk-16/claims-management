package com.claims.manage.repositories;

import com.claims.manage.models.Insurances;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InsurancesRepository extends MongoRepository<Insurances, String> {
    List<Insurances> findInsurancesByUsername(String username);

    boolean findByUsername(String username);
}
