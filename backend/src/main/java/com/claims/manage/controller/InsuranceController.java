package com.claims.manage.controller;

import com.claims.manage.domain.Insurance;
import com.claims.manage.exception.NotFoundException;
import com.claims.manage.service.InsuranceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<?> buyInsurance(@RequestBody Insurance insurance){
        try {
            return ResponseEntity.ok(insuranceService.buyInsurance(insurance));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
    @GetMapping("/user/{username}")
    public ResponseEntity<?> getInsurancesByUsername(@PathVariable String username){
        try {
            return ResponseEntity.ok(insuranceService.getByUsername(username));
        } catch (NotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getInsurancesById(@PathVariable String id){
        try {
            return ResponseEntity.ok(insuranceService.getById(id));
        } catch (NotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }
}
