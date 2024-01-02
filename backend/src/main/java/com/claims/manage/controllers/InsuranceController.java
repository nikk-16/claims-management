package com.claims.manage.controllers;

import com.claims.manage.models.Insurances;
import com.claims.manage.services.InsuranceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/insurances")
@RequiredArgsConstructor
public class InsuranceController {
    private final InsuranceService insuranceService;

    @PostMapping("/buy_insurance")
    private ResponseEntity<Insurances> buyInsurance(@RequestBody Insurances insurance){
        return ResponseEntity.ok(insuranceService.buyInsurance(insurance));
    }

    @GetMapping("/getInsurancesByUsername/{username}")
    private ResponseEntity<List<Insurances>> getInsurancesByUsername(@PathVariable String username){
        return ResponseEntity.ok(insuranceService.getByUsername(username));
    }
}
