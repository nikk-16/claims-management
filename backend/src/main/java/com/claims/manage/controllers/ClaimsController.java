package com.claims.manage.controllers;

import com.claims.manage.components.ApplyClaims;
import com.claims.manage.models.Claims;
import com.claims.manage.services.ClaimsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.RequestEntity;
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

    @PostMapping("/applyClaim")
    private ResponseEntity<Claims> applyForClaims(@RequestBody ApplyClaims claim) {
        return ResponseEntity.ok(claimsService.apply(claim));
    }
}
