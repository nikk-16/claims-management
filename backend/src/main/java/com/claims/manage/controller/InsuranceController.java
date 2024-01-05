package com.claims.manage.controller;

import com.claims.manage.domain.Insurance;
import com.claims.manage.service.InsuranceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/insurance")
@RequiredArgsConstructor
public class InsuranceController {
    private final InsuranceService insuranceService;

    @PostMapping("/buy")
    private ResponseEntity<Insurance> buyInsurance(@RequestBody Insurance insurance){
        return ResponseEntity.ok(insuranceService.buyInsurance(insurance));
    }

    @GetMapping("/{username}")
    private ResponseEntity<List<Insurance>> getInsurancesByUsername(@PathVariable String username){
        return ResponseEntity.ok(insuranceService.getByUsername(username));
    }
}
