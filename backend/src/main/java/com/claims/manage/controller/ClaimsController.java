package com.claims.manage.controller;

import com.claims.manage.model.ApplyClaims;
import com.claims.manage.domain.Claims;
import com.claims.manage.service.ClaimsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
@RequestMapping("/claims")
@RequiredArgsConstructor
public class ClaimsController {
    private final ClaimsService claimsService;

    @PostMapping("/apply")
    public ResponseEntity<?> applyForClaims(@RequestBody ApplyClaims claim) {
        try {
            return ResponseEntity.ok(claimsService.apply(claim));
        } catch (IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
