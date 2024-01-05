package com.claims.manage.controller;

import com.claims.manage.domain.InsurancePolicies;
import com.claims.manage.domain.Users;
import com.claims.manage.service.InsurancePoliciesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/insurancepolicies")
@RequiredArgsConstructor
public class InsurancePoliciesController {
    private final InsurancePoliciesService insurancePoliciesService;

    @GetMapping("/getall")
    private ResponseEntity<List<InsurancePolicies>> getAllInsurancePolicies(){
        return ResponseEntity.ok(insurancePoliciesService.getAllInsurancePolicies());
    }

    @GetMapping("/{id}")
    private ResponseEntity<InsurancePolicies> getInsurancePolicyById(@PathVariable String id){
        return ResponseEntity.ok(insurancePoliciesService.getById(id));
    }
}
