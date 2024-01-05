package com.claims.manage.repository;

import com.claims.manage.domain.InsurancePolicies;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface InsurancePoliciesRepository extends MongoRepository<InsurancePolicies,String> {

}
