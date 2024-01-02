package com.claims.manage.controllers;

import com.claims.manage.components.NewInsurance;
import com.claims.manage.models.Insurance;
import com.claims.manage.services.InsuranceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.RequestEntity;
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
    private ResponseEntity<Insurance> buyInsurance(@RequestBody Insurance insurance){
        return ResponseEntity.ok(insuranceService.buyInsurance(insurance));
    }

    @GetMapping("/getInsurancesByUsername/{username}")
    private ResponseEntity<List<Insurance>> getInsurancesByUsername(@PathVariable String username){
        return ResponseEntity.ok(insuranceService.getByUsername(username));
    }
}
