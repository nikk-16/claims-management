package com.claims.manage.service;

import com.claims.manage.domain.InsurancePolicies;
import com.claims.manage.exception.NotFoundException;
import com.claims.manage.exception.ResourceNotFoundException;
import com.claims.manage.repository.InsurancePoliciesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InsurancePoliciesService {
    private final InsurancePoliciesRepository insurancePoliciesRepository;

    public List<InsurancePolicies> getAllInsurancePolicies() {
        List<InsurancePolicies> policies = insurancePoliciesRepository.findAll();
        if (policies.isEmpty()) {
            throw new ResourceNotFoundException("No insurance policies found");
        }
        return policies;
    }

    public InsurancePolicies getById(String id) throws NotFoundException {
        return insurancePoliciesRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Insurance policy not found with id: " + id));
    }
}
