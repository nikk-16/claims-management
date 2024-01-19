package com.claims.manage.controller;

import com.claims.manage.domain.InsurancePolicies;
import com.claims.manage.exception.NotFoundException;
import com.claims.manage.exception.ResourceNotFoundException;
import com.claims.manage.service.InsurancePoliciesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@CrossOrigin("*")
@RestController
@RequestMapping("/insurancepolicies")
@RequiredArgsConstructor
public class InsurancePoliciesController {
    private final InsurancePoliciesService insurancePoliciesService;

//    @GetMapping("/getall")
//    private ResponseEntity<List<InsurancePolicies>> getAllInsurancePolicies(){
//        return ResponseEntity.ok(insurancePoliciesService.getAllInsurancePolicies());
//    }
//
//    @GetMapping("/{id}")
//    private ResponseEntity<InsurancePolicies> getInsurancePolicyById(@PathVariable String id){
//        return ResponseEntity.ok(insurancePoliciesService.getById(id));
//    }
    @GetMapping("/getall")
    public ResponseEntity<?> getAllInsurancePolicies(){
        try {
            List<InsurancePolicies> policies = insurancePoliciesService.getAllInsurancePolicies();
            return ResponseEntity.ok(policies);
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No insurances found");
        }
    }

    @GetMapping("/{id}")
    public  ResponseEntity<?> getInsurancePolicyById(@PathVariable String id){
        try {
            InsurancePolicies policy = insurancePoliciesService.getById(id);
            return ResponseEntity.ok(policy);
        } catch (NotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }
}
