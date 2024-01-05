package com.claims.manage.service;

import com.claims.manage.domain.InsurancePolicies;
import com.claims.manage.repository.InsurancePoliciesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InsurancePoliciesService {
    private final InsurancePoliciesRepository insurancePoliciesRepository;

    public List<InsurancePolicies> getAllInsurancePolicies(){
        return insurancePoliciesRepository.findAll();
    }

    public InsurancePolicies getById(String id){
        return insurancePoliciesRepository.findById(id).get();
    }
}
