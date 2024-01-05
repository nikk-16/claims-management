package com.claims.manage.controller;

import com.claims.manage.model.ApplyClaims;
import com.claims.manage.domain.Claims;
import com.claims.manage.service.ClaimsService;
import lombok.RequiredArgsConstructor;
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
    private ResponseEntity<Claims> applyForClaims(@RequestBody ApplyClaims claim) {
        return ResponseEntity.ok(claimsService.apply(claim));
    }
}
